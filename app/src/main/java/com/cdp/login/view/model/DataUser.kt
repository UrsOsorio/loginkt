package com.cdp.login.view.model

import com.google.gson.annotations.SerializedName

data class DataUser (

    @SerializedName("page")
    val page: Int=0,

    @SerializedName("data")
    val data: List<User>?=null

)