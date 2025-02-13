package com.example.gotapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gotapp.model.Data
import com.example.gotapp.viewmodel.APIViewModel

@Composable
fun CharacterListView(viewModel: APIViewModel) {
    val showLoading: Boolean by viewModel.loading.observeAsState(true)
    val characters: Data by viewModel.characters.observeAsState(Data(emptyList()))

    viewModel.getCharacters()

    if (showLoading) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        }
    } else {
        LazyColumn {
            items(characters.characters) {
                CharacterItem(character = it)
            }
        }
    }
}