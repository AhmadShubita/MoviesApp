package com.ahmadshubita.moviesapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, UiEffect>(initState: STATE): ViewModel() {


    protected val _uiState: MutableStateFlow<STATE> by lazy { MutableStateFlow(initState) }
    val uiState = _uiState.asStateFlow()

    protected val _uiEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = _uiEffect.asSharedFlow()

    fun <T> tryToExecute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun <T1, T2, T3> tryToExecuteConcurrently(
        call1: suspend () -> T1,
        call2: suspend () -> T2,
        call3: suspend () -> T3,
        onSuccess: (T1, T2, T3) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result1 = async { call1() }.await()
                val result2 = async { call2() }.await()
                val result3 = async { call3() }.await()
                onSuccess(result1, result2, result3)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun <T : Any> tryToExecutePaging(
            call: suspend () -> Flow<PagingData<T>>,
            onSuccess: suspend (PagingData<T>) -> Unit,
            onError: (Throwable) -> Unit,
            dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = call().cachedIn(viewModelScope)
                result.collect { data ->
                    onSuccess(data)
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected fun <T> collectFlow(
        flow: Flow<T>,
        updateState: STATE.(T) -> STATE
    ) {
        viewModelScope.launch {
            flow.collect { value ->
                _uiState.update { it.updateState(value) }
            }
        }
    }

    protected fun triggerUiEffect(effect: UiEffect) {
        viewModelScope.launch { _uiEffect.emit(effect) }
    }

    interface BaseUiEffect
}