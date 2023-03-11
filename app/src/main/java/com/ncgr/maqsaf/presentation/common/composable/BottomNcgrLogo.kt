package com.ncgr.maqsaf.presentation.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.R

@Composable
fun BottomNcgrLogo(
    logoId: Int = R.drawable.ncgr_logo_only_2,
    alpha: Float = 0.5f
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier.fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = logoId),
            contentDescription = "Bottom Logo",
            contentScale = ContentScale.Fit,
            alignment = Alignment.BottomStart,
            alpha = alpha,
            modifier = Modifier
                .padding(top = 10.dp)
                .size(300.dp)
        )
    }
}
