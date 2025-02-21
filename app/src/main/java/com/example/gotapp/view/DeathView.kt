package com.example.gotapp.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.gotapp.ui.theme.GotBlack
import com.example.gotapp.ui.theme.GotDarkGray
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotLightGold
import com.example.gotapp.viewmodel.APIViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DeathView() {
    val viewModel: APIViewModel = viewModel()
    val deadCharacters by viewModel.deadCharacters.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Personajes Caídos",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = GotGold,
                        modifier = Modifier.padding(vertical = 8.dp),
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
            if (deadCharacters.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Aún no hay personajes caídos",
                        color = GotGold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(deadCharacters) { character ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
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
                                    modifier = Modifier.size(80.dp)
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
                                        text = "${character.fullName} ha muerto",
                                        color = GotGold,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif
                                    )
                                    Text(
                                        text = character.family,
                                        color = GotLightGold,
                                        fontSize = 16.sp,
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