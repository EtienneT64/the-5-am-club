package com.etienne.the5amclub

import com.etienne.the5amclub.screens.Event

data class UserModel(
    var userID: String? = null,
    var userFullName: String? = null,
    var userEmail: String? = null,
    var userStatus: String? = null,
    var Attending: Map<String, String>? = null,
    var Events: Map<String, Event>? = null,
)



