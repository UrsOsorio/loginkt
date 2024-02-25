package com.cdp.login.data.apiService

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    companion object {
        //url de API de prueba
        private const val url: String = "https://reqres.in/";
        private var retrofit: Retrofit? = null

        private fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getUserApiService(): UserService? {
            return getRetrofit().create(UserService::class.java)
        }
    }


}