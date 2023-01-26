package com.example.mybookshelf.data.network

import com.example.mybookshelf.data.model.BookInfoListOfDataDto
import com.example.mybookshelf.data.model.BookDetailDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleapisService {

    //    поисковый запрос на книги
//    https://www.googleapis.com/books/v1/volumes?q=Онегин&AIzaSyBNNZyP7qFC2MI66J39J3BAxPScDtzAIPE
    //    https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:пушкин+intitle:онегин

    @GET("volumes")
    suspend fun getBookList(
        @Query(QUERY_Q) q: String,
        @Query(START_INDEX_PARAM) page: Int = START_INDEX,
        @Query(MAX_RESULTS_PARAM) itemsPerPage: Int = MAX_RESULTS,
        @Query(QUERY_PARAM_API_KEY) key: String = API_KEY
    ): BookInfoListOfDataDto

//    детальный вывод по книге
    //    https://www.googleapis.com/books/v1/volumes/volumeId
//    https://www.googleapis.com/books/v1/volumes/8Pr_kLFxciYC?AIzaSyBNNZyP7qFC2MI66J39J3BAxPScDtzAIPE
//    https://www.googleapis.com/books/v1/volumes/zyTCAlFPjgYC?AIzaSyBNNZyP7qFC2MI66J39J3BAxPScDtzAIPE

    @GET("volumes/{volumeId}")
    suspend fun getDetailBookInfo(
        @Path("volumeId") volumeId: String,
        @Query(QUERY_PARAM_API_KEY) key: String = API_KEY
    ): BookDetailDto

    companion object {
        private const val API_KEY = "AIzaSyBNNZyP7qFC2MI66J39J3BAxPScDtzAIPE"
        private const val QUERY_Q = "q"
        private const val QUERY_PARAM_API_KEY = "key"

        private const val MAX_RESULTS_PARAM = "maxResults"
        private const val MAX_RESULTS = 10
        private const val START_INDEX_PARAM = "startIndex"
        private const val START_INDEX = 0

        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
        fun create(): GoogleapisService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GoogleapisService::class.java)
        }
    }
}