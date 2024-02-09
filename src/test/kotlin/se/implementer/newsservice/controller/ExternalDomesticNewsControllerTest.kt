package se.implementer.newsservice.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import se.implementer.newsservice.mock.createFirstPoliceEvent
import se.implementer.newsservice.mock.createSecondPoliceEvent
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
import se.implementer.newsservice.service.NewsService

@WebMvcTest(ExternalDomesticNewsController::class)
@Import(SecurityConfig::class)
@AutoConfigureMockMvc
class ExternalDomesticNewsControllerTest {

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

        mockMvc.perform(get("/external/v1/domestic-news"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(ExternalDomesticNewsControllerTest::class.java.classLoader.getResource("__files/DomesticNews.json")
                ?.readText() ?: ""))
    }

}