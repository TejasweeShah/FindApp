package com.codewithteju.findapp.data.remote.dto


data class AdsDto(
    val fetchMore: List<Any>,
    val items: List<Item>,
    val size: Int,
    val version: String
)