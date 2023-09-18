package model.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import data.PropertiesDataHandler
import java.lang.reflect.Type

data class GetCityResponse(
    @SerializedName("data") var data: List<DataList>? = null
) {
    fun saveResponse(responseBody: String) {
        var response: BaseResponse<GetCityResponse>? = null
        val gson = Gson()
        val type: Type = object: TypeToken<BaseResponse<GetCityResponse>?>() {}.type
        gson.fromJson<BaseResponse<GetCityResponse>?>(responseBody, type)?.let { result ->
            response = result }

        val cityCode: String? = response?.body?.data?.get(0)?.code

        PropertiesDataHandler().setPropsValue("CITY_ID", cityCode)
    }
}
