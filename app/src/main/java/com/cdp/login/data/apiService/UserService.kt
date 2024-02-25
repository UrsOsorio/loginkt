package com.cdp.login.data.apiService

import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.PostModel
import com.cdp.login.view.model.ResponseUser
import com.cdp.login.view.model.User
import retrofit2.http.*

interface UserService {
    //Métodos para consumo de API de prueba
    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): ResponseUser

    @GET("api/users")
    suspend fun getListUser(): DataUser

    @FormUrlEncoded
    @POST("api/login")
    suspend fun postLogin(
        @Field("email") email:String,
        @Field("password") password:String
    ): ResponseUser

    @FormUrlEncoded
    @POST("api/register")
    suspend fun postRegister(
        @Field("email") email:String,
        @Field("password") password:String
    ): ResponseUser
}