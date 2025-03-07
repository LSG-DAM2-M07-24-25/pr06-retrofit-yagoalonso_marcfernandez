package com.example.gotapp.view.compact

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.gotapp.navigation.Routes
import com.example.gotapp.ui.theme.GotBlack
import com.example.gotapp.ui.theme.GotDarkGray
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotLightGold
import com.example.gotapp.viewmodel.HousesViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun CompactHousesView(
    navController: NavController,
    housesViewModel: HousesViewModel
) {
    val houses by housesViewModel.houses.observeAsState(emptyList())
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Casas de Poniente",
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(houses) { house ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.8f)
                            .clickable {
                                navController.navigate(Routes.HouseDetailScreenCompact.createRoute(house.id.toString()))
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = GotDarkGray
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Card(
                                shape = CircleShape,
                                border = BorderStroke(2.dp, GotGold),
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(4.dp)
                            ) {
                                // Obtenemos el ID del recurso de imagen
                                val resourceId = context.resources.getIdentifier(
                                    house.imageUrl, 
                                    null, 
                                    context.packageName
                                )
                                
                                GlideImage(
                                    model = resourceId,
                                    contentDescription = house.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Text(
                                text = house.name,
                                color = GotGold,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif,
                                modifier = Modifier.fillMaxWidth()
                            )
                            
                            Text(
                                text = "\"${house.motto}\"",
                                color = GotLightGold,
                                fontSize = 12.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}