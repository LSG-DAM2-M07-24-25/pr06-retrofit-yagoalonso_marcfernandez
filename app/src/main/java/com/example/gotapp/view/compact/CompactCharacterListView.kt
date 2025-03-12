package com.example.gotapp.view.compact

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gotapp.model.CharacterData
import com.example.gotapp.navigation.Routes
import com.example.gotapp.viewmodel.APIViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotDarkGray
import com.example.gotapp.ui.theme.GotBlack
import com.example.gotapp.ui.theme.GotLightGold
import com.bumptech.glide.integration.compose.GlideImage
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import com.example.gotapp.view.SearchScreen
import com.example.gotapp.view.MySearchBarView
import com.example.gotapp.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun CompactCharacterListView(modifier: Modifier = Modifier, navController: NavController) {
    val myViewModel: APIViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val characters: List<CharacterData> by myViewModel.characters.observeAsState(emptyList())
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Game of Thrones",
                        fontSize = if (isPortrait) 28.sp else 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GotGold,
                        modifier = Modifier.padding(vertical = if (isPortrait) 8.dp else 4.dp),
                        fontFamily = FontFamily.Serif
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GotDarkGray
                ),
                modifier = Modifier.shadow(4.dp)
            )
        },
        containerColor = GotBlack
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(GotBlack)
        ) {
            if (showLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = GotGold)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        SearchScreen(searchViewModel, PaddingValues(0.dp))
                    }

                    items(characters) { character ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Routes.DetailScreen.createRoute(character.id.toString()))
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = GotDarkGray
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Card(
                                    shape = CircleShape,
                                    border = BorderStroke(2.dp, GotGold),
                                    modifier = Modifier.size(if (isPortrait) 80.dp else 60.dp)
                                ) {
                                    GlideImage(
                                        model = character.imageUrl,
                                        contentDescription = character.fullName,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                                Column {
                                    Text(
                                        text = character.fullName,
                                        color = GotGold,
                                        fontSize = if (isPortrait) 18.sp else 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif
                                    )
                                    if (character.title.isNotBlank()) {
                                        Text(
                                            text = character.title,
                                            color = Color.Gray,
                                            fontSize = if (isPortrait) 14.sp else 12.sp,
                                            fontFamily = FontFamily.Serif
                                        )
                                    }
                                    Text(
                                        text = character.family,
                                        color = GotLightGold,
                                        fontSize = if (isPortrait) 16.sp else 14.sp,
                                        fontFamily = FontFamily.Serif
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}