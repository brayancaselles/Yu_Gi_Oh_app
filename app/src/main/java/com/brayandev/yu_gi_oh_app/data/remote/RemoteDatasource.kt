package com.brayandev.yu_gi_oh_app.data.remote

import android.util.Log
import com.brayandev.yu_gi_oh_app.data.remote.models.ArchetypeResponse
import com.brayandev.yu_gi_oh_app.data.remote.models.CardsResult
import javax.inject.Inject

class RemoteDatasource @Inject constructor(private val apiService: ApiService) {

    suspend fun getArchetypesFromApi(): List<ArchetypeResponse> {
        val response = apiService.getArchetypesFromApi()
        return if (response.isNullOrEmpty()) {
            emptyList()
        } else {
            response
        }
    }

    suspend fun getCardListFromApi(archetypeName: String): CardsResult {
        Log.d("RemoteDatasource", archetypeName)
        val response = apiService.getListLetters(archetypeName)
        return if (response.data.isNullOrEmpty()) {
            CardsResult(emptyList())
        } else {
            response
        }
    }
}
