package com.codewithteju.findapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codewithteju.findapp.presentation.get_ads.AdsListScreen

@Composable
fun AppNavigationHost(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NAV_HOME
    ) {
        composable(NAV_HOME) {
            AdsListScreen()
        }
        composable(NAV_FAV) {
            //FavoritesScreen()
        }
    }
}
