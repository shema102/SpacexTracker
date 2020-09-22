package com.example.spacextracker.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.spacextracker.data.db.entity.converters.IdListConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "links")
@TypeConverters(IdListConverter::class)
data class Links(
    @SerializedName("patch")
    @Embedded(prefix = "patch_")
    val patch: Patch?,

    @SerializedName("reddit")
    @Embedded(prefix = "reddit_")
    val reddit: Reddit?,

    @SerializedName("flickr")
    @Embedded(prefix = "flickr_")
    val flickr: Flickr?,

    @SerializedName("presskit")
    val presskit: String?,
    @SerializedName("webcast")
    val webcast: String?,
    @SerializedName("youtube_id")
    val youtubeId: String?,
    @SerializedName("article")
    val article: String?,
    @SerializedName("wikipedia")
    val wikipedia: String?,
) {
    data class Patch(
        @SerializedName("small")
        val small: String?,
        @SerializedName("large")
        val large: String?
    )

    data class Reddit(
        @SerializedName("campaign")
        val campaign: String?,
        @SerializedName("launch")
        val launch: String?,
        @SerializedName("media")
        val media: String?,
        @SerializedName("recovery")
        val recovery: String?
    )

    data class Flickr(
        @SerializedName("small")
        val small: List<String>,
        @SerializedName("original")
        val original: List<String>
    )

    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}
