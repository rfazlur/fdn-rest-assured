package model.response

import com.google.gson.annotations.SerializedName

/**
 *
 * @project api-automation-beautyreview-kotlin
 * @author fdn-faiz on 27/12/22
 */
data class BaseResponse<T>(
    @SerializedName("body") var body: T
) {

}
