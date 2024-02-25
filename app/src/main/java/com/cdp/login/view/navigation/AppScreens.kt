package com.cdp.login.view.navigation

sealed class AppScreens (val route: String){
    object FirstScreen:AppScreens("firs_screen")
    object PasswordScreen:AppScreens("view_password")
    object LoginScreen:AppScreens("view_login")
    object WelcomeScreen:AppScreens("view_welcome")


}
