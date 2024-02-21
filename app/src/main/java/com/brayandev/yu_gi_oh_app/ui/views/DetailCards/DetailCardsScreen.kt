package com.brayandev.yu_gi_oh_app.ui.views.DetailCards

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.brayandev.yu_gi_oh_app.R
import com.brayandev.yu_gi_oh_app.data.repository.models.CardModel

@Composable
fun DetailCardsScreen(viewModel: DetailViewModel) {
    val loadingState by viewModel.loading.observeAsState(false)
    val listState by viewModel.list.observeAsState(emptyList())

    val title by viewModel.archetypeNameText.observeAsState("")

    if (loadingState) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    }

    viewModel.getListCards()

    if (listState.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GlobalTitleCard(title, painterResource(id = R.drawable.archetype_ic))
            Box(modifier = Modifier.fillMaxSize()) { CardList(listState) }
        }
    }
}

@Composable()
fun GlobalTitleCard(title: String, painterResource: Painter) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.size(18.dp).padding(horizontal = 16.dp),
            painter = painterResource,
            contentDescription = "Icon_title",
        )
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun CardList(list: List<CardModel>) {
    LazyColumn {
        items(list, key = { it.id }) { item ->
            Log.d("TAG", "CardList: ${item.imageUrl}")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        AsyncImage(
                            model = item.listCardImages[0].image_url_cropped,
                            contentDescription = item.name,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Text(
                        text = item.name,
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        text = item.description,
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        maxLines = 6,
                        textAlign = TextAlign.Start,
                        fontSize = 10.sp,
                    )
                }
                Column {
                }
            }
        }
    }
}
