package com.example.atinsnlc.data.registration

import android.content.Context
import android.net.Uri
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import javax.inject.Inject

class DataRepository @Inject constructor(private val client: HttpClient){

    suspend fun postData(studentDataItem: StudentDataItem, imageUri: Uri, context: Context, onSuccess:(String)->Unit) :Boolean{
        val imageInput = context.contentResolver.openInputStream(imageUri)
        val formData = formData {
            if (imageInput != null) {
                append(
                    "image",
                    imageInput.readBytes(), // Read bytes from input stream
                    Headers.build {
                        append(HttpHeaders.ContentType, "images/*") // Mime type required
                        append(HttpHeaders.ContentDisposition, "filename=image.jpg") // Filename in content disposition required
                    }
                )
            }
            append("name", studentDataItem.name)
            append("father_name", studentDataItem.father_name)
            append("dob", studentDataItem.dob)
            append("cnic", studentDataItem.cnic.toString())
            append("phone_number", studentDataItem.phone_number.toString())
            append("gmail", studentDataItem.gmail)
            append("course", studentDataItem.course)
        }
        try {
            val response = client.submitFormWithBinaryData(
                url = HttpRoutes.POST_STUDENT_DATA,
                formData = formData
            )
            if (response.status.isSuccess()) {
                Log.d("DataRepository", response.body())
                onSuccess(response.body())
                return true
            } else {
                Log.e("DataRepository", response.body())
                onSuccess(response.body())
                return false
            }
        } catch (e:Exception) {
            e.printStackTrace()
            return false
        }
    }

}
