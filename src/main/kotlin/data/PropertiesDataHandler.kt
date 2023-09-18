package data

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class PropertiesDataHandler {
    private val usernameMachine: String = System.getProperty("user.name")
    private val envTest: String = System.getProperty("environment")
    private val filePathProd = "/Users/$usernameMachine/.jenkins/workspace/.secret"
    private val filePathStag = "/Users/$usernameMachine/.secret"
    private var fileName = ""

    fun loadPropsValue(key: String): String? {
        fileName = if (envTest=="prod") {
            "$filePathProd/live.properties"
        } else {
            "$filePathStag/staging.properties"
        }

        val fis = FileInputStream(fileName)
        val prop = Properties()
        prop.load(fis)
        return prop.getProperty(key)
    }

    fun setPropsValue(key: String, value: String?) {
        fileName = if (envTest=="prod") {
            "$filePathProd/live.properties"
        } else {
            "$filePathStag/staging.properties"
        }
        val fis = FileInputStream(fileName)
        val prop = Properties()
        prop.load(fis)
        prop[key] = value
        val fos = FileOutputStream(fileName)
        prop.store(fos, "Test Data")
    }
}
