package com.cdp.login.view

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.cdp.login.R
import com.cdp.login.view.model.ResponseUser
import com.cdp.login.view.navigation.AppScreens
import com.cdp.login.view.theme.Shapes
import com.cdp.login.viewmodel.LoginViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import kotlinx.coroutines.runBlocking


@Composable
fun LoginPassword(navController: NavController, loginViewModel: LoginViewModel, email:String?,activity : Activity) {

    val emailVM: String by loginViewModel.email.observeAsState(initial ="")
    val password: String by loginViewModel.password.observeAsState(initial ="")

    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val colorButton = Color(0xF214AF78)
    val colorBoxbackground = Color(0x9C524C4C)//0x9C524C4C

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val backgroundImage: Painter = painterResource(id = R.drawable.f_login)
        Image(
            painter = backgroundImage,
            contentDescription = null, // Opcional, puede ser nulo si no se necesita
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

    }

    ProvideWindowInsets {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint=Color.White,
            contentDescription = "Arrow back",
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
        Column(
            Modifier
                .navigationBarsWithImePadding()
                .padding(
                    start = 20.dp,
                    top = 290.dp,
                    end = 20.dp,
                    bottom = 130.dp
                ),

            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(color = colorBoxbackground)
                    .clip(RoundedCornerShape(60.dp))
                    .graphicsLayer {
                        // Aplicar desenfoque
                        alpha = 0.1f
                    }
                    .size(width = 800.dp, height = 565.dp)

            ) {

            }
        }

        Column(
            Modifier
                .navigationBarsWithImePadding()
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                Modifier
                    .navigationBarsWithImePadding()
                    .padding(
                        start = 40.dp,
                        top = 290.dp,
                        end = 40.dp,
                        bottom = 150.dp
                    ),

                verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Log in",
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,

                    )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)

                ) {
                    // Texto del nombre
                    Text(
                        text = "Jane Dow",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp),

                        color = Color.White,
                        fontSize = 18.sp
                    )
                    // Texto del correo electrónico
                    if (email != null) {
                        Text(
                            text = email,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 16.dp),
                            color = Color.White, fontSize = 14.sp
                        )
                    }
                }


                TextInput(password, {loginViewModel.onLoginChanged(emailVM,it,"")},InputType.Password, keyboardActions = KeyboardActions(onDone = {
                    context.doLogin()
                }), focusRequester = passwordFocusRequester)


                Button(
                    onClick = {
                        runBlocking {
                            (activity as MainActivity).doSentLogin(emailVM, password, navController)
                        }

                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = colorButton,
                        contentColor = Color.White
                    ),
                ) {
                    Text(
                        "Continue",
                        Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {}) {
                        Text("Forgot your password?", color = colorButton,  fontWeight = FontWeight.Bold)
                    }
                }
            }



        }

    }
}


sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    object Name : InputType(
        label = "Email",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password : InputType(
        label = "Password",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}


@Composable
fun TextInput(password: String,
              onTextFieldChanged:(String)->Unit,
              inputType: InputType,
              focusRequester: FocusRequester? = null,
              keyboardActions: KeyboardActions
) {

    TextField(
        value = password,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .focusOrder(focusRequester ?: FocusRequester()),
        label = { Text(text = inputType.label) },
        shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions
    )
}






private fun Context.doLogin() {

    Toast.makeText(
        this,
        "Try again later!",
        Toast.LENGTH_SHORT
    ).show()
}