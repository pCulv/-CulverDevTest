package dev.codephoenix.phildevtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class NewsViewModel : ViewModel() {

    val headlinesLiveData = MutableLiveData<ArrayList<Headline>>()

    fun getHeadlines() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getApiClient()?.getTopHeadlines("us", 1)
                withContext(Dispatchers.Main) {
                    response?.let {
                        if (it.isSuccessful) {
                            val headlines = it.body()?.articles
                            headlinesLiveData.value = headlines
                        }
                    }

                }
            } catch (e: Exception){
                //Todo: log exception timber
                e.stackTrace
            }
        }
    }

    fun getApiClient(): NewsApiClient? = RetrofitBuilder().createService(NewsApiClient::class.java, NEWS_API_KEY)
}
