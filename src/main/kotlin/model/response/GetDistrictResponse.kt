package model.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import data.PropertiesDataHandler
import java.lang.reflect.Type

data class GetDistrictResponse(
    @SerializedName("data") var data: List<DataList>? = null
) {
    fun saveResponse(responseBody: String) {
        var response: BaseResponse<GetDistrictResponse>? = null
        val gson = Gson()
        val type: Type = object: TypeToken<BaseResponse<GetDistrictResponse>?>() {}.type
        gson.fromJson<BaseResponse<GetDistrictResponse>?>(responseBody, type)?.let { result ->
            response = result }

        val districtCode: String? = response?.body?.data?.get(0)?.code

        PropertiesDataHandler().setPropsValue("DISTRICT_ID", districtCode)
    }

}
