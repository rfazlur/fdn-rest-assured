package api

import builder.EndpointBuilder
import data.PropertiesDataHandler
import io.restassured.response.Response
import network.Region

class Runner(
    methodName: String,
    caseName: String
) {
    init {
        val response: Response
        val token: String? = PropertiesDataHandler().loadPropsValue("TOKEN")
        val apiMethod: String = Region().method(methodName)
        val apiURL: Array<String> = Region().url(methodName, caseName)
        val queryParam: HashMap<String, String>? = Region().query(methodName)

        response = EndpointBuilder(apiURL[0], apiURL[1], apiMethod,
            token, queryParam).executeAPI() as Response
        ResponseValidator(methodName, response, apiMethod, apiURL, token, queryParam)
    }
}