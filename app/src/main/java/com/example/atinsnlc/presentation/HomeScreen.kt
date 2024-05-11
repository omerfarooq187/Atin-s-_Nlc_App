package com.example.atinsnlc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.Workspaces
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController) {
    NavigationDrawer(navController)
}


@Composable
fun Sections(navController: NavController, lazyListState: LazyStaggeredGridState) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        userScrollEnabled = true,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = lazyListState,
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color("#E65100".toColorInt()))
                    .clickable {
                        when (item.title) {
                            "News & Events" -> {
                                navController.navigate("news&events")
                            }

                            "Registration" -> {
                                navController.navigate("registration")
                            }

                            "Fee Structure" -> {
                                navController.navigate("fee_structure")
                            }

                            "Courses Catalog" -> {
                                navController.navigate("courses")
                            }

                            "About Us" -> {
                                navController.navigate("about_us")
                            }

                            "Contact Us" -> {
                                navController.navigate("contact_us")
                            }

                            "Recommendations" -> {
                                navController.navigate("recommendations")
                            }

                            "Admissions" -> {
                                navController.navigate("admissions")
                            }

                            "Results" -> {
                                navController.navigate("results")
                            }

                            "Support & Information" -> {
                                navController.navigate("support_info")
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    if (item.title == "Recommendations") {
                        Text(text = item.title, fontSize = 12.5.sp, color = Color.White)
                    }
                    else {
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

private data class Item(
    val icon: ImageVector,
    val title: String,
)

private val items = mutableListOf(
    Item(
        icon = Icons.Filled.Newspaper,
        title = "News & Events"
    ),

    Item(
        icon = Icons.Filled.Money,
        title = "Fee Structure"
    ),

    Item(
        icon = Icons.Filled.AppRegistration,
        title = "Registration"
    ),

    Item(
        icon = Icons.Filled.Workspaces,
        title = "Courses Catalog"
    ),

    Item(
        icon = Icons.Filled.PersonSearch,
        title = "Admissions",
    ),

    Item(
        icon = Icons.Filled.Recommend,
        title = "Recommendations"
    ),

    Item(
        icon = Icons.AutoMirrored.Filled.Assignment,
        title = "Results"
    ),

    Item(
        icon = Icons.Filled.Description,
        title = "About Us"
    ),

    Item(
        icon = Icons.Filled.Call,
        title = "Contact Us"
    ),

    Item(
        icon = Icons.Filled.Info,
        title = "Support & Information"
    )
)