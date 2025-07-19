package com.example.dogsapp.ui.screens.dog_list

import com.example.dogsapp.ui.state.UiState
import com.example.dogsapp.ui.state.UiStateHolder
import com.example.domain.models.Dog

data class DogsListData(
    val getData: () -> Unit = {},
    val dogs: List<Dog> = emptyList(),
    val selectedDog: Dog? = null,
    override var currentState: UiState = UiState.INIT,
) : UiStateHolder