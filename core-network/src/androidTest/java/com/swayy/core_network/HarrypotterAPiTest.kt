package com.swayy.core_network

import android.content.Context
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class HarrypotterAPiTest {
    private var context: Context? = null
    private var mockWebServer = MockWebServer()
    private lateinit var harryPotterApi: HarryPotterApi

    @Before
    fun Setup() {
        mockWebServer.start()

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        context = InstrumentationRegistry.getInstrumentation().targetContext

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        harryPotterApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HarryPotterApi::class.java)

        val jsonStream: InputStream = context!!.resources.assets.open("characters.json")
        val jsonBytes: ByteArray = jsonStream.readBytes()

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(String(jsonBytes))
        mockWebServer.enqueue(response)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
    @Test
    fun test_response() = runBlocking {
        val data = harryPotterApi.getCharacters().take(1).filter { it.id == "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8" }
        val result = data.first()
        val name = result.name
        ViewMatchers.assertThat(name,CoreMatchers.equalTo("Harry Potter"))
    }
}