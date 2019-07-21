package com.grafschokula.stackquestions.data.repository

import com.grafschokula.stackquestions.data.model.Question

/**
 *Created by Tim on 19, July, 2019
 **/
interface QuestionsRepository {
    fun getQuestionsByTag(query: String, pageSize: Int): Listing<Question>
}