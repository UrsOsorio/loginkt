package com.cdp.login.domain

import com.cdp.login.data.UserDataGet
import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.ResponseUser
import com.cdp.login.view.model.User

class UserUseCase {

    private val userDataGet=UserDataGet();
    private val userGet=UserDataGet();

    //Realizar Login
    suspend fun posLogin(email:String,password:String): ResponseUser {
        return userGet.posLogin(email, password)
    }
    //Realizar Registro
    suspend fun posRegister(email:String,password:String): ResponseUser {
        return userGet.posRegister(email, password)
    }
    //Obtener lista de usuario
    suspend fun getListUser():DataUser{
        return userDataGet.getListUsers()
    }


}