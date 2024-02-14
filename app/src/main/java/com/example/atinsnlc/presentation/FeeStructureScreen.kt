package com.example.atinsnlc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R

@Composable
fun FeeStructureScreen(navController: NavController) {
    FeeScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeeScreenContent(navController: NavController) {

    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Fee Structure")
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back", modifier = Modifier.padding(5.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color("#33691E".toColorInt()),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(4.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.fee_structure),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )

            Text(
                text = "Tuition Fees",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(Color("#33691E".toColorInt()))
                    .padding(4.dp)
            )
        }
    }
}