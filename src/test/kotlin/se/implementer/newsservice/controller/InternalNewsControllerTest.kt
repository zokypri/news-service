package se.implementer.newsservice.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import se.implementer.newsservice.configuration.SecurityConfig
import se.implementer.newsservice.model.DomesticNews
import se.implementer.newsservice.model.PoliceEvent
import se.implementer.newsservice.service.NewsService

@WebMvcTest(InternalNewsController::class)
@Import(SecurityConfig::class)
@AutoConfigureMockMvc
class InternalNewsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var newsService: NewsService

    @Test
    fun `Test fetching domestic news`()  {
        every { newsService.fetchDomesticNews() } returns DomesticNews(
            policeEvents = listOf(
                createFirstPoliceEvent(),
                createSecondPoliceEvent(),
            )
        )

        mockMvc.perform(get("/internal/v1/domestic-news"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(InternalNewsControllerTest::class.java.classLoader.getResource("__files/DomesticNews.json")
                ?.readText() ?: ""))
    }

    fun createFirstPoliceEvent(): PoliceEvent {
        return PoliceEvent(
            id = 431488,
            summary = "summary 1",
            url = "url1",
            topic = "22 juni 03:49, Olaga intrång, Solna",
            type = "Olaga intrång",
        )
    }
    fun createSecondPoliceEvent() =
        PoliceEvent(
            id = 431463,
            summary = "Fullt utvecklad brand i Järva",
            url = "https://polisen.se/aktuellt/handelser/2023/juni/21/21-juni-1818-brand-solna/",
            topic = "21 juni 18:18, Brand, Solna",
            type = "Brand",
        )

}