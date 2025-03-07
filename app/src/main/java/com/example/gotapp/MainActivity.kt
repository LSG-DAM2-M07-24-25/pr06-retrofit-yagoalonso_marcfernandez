package com.example.gotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.gotapp.navigation.NavGraph
import com.example.gotapp.viewmodel.APIViewModel
import com.example.gotapp.viewmodel.HousesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var apiViewModel: APIViewModel
    private lateinit var housesViewModel: HousesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiViewModel = ViewModelProvider(this)[APIViewModel::class.java]
        housesViewModel = ViewModelProvider(this)[HousesViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            NavGraph(
                navController = navController,
                apiViewModel = apiViewModel,
                housesViewModel = housesViewModel
            )
        }
    }
}