package com.example.atinsnlc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R

@Composable
fun RecommendationsScreen(navController: NavController) {
    RecommendationsContent(navController)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RecommendationsContent(navController:NavController) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Recommendations")
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier.padding(5.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color("#013220".toColorInt()),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(id = R.string.recommendation_description),
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
            Text(
                text = "1. Civil Engineering",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.civil_recommendation),
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
            Text(
                text = "2. Mechanical Engineering",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.mech_recommendations),
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
            Text(
                text = "3. CIT (Computer Information Technology)",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.cit_recommendations),
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
            Text(
                text = "4. IOTAT\n(Internet of Things and Automation Technology)",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.iot_recommendations),
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
            Text(
                text = "5. MMAT\n(Mechanical Manufacturing and Automation Technology)",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.mmat_recommendations),
                textAlign = TextAlign.Justify,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(6.dp)
            )
        }
    }
}