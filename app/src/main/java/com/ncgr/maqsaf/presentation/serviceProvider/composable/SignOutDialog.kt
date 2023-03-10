package com.ncgr.maqsaf.presentation.serviceProvider.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel
import com.ncgr.maqsaf.presentation.user.viewModel.UserViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun SignOutDialog(
    viewModel: ServiceProviderViewModel,
    orderStatus: Resource<String>,
    zoneColorSelectionError: String?,
    itemSelectionError: String?,
    showOrderDialog: Boolean,
) {
    if (!showOrderDialog) return

    when (orderStatus) {
        is Resource.Loading -> {
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Waiting...",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Blue)
                    }
                },
                buttons = {}
            )
        }
        is Resource.Success -> {
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_cloud_done_24),
                            contentDescription = "Success",
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "Success",)
                    }
                },
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = orderStatus.data,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                buttons = {}
            )
        }
        is Resource.Error -> {
            if (orderStatus.apiError.errorMessage == "Error sending order"){
                AlertDialog(
                    onDismissRequest = {
                        viewModel.closeOrderDialog()
                    },
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_warning_24),
                                contentDescription = "Something went wrong",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Error Occurred")
                        }
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "${zoneColorSelectionError ?: ""}\n${itemSelectionError ?: ""}")
                        }
                    },
                    buttons = {}
                )
            }
            else{
                AlertDialog(
                    onDismissRequest = {
                        viewModel.closeOrderDialog()
                    },
                    title = {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_warning_24),
                                contentDescription = "Something went wrong",
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Error Occurred")
                        }
                    },
                    text = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = orderStatus.apiError.errorMessage)
                        }
                    },
                    buttons = {}
                )
            }
        }
    }
}