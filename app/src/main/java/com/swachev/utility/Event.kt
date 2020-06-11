package com.swachev.utility

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

   //check for contents return
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}