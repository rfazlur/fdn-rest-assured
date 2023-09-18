package runner

import api.Runner
import org.testng.annotations.Test

class RegionAPI {
    private lateinit var methodName: String

    @Test
    fun getProvince(){
        methodName = Throwable().stackTrace[0].methodName
        Runner(methodName, "region province")
    }

    @Test
    fun getCity() {
        methodName = Throwable().stackTrace[0].methodName
        Runner(methodName, "region city")
    }

    @Test
    fun getDistrict() {
        methodName = Throwable().stackTrace[0].methodName
        Runner(methodName, "region district")
    }

    @Test
    fun getSubDistrict() {
        methodName = Throwable().stackTrace[0].methodName
        Runner(methodName, "region village" )
    }
}