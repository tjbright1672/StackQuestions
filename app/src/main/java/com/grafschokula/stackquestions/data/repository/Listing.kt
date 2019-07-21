package com.grafschokula.stackquestions.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 *Created by Tim on 19, July, 2019
 **/
data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refreshState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)