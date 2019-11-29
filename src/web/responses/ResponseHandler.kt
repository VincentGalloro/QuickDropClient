package web.responses

import org.json.JSONObject

abstract class ResponseHandler {

    var nextHandler: ResponseHandler? = null

    fun addChain(responseHandler: ResponseHandler): ResponseHandler{
        nextHandler = responseHandler
        return responseHandler
    }

    abstract fun handleResponse(data: JSONObject)
}