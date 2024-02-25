package com.cdp.login.view


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cdp.login.view.theme.LoginTheme
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.cdp.login.R
import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.ResponseUser
import com.cdp.login.view.model.User
import com.cdp.login.view.navigation.AppNavigation
import com.cdp.login.view.navigation.AppScreens
import com.cdp.login.view.theme.Shapes
import com.cdp.login.viewmodel.LoginViewModel
import com.cdp.login.viewmodel.UserViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import java.time.format.TextStyle


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: UserViewModel
    private lateinit var loginViewModel: LoginViewModel
    lateinit var  contex: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contex=this

        setContent {

            LoginTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Inicializa la primera pantalla
                    AppNavigation(LoginViewModel(), this);
                }
            }
        }
    }

    //funciones para consumo de Api
    suspend fun doSentLogin(email: String?, password: String?, navController: NavController) {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val userObserver = Observer<ResponseUser> { dataUser ->
            val token=dataUser.token
            if (token != null && token !="") {
                Log.d("User: ", "doSentLogin Observer token:   ${token}")
                navController.navigate(route = AppScreens.WelcomeScreen.route+
                        "/"+ email)
            }else{
                Log.d("User: ", "doSentLogin Observer token else:   ${token}")

                Toast.makeText(
                    this,
                    "Don't have an account!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        loginViewModel.getLoginLiveData().observe(this, userObserver)

        if (password != null) {
            if (email != null) {
                loginViewModel.setLogin(email,password)

            }else{
                Toast.makeText(
                    this,
                    "Email required!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }else{
            Toast.makeText(
                this,
                "Password required!",
                Toast.LENGTH_SHORT
            ).show()
        }



    }

    suspend fun doSentEmail(email: String?, password: String?, navController: NavController) {

        if (email != null && email!="") {
            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

            val userObserver = Observer<ResponseUser> { dataUser ->
                val listUserget=dataUser.token
                if (listUserget != null && listUserget !="") {
                    Log.d("User: ", "doSentEmail Observer token:   ${listUserget}")
                    navController.navigate(route = AppScreens.PasswordScreen.route+"/"+ email)

                }else{
                    Log.d("User: ", "doSentEmail Observer token else:   ${listUserget}")
                    navController.navigate(route = AppScreens.LoginScreen.route+"/"+ email)

                }
                Log.d("User: ", "doSentEmail Observer ResponseUser")
            }

            loginViewModel.getLoginEmailLiveData().observe(this, userObserver)

            loginViewModel.setLoginEmail(email,"123")
        }else{
            Toast.makeText(
                this,
                "Email required!",
                Toast.LENGTH_SHORT
            ).show()
        }





    }

    suspend fun doListUser(): DataUser{

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        var listUserget: DataUser= DataUser()
        val userObserver = Observer<DataUser> { dataUser ->

            if (dataUser != null) {
                listUserget=dataUser

                /*for (user:User in listUserget)
                {
                    Log.d("User: ", "Page:   ${user.first_name}")
                }*/

            }
        }

        viewModel.getListUserLiveData().observe(this, userObserver)
        return  listUserget
    }

    suspend fun doSentRegister(email: String?, password: String?, navController: NavController) {

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val userObserver = Observer<ResponseUser> { dataUser ->
            val token=dataUser.token
            if (token != null && token !="") {
                Log.d("User: ", "doSentRegister Observer token:   ${token}")
                navController.navigate(route = AppScreens.PasswordScreen.route+
                        "/"+ email)
            }else{
                Log.d("User: ", "doSentRegister Observer token else:   ${token}")

                Toast.makeText(
                    this,
                    "Only defined users succeed registration!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        loginViewModel.getRegisterLiveData().observe(this, userObserver)

        if (password != null) {
            if (email != null) {
                loginViewModel.setRegister(email,password)

            }
        }


    }

}

