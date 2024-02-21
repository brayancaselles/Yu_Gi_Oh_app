package com.brayandev.yu_gi_oh_app.domain

import com.brayandev.yu_gi_oh_app.data.repository.Repository
import com.brayandev.yu_gi_oh_app.data.repository.models.ArchetypeModel
import javax.inject.Inject

class GetListArchetypeUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<ArchetypeModel> = repository.requestArchetypes()
}
