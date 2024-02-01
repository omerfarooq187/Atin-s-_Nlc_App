package com.example.atinsnlc.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController

@Composable
fun RegistrationScreen(navController: NavHostController) {
    RegistrationContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationContent(navController: NavHostController) {
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
        Box(modifier = Modifier.padding(padding)) {
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

                Button(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(height = 50.dp, width = 140.dp),
                    onClick = {},
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