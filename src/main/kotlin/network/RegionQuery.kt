package network

import data.PropertiesDataHandler
import java.util.HashMap

class RegionQuery {
    fun setQueryParam(methodName: String): HashMap<String, String>? =
        when(methodName) {
            "getCity" -> {
                val keyword: String = PropertiesDataHandler().loadPropsValue("PROVINCE_ID").toString()
                setQueryByKeyword(keyword)
            }
            "getDistrict" -> {
                val keyword: String = PropertiesDataHandler().loadPropsValue("CITY_ID").toString()
                setQueryByKeyword(keyword)
            }
            "getSubDistrict" -> {
                val keyword: String = PropertiesDataHandler().loadPropsValue("DISTRICT_ID").toString()
                setQueryByKeyword(keyword)
            }
            else -> null
        }

    private fun setQueryByKeyword(keyword: String): HashMap<String, String> {
        val param: HashMap<String, String> = HashMap()

        param["code"] = keyword
        return param
    }
}
