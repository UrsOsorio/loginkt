package com.cdp.login.view.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.*
import com.cdp.login.view.FirstView
import com.cdp.login.view.LoginPassword
import com.cdp.login.view.Signup
import com.cdp.login.view.Welcome

import com.cdp.login.viewmodel.LoginViewModel

@Composable
fun AppNavigation(loginViewModel: LoginViewModel, activity : Activity) {
    //Rutas para cada vista
    val navControllerRemember = rememberNavController()
    NavHost(navController = navControllerRemember, startDestination = AppScreens.FirstScreen.route ){
        //Vista Principal
        composable(route = AppScreens.FirstScreen.route){
            FirstView(navController = navControllerRemember, loginViewModel = loginViewModel,activity)
        }
        //Vista para colocar el password
        composable(route = AppScreens.PasswordScreen.route+"/{email}",
            arguments = listOf(navArgument(name="email"){
                type= NavType.StringType
            })){
            LoginPassword(navController = navControllerRemember,
                loginViewModel = loginViewModel,it.arguments?.getString("email"),
                activity)
        }
        //Vista para el registro
        composable(route = AppScreens.LoginScreen.route+"/{email}",
            arguments = listOf(navArgument(name="email"){
                type= NavType.StringType
            })){
            Signup(navController = navControllerRemember,
                loginViewModel = loginViewModel,it.arguments?.getString("email"),activity)
        }
        //Vista para la bienvenida
        composable(route = AppScreens.WelcomeScreen.route+"/{token}",
            arguments = listOf(navArgument(name="token"){
                type= NavType.StringType
            })){
            Welcome(navController = navControllerRemember,
                loginViewModel = loginViewModel,it.arguments?.getString("token"),activity)
        }

    }

}
