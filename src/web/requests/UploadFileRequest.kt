package web.requests

import data.Token
import org.json.JSONObject
import java.io.File

class UploadFileRequest(private val file: File, private val token: Token) : Request {

    override val requestData: JSONObject
        get(){
            val obj = JSONObject()

            obj.put("requestType", "uploadFile")
            obj.put("tokenKey", token.tokenKey)
            obj.put("filename", file.name)
            obj.put("fileData", String(file.readBytes()))

            return obj
        }
}