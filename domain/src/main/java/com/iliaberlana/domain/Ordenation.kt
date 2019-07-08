package com.iliaberlana.domain

enum class Ordenation (val typeOrdenation: String) {
    NAME("name"),
    MODIFIED("-modified");

    companion object {
        fun getOrdenationByType(type: String) = valueOf(type.removePrefix("-").toUpperCase())
    }
}