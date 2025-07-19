package com.example.dogsapp.ui.state

enum class UiState {
    INIT,
    LOADING,
    EMPTY,
    SUCCESS,
    ERROR;

    companion object {
        @JvmStatic
        fun from(list: List<*>): UiState = if (list.isEmpty()) EMPTY else SUCCESS
    }

}