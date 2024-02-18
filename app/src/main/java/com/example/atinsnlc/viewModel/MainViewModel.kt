package com.example.atinsnlc.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atinsnlc.data.news_events.repository.NewsRepository
import com.example.atinsnlc.data.news_events.room.NewsEntity
import com.example.atinsnlc.data.registration.NotificationUtils
import com.example.atinsnlc.data.registration.StudentDataItem
import com.example.atinsnlc.data.registration.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dataRepository: DataRepository,
    private val notificationUtils: NotificationUtils
) : ViewModel() {
    var news: Flow<List<NewsEntity>> = emptyFlow()

    init {
        viewModelScope.launch {
            newsRepository.getNews()
            news = newsRepository.getData()
        }

    }

    fun extractData(data: State<List<NewsEntity>>): String {
        val resultBuilder = StringBuilder()

        data.value.forEach {
            resultBuilder.append("${it.news} ${it.date} ")
        }

        return resultBuilder.toString().trim()
    }

    suspend fun postStudentData(
        studentDataItem: StudentDataItem,
        image: MultipartBody.Part,
    ): Int {
        return dataRepository.postData(studentDataItem, image)
    }

    suspend fun downloadForm(id: Int): ResponseBody {
        return dataRepository.getPdf(id)
    }
    fun savePdf(responseBody: ResponseBody?, fileName: String, context: Context) :File?{
        return try {
            val file = File(context.getExternalFilesDir(null), fileName)
            val inputStream: InputStream? = responseBody?.byteStream()
            val outputStream = FileOutputStream(file)
            inputStream.use { input ->
                outputStream.use { output ->
                    input?.copyTo(output)
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun throwRegistrationNotification(context: Context, title:String, content:String) {
        notificationUtils.createNotification(context,title,content)
    }
}
