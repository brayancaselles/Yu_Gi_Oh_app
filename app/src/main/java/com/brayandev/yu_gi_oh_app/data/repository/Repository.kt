package com.brayandev.yu_gi_oh_app.data.repository

import com.brayandev.yu_gi_oh_app.data.remote.RemoteDatasource
import com.brayandev.yu_gi_oh_app.data.repository.models.ArchetypeModel
import com.brayandev.yu_gi_oh_app.data.repository.models.CardModel
import com.brayandev.yu_gi_oh_app.data.repository.models.toModel
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDatasource: RemoteDatasource) {

    suspend fun requestArchetypes(): List<ArchetypeModel> {
        return remoteDatasource.getArchetypesFromApi().map { it.toModel() }
    }

    suspend fun getCardList(archetypeName: String): List<CardModel> {
        return remoteDatasource.getCardListFromApi(archetypeName).data.map { it.toModel() }
    }
}
