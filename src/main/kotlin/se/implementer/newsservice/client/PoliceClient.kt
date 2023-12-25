package se.implementer.newsservice.client

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import se.implementer.newsservice.model.PoliceEvent

@Component
class PoliceClient(private val okHttpClient: OkHttpClient,
                   @Value("\${integration.police-service.url}")  val baseUrl: String) {

    private val cityOfSolna = "Solna"

    fun fetchSolnaNews(): List<PoliceEvent> {

        val uri = UriComponentsBuilder
            .fromUriString(baseUrl)
            .queryParam("city", cityOfSolna )
            .build()
            .toUriString()

        val request = Request.Builder()
            .url(uri)
            .build()

        val response = okHttpClient.newCall(request).execute().body?.string()

        val objectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return objectMapper.readValue(response, object : TypeReference<List<PoliceEvent>>() {})
    }
}