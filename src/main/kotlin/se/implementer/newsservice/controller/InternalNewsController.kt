package se.implementer.newsservice.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("internal/v1/news")
class InternalNewsController {

  @Operation(
    summary = "Gets news.",
    responses = [
      ApiResponse(
        responseCode = "200",
        description = "Gets news",
      ),
    ],
  )
  @GetMapping
  fun getNews(): String {
    return "12344"
  }

}
