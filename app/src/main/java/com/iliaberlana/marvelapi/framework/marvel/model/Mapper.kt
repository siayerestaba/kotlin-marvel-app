package com.iliaberlana.marvelapi.framework.marvel.model

import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.marvel.model.Character as MarvelCharacter

fun MarvelCharacter.toSuperheroe(): Superheroe = Superheroe(
    this.id,
    this.name,
    this.description,
    this.modified,
    "${this.thumbnail.path}.${this.thumbnail.extension}"
)