package com.iliaberlana.marvelapi.ui.model

import com.iliaberlana.domain.Superheroe
import org.joda.time.DateTime
import org.joda.time.Days
import com.iliaberlana.marvelapi.framework.marvel.model.Character as MarvelCharacter

fun Superheroe.toMarvelSuperheroe(): MarvelSuperHeroe {
    val dt = DateTime(this.lastModify)
    val now = DateTime()
    val interval = Days.daysBetween(dt.toLocalDate(), now.toLocalDate()).getDays()

    return MarvelSuperHeroe(this.id, this.name, this.description, interval, this.imageUrl)
}