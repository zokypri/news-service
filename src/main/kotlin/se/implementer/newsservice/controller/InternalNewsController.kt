package se.implementer.newsservice.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import se.implementer.newsservice.model.DomesticNews
import se.implementer.newsservice.service.NewsService

@RestController
@RequestMapping("internal/v1/domestic-news")
class InternalNewsController(val newsService: NewsService) {

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
    return  newsService.fetchDomesticNews()
  }

}
