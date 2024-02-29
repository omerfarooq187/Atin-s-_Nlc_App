package com.example.atinsnlc.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material.icons.filled.Recommend
import androidx.compose.material.icons.filled.Workspaces
import androidx.compose.material.icons.outlined.AppRegistration
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material.icons.outlined.Workspaces
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var selectedItem by remember {
        mutableStateOf(navigationItems[0])
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.wrapContentSize()
                )
                {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .verticalScroll(scrollState)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nlc_logo),
                            contentDescription = "NLC",
                            modifier = Modifier
                                .width(200.dp)
                                .padding(top = 20.dp)

                        )

                        Text(
                            text = "Applied Technologies Institutes",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.padding(8.dp))
                        HorizontalDivider()
                        Spacer(modifier = Modifier.padding(8.dp))
                    }

                }
                navigationItems.forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Text(text = item.title)
                        },
                        colors = NavigationDrawerItemDefaults
                            .colors(
                                selectedContainerColor = Color("#E65100".toColorInt()),
                                selectedTextColor = Color.White,
                                selectedIconColor = Color.White
                            ),
                        selected = item == selectedItem,
                        onClick = {
                            selectedItem = item
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
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (item == selectedItem) {
                                    item.selectedIcon
                                } else {
                                    item.unSelectedIcon
                                },
                                contentDescription = item.title
                            )
                        },

                        badge = {
                            if (item.badgeCounts != null && item.badgeCounts != 0) {
                                Text(text = item.badgeCounts.toString())
                            } else {
                                Text(text = "")
                            }
                        }
                        )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "ATINs NLC")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color("#9E9E9E".toColorInt()),
                        navigationIconContentColor = Color.Black,
                        titleContentColor = Color.Black
                    )
                )
            },
        ) {
            Box(modifier = Modifier.padding(it)) {
                Sections(navController)
            }
        }
    }

}

data class NavigationItem(
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val title: String,
    val badgeCounts: Int? = null
)

val navigationItems = listOf(
    NavigationItem(
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        title = "Home"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Newspaper,
        unSelectedIcon = Icons.Outlined.Newspaper,
        title = "News & Events"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Money,
        unSelectedIcon = Icons.Outlined.Money,
        title = "Fee Structure"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.AppRegistration,
        unSelectedIcon = Icons.Outlined.AppRegistration,
        title = "Registration"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Workspaces,
        unSelectedIcon = Icons.Outlined.Workspaces,
        title = "Courses Catalog"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.PersonSearch,
        unSelectedIcon = Icons.Filled.PersonSearch,
        title = "Admissions",
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Recommend,
        unSelectedIcon = Icons.Outlined.Recommend,
        title = "Recommendations"
    ),

    NavigationItem(
        selectedIcon = Icons.AutoMirrored.Filled.Assignment,
        unSelectedIcon = Icons.AutoMirrored.Outlined.Assignment,
        title = "Results"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Description,
        unSelectedIcon = Icons.Outlined.Description,
        title = "About Us"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Call,
        unSelectedIcon = Icons.Outlined.Call,
        title = "Contact Us"
    ),

    NavigationItem(
        selectedIcon = Icons.Filled.Info,
        unSelectedIcon = Icons.Outlined.Info,
        title = "Support & Information"
    )
)