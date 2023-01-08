package com.ncgr.maqsaf.presentation.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.ui.theme.Green
import com.ncgr.maqsaf.ui.theme.Red

@Composable
fun CustomAlertDialog(
    openDialog: MutableState<Boolean>,
    navigateToServiceProviderActivity: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    val openPasswordError = remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_lock_24),
                    contentDescription = "Password"
                )
            }
        },
        text = {
            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            Column() {
                TextField(
                    value = text,
                    label = { Text("Password") },
                    placeholder = { Text("Your Password") },
                    onValueChange = { text = it },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            R.drawable.ic_baseline_visibility_off_24
                        else R.drawable.ic_baseline_visibility_24

                        // Please provide localized description for accessibility services
                        val description =
                            if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painter = painterResource(id = image),
                                contentDescription = description
                            )
                        }
                    }
                )
            }
        },
        buttons = {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)

            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Red)
                        .clickable { openDialog.value = false }
                        .padding(10.dp)
                ) {
                    Text("Cancel")
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Green)
                        .clickable {
                            if (text == "123456") {
                                openDialog.value = false
                                navigateToServiceProviderActivity()
                            }
                            else {
                                openPasswordError.value = true
                            }
                        }
                        .padding(10.dp)
                ) {
                    Text("Confirm")
                }
            }
        }
    )

    if (openPasswordError.value) {
        AlertDialog(
            onDismissRequest = {
                openPasswordError.value = false
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_warning_24),
                            contentDescription = "Wrong Password",
                        )
                    }
                    Text("Wrong Password")
                }
            },
            buttons = {}
        )

    }


}