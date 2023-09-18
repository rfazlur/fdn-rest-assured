package model.response

import com.google.gson.annotations.SerializedName

data class DataList(
    @SerializedName("code") var code: String = ""
)