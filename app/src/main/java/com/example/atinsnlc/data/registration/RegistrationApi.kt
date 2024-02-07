package com.example.atinsnlc.data.registration

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RegistrationApi {
    @Multipart
    @POST("student_registration/")
    suspend fun postData(
        @Part image: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("father_name") fatherName: RequestBody,
        @Part("cnic") cnic: RequestBody,
        @Part("contact_no") contactNo: RequestBody,
        @Part("course") course: RequestBody,
        @Part("dob") dob: RequestBody,
        @Part("gmail") gmail: RequestBody
    ): ResponseBody
}
