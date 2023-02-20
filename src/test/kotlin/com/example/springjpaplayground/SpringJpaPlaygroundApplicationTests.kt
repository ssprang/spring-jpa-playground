package com.example.springjpaplayground

import com.example.springjpaplayground.db.GenreRepository
import com.example.springjpaplayground.db.ReleaseRepository
import com.example.springjpaplayground.db.entities.Release
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringJpaPlaygroundApplicationTests {

    @Autowired
    private lateinit var repository: ReleaseRepository

    @Autowired
    private lateinit var genreRepository: GenreRepository

    @Autowired
    private lateinit var testService: ReleaseService

    @Test
    fun test() {
        val release = Release(
            title = "Release me",
            genres = genreRepository.findAll()
        )
        val savedRelease = repository.save(release)
        assertThat(savedRelease.id).isNotNull
        assertThat(savedRelease.genres).hasSize(3)

        val subGenreId = testService.getFirstSubGenreId(savedRelease.id!!)
        val subGenre = genreRepository.findById(subGenreId!!)

        val releasesWithGenre = repository.findByGenresId(savedRelease.genres.first().id!!)
        assertThat(releasesWithGenre).hasSize(1)

        val releasesWithSubgenre = repository.findByGenresSubGenresId(subGenreId)
        assertThat(releasesWithSubgenre).hasSize(1)
    }
}

@Service
internal class ReleaseService {
    @Autowired
    private lateinit var repository: ReleaseRepository

    @Transactional
    fun getFirstSubGenreId(releaseId: UUID): UUID? =
        repository.findByIdOrNull(releaseId)?.genres?.firstOrNull()?.subGenres?.lastOrNull()?.id

}