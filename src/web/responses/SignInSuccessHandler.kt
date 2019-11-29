package web.responses

import data.SignInCredentials
import data.Token
import org.json.JSONObject

class SignInSuccessHandler(val onSuccess: (SignInCredentials, Token)->Unit) : ResponseHandler() {

    override fun handleResponse(data: JSONObject) {
        if(data.getJSONObject("originalRequest")["requestType"] == "signIn" && data["errorCode"]==0){
            data.getJSONObject("originalRequest").getJSONObject("account").let {
                onSuccess(SignInCredentials(it.getString("username"), it.getString("password")),
                        Token(data.getJSONObject("responseData").getString("tokenKey")))
            }
            return
        }
        nextHandler?.handleResponse(data)
    }
}