package se.implementer.newsservice.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.util.logging.Logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.implementer.newsservice.model.ExternalSportsNewsResponse
import se.implementer.newsservice.service.SportService

@RestController
@RequestMapping("external/v1/sport-news")
class ExternalSportNewsController (val sportService: SportService) {

    private val logger = Logger.getLogger(ExternalSportNewsController::class.java.name)

    @Operation(
        summary = "Fetches sport news.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Fetches sport news",
            ),
        ],
    )
    @GetMapping
    fun getSportNews(): ExternalSportsNewsResponse {
        return sportService.getSportNews()
    }
}
