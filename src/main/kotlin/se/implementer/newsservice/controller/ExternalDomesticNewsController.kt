package se.implementer.newsservice.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.util.logging.Logger
import org.springframework.web.bind.annotation.GetMapping
import se.implementer.newsservice.model.DomesticNews
import se.implementer.newsservice.service.NewsService

@RestController
@RequestMapping("external/v1/domestic-news")
class ExternalDomesticNewsController(val newsService: NewsService) {

  private val logger = Logger.getLogger(ExternalDomesticNewsController::class.java.name)

  @Operation(
    summary = "Fetches domestic news.",
    responses = [
      ApiResponse(
        responseCode = "200",
        description = "Fetches domestic news",
      ),
    ],
  )
  @GetMapping
  fun fetchDomesticNews(): DomesticNews {
    return newsService.fetchDomesticNews()
  }

}
