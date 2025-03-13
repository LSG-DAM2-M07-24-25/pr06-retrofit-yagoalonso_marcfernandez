package com.example.gotapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gotapp.viewmodel.SearchViewModel
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotDarkGray
import com.example.gotapp.model.CharacterData

@Composable
fun SearchScreen(
    myViewModel: SearchViewModel, 
    paddingValues: PaddingValues,
    characters: List<CharacterData>,
    onCharacterClick: (CharacterData) -> Unit
) {
    val searchedText by myViewModel.searchedText.observeAsState("")
    val searchHistory by myViewModel.searchHistory.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        MySearchBarView(myViewModel)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) {
            if (searchedText.isEmpty()) {
                // Mostrar historial cuando no hay búsqueda
                items(searchHistory) { search ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = GotDarkGray)
                    ) {
                        Text(
                            text = search,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = GotGold
                        )
                    }
                }
            } else {
                // Mostrar personajes filtrados cuando hay texto en la búsqueda
                items(characters.filter { character ->
                    character.fullName.contains(searchedText, ignoreCase = true) ||
                    character.family.contains(searchedText, ignoreCase = true) ||
                    character.title.contains(searchedText, ignoreCase = true)
                }) { character ->
                    CharacterCard(character = character, onClick = onCharacterClick)
                }
            }
        }
    }
}

@Composable
private fun CharacterCard(
    character: CharacterData,
    onClick: (CharacterData) -> Unit
) {
    // Tu Card existente de personaje aquí
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(character) },
        colors = CardDefaults.cardColors(
            containerColor = GotDarkGray
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        // ... resto del contenido de la Card
    }
} 