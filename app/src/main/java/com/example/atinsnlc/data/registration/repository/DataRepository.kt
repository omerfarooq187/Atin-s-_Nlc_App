package com.example.atinsnlc.data.registration.repository

import android.util.Log
import com.example.atinsnlc.data.registration.RegistrationApi
import com.example.atinsnlc.data.registration.StudentData
import com.example.atinsnlc.data.registration.StudentDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataRepository @Inject constructor(private val api: RegistrationApi){
    fun postData(studentDataItem: StudentDataItem) {
        try {
            val data = api.postData(
                studentDataItem.cnic,
                studentDataItem.contact_no,
                studentDataItem.course,
                studentDataItem.dob,
                studentDataItem.father_name,
                studentDataItem.gmail,
                studentDataItem.image,
                studentDataItem.name
            )
            data.enqueue(object: Callback<StudentData> {
                override fun onResponse(call: Call<StudentData>, response: Response<StudentData>) {
                    Log.d("Response",response.code().toString())
                }

                override fun onFailure(call: Call<StudentData>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
        catch (e:Exception) {
            e.printStackTrace()
        }
    }
}