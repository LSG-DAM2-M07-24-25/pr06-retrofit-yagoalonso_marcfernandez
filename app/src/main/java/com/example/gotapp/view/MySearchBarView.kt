package com.example.gotapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.gotapp.viewmodel.SearchViewModel
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotDarkGray
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBarView(myViewModel: SearchViewModel) {
    val searchedText by myViewModel.searchedText.observeAsState("")
    val searchHistory by myViewModel.searchHistory.observeAsState(emptyList())

    SearchBar(
        query = searchedText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.addToHistory(it) },
        active = false,
        onActiveChange = { },
        leadingIcon = { 
            Icon(
                imageVector = Icons.Filled.Search, 
                contentDescription = "Search",
                tint = GotGold
            )
        },
        trailingIcon = {
            if (searchedText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear",
                    tint = GotGold,
                    modifier = Modifier.clickable { myViewModel.onSearchTextChange("") }
                )
            }
        },
        placeholder = { Text("Buscar personaje...", color = GotGold.copy(alpha = 0.6f)) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = SearchBarDefaults.colors(
            containerColor = GotDarkGray,
            inputFieldColors = TextFieldDefaults.colors(
                focusedTextColor = GotGold,
                unfocusedTextColor = GotGold
            )
        )
    ) {}
}