package com.ahmadshubita.moviesapp.ui.movies.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadshubita.moviesapp.ui.theme.MoviesAppTheme

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }
    var switchOn by remember {
        mutableStateOf(checked)
    }
    val alignment by animateAlignmentAsState(if (switchOn) 1f else -1f)

    Box(
        modifier = Modifier
            .size(width = 50.dp, height = 28.dp)
            .border(
                width = 2.dp,
                color = if (switchOn) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(percent = 50)
            )
            .clickable(
                indication = null, interactionSource = interactionSource
            ) {
                onCheckedChange(!switchOn)
                switchOn = !switchOn
            }, contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding(
                    start = 6.dp, end = 6.dp
                )
                .fillMaxSize(), contentAlignment = alignment
        ) {

            Icon(
                imageVector = if (switchOn) Icons.Filled.Done else Icons.Filled.Close,
                contentDescription = if (switchOn) "Enabled" else "Disabled",
                modifier = Modifier
                    .size(size = 20.dp)
                    .background(
                        color = if (switchOn) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        shape = CircleShape
                    )
                    .padding(all = 4.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun animateAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue, label = "")
    return derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) }
}

@Preview
@Composable
private fun previewSwitch() {
    MoviesAppTheme {
        CustomSwitch(true, {})
    }
}