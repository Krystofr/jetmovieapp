package app.christopher.jetmovieapp.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.christopher.jetmovieapp.model.Movie
import app.christopher.jetmovieapp.model.getMovies
import app.christopher.jetmovieapp.navigation.MovieScreens
import app.christopher.jetmovieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Blue,
            elevation = 5.dp,
        ) {
            Text(text = "Movies", color = Color.White, modifier = Modifier.padding(5.dp))
        }
    }) {
        MainContent(navController = navController)

    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {

    Column(modifier = Modifier.padding(3.dp)) {
        val context = LocalContext.current
        LazyColumn {
            items(items = movieList) { listItems ->
                MovieRow(movie = listItems){movie_item ->
                    Toast.makeText(context, movie_item, Toast.LENGTH_SHORT).show()
                    navController.navigate(route = MovieScreens.DetailsScreen.name+ "/$movie_item")//Navigate to DetailsScreen, passing the movie string as argument.
                }
            }
        }
    }

}