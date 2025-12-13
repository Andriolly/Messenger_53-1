package com.example.messenger53_1.screen.Login.signUp

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.messenger53_1.DataMessanger.NODE_USERS
import com.example.messenger53_1.MainActivity
import com.example.messenger53_1.model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<SignUpState>(SignUpState.Nothing)
    val state = _state.asStateFlow()

    fun signUp(name: String, email: String, password: String) {
        _state.value = SignUpState.Loading
//        Firebase signUp
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.user?.let {
                        it.updateProfile(
                            com.google.firebase.auth.UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build()
                        )?.addOnCompleteListener {
                            _state.value = SignUpState.Success
                        }
//                      Создание пользователя в базе данных
                        createUserProfile(name = name, email = email, password = password)

                        _state.value = SignUpState.Success
                        return@addOnCompleteListener
                    }
                    _state.value = SignUpState.Error

                } else {
                    _state.value = SignUpState.Error
                }
            }




    }

    fun createUserProfile(name: String, email: String, password: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference(NODE_USERS).child(uid)

        Log.d("signUP",uid)

        val user = UserData(
            uid = uid,
            name = name,
            email = email,
            password = password
        )

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("signUP","Пользователь сохранен")
            }
            .addOnFailureListener {
                Log.d("signUP","Пользователь не сохранен")
                _state.value = SignUpState.Error
            }

    }

}

sealed class SignUpState {
    object Nothing : SignUpState()
    object Loading : SignUpState()
    object Success : SignUpState()
    object Error : SignUpState()

}