package com.example.dogsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsapp.navigation.UIEvents
import com.example.dogsapp.ui.screens.dog_list.DogsListData
import com.example.dogsapp.ui.state.UiState
import com.example.domain.models.Dog
import com.example.domain.usecase.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogViewModel @Inject constructor(
    val useCase: GetDogsUseCase,
) : ViewModel() {

    private var _events = MutableSharedFlow<UIEvents>()
    val events: SharedFlow<UIEvents>
        get() = _events

    private var _state = mutableStateOf(DogsListData())
    val state: State<DogsListData>
        get() = _state

    init {
        _state.value = state.value.copy(getData = ::getData)
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            _state.value = state.value.copy(currentState = UiState.LOADING)
            useCase().onSuccess { dogs ->
                _state.value = state.value.copy(
                    dogs = dogs,
                    currentState = UiState.from(dogs)
                )
            }.onFailure {
                _state.value = state.value.copy(currentState = UiState.ERROR)
            }
        }
    }

    fun goToDetail(dog: Dog?) {
        if (dog == null) return
        viewModelScope.launch {
            _events.emit(UIEvents.GoDetail)
            _state.value = state.value.copy(selectedDog = dog)
        }
    }

}
