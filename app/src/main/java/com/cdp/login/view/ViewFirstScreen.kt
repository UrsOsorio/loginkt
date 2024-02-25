package com.cdp.login.view

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import com.cdp.login.R
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
import kotlinx.coroutines.runBlocking

@Composable
fun FirstView(navController: NavController, loginViewModel: LoginViewModel,activity : Activity) {

    val email: String by loginViewModel.email.observeAsState(initial ="")
    val password: String by loginViewModel.password.observeAsState(initial ="")

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
        // Aquí colocas el contenido que quieres que esté encima de la imagen de fondo
    }
    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val colorButton = Color(0xF214AF78)
    val colorButtonSocial = Color(0xFFE5F9F2)
    val colorBoxbackground = Color(0x9C524C4C)//0x9C524C4C

    ProvideWindowInsets {

        Column(
            Modifier
                .navigationBarsWithImePadding()
                .padding(start= 20.dp,
                    top= 263.dp,
                    end= 20.dp,
                    bottom= 10.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.background(color = colorBoxbackground)
                    .clip(RoundedCornerShape(60.dp))
                    .graphicsLayer {
                        // Aplicar desenfoque
                        alpha=0.1f
                    }
                    .size(width = 800.dp, height = 535.dp)

            ) {

            }
        }

        Column(
            Modifier
                .navigationBarsWithImePadding()
                .padding(40.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Hi!",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,

                )
            TextInputEmail(email, {loginViewModel.onLoginChanged(it,password,"")},
                InputTypeEmail.Name, keyboardActions = KeyboardActions(onNext = {
                    passwordFocusRequester.requestFocus()
                })
            )

            Button(
                onClick = {

                    runBlocking {
                        (activity as MainActivity).doSentEmail(email, password, navController)
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

            Text(
                text = "or",
                color = Color.White,
                fontSize = 20.sp,
                textAlign = TextAlign.Start,

                )

            Button(
                onClick = {
                    runBlocking {
                        (activity as MainActivity).doSentEmail(email, password, navController)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = colorButtonSocial,
                    contentColor = Color.Black
                ),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val backgroundImage: Painter = painterResource(id = R.drawable.facebook)
                    Image(
                        painter = backgroundImage,
                        contentDescription = null, // Opcional, puede ser nulo si no se necesita
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Continue with Facebook",
                        Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Button(
                onClick = {
                    runBlocking {
                        (activity as MainActivity).doSentEmail(email, password, navController)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = colorButtonSocial,
                    contentColor = Color.Black
                ),
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    val backgroundImage: Painter = painterResource(id = R.drawable.google)
                    Image(
                        painter = backgroundImage,
                        contentDescription = null, // Opcional, puede ser nulo si no se necesita
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Continue with Google",
                        Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Button(
                onClick = {
                    runBlocking {
                        (activity as MainActivity).doSentEmail(email, password, navController)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = colorButtonSocial,
                    contentColor = Color.Black
                ),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val backgroundImage: Painter = painterResource(id = R.drawable.apple)
                    Image(
                        painter = backgroundImage,
                        contentDescription = null, // Opcional, puede ser nulo si no se necesita
                        contentScale = ContentScale.FillBounds
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Continue with Apple",
                        Modifier.padding(vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Don't have an account?", color = Color.White)
                TextButton(onClick = {}) {
                    Text("SING UP", color = colorButton,  fontWeight = FontWeight.Bold)
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(onClick = {}) {
                    Text("Forgot your password?", color = colorButton,  fontWeight = FontWeight.Bold)
                }
            }
        }

    }
}

sealed class InputTypeEmail(
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

}

@Composable
fun TextInputEmail(email: String,
              onTextFieldChanged:(String)->Unit,
              inputType: InputType,
              focusRequester: FocusRequester? = null,
              keyboardActions: KeyboardActions
) {

    TextField(
        value = email,
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

