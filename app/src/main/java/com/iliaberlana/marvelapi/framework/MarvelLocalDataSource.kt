package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.SuperheroeLocalDataSource
import com.iliaberlana.domain.Superheroe

class MarvelLocalDataSource : SuperheroeLocalDataSource {

    override fun getSuperheroe(superheroe: Superheroe): Superheroe {
        return superheroe
    }

}