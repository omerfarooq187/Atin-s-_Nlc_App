package com.example.atinsnlc.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController

@Composable
fun ContactUsScreen(navController: NavController) {
    ContactScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreenContent(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contact Us")
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
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(6.dp)) {
                Text(
                    buildAnnotatedString {
                        append("Have questions, concerns, or just want to reach out? We're here to help! Feel free to connect with us using the following contact information:")
                        append("\n\n")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("College Address")
                        }
                        append("\n")
                        append("Applied Technologies Institutes NLC")
                        append("\n")
                        append("Grand Trunk Road Mandra, Rawalpindi")
                        append("\n")
                        append("Punjab, Pakistan")
                    },
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}