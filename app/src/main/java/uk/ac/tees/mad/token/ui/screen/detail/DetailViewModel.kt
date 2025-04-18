package uk.ac.tees.mad.token.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _tokenDetail = MutableStateFlow<DetailData?>(null)
    val tokenDetail:StateFlow<DetailData?> get() = _tokenDetail

     fun getDetailData(id:String){
        viewModelScope.launch {
            try {
                _tokenDetail.value = repository.getTokenDetails(id)
            }catch (e:Exception){
                Log.e("Error in getting detail data", e.message.toString())
            }
        }
    }
}