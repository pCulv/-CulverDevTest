package dev.codephoenix.phildevtest

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val NEWS_API_KEY = "9334c90d6bf548ccb08cdeeca4fc61c5"

interface NewsApiClient {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines( @Query("country") country: String?, @Query("page") page: Int?): Response<HeadlinesResponse>


}

data class HeadlinesResponse(
    @SerializedName("status") var status: String,
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String,
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("articles") var articles: ArrayList<Headline>
)

data class Headline(
    @SerializedName("source") var source: Source,
    @SerializedName("author") var author: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlToImage") var urlToImage: String,
    @SerializedName("publishedAt") var publishedAt: String
)

data class Source(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("category") var category: String,
    @SerializedName("language") var language: String,
    @SerializedName("country") var country: String
)