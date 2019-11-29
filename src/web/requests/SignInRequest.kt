package web.requests

import data.SignInCredentials
import org.json.JSONObject

class SignInRequest(private val credentials: SignInCredentials) : Request{

    override val requestData: JSONObject
        get(){
            val obj = JSONObject()
            val account = JSONObject()

            account.put("username", credentials.username)
            account.put("password", credentials.password)

            obj.put("requestType", "signIn")
            obj.put("account", account)

            return obj
        }
}