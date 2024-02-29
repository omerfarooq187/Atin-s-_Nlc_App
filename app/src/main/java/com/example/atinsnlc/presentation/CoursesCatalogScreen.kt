package com.example.atinsnlc.presentation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.atinsnlc.R
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@Composable
fun CoursesCatalogScreen(navController: NavController) {
    CoursesContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesContent(navController: NavController) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Courses Catalog")
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Text(
                stringResource(id = R.string.course_catalog_desc),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "1. DAE in IOTAT\n(Internet of Things and Automation Technology)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )
                Image(
                    painter = painterResource(R.drawable.iotat),
                    contentDescription = "IOTAT",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                )
            }
            Text(
                stringResource(id = R.string.IOTAT),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            Button(
                onClick = {
                    openPdfFile(context,"iot.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(containerColor = Color("#E65100".toColorInt()), contentColor = Color.White, disabledContainerColor = Color("#9E9E9E".toColorInt()), disabledContentColor = Color.White)
            ) {
                Text(
                    text = "IOTAT Course Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "2. DAE in MMAT\n(Mechanical, Manufacturing, and Automation Technology)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )
                Image(
                    painter = painterResource(id = R.drawable.mmat),
                    contentDescription = "MMAT",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Text(
                stringResource(id = R.string.MMAT),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )
            Button(
                onClick = {
                    openPdfFile(context,"mmat.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(containerColor = Color("#E65100".toColorInt()), contentColor = Color.White, disabledContainerColor = Color("#9E9E9E".toColorInt()), disabledContentColor = Color.White)
            ) {
                Text(
                    text = "MMAT Course Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "3. DAE in CIT\n(Computer Information Technology)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )
                
                Image(
                    painter = painterResource(id = R.drawable.cit),
                    contentDescription = "CIT",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Text(
                stringResource(id = R.string.CIT),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            Button(
                onClick = {
                    openPdfFile(context,"cit_subjects.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(containerColor = Color("#E65100".toColorInt()), contentColor = Color.White, disabledContainerColor = Color("#9E9E9E".toColorInt()), disabledContentColor = Color.White)
            ) {
                Text(
                    text = "CIT Course Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "4. DAE in CIVIL\n(Civil Engineering)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .weight(0.5f)
                )

                Image(
                    painter = painterResource(id = R.drawable.civil),
                    contentDescription = "CIVIL",
                    modifier = Modifier
                        .size(70.dp)
                )
            }

            Text(
                stringResource(id = R.string.CIVIL),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )

            Button(
                onClick = {
                    openPdfFile(context,"civil_subjects.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(containerColor = Color("#E65100".toColorInt()), contentColor = Color.White, disabledContainerColor = Color("#9E9E9E".toColorInt()), disabledContentColor = Color.White)
            ) {
                Text(
                    text = "Civil Course Subjects",
                    textAlign = TextAlign.Center
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .background(Color("#E65100".toColorInt()))
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "5. DAE in MECHANICAL\n(Mechanical Engineering)",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                            .weight(0.5f)
                )

                Image(
                    painter = painterResource(id = R.drawable.mech),
                    contentDescription = "MECH",
                    modifier = Modifier
                        .size(70.dp)
                )

            }

            Text(
                stringResource(id = R.string.MECH),
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(10.dp)
            )
            Button(
                onClick = {
                    openPdfFile(context,"mech_subjects.pdf")
                },
                elevation = ButtonDefaults.buttonElevation(hoveredElevation = 40.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(containerColor = Color("#E65100".toColorInt()), contentColor = Color.White, disabledContainerColor = Color("#9E9E9E".toColorInt()), disabledContentColor = Color.White)
            ) {
                Text(
                    text = "Mechanical Course Subjects",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

private fun openPdfFile(context: Context, fileName:String) {
    val assetManager: AssetManager = context.assets
    var `in`: InputStream? = null
    var out: OutputStream? = null
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