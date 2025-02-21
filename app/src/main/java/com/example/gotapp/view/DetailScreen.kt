package com.example.gotapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.GlideImage
import com.example.gotapp.viewmodel.APIViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    characterId: String,
    modifier: Modifier = Modifier
) {
    val viewModel: APIViewModel = viewModel()
    val characters by viewModel.characters.observeAsState(emptyList())
    val character = characters.find { it.id == characterId }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        if (character != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                // Imagen sin círculo
                GlideImage(
                    model = character.imageUrl,
                    contentDescription = character.fullName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = character.fullName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Título: ${character.title}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Familia: ${character.family}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        } else {
            Text(
                text = "Personaje no encontrado",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text("Volver")
        }
    }
}