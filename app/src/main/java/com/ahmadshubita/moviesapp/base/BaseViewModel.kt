package com.ahmadshubita.moviesapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmadshubita.moviesapp.data.remote.utils.MoviesAppException
import com.ahmadshubita.moviesapp.data.remote.utils.NetworkException
import com.ahmadshubita.moviesapp.data.remote.utils.NullDataException
import com.ahmadshubita.moviesapp.data.remote.utils.RateLimitExceededException
import com.ahmadshubita.moviesapp.data.remote.utils.UnAuthorizedException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

abstract class BaseViewModel<STATE, UiEffect>(initState: STATE) : ViewModel() {

    protected val uiMutableState: MutableStateFlow<STATE> by lazy { MutableStateFlow(initState) }
    val uiState = uiMutableState.asStateFlow()

    protected val uiMutableEffect = MutableSharedFlow<UiEffect>()
    val uiEffect = uiMutableEffect.asSharedFlow()

    fun <T> execute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                call().also(onSuccess)
            } catch (e: NetworkException) {
                onError(e)
            } catch (e: UnAuthorizedException) {
                onError(e)
            } catch (e: NullDataException) {
                onError(e)
            } catch (e: RateLimitExceededException) {
                onError(e)
            } catch (e: MoviesAppException) {
                onError(e)
            }
        }
    }

    suspend fun <T1, T2, T3> executeConcurrently(
        call1: suspend () -> T1,
        call2: suspend () -> T2,
        call3: suspend () -> T3,
        onSuccess: (T1, T2, T3) -> Unit,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        supervisorScope {
            try {
                val result1 = async(dispatcher) { call1() }
                val result2 = async(dispatcher) { call2() }
                val result3 = async(dispatcher) { call3() }

                val r1 = result1.await()
                val r2 = result2.await()
                val r3 = result3.await()

                onSuccess(r1, r2, r3)
            } catch (e: Throwable) {
                onError(e)
            }
        }
    }

    fun <T : Any> executePaging(
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
            } catch (e: NetworkException) {
                onError(e)
            } catch (e: UnAuthorizedException) {
                onError(e)
            } catch (e: NullDataException) {
                onError(e)
            } catch (e: RateLimitExceededException) {
                onError(e)
            } catch (e: MoviesAppException) {
                onError(e)
            }

        }
    }

    protected fun triggerUiEffect(effect: UiEffect) {
        viewModelScope.launch { uiMutableEffect.emit(effect) }
    }

    interface BaseUiEffect
}