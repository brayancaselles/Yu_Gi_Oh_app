package com.brayandev.yu_gi_oh_app.data.remote

import com.brayandev.yu_gi_oh_app.data.remote.models.ArchetypeResponse
import com.brayandev.yu_gi_oh_app.data.remote.models.CardsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("archetypes.php")
    suspend fun getArchetypesFromApi(): List<ArchetypeResponse>

    @GET("cardinfo.php?archetype={archetype}")
    suspend fun getListLetters(@Query("archetype") archetypeName: String): CardsResult
}
