package com.brayandev.yu_gi_oh_app.ui.views.archetype

import com.brayandev.yu_gi_oh_app.data.repository.models.ArchetypeModel

data class UiStateArchetype(
    val loading: Boolean = false,
    val list: List<ArchetypeModel> = emptyList(),
)
