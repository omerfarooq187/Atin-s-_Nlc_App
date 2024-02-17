package com.example.atinsnlc.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.atinsnlc.presentation.theme.ATINsNLCTheme
import com.example.atinsnlc.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            ATINsNLCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    requestAppPermissions(this)
                    App(mainViewModel)
                }
            }
        }

    }

}

@Composable
fun App(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(route = "news&events") {
            NewsEventsScreen(navController, mainViewModel)
        }
        composable(route = "registration") {
            RegistrationScreen(navController, mainViewModel)
        }
        composable(route = "fee_structure") {
            FeeStructureScreen(navController)
        }
        composable(route = "recommendations") {
            RecommendationsScreen(navController)
        }
        composable(route = "courses") {
            CoursesCatalogScreen(navController)
        }
        composable(route = "about_us") {
            AboutUsScreen(navController)
        }
        composable(route = "contact_us") {
            ContactUsScreen(navController)
        }
    }
}

fun requestAppPermissions(context: Context) {
    if (ActivityCompat.checkSelfPermission(context,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            255
        )
    }
}

