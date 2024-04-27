package com.ahmadshubita.moviesapp.ui.util

import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ahmadshubita.moviesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SnackBarBuilder(var snackBarHostState: SnackbarHostState? = null) {

    @Composable
    fun SnackBarHost() {
        snackBarHostState?.let { snackBarState ->
            SnackbarHost(hostState = snackBarState) { snackBarData ->
                SnackBar(snackBarData = snackBarData)
            }
        }
    }

    /**
     * This method is used to give awareness about the internet connectivity if we are connected it will show connected snack bar
     * and if we are not connected will show no internet connection snack bar.
     */
    @Composable
    fun ConnectivityAwareSnackBar() {
        var isNetworkAvailable by remember { mutableStateOf(true) }
        var initialComposition by remember { mutableStateOf(true) }

        val connectivityManager =
            LocalContext.current.getSystemService(ConnectivityManager::class.java)

        DisposableEffect(connectivityManager) {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isNetworkAvailable = true
                }

                override fun onLost(network: Network) {
                    isNetworkAvailable = false
                }
            }

            connectivityManager?.registerDefaultNetworkCallback(networkCallback)

            onDispose {
                connectivityManager?.unregisterNetworkCallback(networkCallback)
            }
        }

        val coroutineScope = rememberCoroutineScope()
        val noInternetMessage = stringResource(id = R.string.no_internet_connection)
        val connectedMessage = stringResource(id = R.string.connected)

        LaunchedEffect(isNetworkAvailable) {
            if (!initialComposition) {
                if (!isNetworkAvailable) {
                    showSnackBar(
                        coroutineScope,
                        SnackBarStatus.ERROR,
                        noInternetMessage,
                        duration = SnackbarDuration.Indefinite
                    )
                } else {
                    showSnackBar(
                        coroutineScope,
                        SnackBarStatus.SUCCESS,
                        connectedMessage
                    )
                }
            }
        }

        LaunchedEffect(Unit) {
            // Set initialComposition to false after the initial composition is complete
            initialComposition = false
        }
    }

    fun showSnackBar(
        coroutineScope: CoroutineScope,
        status: SnackBarStatus,
        message: String?,
        throwable: Throwable? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
        actionLabel: String? = null,
        retryCallback: (() -> Unit)? = null
    ) {
        snackBarStatus = status

        val apiErrorMessage: String = when {
            throwable?.message != null -> throwable.message.toString()
            else -> ""
        }


        // rememberCoroutinesScope is used here instead of LaunchedEffect because we need to use the snack bar outside the composable scope
        coroutineScope.launch {
            val result = snackBarHostState?.showSnackbar(
                message = (if (throwable == null || throwable.message.isNullOrEmpty()) message.toString() else apiErrorMessage),
                actionLabel = if (retryCallback != null) actionLabel else null,
                withDismissAction = retryCallback != null,
                duration = if (retryCallback != null) SnackbarDuration.Indefinite else duration
            )
            if (result == SnackbarResult.ActionPerformed) {
                retryCallback?.invoke()
            }
        }
    }

    @Composable
    private fun SnackBar(snackBarData: SnackbarData) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Snackbar(
                modifier = Modifier.wrapContentSize(),
                snackbarData = snackBarData,
                containerColor = snackBarBackgroundColor,
                actionColor = MaterialTheme.colorScheme.onError
            )
        }
    }

    companion object {
        private var snackBarStatus: SnackBarStatus = SnackBarStatus.DEFAULT
        private val snackBarBackgroundColor: Color
            @Composable
            get() = when (snackBarStatus) {
                SnackBarStatus.DEFAULT -> MaterialTheme.colorScheme.primary
                SnackBarStatus.SUCCESS -> MaterialTheme.colorScheme.primary
                SnackBarStatus.WARNING -> MaterialTheme.colorScheme.tertiary
                SnackBarStatus.ERROR -> MaterialTheme.colorScheme.error
            }
    }
}

enum class SnackBarStatus {
    DEFAULT,
    SUCCESS,
    WARNING,
    ERROR
}