package com.brayandev.yu_gi_oh_app.data.repository.models

import com.brayandev.yu_gi_oh_app.data.remote.models.CardImage
import com.brayandev.yu_gi_oh_app.data.remote.models.CardResult

data class CardModel(
    val id: Int,
    val name: String,
    val type: String,
    val frameType: String,
    val description: String,
    val attack: Int,
    val defence: Int,
    val level: Int,
    val race: String,
    val archetype: String?,
    val imageUrl: String,
    val listCardImages: List<CardImage>,
)

fun CardResult.toModel() = CardModel(
    id = id,
    name = name,
    type = type,
    frameType = frameType,
    description = desc,
    attack = atk,
    defence = def,
    level = level,
    race = race,
    archetype = archetype,
    imageUrl = ygoprodeck_url,
    listCardImages = card_images,
)
