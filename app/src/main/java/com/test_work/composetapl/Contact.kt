package com.test_work.composetapl

data class Contact(
    val name: String,
    val surname: String? = null,
    val familyName: String,
    val imageRes: Int? = null,
    val isFavorite: Boolean,
    val phone: String,
    val address: String,
    val email: String? = null
)