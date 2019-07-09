package com.iliaberlana.domain

import org.junit.Assert.*
import org.junit.Test

class OrdenationTest {

    @Test
    fun `return NAME when string is name`() {
        assertEquals(Ordenation.NAME, Ordenation.getOrdenationByType("name"))
    }

    @Test
    fun `return MODIFIED when string is -modified`() {
        assertEquals(Ordenation.MODIFIED, Ordenation.getOrdenationByType("-modified"))
    }

    @Test
    fun `return MODIFIED when string is empty`() {
        assertEquals(Ordenation.MODIFIED, Ordenation.getOrdenationByType(""))
    }

    @Test
    fun `return MODIFIED when string is random`() {
        assertEquals(Ordenation.MODIFIED, Ordenation.getOrdenationByType("random"))
    }
}