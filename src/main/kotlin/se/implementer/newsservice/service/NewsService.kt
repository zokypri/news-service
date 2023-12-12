package se.implementer.newsservice.service

import org.springframework.stereotype.Service
import se.implementer.newsservice.client.PoliceClient
@Service
class NewsService (val policeClient: PoliceClient){

    fun fetchSolnaNews(): String {
        return policeClient.fetchSolnaNews()
    }
}