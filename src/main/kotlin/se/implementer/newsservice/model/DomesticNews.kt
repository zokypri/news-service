package se.implementer.newsservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DomesticNews(
    val policeEvents: List<PoliceEvent>,
)

data class PoliceEvent (
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("type")
    val type: String,
    @JsonProperty("name")
    val topic: String,
)
