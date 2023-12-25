package se.implementer.newsservice.service

import org.springframework.stereotype.Service
import se.implementer.newsservice.client.PoliceClient
import se.implementer.newsservice.model.DomesticNews
import se.implementer.newsservice.model.PoliceEvent

@Service
class NewsService (val policeClient: PoliceClient){

    fun fetchDomesticNews(): DomesticNews {
        return DomesticNews(
            policeEvents = fetchPoliceNewsFromSolna()
        )
    }

    private fun fetchPoliceNewsFromSolna(): List<PoliceEvent> {
        return policeClient.fetchSolnaNews()
    }
}