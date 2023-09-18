package api

import io.restassured.path.json.JsonPath
import io.restassured.response.Response

class ResponseParser {
    companion object {
        fun responseCodeParser(response: Response): Array<Int?> {
            val code = setOf(200, 201, 422, 204)
            val responseCode : Int = response.statusCode
            return if (responseCode in code) {
                val jsonPath : JsonPath = response.body.jsonPath()
                val metaCode : Int = jsonPath.get("code")
                arrayOf(responseCode, metaCode)
            } else {
                arrayOf(responseCode, 0)
            }
        }
    }
}
