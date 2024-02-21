package com.brayandev.yu_gi_oh_app.data.remote.models

data class CardResult(
    val id: Int,
    val name: String,
    val type: String,
    val frameType: String,
    val desc: String,
    val atk: Int,
    val def: Int,
    val level: Int,
    val race: String,
    val attribute: String,
    val archetype: String?,
    val ygoprodeck_url: String,
    val card_images: List<CardImage>,
)
