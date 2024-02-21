package com.brayandev.yu_gi_oh_app.ui.views.DetailCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

@Composable
fun DetailCardsScreen(viewModel: DetailViewModel) {
    val loadingState by viewModel.loading.observeAsState(false)
    val listState by viewModel.list.observeAsState(emptyList())

    if (loadingState) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    }
    viewModel.getListCards()
}
