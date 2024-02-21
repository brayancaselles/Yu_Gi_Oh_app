package com.brayandev.yu_gi_oh_app.ui.views.archetype

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brayandev.yu_gi_oh_app.data.repository.models.ArchetypeModel
import com.brayandev.yu_gi_oh_app.domain.GetListArchetypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchetypeViewModel @Inject constructor(useCase: GetListArchetypeUseCase) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _list = MutableLiveData<List<ArchetypeModel>>()
    val list: LiveData<List<ArchetypeModel>> = _list

    init {
        viewModelScope.launch {
            _loading.value = true
            _list.value = useCase.invoke()
            _loading.value = false
        }
    }
}
