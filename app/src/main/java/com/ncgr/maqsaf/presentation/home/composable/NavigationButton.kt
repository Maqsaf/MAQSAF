package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.ui.theme.ButtonTextStyle

@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    icon: Int,
) {
    Surface(
        elevation = 6.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .height(200.dp)
            .width(140.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    onClick()
                }
        ) {
            Column {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .background(Color(0xFFFAFAFA))
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Icon Button",
                        modifier = Modifier.size(100.dp)
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color(0xFF183C69))
                ) {
                    Text(
                        text = title,
                        style = ButtonTextStyle.copy(color = Color.White),
                    )
                }
            }
        }
    }

}