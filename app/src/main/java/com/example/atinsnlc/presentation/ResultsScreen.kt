package com.example.atinsnlc.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R

@Composable
fun ResultsScreen(navController: NavController) {
    ResultsScreenContents(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreenContents(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Results")
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
    ){
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Text(
                text = stringResource(id = R.string.result_desc),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(6.dp)
            )
        }
    }
}
