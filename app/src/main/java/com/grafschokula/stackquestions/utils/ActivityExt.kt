package com.grafschokula.stackquestions.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 *Created by Tim on 19, July, 2019
 **/

fun AppCompatActivity.replaceFragmentInActivity(frameId: Int, fragment: Fragment) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

fun AppCompatActivity.addFragmentToActivity(frameId: Int, fragment: Fragment) {
    supportFragmentManager.transact {
        add(frameId, fragment)
    }
}


private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply(action).commit()
}