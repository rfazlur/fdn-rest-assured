package network

class RegionMethod {
    fun setMethod(methodName: String): String {
        return when(methodName) {
            "getSubDistrict",
            "getDistrict",
            "getCity",
            "getProvince" -> "GET"

            else -> ""
        }
    }
}
