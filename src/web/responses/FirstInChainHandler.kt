package web.responses

import org.json.JSONObject

class FirstInChainHandler : ResponseHandler() {

    override fun handleResponse(data: JSONObject) {
        nextHandler?.handleResponse(data)
    }
}