package com.codewithteju.findapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Advertisement(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val location: String,
    val price: Int,
    val imageUrl: String,
    var isFavorite: Boolean = false
)

