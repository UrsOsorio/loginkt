package com.cdp.login.view


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cdp.login.view.model.DataUser
import com.cdp.login.view.model.User
import com.cdp.login.viewmodel.LoginViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import kotlinx.coroutines.runBlocking


@Composable
fun Welcome(navController: NavController, loginViewModel: LoginViewModel, token:String?,activity : Activity) {

    var listData: DataUser= DataUser()
    runBlocking {
        listData= (activity as MainActivity).doListUser()
    }
    val colorButton = Color(0xF214AF78)
    val colorBoxbackground = Color(0x9C524C4C)//0x9C524C4C

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

    }

    ProvideWindowInsets {

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
                        top = 20.dp,
                        end = 40.dp,
                        bottom = 120.dp
                    ),

                verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Welcome: $token",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,

                    )

                LazyColumn(
                    contentPadding = PaddingValues(all=10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    items(listData.data.orEmpty()){
                            item -> ListItemRow(item)
                    }
                }

            }


        }


    }
}

@Composable
fun ListItemRow(item: User){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .background(color=MaterialTheme.colors.secondary)
            .padding(horizontal = 16.dp, vertical = 10.dp)

    ){
        Text(item.first_name)

    }
}