package uk.ac.tees.mad.token.ui.screen.profile

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: FirebaseAuth
):ViewModel() {

    private val _name = MutableStateFlow("Name")
    val name: StateFlow<String> get() = _name
    private val _email = MutableStateFlow("Email")
    val email: StateFlow<String> get() = _email

    init {
        _name.value = auth.currentUser?.displayName.toString()
        _email.value = auth.currentUser?.email.toString()
    }

    fun updateProfile(newName:String, context: Context){
        _name.value = newName
        viewModelScope.launch {
            val profileUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(_name.value)
                .build()
            auth.currentUser?.updateProfile(profileUpdate)
            Toast.makeText(context, "Name changed successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun logOut(){
        auth.signOut()
    }
}