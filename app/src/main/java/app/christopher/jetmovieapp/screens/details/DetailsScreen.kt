package app.christopher.jetmovieapp.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailsScreen(navController: NavController, movieData: String?) {

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.White,
        ) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow",
                Modifier.clickable {
                    navController.popBackStack()
                }.padding(6.dp), tint = Color.Black)
                Spacer(Modifier.width(10.dp))
                Text(text = "Movie Details", color = Color.Black
                    , modifier = Modifier.padding(5.dp))

            }
        }
    }) {

    }
    
   /* Surface(Modifier.fillMaxSize()){
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = movieData.toString())

            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Navigate back")
            }
        }
    }*/
}