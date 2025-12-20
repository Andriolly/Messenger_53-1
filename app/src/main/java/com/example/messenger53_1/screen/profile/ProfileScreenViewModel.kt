package com.example.messenger53_1.screen.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.messenger53_1.DataMessanger.CHILD_CREATED_AT
import com.example.messenger53_1.DataMessanger.NODE_CHANNELS
import com.example.messenger53_1.DataMessanger.NODE_MESSAGES
import com.example.messenger53_1.DataMessanger.NODE_USERS
import com.example.messenger53_1.model.Channel
import com.example.messenger53_1.model.Message
import com.example.messenger53_1.model.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class ProfileScreenViewModel @Inject constructor() : ViewModel() {

    val TAG = "profile"

    private val firebaseDatabase = Firebase.database

    private val _userData = MutableStateFlow(UserData())
    val userData = _userData.asStateFlow()
    val uid = FirebaseAuth.getInstance().uid ?: ""

    fun initProfile() {

        firebaseDatabase.getReference(NODE_USERS).child(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val userData: UserData? = dataSnapshot.getValue(UserData::class.java)
                    if (userData != null) {
                        _userData.update { currentUserData ->
                            userData
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "Ошибка: ${error.message}")
                }

            })


    }


    fun changePhone(phone: String) {
        val updates = HashMap<String, Any>()
        updates["phone"] = phone
        firebaseDatabase.getReference(NODE_USERS).child(uid).updateChildren(updates)
            .addOnSuccessListener {
                Log.d(TAG, "Телефон успешно обновлён!")
                initProfile()
            }
            .addOnFailureListener { error ->
                Log.e(TAG, "Ошибка при обновлении телефона: ${error.message}")
            }
    }


    fun changeUsername(username: String) {
        val updates = HashMap<String, Any>()
        updates["userName"] = username
        firebaseDatabase.getReference(NODE_USERS).child(uid).updateChildren(updates)
            .addOnSuccessListener {
                Log.d(TAG, "Имя пользователя успешно обновлёно!")
                initProfile()
            }
            .addOnFailureListener { error ->
                Log.e(TAG, "Ошибка при обновлении имени пользователя: ${error.message}")
            }
    }


}