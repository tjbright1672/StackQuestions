package com.grafschokula.stackquestions.data.model

import com.google.gson.annotations.SerializedName

/**
 *Created by Tim on 19, July, 2019
 **/
data class User(
    @SerializedName("reputation") val reputation: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("user_type") val userType: String,
    @SerializedName("accept_rate") val acceptRate: Int,
    @SerializedName("profile_image") val profileImage: String,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("link") val link: String
)