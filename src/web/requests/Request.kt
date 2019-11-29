package web.requests

import org.json.JSONObject

interface Request {

    val requestData: JSONObject
}