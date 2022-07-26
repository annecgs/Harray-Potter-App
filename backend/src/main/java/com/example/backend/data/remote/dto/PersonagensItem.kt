package com.example.harraypotterapp.data.remote.dto

data class PersonagensItem(
    val actor: String,
    val alive: Boolean,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wizard: Boolean,
    val yearOfBirth: String,
    var isFavorite: Boolean = false
)
