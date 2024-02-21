package com.brayandev.yu_gi_oh_app.data.repository.models

import com.brayandev.yu_gi_oh_app.data.remote.models.ArchetypeResponse

data class ArchetypeModel(val archetypeName: String)

fun ArchetypeResponse.toModel() = ArchetypeModel(archetype_name)
