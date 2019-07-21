package com.grafschokula.stackquestions.data.model

import com.google.gson.annotations.SerializedName

/**
 *Created by Tim on 19, July, 2019
 **/
data class Question(
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("owner") val user: User,
    @SerializedName("is_answered") val isAnswered: Boolean,
    @SerializedName("view_count") val viewCount: Int,
    @SerializedName("answer_count") val answerCount: Int,
    @SerializedName("score") val score: Int,
    @SerializedName("last_activity_date") val lastActivityDate: Int,
    @SerializedName("creation_date") val creationDate: Long,
    @SerializedName("last_edit_date") val lastEditDate: Int,
    @SerializedName("question_id") val questionId: Int,
    @SerializedName("link") val link: String,
    @SerializedName("title") val title: String,
    @SerializedName("closed_date") val closedDate: Int,
    @SerializedName("closed_reason") val closedReason: String?,
    @SerializedName("accepted_answer_id") val acceptedAnswerId: Int
)