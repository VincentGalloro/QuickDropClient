package web.responses

import org.json.JSONObject

class SignInFailedHandler(val onFailure: ()->Unit) : ResponseHandler() {

    override fun handleResponse(data: JSONObject) {
        /*if(data.getJSONObject("originalRequest")["requestType"] == "signIn"){
            if(data["errorCode"] == 102){
                onFailure()
                return
            }
        }*/
        nextHandler?.handleResponse(data)
    }
}