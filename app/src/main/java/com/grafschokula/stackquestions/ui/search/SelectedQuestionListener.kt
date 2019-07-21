package com.grafschokula.stackquestions.ui.search

import com.grafschokula.stackquestions.data.model.Question

/**
 *Created by Tim on 20, July, 2019
 **/
interface SelectedQuestionListener {

    fun onQuestionSelected(question: Question)

}