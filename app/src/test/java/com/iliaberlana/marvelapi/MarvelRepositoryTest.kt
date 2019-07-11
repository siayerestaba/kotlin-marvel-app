package com.iliaberlana.marvelapi

import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.MarvelRepository
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MarvelRepositoryTest {

    @Test
    fun `return a superhero with same id`() = runBlocking {
        val marvelClientService = mockk<MarvelClientService>(relaxed = true)
        val marvelRepository = MarvelRepository(marvelClientService)

        coEvery { marvelClientService.getCharacter(123) } returns Superheroe(123, "Name", "This is a superhero", "23", "https://via.placeholder.com/150")

        val superheroe = marvelRepository.showSuperheroe(123)

        assertEquals(123, superheroe.id)
    }

    @Test
    fun `return a List of Superheroe when execute ListSuperHeroe`() = runBlocking {
        val marvelClientService = mockk<MarvelClientService>(relaxed = true)
        val marvelRepository = MarvelRepository(marvelClientService)

        coEvery { marvelClientService.getCharacters(0, 50, "name") } returns emptyList()

        val list= marvelRepository.listSuperheroes(0, 50, "name")

        assertEquals(emptyList<Superheroe>(), list)
    }
}