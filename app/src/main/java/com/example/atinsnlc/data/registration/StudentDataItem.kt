package com.example.atinsnlc.data.registration

import okhttp3.MultipartBody
import retrofit2.http.Part


data class StudentDataItem(
    val cnic: Long,
    val contact_no: Long,
    val course: String,
    val dob: String,
    val father_name: String,
    val gmail: String,
    val image: MultipartBody.Part,
    val name: String
)