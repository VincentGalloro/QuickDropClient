package web

import io.socket.client.IO
import org.json.JSONObject
import web.requests.Request
import web.responses.*

class Socket {

    private val socket = IO.socket(java.net.URI.create("https://quickdropbeta.herokuapp.com/"))

    var responseHandler: ResponseHandler? = null

    init{
        socket.on("response") { responseHandler?.handleResponse(it[0] as JSONObject) }

        socket.connect()
    }

    fun sendRequest(request: Request){
        //println(request.requestData)
        socket.emit("request", request.requestData)
    }
}