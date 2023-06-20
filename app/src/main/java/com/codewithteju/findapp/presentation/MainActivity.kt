package com.codewithteju.findapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.codewithteju.findapp.navigation.AppBottomNavigation
import com.codewithteju.findapp.navigation.AppNavigationHost
import com.codewithteju.findapp.navigation.NavItem
import com.codewithteju.findapp.ui.theme.FindAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            AppBottomNavigation(
                                navItems = listOf(NavItem.Home, NavItem.Favorite),
                                navController = navController
                            )
                        }

                    ) { innerPadding ->
                        AppNavigationHost(
                            modifier = Modifier.padding(innerPadding),
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }
}

