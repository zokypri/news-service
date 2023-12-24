package se.implementer.newsservice.client

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.jupiter.api.Test
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.ActiveProfiles
import se.implementer.newsservice.mock.createFirstPoliceEvent

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PoliceClientTest {

    private lateinit var mockWebServer: MockWebServer

    @Autowired
    private lateinit var policeClient: PoliceClient

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch domestic news`()  {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(ClassPathResource("__files/SlimmedPoliceEventSolna.json").file.readText())

        mockWebServer.enqueue(mockResponse)

        val actualResult = policeClient.fetchSolnaNews()

        val expectedResult = listOf(createFirstPoliceEvent())

        assertEquals(expectedResult, actualResult)
    }
}