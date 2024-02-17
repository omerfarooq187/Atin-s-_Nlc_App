package com.example.atinsnlc.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


@Composable
fun FeeStructureScreen(navController: NavController) {
    FeeScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeeScreenContent(navController: NavController) {

    val context = LocalContext.current

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
                    containerColor = Color("#9E9E9E".toColorInt()),
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.fee_structure),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(200.dp))
            Button(
                onClick = {
                    openPdfFile(context)
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f)
                    .height(80.dp),
                colors = ButtonColors(containerColor = Color("#E65100".toColorInt()), contentColor = Color.White, disabledContainerColor = Color("#9E9E9E".toColorInt()), disabledContentColor = Color.White)
            ) {
                Text(
                    text = "Click Here to download fee details",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
private fun openPdfFile(context: Context) {
    val assetManager: AssetManager = context.assets
    var `in`: InputStream? = null
    var out: OutputStream? = null
    val fileName = "fee_details.pdf"
    val file: File = File(context.filesDir, fileName)

    try {
        `in` = assetManager.open(fileName)
        out = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        copyFile(`in`, out)
    } catch (e: Exception) {
        Log.e("tag", e.message ?: "Unknown error")
    } finally {
        `in`?.close()
        out?.flush()
        out?.close()
    }

    val fileUri = FileProvider.getUriForFile(
        context,
        "com.example.atinsnlc.provider",
        file
    )

    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(fileUri, "application/pdf")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
    }
}

@Throws(IOException::class)
private fun copyFile(`in`: InputStream, out: OutputStream) {
    val buffer = ByteArray(1024)
    var read: Int
    while (`in`.read(buffer).also { read = it } != -1) {
        out.write(buffer, 0, read)
    }
}