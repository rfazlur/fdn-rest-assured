package network

import model.RegionResponseParser
import java.util.HashMap

class Region {
    fun method(methodName: String): String {
        return RegionMethod().setMethod(methodName)
    }

    fun url(methodName: String, caseName: String): Array<String> {
        return RegionURL().setURL(caseName)
    }

    fun responseParser(methodName: String, responseBody: String) {
        return RegionResponseParser().setToProperties(methodName, responseBody)
    }

    fun query(methodName: String): HashMap<String, String>? {
        return RegionQuery().setQueryParam(methodName)
    }
}
