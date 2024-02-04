package com.example.atinsnlc.viewModel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atinsnlc.data.news_events.repository.NewsRepository
import com.example.atinsnlc.data.news_events.room.NewsEntity
import com.example.atinsnlc.data.registration.StudentDataItem
import com.example.atinsnlc.data.registration.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsRepository: NewsRepository, private val dataRepository: DataRepository): ViewModel() {
    lateinit var news:Flow<List<NewsEntity>>
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

    fun postStudentData(studentDataItem: StudentDataItem) {
        dataRepository.postData(studentDataItem)
    }
}
