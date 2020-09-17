package com.example.spacextracker.data.db.entity

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("parch")
    val patch: Patch,
    @SerializedName("reddit")
    val reddit: Reddit,
    @SerializedName("flickr")
    val flickr: Flickr,
    @SerializedName("presskit")
    val presskit: String? = null,
    @SerializedName("webcast")
    val webcast: String? = null,
    @SerializedName("youtube_id")
    val youtubeId: String? = null,
    @SerializedName("article")
    val article: String? = null,
    @SerializedName("wikipedia")
    val wikipedia: String? = null,
) {
    data class Patch(
        @SerializedName("small")
        val small: String? = null,
        @SerializedName("large")
        val large: String? = null
    )

    data class Reddit(
        @SerializedName("campaign")
        val campaign: String? = null,
        @SerializedName("launch")
        val launch: String? = null,
        @SerializedName("media")
        val media: String? = null,
        @SerializedName("recovery")
        val recovery: String? = null
    )

    data class Flickr(
        @SerializedName("small")
        val small: List<String>,
        @SerializedName("original")
        val original: List<String>
    )
}
