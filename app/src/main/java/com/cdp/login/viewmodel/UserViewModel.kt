package com.cdp.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cdp.login.domain.UserUseCase
import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.User
import kotlinx.coroutines.runBlocking

class UserViewModel:ViewModel() {

    private val userUseCase=UserUseCase()
    private val listUserData=MutableLiveData<DataUser>()
    init{
        runBlocking {
            getListUser()
        }
    }
    private fun setListUserData(listUser:DataUser){
        listUserData.value=listUser
    }
    suspend fun getListUser(){
        setListUserData(userUseCase.getListUser())
    }
     fun getListUserLiveData(): LiveData<DataUser>{
        return listUserData
    }

}