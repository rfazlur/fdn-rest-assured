package builder

import data.Constant
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.response.Response
import io.restassured.response.ResponseOptions
import io.restassured.specification.RequestSpecification

class EndpointBuilder(
    baseURL: String,
    endpoint: String,
    apiMethod: String,
    token: String?,
    queryParam: HashMap<String, String>?
) {

    private val url: String = baseURL.plus(endpoint)
    private val httpMethod: String = apiMethod
    private val builder: RequestSpecBuilder = RequestSpecBuilder()
    private var request: RequestSpecification = RestAssured.given()

    init {
        builder.addHeader("version", Constant.HeaderAPI.VERSION)
        builder.addHeader("key", Constant.HeaderAPI.KEY)
        builder.addHeader("device", Constant.HeaderAPI.DEVICE)
        builder.addHeader("Authorization", token)

        if (queryParam!=null) {
            builder.addHeader("Content-Type", "application/json")
            for ((key, value) in queryParam) {
                request.queryParam(key, value)
            }
        }
    }

    fun executeAPI(): ResponseOptions<Response> {
        val requestSpec: RequestSpecification = builder.build()
        request.spec(requestSpec)
        request.log().all()

        return when(httpMethod) {
            "POST" -> request.post(url)
            "PUT" -> request.put(url)
            "DELETE" -> request.delete(url)
            else -> request.get(url)
        }
    }
}
