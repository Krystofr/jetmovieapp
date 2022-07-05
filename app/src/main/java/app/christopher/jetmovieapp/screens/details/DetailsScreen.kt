package app.christopher.jetmovieapp.screens.details

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.christopher.jetmovieapp.model.Movie
import app.christopher.jetmovieapp.model.getMovies
import app.christopher.jetmovieapp.widgets.MovieRow
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation


@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val context = LocalContext.current
    //Filter through Movie to get a movie with the particular id
    val newMovieList = getMovies().filter {
        it.id == movieId
    }

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.White,
        ) {
            Row(horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back arrow",
                    Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .padding(6.dp), tint = Color.Black)
                Spacer(Modifier.width(10.dp))
                Text(text = "Movie Details", color = Color.Black, modifier = Modifier.padding(5.dp))

            }
        }
    }) {
        Surface(Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                MovieRow(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(25.dp))
                Divider(Modifier.padding(10.dp))
                Text(text = "Movie Images", style = MaterialTheme.typography.h5)
                HorizontalScrollableImageView(newMovieList, context)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(
    newMovieList: List<Movie>,
    context: Context,
) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(Modifier
                .padding(12.dp)
                .size(170.dp),
                elevation = 5.dp) {
                Image(painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(image)
                        .size(Size.ORIGINAL)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()),
                    contentDescription = "Movie Poster")
            }

        }
    }
}