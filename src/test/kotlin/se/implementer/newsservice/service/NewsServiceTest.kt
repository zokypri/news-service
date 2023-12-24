package se.implementer.newsservice.service

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import se.implementer.newsservice.mock.createDomesticNews
import se.implementer.newsservice.mock.createFirstPoliceEvent
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import se.implementer.newsservice.client.PoliceClient

@ExtendWith(MockKExtension::class)
class NewsServiceTest {

    @MockK
    private lateinit var policeClient: PoliceClient

    private lateinit var newsService: NewsService

    @BeforeEach
    fun setup() {
        newsService = NewsService(policeClient)
    }

    @Test
    fun `fetch domestic news`()  {
        every { policeClient.fetchSolnaNews() } returns listOf(createFirstPoliceEvent())

        val actual = newsService.fetchDomesticNews()

        verify(exactly = 1) { policeClient.fetchSolnaNews() }
        assertEquals(createDomesticNews(), actual)
    }
}