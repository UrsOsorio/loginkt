package com.cdp.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cdp.login.domain.UserUseCase
import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.ResponseUser
import com.cdp.login.view.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


class LoginViewModel:ViewModel() {
    private val userUseCase=UserUseCase()
    private val _email= MutableLiveData<String>()
    val email:LiveData<String> = _email

    private val _password= MutableLiveData<String>()
    val password:LiveData<String> = _password

    private val _name= MutableLiveData<String>()
    val name:LiveData<String> = _name

    fun onLoginChanged(email:String,password:String,name:String){
        _email.value=email
        _password.value=password
        _name.value=name
    }

    private val _emailResponse=MutableLiveData<ResponseUser>()

    private fun setLoginEmailData(loginEmail: ResponseUser){
        _emailResponse.value=loginEmail
    }
    suspend fun setLoginEmail(email:String,password:String){
        setLoginEmailData(userUseCase.posLogin(email,password))
    }
    fun getLoginEmailLiveData(): LiveData<ResponseUser>{
        return _emailResponse
    }


    //login
    private val _loginResponse=MutableLiveData<ResponseUser>()

    private fun setLoginData(login: ResponseUser){
        _loginResponse.value=login
    }
    suspend fun setLogin(email:String,password:String){
        setLoginData(userUseCase.posLogin(email,password))
    }
    fun getLoginLiveData(): LiveData<ResponseUser>{
        return _loginResponse
    }

    //register user

    private val _registerResponse=MutableLiveData<ResponseUser>()

    private fun setRegisterData(login: ResponseUser){
        _registerResponse.value=login
    }
    suspend fun setRegister(email:String,password:String){
        setRegisterData(userUseCase.posRegister(email,password))
    }
    fun getRegisterLiveData(): LiveData<ResponseUser>{
        return _registerResponse
    }

    suspend fun onLoginSelected(){
        delay(4000)
    }

}