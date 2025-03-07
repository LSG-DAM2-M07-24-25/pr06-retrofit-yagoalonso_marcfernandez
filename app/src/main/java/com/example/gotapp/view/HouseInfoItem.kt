package com.example.gotapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gotapp.ui.theme.GotGold
import com.example.gotapp.ui.theme.GotLightGold
import androidx.compose.ui.Modifier

@Composable
fun HouseInfoItem(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = GotGold,
            fontFamily = FontFamily.Serif
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = GotLightGold,
            fontFamily = FontFamily.Serif
        )
    }
} 