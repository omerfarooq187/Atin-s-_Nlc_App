package com.example.atinsnlc.data.registration

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class StudentDataItem(
    val name: String,
    val father_name: String,
    val dob: String,
    val cnic: Long,
    val phone_number: Long,
    val gmail: String,
    val course: String,
)