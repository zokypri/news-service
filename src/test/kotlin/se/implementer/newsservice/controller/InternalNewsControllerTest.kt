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
            policeEvents = listOf()
        )

        mockMvc.perform(get("/internal/v1/domestic-news"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

}