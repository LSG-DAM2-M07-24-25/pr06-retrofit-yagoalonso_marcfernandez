package com.example.gotapp.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.GlideImage
import com.example.gotapp.R
import com.example.gotapp.viewmodel.APIViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import com.example.gotapp.ui.theme.GotBlack
import com.example.gotapp.ui.theme.GotDarkGray
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotLightGold

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    characterId: String,
    modifier: Modifier = Modifier
) {
    val viewModel: APIViewModel = viewModel()
    val characters by viewModel.characters.observeAsState(emptyList())
    val showLoading by viewModel.loading.observeAsState(true)
    val character = characters.find { it.id == characterId }
    val deathMessage by viewModel.deathMessage.observeAsState("")

    // Mostrar el mensaje de muerte si existe
    if (deathMessage.isNotEmpty()) {
        Toast.makeText(LocalContext.current, deathMessage, Toast.LENGTH_SHORT).show()
        viewModel.clearDeathMessage()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalles del Personaje",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GotGold,
                        fontFamily = FontFamily.Serif
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GotDarkGray
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = GotGold
                        )
                    }
                },
                modifier = Modifier.shadow(4.dp)
            )
        },
        containerColor = GotBlack
    ) { paddingValues ->
        Box(
            modifier = modifier
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
            } else if (character != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Imagen del personaje
                    Card(
                        modifier = Modifier
                            .size(300.dp)
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        border = BorderStroke(2.dp, GotGold)
                    ) {
                        GlideImage(
                            model = character.imageUrl,
                            contentDescription = character.fullName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tarjeta para la información
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = GotDarkGray
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = character.fullName,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = GotGold,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif
                            )
                            
                            if (character.title.isNotBlank()) {
                                Text(
                                    text = character.title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.Serif
                                )
                            }

                            Text(
                                text = "${character.family}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium,
                                color = GotLightGold,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // Botón Kill
                    Button(
                        onClick = {
                            viewModel.addDeadCharacter(character)
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GotDarkGray
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.sword),
                            contentDescription = "Kill character",
                            tint = GotGold,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Kill",
                            color = GotGold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
            } else {
                Text(
                    text = "Personaje no encontrado",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = GotGold,
                    modifier = Modifier.align(Alignment.Center),
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}