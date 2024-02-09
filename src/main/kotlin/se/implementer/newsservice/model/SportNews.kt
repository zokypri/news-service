package se.implementer.newsservice.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ExternalSportsNewsResponse(
    val muayThaiFighters: List<FighterOverview> = emptyList(),
)

data class FighterOverview(
    @JsonProperty("fighterId")
    val fighterId : Long,
    @JsonProperty("firstName")
    val firstName: String,
    @JsonProperty("lastName")
    val lastName: String,
    @JsonProperty("showName")
    val showName: String,
    @JsonProperty("gender")
    val gender: String,
    @JsonProperty("totalFights")
    val totalFights: Int,
    @JsonProperty("wins")
    val wins: Int,
    @JsonProperty("losses")
    val losses: Int,
    @JsonProperty("countryOrigin")
    val countryOrigin: String,
    @JsonProperty("age")
    val age: Int,
    @JsonProperty("status")
    val fighterStatus: String,
)
