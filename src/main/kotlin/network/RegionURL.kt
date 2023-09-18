package network

import data.Constant

class RegionURL {
    fun setURL(caseName: String): Array<String> {
        val baseURL: String
        val envTest: String = System.getProperty("environment")
        val endpoint: String = RegionPath().setEndpoint(caseName)

        return if (envTest == "prod") {
            baseURL = setBaseURLProd()
            arrayOf(baseURL, endpoint)
        } else {
            baseURL = setBaseURLStag()
            arrayOf(baseURL, endpoint)
        }
    }

    private fun setBaseURLStag(): String {
        return Constant.BaseURL.REGION_STAG
    }

    private fun setBaseURLProd(): String {
        return Constant.BaseURL.REGION_PROD
    }
}
