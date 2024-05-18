package com.example.atinsnlc.presentation

import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.atinsnlc.data.registration.StudentDataItem
import com.example.atinsnlc.viewModel.MainViewModel
import kotlinx.coroutines.launch
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
    var dob by remember {
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

    var nameError by remember {
        mutableStateOf(false)
    }

    var fatherNameError by remember {
        mutableStateOf(false)
    }

    var cnicError by remember {
        mutableStateOf(false)
    }

    var dobError by remember {
        mutableStateOf(false)
    }

    var contactNumberError by remember {
        mutableStateOf(false)
    }

    //Image picker logic
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var imageVisibility by remember {
        mutableStateOf(true)
    }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
        }
    }
    val scope = rememberCoroutineScope()

    val scrollableState = rememberScrollState()

    var isLoading by remember {
        mutableStateOf(false)
    }

    var responseMessage by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Registration")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color("#013220".toColorInt()),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollableState),
            contentAlignment = Alignment.Center
        )
        {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    singleLine = true,
                    onValueChange = {
                        name = it
                        nameError = name.contains(Regex("[^A-Za-z ]"))

                    },
//                    supportingText = {
//                        if (nameError) {
//                            Text(
//                                modifier = Modifier.fillMaxWidth(),
//                                text = "Name should not contain numbers or special characters",
//                                color = MaterialTheme.colorScheme.error
//                            )
//                        }
//                    },
                    trailingIcon = {
                        if (nameError)
                            Icon(
                                Icons.Filled.Error,
                                "error",
                                tint = MaterialTheme.colorScheme.error
                            )
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
                    isError = nameError,
                    modifier = Modifier
                        .padding(5.dp)
                )
                OutlinedTextField(
                    value = fatherName,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    onValueChange = {
                        fatherName = it
                        fatherNameError = fatherName.contains(Regex("[^A-Za-z ]"))
                    },
//                    supportingText = {
//                        if (fatherNameError) {
//                            Text(
//                                modifier = Modifier.fillMaxWidth(),
//                                text = "Name should not contain numbers or special characters",
//                                color = MaterialTheme.colorScheme.error
//                            )
//                        } else {
//                            Spacer(modifier = Modifier.height(0.dp))
//                        }
//                    },
                    trailingIcon = {
                        if (fatherNameError)
                            Icon(
                                Icons.Filled.Error,
                                "error",
                                tint = MaterialTheme.colorScheme.error
                            )
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
                    singleLine = true,
                    modifier = Modifier
                        .padding(5.dp)
                )

                OutlinedTextField(
                    value = cnic,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        cnic = it
                        cnicError = cnic.length > 13
                    },
                    trailingIcon = {
                        if (cnicError)
                            Icon(
                                Icons.Filled.Error,
                                "error",
                                tint = MaterialTheme.colorScheme.error
                            )
                    },
                    label = {
                        Text(text = "CNIC/B-Form")
                    },
                    placeholder = {
                        Text(text = "Enter CNIC Number")
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.CreditCard, contentDescription = "name")
                    },
                    modifier = Modifier
                        .padding(5.dp)
                )
                //Date field
                DatePicker(context = context) {
                    dob = it
                    dobError = dob.isEmpty()
                }

                OutlinedTextField(
                    value = contact,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        contact = it
                        contactNumberError = contact.length > 11
                    },
                    trailingIcon = {
                        if (contactNumberError)
                            Icon(
                                Icons.Filled.Error,
                                "error",
                                tint = MaterialTheme.colorScheme.error
                            )
                    },
                    label = {
                        Text(text = "Contact Number")
                    },
                    singleLine = true,
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {
                        gmail = it
                    },
                    label = {
                        Text(text = "Email (optional)")
                    },
                    placeholder = {
                        Text(text = "Enter your email address")
                    },
                    singleLine = true,
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
                        onDismissRequest = { isExpanded = false }
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
                            imageVisibility = false
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (imageVisibility) {
                        Text(
                            text = "Upload Your passport size picture with blue background",
                        )
                        Text(text = "Image should be 20 to 30 KB")
                    }
                    selectedImageUri?.let { uri ->
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
                        scope.launch {
                            val validation = validateFields(
                                name = name,
                                fatherName = fatherName,
                                cnic = cnic,
                                contact = contact,
                                gmail = gmail,
                                course = selectedCourse,
                                imageUri = selectedImageUri!!,
                                context = context
                            )
                            if (validation) {
                                isLoading = true
                                val studentData = StudentDataItem(
                                    name = name.trim(),
                                    father_name = fatherName.trim(),
                                    dob = dob.trim(),
                                    cnic = cnic.toLong(),
                                    phone_number = contact.toLong(),
                                    gmail = gmail.trim(),
                                    course = selectedCourse,
                                )
                                if (isInternetAvailable(context)) {
                                    val response = mainViewModel.postStudentData(
                                        studentData,
                                        selectedImageUri!!,
                                        context
                                    ) { response->
                                        responseMessage = response
                                    }
                                    if (response) {
                                        isLoading = false
                                        Toast.makeText(
                                            context,
                                            "Registered Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        navController.popBackStack()
                                        mainViewModel.throwRegistrationNotification(
                                            context,
                                            "ATIN NLC Registration",
                                            responseMessage
                                        )
                                    } else {
                                        isLoading = false
                                        Toast.makeText(
                                            context,
                                            "Registration Failed!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        mainViewModel.throwRegistrationNotification(
                                            context,
                                            "ATIN NLC Registration",
                                            "Registration failed!\n$responseMessage"
                                        )
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "No Internet connection, Try again",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isLoading = false
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#E65100".toColorInt()),
                        contentColor = Color.White
                    )
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
fun DatePicker(context: Context, onDateSelected:(String) -> Unit) {
    val currentCalendar = Calendar.getInstance()
    val selectedDate = remember { mutableStateOf(currentCalendar) }
    val dateText = remember { mutableStateOf("dd-MM-yyyy") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            val selectedCalendar = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, day)
            }
            if (isDateValid(selectedCalendar)) {
                selectedDate.value = selectedCalendar
                val formattedMonth = String.format("%02d", month + 1)
                val formattedDay = String.format("%02d", day)
                dateText.value = "$formattedDay-$formattedMonth-$year"
            } else {
                Toast.makeText(context, "Enter valid date", Toast.LENGTH_SHORT).show()
            }
        },
        currentCalendar.get(Calendar.YEAR),
        currentCalendar.get(Calendar.MONTH),
        currentCalendar.get(Calendar.DAY_OF_MONTH)
    )
    onDateSelected(dateText.value)
    OutlinedTextField(
        value = dateText.value,
        onValueChange = {},
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

private fun isDateValid(selectedDate: Calendar): Boolean {
    val currentDate = Calendar.getInstance()
    currentDate.add(Calendar.YEAR, -16) // Subtract 16 years from the current date
    return selectedDate <= currentDate
}

fun validateFields(
    name: String,
    fatherName: String,
    cnic: String,
    contact: String,
    gmail: String,
    course: String,
    imageUri: Uri,
    context: Context
): Boolean {
    var validation = true

    if (name.contains(Regex("[^A-Za-z ]")) || name.isEmpty()) {
        Toast.makeText(context, "Enter valid name", Toast.LENGTH_SHORT).show()
        validation = false
    } else if (fatherName.contains(Regex("[^A-Za-z ]")) || fatherName.isEmpty()) {
        Toast.makeText(context, "Enter valid Father's name", Toast.LENGTH_SHORT).show()
        validation = false
    } else if (cnic.length != 13) {
        Toast.makeText(context, "Enter valid CNIC or B-form", Toast.LENGTH_SHORT).show()
        validation = false
    } else if (contact.length != 11) {
        Toast.makeText(context, "Enter Valid Contact No", Toast.LENGTH_SHORT).show()
        validation = false
    } else if (course == "Select your desired course") {
        Toast.makeText(context, "Select course", Toast.LENGTH_SHORT).show()
        validation = false
    } else if (gmail.isNotEmpty() && !gmail.contains("@")) {
        Toast.makeText(
            context,
            "Enter valid email otherwise don't enter email",
            Toast.LENGTH_SHORT
        ).show()
        validation = false
    } else if (imageUri == null) {
        Toast.makeText(
            context,
            "Select a photo",
            Toast.LENGTH_SHORT
        ).show()
        validation = false
    }
    return validation
}

fun downloadPdf(context: Context, pdfFile: File) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(
        FileProvider.getUriForFile(
            context,
            "com.example.atinsnlc.provider",
            pdfFile
        ),
        "application/pdf"
    )
    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION)

    // Create a chooser to allow the user to pick an app for viewing the PDF
    val chooserIntent = Intent.createChooser(intent, "Open PDF with")

    try {
        context.startActivity(chooserIntent)
    } catch (e: ActivityNotFoundException) {
        // Handle the case where no app is available to open the PDF
        Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
    }
}

//Internet connection check method
private fun isInternetAvailable(context: Context): Boolean {
    var result: Boolean
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)
    result = when {
        actNw?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
        actNw?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
        actNw?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> true
        else -> false
    }
    return result
}
