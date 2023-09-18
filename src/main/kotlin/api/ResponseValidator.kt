package api

import io.restassured.response.Response
import network.Region
import notification.TextFileCreator
import org.testng.Assert

class ResponseValidator(
    private var methodName: String,
    response: Response,
    private var apiMethod: String,
    private var apiURL: Array<String>,
    private var token: String?,
    private var queryParam: HashMap<String, String>?
) {
    init {
        val responseBody: String = response.body.prettyPrint()
        val responseAndMetaCode: Array<Int?> = ResponseParser.responseCodeParser(response)
        validateResponseCode(responseAndMetaCode)
        Region().responseParser(methodName, responseBody)
    }

    private fun validateResponseCode(responseCode: Array<Int?>) {
        when(responseCode[0]) {
            201,
            200,
            422,
            204 -> responseCode[1]?.let { validateMetaCode(it) }
            else -> {
                TextFileCreator(methodName, apiMethod, apiURL, token, queryParam)
                responseCode[0]?.let { assertMetaCode(it, 200) }
            }
        }
    }

    private fun validateMetaCode(metaCode: Int) {
        val methodOptions = setOf("existingUserSendOTP", "checkExistingAccount")
        when(metaCode) {
            0 -> assertMetaCode(metaCode, 0)
            200 -> assertMetaCode(metaCode, 200)
            204 -> assertMetaCode(metaCode, 204)
            422 -> assertMetaCode(metaCode, 422)
            401 -> {
                if (methodName in methodOptions) {
                    assertMetaCode(metaCode, 401)
                } else {
                    assertMetaCode(metaCode, 200)
                }
            }

            else -> {
                if (methodName != "createPost" && metaCode == 400) {
                    assertMetaCode(metaCode, 400)
                } else {
                    TextFileCreator(methodName, apiMethod, apiURL, token, queryParam)
                    assertMetaCode(metaCode, 200)
                }
            }
        }
    }

    private fun assertMetaCode(actual: Int, expected: Int) {
        Assert.assertEquals(actual, expected)
    }
}
