package com.example.dogsapp.navigation

import androidx.compose.runtime.Composable
import androidx.core.util.Function
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dogsapp.DogViewModel

@Composable
fun NavHost(viewModel: DogViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    Routing(navController, viewModel.events)

    NavHost(
        navController = navController,
        startDestination = NavigationHost.InitNavHost.route
    ) {
        graph(
            state = { viewModel.state.value },
            goToDetail = Function(viewModel::goToDetail),
            onBack = { navController.popBackStack() }
        )
    }
}
