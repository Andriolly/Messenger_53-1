package com.example.messenger53_1.screen.contacts

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
import javax.inject.Inject

class ContactsScreenViewModel @Inject constructor() : ViewModel() {
    private val firebaseDatabase = Firebase.database
    private val _users = MutableStateFlow<List<UserData>>(emptyList())
    val users = _users.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        firebaseDatabase.getReference(NODE_USERS)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<UserData>()
                    snapshot.children.forEach { data ->
                        val message = data.getValue(UserData::class.java)
                        message?.let {
                            list.add(it)
                        }
                    }
                    _users.value = list
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }


}