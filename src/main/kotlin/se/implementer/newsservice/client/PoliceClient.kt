package se.implementer.newsservice.client

import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class PoliceClient(private val okHttpClient: OkHttpClient,
                   @Value("\${integration.police-service.url}")  val baseUrl: String) {

    private val cityOfSolna = "Solna"

    fun fetchSolnaNews(): String {

        val uri = UriComponentsBuilder
            .fromUriString(baseUrl)
            .queryParam("city", cityOfSolna )
            .build()
            .toUriString()

        val request = Request.Builder()
            .url(uri)
            .build()

        val response = okHttpClient.newCall(request).execute().body?.string()


        return response!!
    }
}