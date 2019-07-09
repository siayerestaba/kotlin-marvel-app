package com.iliaberlana.marvelapi

import com.iliaberlana.data.OrdenationRepository
import com.iliaberlana.domain.Ordenation
import com.iliaberlana.usecases.SaveOrdenation
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class OrdenationTest {

    @Test
    fun `when execute SaveOrdenation, call ordenationRepository`() {
        val ordenationRepository = mockk<OrdenationRepository>(relaxed = true)
        val saveOrdenation = SaveOrdenation(ordenationRepository)

        saveOrdenation(Ordenation.NAME)

        verify { ordenationRepository.saveOrdenation(Ordenation.NAME) }
    }
}