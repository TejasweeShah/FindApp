package com.codewithteju.findapp.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.codewithteju.findapp.R

sealed class NavItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val navRoute: String
) {
    object Home : NavItem(R.string.home, Icons.Default.Home, NAV_HOME)
    object Favorite : NavItem(R.string.favorite, Icons.Default.Favorite, NAV_FAV)

}