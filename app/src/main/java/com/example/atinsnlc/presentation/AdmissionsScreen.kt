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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun AdmissionScreen(navController: NavController) {
    AdmissionScreenContents(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdmissionScreenContents(navController:NavController) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Admissions")
            },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back", modifier = Modifier.padding(5.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color("#9E9E9E".toColorInt()),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ){
        val bullet = "\u2022"
        val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "1. Admission Process Overview",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Learn about the admission process for DAE courses, including CIT, Mechanical, Civil, IOTAT, and MMAT.")
                    }
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Important dates and deadlines for the current admission cycle.")
                    }
                },
                modifier = Modifier
                    .padding(6.dp)
            )

            Text(
                text = "2. Eligibility Criteria",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Matric(Science) and FSC(Science) qualifications required for DAE courses.")
                    }
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Any specific entrance exams or additional criteria for CIT, Mechanical, Civil, IOTAT, and MMAT.")
                    }
                },
                modifier = Modifier
                    .padding(6.dp)
            )

            Text(
                text = "3. Application Form",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Register yourself from register section if you did not register already.")
                    }
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Provide personal information, academic history, and select the preferred DAE course.")
                    }
                },
                modifier = Modifier
                    .padding(6.dp)
            )

            Text(
                text = "4. Documentation Submission",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("List of required documents for the DAE course application.")
                    }
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Instructions on how to submit/upload documents securely through the app.")
                    }
                },
                modifier = Modifier
                    .padding(6.dp)
            )

            Text(
                text = "5. Fee Structure and Payment",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            )

            Text(
                buildAnnotatedString {
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("Check our fee structure by navigating 'Fee Structure' section.")
                    }
                    withStyle(style = paragraphStyle) {
                        withStyle(style = SpanStyle(fontSize = 30.sp)) {
                            append(bullet)
                            append("\t\t")
                        }
                        append("You can pay your fee online or by bank.")
                    }
                },
                modifier = Modifier
                    .padding(6.dp)
            )
        }
    }
}
