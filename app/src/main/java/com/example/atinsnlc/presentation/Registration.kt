package com.example.atinsnlc.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.atinsnlc.data.registration.StudentDataItem
import com.example.atinsnlc.viewModel.MainViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.Calendar

@Composable
fun RegistrationScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    RegistrationContent(navController, mainViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationContent(navController: NavHostController, mainViewModel: MainViewModel) {
    val menuItems = listOf(
        "Computer Information Technology",
        "Mechanical Technology",
        "Civil Technology",
        "Internet Of Things",
        "MMAT"
    )
    var name by remember {
        mutableStateOf("")
    }
    var fatherName by remember {
        mutableStateOf("")
    }
    val dob by remember {
        mutableStateOf("")
    }
    var cnic by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }
    var gmail by remember {
        mutableStateOf("")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedCourse by remember {
        mutableStateOf("Select your desired course")
    }

    //Image picker logic
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var imageVisibilty by remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {uri ->
        uri?.let {
            selectedImageUri = it
        }
    }

    val scrollableState = rememberScrollState()

        Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Registration")
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
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .verticalScroll(scrollableState)
        )
        {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = "Welcome",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,

                )
                Text(
                    text = "Applied Technologies Institute",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = name,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                    singleLine = true,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(text = "Name")
                    },
                    placeholder = {
                        Text(text = "Enter Your Name")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "name")
                    },
                    modifier = Modifier
                        .padding(5.dp)
                )
                OutlinedTextField(
                    value = fatherName,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                    onValueChange = {
                        fatherName = it
                    },
                    label = {
                        Text(text = "Father's Name")
                    },
                    placeholder = {
                        Text(text = "Enter Father's Name")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "name")
                    },
                    modifier = Modifier
                        .padding(5.dp)
                )

                OutlinedTextField(
                    value = cnic,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    onValueChange = {
                        cnic = it
                    },
                    label = {
                        Text(text = "CNIC/B-Form")
                    },
                    placeholder = {
                        Text(text = "Enter CNIC Number")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.CreditCard, contentDescription = "name")
                    },
                    modifier = Modifier
                        .padding(5.dp)
                )
                //Date field
                DatePicker(context = context)
                
                OutlinedTextField(
                    value = contact,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    onValueChange = {
                        contact = it
                    },
                    label = {
                        Text(text = "Contact Number")
                    },
                    placeholder = {
                        Text(text = "Enter Contact Number")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Phone, contentDescription = "name")
                    },
                    modifier = Modifier
                        .padding(5.dp)
                )
                OutlinedTextField(
                    value = gmail,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                    onValueChange = {
                        gmail = it
                    },
                    label = {
                        Text(text = "Gmail")
                    },
                    placeholder = {
                        Text(text = "Enter Gmail")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Email, contentDescription = "name")
                    },
                    modifier = Modifier
                        .padding(5.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = {
                        isExpanded = it
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    TextField(
                        value = selectedCourse,
                        readOnly = true,
                        onValueChange = {},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = {isExpanded = false}
                    ) {
                        menuItems.forEach {
                            DropdownMenuItem(
                                text = {
                                       Text(text = it)
                                },
                                onClick = {
                                    isExpanded = false
                                    selectedCourse = it
                                }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(horizontal = 22.dp, vertical = 16.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(120.dp)
                        .clickable {
                            launcher.launch("image/*")
                            imageVisibilty = false
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (imageVisibilty) {
                        Text(
                            text = "Upload Your passport size picture with blue background",
                        )
                        Text(text = "Image should be 20 to 30 KB")
                    }
                    selectedImageUri?.let {uri ->
                       val painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(uri)
                                .allowHardware(false)
                                .build()
                        )

                        Image(
                            painter = painter,
                            contentDescription = "image",
                            modifier = Modifier
                                .size(100.dp)
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(height = 50.dp, width = 140.dp),
                    onClick = {
                        val studentDataItem = StudentDataItem(
                            name = name,
                            father_name = fatherName,
                            dob = dob,
                            cnic = cnic.toLong(),
                            contact_no = contact.toLong(),
                            gmail = gmail,
                            course = selectedCourse,
                            image = uploadImage(selectedImageUri)
                        )
                        mainViewModel.postStudentData(studentDataItem)
                        Log.d("Register","Button Clicked....")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color("#33691E".toColorInt()), contentColor = Color.White)
                    ) {
                    Text(
                        text = "Register",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DatePicker(context: Context) {
    val day: Int
    val month: Int
    val year: Int

    val calendar = Calendar.getInstance()
    day = calendar.get(Calendar.DAY_OF_MONTH)
    month = calendar.get(Calendar.MONTH)
    year = calendar.get(Calendar.YEAR)

    var date by remember {
        mutableStateOf("yyyy-MM-dd")
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, pickedYear, pickedMonth, pickedDay ->
            val formattedMonth = String.format("%02d", pickedMonth + 1) // Add leading zero
            val formattedDay = String.format("%02d", pickedDay) // Add leading zero
            date = "$pickedYear-$formattedMonth-$formattedDay"
        },
        year,
        month,
        day
    )

    OutlinedTextField(
        value = date,
        onValueChange = { date = it },
        readOnly = true,
        label = {
            Text(text = "Date of birth")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.DateRange, contentDescription = "dob")
        },
        trailingIcon = {
            IconButton(onClick = { datePickerDialog.show() }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "date")
            }
        },
        modifier = Modifier
            .padding(5.dp)
    )
}

fun uploadImage(uri: Uri?): MultipartBody.Part {
    uri?.let {
        val file = File(uri.path) // Use a function to get the real path from the content URI
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
    // Handle the case where uri is null (optional)
    throw IllegalArgumentException("Uri is null")
}