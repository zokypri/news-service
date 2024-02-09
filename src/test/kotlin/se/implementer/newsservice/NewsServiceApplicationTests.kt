package se.implementer.newsservice

import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
class NewsServiceApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun `fetch domestic news`()  {
		stubFor(get(urlEqualTo("/police-service/v1/police/news?city=Solna"))
			.willReturn(
				aResponse()
					.withStatus(HttpStatus.OK.value())
					.withBodyFile("OriginalPoliceEvents.json")
			)
		)

		mockMvc.perform(MockMvcRequestBuilders.get("/external/v1/domestic-news"))
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(
				MockMvcResultMatchers.content().json(
					NewsServiceApplicationTests::class.java.classLoader.getResource("__files/DomesticNews.json")
				?.readText() ?: ""))
	}
}
