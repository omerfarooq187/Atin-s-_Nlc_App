package com.example.atinsnlc.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NavigationDrawer(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = { images.size }
    )
    var selectedItem by remember {
        mutableStateOf(navigationItems[0])
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.90f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .verticalScroll(scrollState)
                )
                {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon),
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
                LazyColumn {
                    items(navigationItems) { item ->
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
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
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
                    actions = {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notification",
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .clickable {
                                    navController.navigate("notifications")
                                }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color("#013220".toColorInt()),
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            },
        ) {
            val lazyListState = rememberLazyStaggeredGridState()
            var pagerVisibility by remember {
                mutableStateOf(true)
            }
            var currentScrollPosition by remember {
                mutableIntStateOf(0)
            }
            LaunchedEffect(lazyListState) {
                snapshotFlow { lazyListState.isScrollInProgress }
                    .collect {scrolling->
                        if (scrolling.and(lazyListState.firstVisibleItemScrollOffset>0)) {
                            pagerVisibility = false
                            currentScrollPosition = lazyListState.firstVisibleItemScrollOffset
                        }
                        if(currentScrollPosition > lazyListState.firstVisibleItemScrollOffset) {
                            pagerVisibility = true
                        }
                    }
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(Color("#636161".toColorInt()))
            ) {
                AnimatedVisibility(
                    visible = pagerVisibility,
                    enter = expandIn(),
                    exit = shrinkOut()
                ) {
                    HorizontalPager(
                        state = pagerState,
                        key = { key ->
                            images[key].image
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                    ) { page ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = images[page].image),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                alpha = 0.6f,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            if (images[page].text == "WELCOME") {
                                TypewriterText(texts = listOf("Welcome to Applied Technologies Institute Mandra"))
                            } else {
                                Text(
                                    text = images[page].text,
                                    fontSize = 22.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins)),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                Sections(navController,lazyListState)
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

data class ImageWithText(
    val image: Int,
    val text: String
)

val images = listOf(
    ImageWithText(
        R.drawable.image2,
        "WELCOME"
    ),
    ImageWithText(
        R.drawable.image1,
        "Applied Technologies Institute NLC"
    )
)


@Composable
fun TypewriterText(
    texts: List<String>,
) {
    var textIndex by remember {
        mutableIntStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        Unit,
    ) {
        while (textIndex < texts.size) {
            texts[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = texts[textIndex]
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(160)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
            break
        }
    }

    Text(
        text = textToDisplay,
        fontSize = 24.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(12.dp)
    )
}