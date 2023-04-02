package com.example.navexample.Memory

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.navexample.Quiz.GameViewModel
import com.example.navexample.R
import com.example.navexample.ui.theme.EmojiMemoryTheme

@Composable
fun onCreate(savedInstanceState: Bundle?, viewModel: game_view = viewModel()) {
    onCreate(savedInstanceState)
    viewModel.loadEmojis()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent(viewModel: game_view = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.Mem))
                },
                actions = {
                    IconButton(onClick = { viewModel.loadEmojis() }) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Reload Game"
                        )
                    }
                }
            )
        }
    ) {
        val cards: List<EmojiModel> by viewModel.getEmojis().observeAsState(listOf())
        CardsGrid(cards = cards)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardsGrid(cards: List<EmojiModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(cards.count()) { cardIndex ->
            CardItem(cards[cardIndex])
        }
    }
}

@Composable
fun CardItem(emoji: EmojiModel, viewModel: game_view = viewModel()) {
    Box(
        modifier = Modifier
            .padding(all = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(150.dp)
                .background(
                    color = Color.Black.copy(alpha = if (emoji.isVisible) 0.4F else 0.0F),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    if (emoji.isVisible) {
                        viewModel.updateShowVisibleCard(emoji.id)
                    }
                }

        ) {
            if (emoji.isSelect) {
                Text(
                    text = emoji.char,
                    fontSize = 32.sp
                )
            }
        }
    }
}

@Composable
fun MemPreview(navController: NavController) {
    EmojiMemoryTheme {
        MainContent()
    }
}

