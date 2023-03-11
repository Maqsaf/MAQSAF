package com.ncgr.maqsaf.presentation.orderDetails.composable

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ncgr.maqsaf.R
import com.ncgr.maqsaf.presentation.common.utils.Resource
import com.ncgr.maqsaf.presentation.orderDetails.viewModel.OrderDetailsViewModel
import com.ncgr.maqsaf.ui.theme.*

@Composable
fun OrderDetailsBody(
    modifier: Modifier = Modifier,
    viewModel: OrderDetailsViewModel,
) {
    val myOrders = viewModel.myOrders.collectAsState().value

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        when (myOrders) {
            is Resource.Loading -> {
                CircularProgressIndicator(color = Blue)
            }
            is Resource.Success -> {

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(30.dp))

                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFF9F9F9))
                            .padding(vertical = 20.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${myOrders.data[0].orderNumber}#",
                                style = TitleTextStyle.copy(color = Color.Black)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "رقم الطلب",
                                style = TitleTextStyle.copy(color = Color(0xFF183C69))
                            )
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.Top
                        ) {

                            Text(
                                text = "_",
                                style = TitleTextStyle.copy(color = Color(0xFF183C69)),
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(100))
                                        .border(
                                            width = 1.dp,
                                            color = if (myOrders.data[0].orderState == "Pending") Color(
                                                0xff183C69
                                            ) else Color.White,
                                            shape = CircleShape
                                        )
                                        .background(
                                            if (myOrders.data[0].orderState != "Pending") Color(
                                                0xff183C69
                                            ) else Color.Transparent
                                        )
                                ) {
                                    Text(
                                        text = "2",
                                        style = ButtonTextStyle.copy(
                                            color = if (myOrders.data[0].orderState == "Pending") Color(
                                                0xff183C69
                                            ) else Color.White,
                                        ),
                                    )
                                }

                                Text(
                                    text = "حالة الطلب", style = BodyTextStyle.copy(
                                        color = Color(
                                            0xff183C69
                                        )
                                    )
                                )
                            }
                            Text(
                                text = "__",
                                style = TitleTextStyle.copy(color = Color(0xFF183C69))
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(RoundedCornerShape(100))
                                        .border(
                                            width = 1.dp,
                                            color = if (myOrders.data[0].orderState != "Pending") Color(
                                                0xff183C69
                                            ) else Color.White,
                                            shape = CircleShape
                                        )
                                        .background(
                                            if (myOrders.data[0].orderState == "Pending") Color(
                                                0xff183C69
                                            ) else Color.Transparent
                                        )
                                ) {
                                    Text(
                                        text = "1",
                                        style = ButtonTextStyle.copy(
                                            color = if (myOrders.data[0].orderState != "Pending") Color(
                                                0xff183C69
                                            ) else Color.White,
                                        ),
                                    )
                                }

                                Text(
                                    text = "بإنتظار موفر الخدمة", style = BodyTextStyle.copy(
                                        color = Color(
                                            0xff183C69
                                        )
                                    )
                                )
                            }
                            Text(
                                text = "_",
                                style = TitleTextStyle.copy(color = Color(0xFF183C69))
                            )
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        Image(
                            painter = painterResource(
                                id =
                                when (myOrders.data[0].orderState) {
                                    "Pending" -> R.drawable.pending_icon
                                    "Accepted" -> R.drawable.done_icon
                                    else -> R.drawable.rejected_icon
                                }
                            ),
                            contentDescription = "Order Status Icon",
                        )

                        Text(
                            text = when (myOrders.data[0].orderState) {
                                "Pending" -> "يقوم الان موفر الخدمة بمراجعة الطلب"
                                "Accepted" -> "قام موفر الخدمة بقبول طلبك"
                                else -> "قام موفر الخدمة برفض طلبك"
                            },
                            style = TitleTextStyle.copy(
                                color = when (myOrders.data[0].orderState) {
                                    "Pending" -> Color(0xFF183C69)
                                    "Accepted" -> Green
                                    else -> Red
                                }
                            ),
                            fontSize = 15.sp
                        )

                        if (myOrders.data[0].orderState == "Accepted") {
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "تفاصيل الطلب",
                                style = TitleTextStyle.copy(color = Color(0xFF183C69))
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                itemsIndexed(myOrders.data[0].have) { _, item ->
                                    Column(
                                        verticalArrangement = Arrangement.SpaceAround,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .height(170.dp)
                                            .width(130.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .border(
                                                2.dp,
                                                color = Color(0xFF183C69),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .background(Color.White)
                                    ) {
                                        Image(
                                            painter = painterResource(
                                                id = when (item.itemId) {
                                                    "263256b9-e00b-4f1e-99f2-5d09152d5fc6" -> R.drawable.bottle_of_water
                                                    "1bf93ba4-2021-4407-96b2-5b0e34bf1104" -> R.drawable.tea_cup
                                                    else -> R.drawable.coffee_cup
                                                }
                                            ),
                                            contentDescription = "Item Icon",
                                            modifier = Modifier
                                                .weight(2f)
                                                .fillMaxWidth()
                                                .padding(20.dp)
                                        )

                                        Text(
                                            text = when (item.itemId) {
                                                "263256b9-e00b-4f1e-99f2-5d09152d5fc6" -> "ماء"
                                                "1bf93ba4-2021-4407-96b2-5b0e34bf1104" -> "شاي"
                                                else -> "قهوة"
                                            }, style = TitleTextStyle.copy(
                                                color = Color(0xFF183C69)
                                            ),
                                            fontSize = 15.sp, modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(30.dp))

                            Text(
                                text = "تفاصيل المكان",
                                style = TitleTextStyle.copy(color = Color(0xFF183C69))
                            )
                            Text(
                                text = ":سيتم توصيل الطلب في",
                                style = TitleTextStyle.copy(
                                    color = Color(0xFF183C69)
                                ),
                                fontSize = 15.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Column(
                                verticalArrangement = Arrangement.SpaceAround,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .height(170.dp)
                                    .width(130.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(
                                        2.dp,
                                        color = Color(0xFF183C69),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .background(Color.White)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .padding(20.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            when (myOrders.data[0].zoneColor) {
                                                "Blue" -> Blue
                                                "Yellow" -> Yellow
                                                "Green" -> Green
                                                else -> Red
                                            }
                                        )
                                )

                                Text(
                                    text = when (myOrders.data[0].zoneColor) {
                                        "Blue" -> "المنطقة الزرقاء"
                                        "Yellow" -> "المنطقة الصفراء"
                                        "Green" -> "المنطقة الخضراء"
                                        else -> "المنطقة الحمراء"
                                    }, style = TitleTextStyle.copy(
                                        color = Color(0xFF183C69),
                                        textAlign = TextAlign.Center
                                    ),
                                    fontSize = 20.sp, modifier = Modifier.weight(1f).fillMaxWidth()
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(30))
                            .background(
                                if (myOrders.data[0].orderState == "Pending") Color.Gray else Color(
                                    0xff183C69
                                )
                            )
                            .clickable(
                                enabled = myOrders.data[0].orderState != "Pending"
                            ) {
                                viewModel.navToUserActivity()
                            }
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "العودة لصفحة الطلبات",
                            style = ButtonTextStyle.copy(color = Color.White, fontSize = 20.sp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
            is Resource.Error -> {
                Text(text = myOrders.apiError.errorMessage)
            }
        }
    }
}