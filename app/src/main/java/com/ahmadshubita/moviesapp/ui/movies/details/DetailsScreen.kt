package com.ahmadshubita.moviesapp.ui.movies.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.movies.details.viewmodel.DetailsScreenState
import com.ahmadshubita.moviesapp.ui.movies.details.viewmodel.DetailsUiEffect
import com.ahmadshubita.moviesapp.ui.movies.details.viewmodel.DetailsViewModel
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme
import com.ahmadshubita.moviesapp.ui.theme.customColors
import com.ahmadshubita.moviesapp.ui.theme.dimens
import com.ahmadshubita.moviesapp.ui.util.HandleUiEffect
import com.ahmadshubita.moviesapp.ui.util.SnackBarBuilder
import com.ahmadshubita.moviesapp.ui.util.SnackBarStatus

@Composable
fun DetailsScreen(
        navController: NavController, viewModel: DetailsViewModel = hiltViewModel()
) {
    val detailsScreenState by viewModel.uiState.collectAsState()
    HandleUiEffect(effect = viewModel.uiEffect) {
        when (it) {
            is DetailsUiEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }
    DetailsContent(detailsScreenState, viewModel)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsContent(state: DetailsScreenState, viewModel: DetailsViewModel) {

    val customColorsPalette = customColors
    val colorScheme = MaterialTheme.colorScheme
    val dimens = dimens
    val fontStyle = MaterialTheme.typography

    val coroutineScope = rememberCoroutineScope()

    /**
     * This code is used to initialize the SnackBarBuilder and all its properties
     */
    val snackBarBuilder = SnackBarBuilder()
    snackBarBuilder.snackBarHostState = remember { SnackbarHostState() }
    snackBarBuilder.ConnectivityAwareSnackBar()

    val notImplemented = stringResource(id = R.string.this_feature_not_implemented)

    Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { snackBarBuilder.SnackBarHost() },
    ) {
        Box(
                modifier = Modifier
                        .fillMaxHeight()
                        .background(colorScheme.surface)
        ) {
            Column {
                Box(modifier = Modifier.height(400.dp)) {
                    AsyncImage(
                            state.detailsItem.posterImageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                    )
                    Box(
                            modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                            Brush.verticalGradient(
                                                    colors = listOf(
                                                            customColorsPalette.transparent,
                                                            customColorsPalette.transparent.copy(alpha = dimens.floatValues.float0_8)
                                                    ), startY = dimens.floatValues.float200
                                            )
                                    )
                    )
                    Column(
                            modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .wrapContentHeight()
                    ) {
                        Text(
                                modifier = Modifier.padding(horizontal = dimens.space20),
                                text = state.detailsItem.title ?: "",
                                color = colorScheme.onTertiary,
                                style = fontStyle.titleLarge,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                        )

                        Row(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(
                                                start = dimens.space20,
                                                end = dimens.space20,
                                                bottom = dimens.space20
                                        ), verticalAlignment = Alignment.Bottom
                        ) {
                            Column(
                                    Modifier.weight(1f)
                            ) {
                                Text(
                                        modifier = Modifier,
                                        text = "${state.detailsItem.voteCount} People Rating",
                                        color = colorScheme.surfaceVariant,
                                        style = fontStyle.titleMedium,
                                        maxLines = 5,
                                        overflow = TextOverflow.Ellipsis
                                )

                                Row(
                                        modifier = Modifier.padding(top = 12.dp),
                                        verticalAlignment = Alignment.Bottom
                                ) {
                                    Text(
                                            text = state.detailsItem.rating,
                                            color = colorScheme.secondary,
                                            style = fontStyle.titleLarge,
                                            maxLines = 5,
                                            overflow = TextOverflow.Ellipsis
                                    )
                                    RatingLayout(
                                            modifier = Modifier.padding(start = 8.dp, bottom = 2.dp),
                                            rating = state.detailsItem.voteAverage ?: 0.0
                                    )
                                }
                            }
                            Card(modifier = Modifier
                                    .size(dimens.space64)
                                    .weight(0.24f)
                                    .padding(top = dimens.space8, end = dimens.space8)
                                    .clickable {
                                        snackBarBuilder.showSnackBar(
                                                coroutineScope = coroutineScope,
                                                status = SnackBarStatus.DEFAULT,
                                                message = notImplemented,
                                                throwable = null,
                                        )
                                    },
                                    shape = RoundedCornerShape(dimens.space56),
                                    elevation = CardDefaults.cardElevation(dimens.space16),
                                    colors = CardDefaults.cardColors(containerColor = colorScheme.secondary)
                            ) {
                                Box(modifier = Modifier
                                        .fillMaxSize()
                                        .background(brush = Brush.horizontalGradient(
                                                colors = listOf(
                                                        colorScheme.onPrimaryContainer,
                                                        colorScheme.primaryContainer
                                                )
                                        )
                                        ), contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_play),
                                            modifier = Modifier
                                                    .size(18.dp)
                                                    .padding(start = 2.dp)
                                                    .align(
                                                            Alignment.Center
                                                    ),
                                            tint = Color.White,
                                            contentDescription = ""
                                    )
                                }
                            }
                        }
                        Text(modifier = Modifier
                                .padding(bottom = dimens.space40)
                                .padding(horizontal = dimens.space20),
                                text = state.detailsItem.overview ?: "",
                                color = colorScheme.onTertiary,
                                style = fontStyle.titleLarge,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimens.space10)
                    ) {

                        IconButton(onClick = { viewModel.onClickBackButton() }) {
                            Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onTertiary
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            snackBarBuilder.showSnackBar(
                                    coroutineScope = coroutineScope,
                                    status = SnackBarStatus.DEFAULT,
                                    message = notImplemented,
                                    throwable = null,
                            )
                        }) {
                            Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                                    contentDescription = "",
                                    tint = colorScheme.onTertiary
                            )
                        }
                    }
                }
                Text(
                        text = "Production Companies",
                        modifier = Modifier.padding(start = dimens.space16, top = dimens.space24),
                        style = fontStyle.titleMedium,
                        color = colorScheme.onBackground
                )
                LazyHorizontalGrid(modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(start = dimens.space16, top = dimens.space20),
                        rows = GridCells.Fixed(1)
                ) {
                    state.detailsItem.productionCompanies?.size?.let {
                        items(it) { item ->
                            ProductionCompaniesListCard(state.detailsItem.productionCompanies[item].name,
                                    "",
                                    state.detailsItem.productionCompanies[item].logoUrl,
                                    onClick = {
                                        snackBarBuilder.showSnackBar(
                                                coroutineScope = coroutineScope,
                                                status = SnackBarStatus.DEFAULT,
                                                message = notImplemented,
                                                throwable = null,
                                        )
                                    })
                        }
                    }
                }

                Row(modifier = Modifier
                        .padding(top = dimens.space26)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(modifier = Modifier
                            .size(70.dp)
                            .clickable {
                                snackBarBuilder.showSnackBar(
                                        coroutineScope = coroutineScope,
                                        status = SnackBarStatus.DEFAULT,
                                        message = notImplemented,
                                        throwable = null,
                                )
                            },
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_like_filled),
                            contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Image(modifier = Modifier
                            .size(70.dp)
                            .clickable {
                                snackBarBuilder.showSnackBar(
                                        coroutineScope = coroutineScope,
                                        status = SnackBarStatus.DEFAULT,
                                        message = notImplemented,
                                        throwable = null,
                                )
                            },
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_star_circle_filled),
                            contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Image(modifier = Modifier
                            .size(70.dp)
                            .clickable {
                                snackBarBuilder.showSnackBar(
                                        coroutineScope = coroutineScope,
                                        status = SnackBarStatus.DEFAULT,
                                        message = notImplemented,
                                        throwable = null,
                                )
                            },
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_comment_filled),
                            contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MoviesAppTheme {
        DetailsScreen(navController = rememberNavController())
    }
}