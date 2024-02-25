package com.cdp.login.view.model

import com.google.gson.annotations.SerializedName

data class ResponseUser (
    @SerializedName("token")
    val token: String="",

    @SerializedName("data")
    val data: User?=null

)