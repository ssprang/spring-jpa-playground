package com.example.springjpaplayground.db

import com.example.springjpaplayground.db.entities.Genre
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DBUtils {
    @Autowired
    private lateinit var genreRepository: GenreRepository

    fun generateGenres(): MutableList<Genre> {
        val genre1 = Genre(
            name = "Rock",
            subGenres = emptyList(),
            influencedBy = emptyList()
        )
        val genre2 = Genre(
            name = "Punk Rock",
            subGenres = emptyList(),
            influencedBy = emptyList()
        )
        val savedEntities = genreRepository.saveAll(listOf(genre1, genre2))
        savedEntities.first().subGenres = listOf(savedEntities[1])
        return genreRepository.findAll()
    }
}