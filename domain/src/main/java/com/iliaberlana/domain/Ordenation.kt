package com.iliaberlana.domain

enum class Ordenation (val typeOrdenation: String) {
    NAME("name"),
    MODIFIED("-modified");

    companion object {
        fun getOrdenationByType(type: String) =
            when(type) {
                NAME.typeOrdenation -> NAME
                MODIFIED.typeOrdenation -> MODIFIED
                else -> MODIFIED
            }
    }
}