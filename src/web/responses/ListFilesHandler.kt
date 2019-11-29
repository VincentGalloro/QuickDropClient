package web.responses

import org.json.JSONObject

class ListFilesHandler(private val filenameConsumer: (List<String>)->Unit) : ResponseHandler() {

    override fun handleResponse(data: JSONObject) {
        if(data.getJSONObject("originalRequest")["requestType"] == "listFiles" && data["errorCode"]==0){
            filenameConsumer(data.getJSONObject("responseData").getJSONArray("filenames").map { it as String })
            return
        }
        nextHandler?.handleResponse(data)
    }
}