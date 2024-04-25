package com.ahmadshubita.moviesapp.ui.core.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadshubita.moviesapp.R
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun DefaultErrorLayout() {
    Column(modifier = Modifier
            .heightIn(min = 240.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
                contentDescription = "",
                modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = stringResource(R.string.some_thing_went_wrong), style = MaterialTheme.typography.titleMedium)
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultErrorLayoutPreview() {
    MoviesAppTheme {
        DefaultErrorLayout()
    }
}