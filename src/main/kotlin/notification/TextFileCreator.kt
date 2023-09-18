package notification

import data.Constant
import java.io.File

class TextFileCreator(
    methodName: String,
    private val apiMethod: String,
    private val apiURL: Array<String>,
    private val token: String?,
    private val queryParam: HashMap<String, String>?
) {

    private val filePth: String = System.getProperty("user.dir") + "/src/main/resources/requestHttp/"
    private val fileName: String = filePth.plus(methodName).plus(".txt")
    private val file= File(fileName)
    private val header: String = contentHeader()

    init {
        file.writeText(header)
    }

    private fun contentHeader(): String {
        val url: String = apiURL[0]
        val endpoint: String = apiURL[1]
        var firstMap = ""

        if (queryParam != null) {
            val queryParam = queryParam.map { it.key + '=' + it.value }
            firstMap = "?"+queryParam[0]
        }

        return "curl --location --request $apiMethod '$url$endpoint$firstMap' \\\n" +
                "--header 'version: "+ Constant.HeaderAPI.VERSION+"'\\\n" +
                "--header 'key: "+ Constant.HeaderAPI.KEY+"'\\\n" +
                "--header 'device: "+ Constant.HeaderAPI.DEVICE+"'\\\n" +
                "--header 'Authorization: $token'\\\n"
    }
}
