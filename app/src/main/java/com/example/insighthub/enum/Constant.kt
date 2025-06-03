package com.example.insighthub.enum

sealed class Constant {
    sealed class URL {
        data class Book(
            val id: String,
        ) : URL()

        object Base : URL()

        val string: String
            get() =
                when (this) {
                    is Base -> "https://insight-hub-kappa.vercel.app/"
                    is Book -> "https://insight-hub-kappa.vercel.app/#/books/$id"
                }
    }
}
