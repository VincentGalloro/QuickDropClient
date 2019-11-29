package web.responses

import org.json.JSONObject

class EndOfChainHandler : ResponseHandler() {

    override fun handleResponse(data: JSONObject) {
        System.err.println("Could not handle request $data")
    }
}