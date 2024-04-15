package com.ahmadshubita.moviesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadshubita.moviesapp.data.models.MoviesResponse
import com.ahmadshubita.moviesapp.data.remote.utils.NetworkException
import com.ahmadshubita.moviesapp.data.remote.utils.NewsHiveException
import com.ahmadshubita.moviesapp.data.remote.utils.NullDataException
import com.ahmadshubita.moviesapp.data.remote.utils.RateLimitExceededException
import com.ahmadshubita.moviesapp.data.remote.utils.UnAuthorizedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE>(initState: STATE): ViewModel() {

    protected val _state: MutableStateFlow<STATE> by lazy { MutableStateFlow(initState) }
    val state = _state.asStateFlow()

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

    protected fun <T> collectFlow(
        flow: Flow<T>,
        updateState: STATE.(T) -> STATE
    ) {
        viewModelScope.launch {
            flow.collect { value ->
                _state.update { it.updateState(value) }
            }
        }
    }
}