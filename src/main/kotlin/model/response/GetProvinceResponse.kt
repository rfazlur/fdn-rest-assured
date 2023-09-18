package model.response

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import data.PropertiesDataHandler
import java.lang.reflect.Type

data class GetProvinceResponse(
    @SerializedName("data") var data: List<DataList>? = null
) {
    fun saveResponse(responseBody: String) {
        var response: BaseResponse<GetProvinceResponse>? = null
        val gson = Gson()
        val type: Type = object: TypeToken<BaseResponse<GetProvinceResponse>?>() {}.type
        gson.fromJson<BaseResponse<GetProvinceResponse>?>(responseBody, type)?.let { result ->
            response = result }

        val provinceCode: String? = response?.body?.data?.get(0)?.code

        PropertiesDataHandler().setPropsValue("PROVINCE_ID", provinceCode)
    }
}
