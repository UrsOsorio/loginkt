package com.cdp.login.data

import android.util.Log
import com.cdp.login.data.apiService.ApiTest
import com.cdp.login.data.apiService.UserService
import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.ResponseUser
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDataGet {
    //Obtener Lista de usuarios luego del login con exito
    suspend fun getListUsers(): DataUser {
        val service: UserService? = ApiTest.getUserApiService()
        var response: DataUser= DataUser()

        service?.let {
            try {
                response = it.getListUser()

            } catch (e: Exception) {
                // Manejo de errores

                Log.d("User response: ", "error"+e.message.toString())
            }
        }

        return response
    }
    //Obtener usuario specifico
    suspend fun getUsers(): ResponseUser {
        val service: UserService? = ApiTest.getUserApiService()
        var response: ResponseUser= ResponseUser()

        service?.let {
            try {
                response = it.getUser(2)

            } catch (e: Exception) {
                // Manejo de errores

                Log.d("User response: ", "error"+e.message.toString())
            }
        }

        return response
    }
    //Realizar la peticion para el login
    suspend fun posLogin(email:String,password:String): ResponseUser {
        val service: UserService? = ApiTest.getUserApiService()
        var response: ResponseUser= ResponseUser()

        service?.let {
            try {
                response = it.postLogin(email, password )
                Log.d("User response: exito", response.toString())
            } catch (e: Exception) {
                // Manejo de errores

                Log.d("User response: ", "error"+e.message.toString())
            }
        }
        return response
    }

    suspend fun posRegister(email:String,password:String): ResponseUser {
        val service: UserService? = ApiTest.getUserApiService()
        var response: ResponseUser= ResponseUser()

        service?.let {
            try {
                response = it.postRegister(email,password)
                Log.d("User response re: exito", response.toString())
            } catch (e: Exception) {
                // Manejo de errores
                Log.d("User response re: ", "error"+e.message.toString())
            }
        }
        return response
    }


}