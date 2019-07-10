package com.iliaberlana.marvelapi

import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.MarvelDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MarvelDataSourceTest {

    @Test
    fun `return the same superhero that provided`() {
        val marvelDataSource = MarvelDataSource()

        val initsuperheroe = Superheroe(123, "Name", "This is a superhero", "23", "https://via.placeholder.com/150")

        val superheroe = marvelDataSource.getSuperheroe(initsuperheroe)

        assertEquals(initsuperheroe, superheroe)
    }

    @Test
    fun `return a List of Superheroe when execute ListSuperHeroe`() = runBlocking {
        val marvelDataSource = mockk<MarvelDataSource>(relaxed = true)

        coEvery { marvelDataSource.listSuperheroes(0, 50, "name") } returns emptyList()

        val list= marvelDataSource.listSuperheroes(0, 50, "name")

        assertEquals(emptyList<Superheroe>(), list)
    }
}