package com.grafschokula.stackquestions.data.api

import com.google.gson.annotations.SerializedName
import com.grafschokula.stackquestions.data.model.Question

/**
 *Created by Tim on 19, July, 2019
 **/
data class StackResponse (
    @SerializedName("items") val questions: List<Question>,
    @SerializedName("has_more") val hasMore: Boolean,
    @SerializedName("quota_max") val quotaMax: Int,
    @SerializedName("quota_remaining") val quotaRemaining: Int
)