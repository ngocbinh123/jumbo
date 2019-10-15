package com.nnbinh.jumbo.obj

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoteUser(
    @SerializedName("user_id") @Expose
    var userId: Int? = null,

    @SerializedName("username") @Expose
    var username: String? = null,

    @SerializedName("full_name") @Expose
    var fullName: String? = null,

    @SerializedName("sex") @Expose
    var sex: String? = null,

    @SerializedName("email") @Expose
    var email: String? = null,

    @SerializedName("address") @Expose
    var address: String? = null,

    @SerializedName("phone") @Expose
    var phone: String? = null,

    @SerializedName("birthday") @Expose
    var birthday: String? = null,

    @SerializedName("abiz_BearerId") @Expose
    var token: String? = null,

    @SerializedName("ErrorMessage") @Expose
    var errorMsg: String? = null

) {
}