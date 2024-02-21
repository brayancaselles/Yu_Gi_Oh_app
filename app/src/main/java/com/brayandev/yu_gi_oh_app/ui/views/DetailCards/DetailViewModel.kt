package com.brayandev.yu_gi_oh_app.ui.views.DetailCards

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brayandev.yu_gi_oh_app.data.repository.models.CardModel
import com.brayandev.yu_gi_oh_app.domain.GetCardsUseCase
import com.brayandev.yu_gi_oh_app.ui.navigation.ARCHETYPE_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: GetCardsUseCase,
) : ViewModel() {

    private var archetypeName by Delegates.notNull<String>()

    init {
        archetypeName = savedStateHandle[ARCHETYPE_NAME] ?: ""
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _list = MutableLiveData<List<CardModel>>()
    val list: LiveData<List<CardModel>> = _list

    fun getListCards(archetypeName: String = this.archetypeName) {
        viewModelScope.launch {
            _loading.value = true
            _list.value = useCase.getCardList(archetypeName)
            _loading.value = false
        }
    }
}
