package com.example.dogsapp.navigation

sealed class NavigationHost(val route: String) {
    object InitNavHost : NavigationHost("navigation_host")
}