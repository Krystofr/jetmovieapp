package app.christopher.jetmovieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.christopher.jetmovieapp.model.Movie
import app.christopher.jetmovieapp.model.getMovies
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation


@Preview(showBackground = true)
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {},
) {

    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        //.height(130.dp)//Commented out to allow flexibility of contents
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {


        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(15.dp)
                .size(100.dp),
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                elevation = 6.dp) {

                Image(painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context)
                        .data(movie.images[1])
                        .size(Size.ORIGINAL)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()),
                    contentDescription = "Movie poster")

                /*Icon(modifier = Modifier.padding(5.dp),
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Movie Image", tint = Color.LightGray)*/
            }

            Column(Modifier.padding(5.dp)) {

                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 14.sp)) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }
                        }, Modifier.padding(7.dp))//lets us change individual strings we want to show.

                        Divider(Modifier.padding(6.dp))
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                        
                    }
                }

                Icon(imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    Modifier
                        .fillMaxWidth()
                        .height(26.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray)
            }
        }


    }

}