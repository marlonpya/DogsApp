package com.example.dogsapp.navigation

sealed class NavigationScreen(val screen: String) {
    object HomeScreen : NavigationScreen("home_screen")
    object DetailScreen : NavigationScreen("detail_screen")
}