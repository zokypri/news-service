package se.implementer.newsservice.service

import java.util.logging.Logger
import org.springframework.stereotype.Service
import se.implementer.newsservice.client.SportClient
import se.implementer.newsservice.model.ExternalSportsNewsResponse

@Service
class SportService (val sportClient: SportClient){

    private val logger = Logger.getLogger(SportService::class.java.name)

    fun getSportNews(): ExternalSportsNewsResponse {
        return sportClient.fetchSportNews()
    }
}