package com.example.atinsnlc.data.registration.repository

import com.example.atinsnlc.data.registration.RegistrationApi
import com.example.atinsnlc.data.registration.StudentDataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class DataRepository @Inject constructor(private val api: RegistrationApi){
    suspend fun postData(studentDataItem: StudentDataItem, image: MultipartBody.Part) {
        try {
            val nameRequestBody = studentDataItem.name.toRequestBody("text/plain".toMediaTypeOrNull())
            val fatherNameRequestBody = studentDataItem.father_name.toRequestBody("text/plain".toMediaTypeOrNull())
            val cnicRequestBody = studentDataItem.cnic.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val contactNoRequestBody = studentDataItem.contact_no.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val courseRequestBody = studentDataItem.course.toRequestBody("text/plain".toMediaTypeOrNull())
            val dobRequestBody = studentDataItem.dob.toRequestBody("text/plain".toMediaTypeOrNull())
            val gmailRequestBody = studentDataItem.gmail.toRequestBody("text/plain".toMediaTypeOrNull())

            val response = api.postData(
                image,
                nameRequestBody,
                fatherNameRequestBody,
                cnicRequestBody,
                contactNoRequestBody,
                courseRequestBody,
                dobRequestBody,
                gmailRequestBody
            )

            CoroutineScope(Dispatchers.IO).launch {
                response.toString()
            }
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
    }
}