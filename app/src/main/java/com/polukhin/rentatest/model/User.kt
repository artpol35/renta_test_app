package com.polukhin.rentatest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String,
): Serializable
