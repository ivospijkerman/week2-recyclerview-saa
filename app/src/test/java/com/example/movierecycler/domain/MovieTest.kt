package com.example.movierecycler.domain

import com.example.movierecycler.domain.Movie
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieTest {

    @Test
    fun `deze test die draai ik omdat ik wil testen of de toString van Movie werkt`() {
        // Arrange
        val sut = Movie(
            1234,
            "Wat een mooie film",
            "Steven Spielberg",
            1994
        )
        val expected = "Wat een mooie film (1994)"

        // Act
        val actual = sut.toString()

        // Assert
        assertEquals(expected, actual)
    }
}