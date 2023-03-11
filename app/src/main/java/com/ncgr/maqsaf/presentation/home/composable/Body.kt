package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.ui.theme.ContinueAsUser
import com.ncgr.maqsaf.ui.theme.DescriptionTextStyle
import com.ncgr.maqsaf.ui.theme.TitleTextStyle

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navigateToUserLoginActivity: () -> Unit,
    navigateToServiceProviderLoginActivity: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .padding(horizontal = 30.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.maqsaf_logo),
            contentDescription = "MAQSAF Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(1f)
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text("مرحبا بك في مقصف", style = TitleTextStyle.copy(color = Color(0xFF183C69)))
        Text(":قم بالانضمام معنا كـ", style = DescriptionTextStyle.copy(color = Color.Black, fontSize = 20.sp))

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                NavigationButton(
                    title = "موفر خدمة",
                    onClick = { navigateToServiceProviderLoginActivity() },
                    icon = R.drawable.service_provider
                )
            }
            Box {
                NavigationButton(
                    title = "موظف",
                    onClick = { navigateToUserLoginActivity() },
                    icon = R.drawable.employee_icon,
                )
            }
        }
    }
}