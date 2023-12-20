package mock

import se.implementer.newsservice.model.DomesticNews
import se.implementer.newsservice.model.PoliceEvent

fun createFirstPoliceEvent(): PoliceEvent {
    return PoliceEvent(
        id = 431488,
        summary = "summary 1",
        url = "url1",
        topic = "22 juni 03:49, Olaga intrång, Solna",
        type = "Olaga intrång",
    )
}
fun createSecondPoliceEvent() =
    PoliceEvent(
        id = 431463,
        summary = "Fullt utvecklad brand i Järva",
        url = "https://polisen.se/aktuellt/handelser/2023/juni/21/21-juni-1818-brand-solna/",
        topic = "21 juni 18:18, Brand, Solna",
        type = "Brand",
    )

fun createDomesticNews() =
    DomesticNews(
        policeEvents = listOf(createFirstPoliceEvent()),
    )