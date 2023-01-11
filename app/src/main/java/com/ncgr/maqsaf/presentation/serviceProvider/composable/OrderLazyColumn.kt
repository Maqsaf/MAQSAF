package com.ncgr.maqsaf.presentation.serviceProvider.composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.data.remote.model.MenuDtoItem
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.serviceProvider.viewModel.ServiceProviderViewModel
import com.ncgr.maqsaf.ui.theme.Blue

@Composable
fun OrderLazyColumn(
    modifier: Modifier = Modifier,
    viewModel: ServiceProviderViewModel,
) {
    val orderList by viewModel.orderList.collectAsState()

    when (orderList) {
        is Resource.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Blue)
            }
        }
        is Resource.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                try {
                    val successOrderList = orderList as Resource.Success<List<MenuDtoItem>>
                    itemsIndexed(successOrderList.data) { index, res ->
                        OrderWidget(
                            orderUid = res.id,
                            orderNumber = res.orderNumber,
                            zoneColor = res.zoneColor,
                            itemImageUrl = res.have[0].item.itemImage,
                            viewModel = viewModel
                        )
                    }
                } catch (e: Exception) {
                    Log.d("TEST", "Cast failed")
                }


            }
        }
        is Resource.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.padding(20.dp)
            ) {
                Text(
                    text = (orderList as Resource.Error<List<MenuDtoItem>>).apiError.errorMessage,
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }


}