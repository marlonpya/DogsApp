package com.example.dogsapp.ui.state

data class MutableUiStateHolder(
    override var currentState: UiState = UiState.INIT
) : UiStateHolder