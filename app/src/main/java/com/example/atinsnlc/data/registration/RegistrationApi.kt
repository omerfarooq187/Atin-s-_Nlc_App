package com.example.atinsnlc.data.registration

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RegistrationApi {
    @Multipart
    @POST("student_registration/")
    fun postData(
        @Part("cnic") cnic: Long,
        @Part("contact_no") contactNo: Long,
        @Part("course") course: String,
        @Part("dob") dob: String,
        @Part("father_name") fatherName: String,
        @Part("gmail") gmail: String,
        @Part image: MultipartBody.Part,
        @Part("name") name: String
    ): Call<StudentData>
}