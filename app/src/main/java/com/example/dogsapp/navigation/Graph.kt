package com.example.dogsapp.navigation

import androidx.core.util.Function
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dogsapp.ui.screens.dog_detail.DogDetailScreen
import com.example.dogsapp.ui.screens.dog_list.DogsListData
import com.example.dogsapp.ui.screens.dog_list.DogsListScreen
import com.example.domain.models.Dog

fun NavGraphBuilder.graph(
    state: () -> DogsListData,
    goToDetail: Function<Dog?, Unit>,
    onBack: () -> Unit,
) {
    navigation(
        startDestination = NavigationScreen.HomeScreen.screen,
        route = NavigationHost.InitNavHost.route
    ) {
        composable(NavigationScreen.HomeScreen.screen) {
            DogsListScreen(state, goToDetail, state().getData)
        }
        composable(NavigationScreen.DetailScreen.screen) {
            DogDetailScreen(dog = state().selectedDog, onBackClick = onBack)
        }
    }
}
