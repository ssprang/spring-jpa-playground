package com.example.springjpaplayground.db

import com.example.springjpaplayground.db.entities.Genre
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
internal class TestData {
    @Autowired
    private lateinit var genreRepository: GenreRepository

    @PostConstruct
    fun generateGenres() {
        val genres = listOf(
            Genre(
                name = "Rock",
                subGenres = emptyList(),
                influencedBy = emptyList()
            ),
            Genre(
                name = "Punk Rock",
                subGenres = emptyList(),
                influencedBy = emptyList()
            ),
            Genre(
                name = "Glam Rock",
                subGenres = emptyList(),
                influencedBy = emptyList()
            )
        )
        val newEntities = genreRepository.saveAll(genres)
        // Rock has sub-genres "Punk Rock", "Glam Rock"
        newEntities[0].subGenres = listOf(newEntities[1], newEntities[2])
        // "Glam Rock" is influenced by "Punk Rock"
        newEntities[2].influencedBy = listOf(newEntities[1])
        genreRepository.saveAll(newEntities)
    }
}