package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesample.R
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MySmoothActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Column() {
                        SearchBar()

                        val alignYourBodyData = listOf<BodyData>(
                            BodyData("Maria", R.drawable.profile_picture),
                            BodyData("Maria", R.drawable.profile_picture),
                            BodyData("Maria", R.drawable.profile_picture),
                            BodyData("Maria", R.drawable.profile_picture),
                            BodyData("Maria", R.drawable.profile_picture)
                        )

                        AlignYourBodyRow(alignYourBodyData)
                    }
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(value = "", onValueChange = {}, leadingIcon = {
        Icon(
            imageVector = Icons.Default.Search, contentDescription = null
        )
    }, colors = TextFieldDefaults.textFieldColors(
        backgroundColor = MaterialTheme.colors.surface,
        textColor = colorResource(id = R.color.black)
    ), placeholder = {
        Text(
            "search data", color = colorResource(id = R.color.black)
        )
    }, modifier = modifier
        .fillMaxWidth()
        .heightIn(min = 56.dp)
    )
}

data class BodyData(val name: String, val drawable: Int)

@Composable
fun AlignYourBodyRow(
    alignYourBodyData: List<BodyData>, modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(item)
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(

    alignYourBodyData: List<BodyData>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(alignYourBodyData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.name,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(
    data: BodyData, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(data.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = data.name
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int, text: String, modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small, modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Column() {
                val alignYourBodyData = listOf<BodyData>(
                    BodyData("Maria", R.drawable.profile_picture),
                    BodyData("Maria", R.drawable.profile_picture),
                    BodyData("Maria", R.drawable.profile_picture),
                    BodyData("Maria", R.drawable.profile_picture),
                    BodyData("Maria", R.drawable.profile_picture)
                )

                FavoriteCollectionsGrid(alignYourBodyData)
            }
        }
    }
}