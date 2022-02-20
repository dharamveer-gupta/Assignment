package com.dharamveer.assignment.model


/**
 * Created by Dharamveer Gupta on 20-February-2022 2:56 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
data class StateWithCity(
    val state: String = "",
    val city: String = "",
    var isChecked: Boolean = false
) {
    override fun toString(): String {
        return "$city, $state"
    }
}
