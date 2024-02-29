package com.example.atinsnlc.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R
import com.example.atinsnlc.viewModel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsEventsScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    ScreenContent(navController, mainViewModel)
    BackHandler {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ScreenContent(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    var newsString by remember { mutableStateOf("") }
    val news = mainViewModel.news.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = true) {
        delay(3000)
    scope.launch {
        val data = mainViewModel.extractData(news)
        newsString = data
    }

    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "News & Events")
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back", modifier = Modifier.padding(5.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color("#9E9E9E".toColorInt()),
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    if (newsString=="") {"Welcome To Applied Technologies Institute Mandra"}
                    else {newsString},
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier
                        .background(Color("#E65100".toColorInt()))
                        .padding(10.dp)
                        .fillMaxWidth()
                        .basicMarquee()
                )

                Image(
                    painter = painterResource(id = R.drawable.college_gate),
                    contentDescription = "Welcome"
                )
                Text(
                    text = "Announcements and Important Updates",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color("#E65100".toColorInt()))
                        .padding(10.dp)
                )
                val bullet = "\u2022"
                val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
                
                Text(
                    buildAnnotatedString {
                        newsItem.forEach {
                            withStyle(style = paragraphStyle) {
                                withStyle(style = SpanStyle(fontSize = 30.sp)) {
                                    append(bullet)
                                }
                                append("\t\t")
                                append(it)
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(6.dp),
                    fontSize = 18.sp,
                )
            }
        }
    }
}

private val newsItem = listOf(
    "We are thrilled to share the exciting news of the recent visit by the esteemed organization, Humanitarian Development and Research Foundation (HDRF), to ATIN Nlc. The event was marked by a significant interaction between HDRF representatives and our talented students.",
    "As part of their visit, HDRF organized a prize distribution ceremony, recognizing and appreciating the exemplary efforts and achievements of our students. The event showcased the dedication and hard work of our student community in various fields.",
)