package com.example.gotapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavController
import com.example.gotapp.model.CharacterData
import com.example.gotapp.navigation.Routes
import com.example.gotapp.viewmodel.APIViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyRecyclerView(modifier: Modifier = Modifier, navController: NavController) {
    val myViewModel: APIViewModel = viewModel()
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val characters: List<CharacterData> by myViewModel.characters.observeAsState(emptyList())

    if (showLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(characters) { character ->
                Box(modifier = Modifier.clickable {
                    navController.navigate(Routes.DetailScreen.createRoute(character.id))
                }) {
                    CharacterItem(character = character)
                }
            }
        }
    }
}