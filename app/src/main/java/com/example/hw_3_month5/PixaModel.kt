package com.example.hw_3_month5

data class PixaModel(
    val hits: List<ImageModel>
)

data class ImageModel(
    val largeImageURL: String
)