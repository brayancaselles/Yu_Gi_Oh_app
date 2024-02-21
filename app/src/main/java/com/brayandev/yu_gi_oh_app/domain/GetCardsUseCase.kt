package com.brayandev.yu_gi_oh_app.domain

import com.brayandev.yu_gi_oh_app.data.repository.Repository
import com.brayandev.yu_gi_oh_app.data.repository.models.CardModel
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getCardList(archetype: String): List<CardModel> {
        return repository.getCardList(archetype)
    }
}
