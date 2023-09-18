package model

import model.response.GetCityResponse
import model.response.GetDistrictResponse
import model.response.GetProvinceResponse

class RegionResponseParser {
    fun setToProperties(methodName: String, responseBody: String) {
        when(methodName) {
            "getProvince" -> GetProvinceResponse().saveResponse(responseBody)
            "getCity" -> GetCityResponse().saveResponse(responseBody)
            "getDistrict" -> GetDistrictResponse().saveResponse(responseBody)
        }
    }
}
