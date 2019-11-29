package web.requests

import data.SignInCredentials
import data.Token
import org.json.JSONObject

class ListFilesRequest(private val token: Token) : Request{

    override val requestData: JSONObject
        get(){
            val obj = JSONObject()

            obj.put("requestType", "listFiles")
            obj.put("tokenKey", token.tokenKey)

            return obj
        }
}