package com.ncgr.maqsaf.presentation.userLogin.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.userLogin.viewModel.UserLoginViewModel
import com.ncgr.maqsaf.ui.theme.BodyTextStyle
import com.ncgr.maqsaf.ui.theme.ButtonTextStyle
import com.ncgr.maqsaf.ui.theme.TitleTextStyle

@Composable
fun LoginScreenBody(
    modifier: Modifier = Modifier,
    viewModel: UserLoginViewModel,
    navigateToRegisterActivity: () -> Unit,
) {
    val phoneNumber by viewModel.phoneNumber.collectAsState()
    val passwordText by viewModel.passwordText.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .fillMaxSize()
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

        Text("تسجيل الدخول", style = TitleTextStyle.copy(color = Color(0xFF183C69)))

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "الرقم الوظيفي", style = BodyTextStyle.copy(color = Color(0xFF183C69)), fontSize = 18.sp)
        OutlinedTextField(
            value = phoneNumber ?: "",
            onValueChange = { if (it.length <= 6) viewModel.setPhoneNumber(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textDirection = TextDirection.Rtl),
            shape = RoundedCornerShape(30),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "الرقم السري", style = BodyTextStyle.copy(color = Color(0xFF183C69), fontSize = 18.sp))
        OutlinedTextField(
            value = passwordText ?: "",
            onValueChange = { viewModel.setPasswordText(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(textDirection = TextDirection.Rtl),
            shape = RoundedCornerShape(30),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30))
                .background(Color(0xff183C69))
                .clickable {
                    viewModel.login()
                }
                .padding(10.dp))
        {
            Text(
                text = "تسجيل الدخول",
                style = ButtonTextStyle.copy(color = Color.White, fontSize = 20.sp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30))
                .border(width = 2.dp, color = Color(0xFF183C69), shape = RoundedCornerShape(30))
                .background(Color.White)
                .clickable {
                    navigateToRegisterActivity()
                }
                .padding(10.dp)
        )
        {
            Text(
                text = "تسجيل",
                style = ButtonTextStyle.copy(color = Color(0xFF183C69), fontSize = 20.sp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}