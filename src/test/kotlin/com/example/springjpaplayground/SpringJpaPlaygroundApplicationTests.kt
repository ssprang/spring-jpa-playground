package com.example.springjpaplayground

import com.example.springjpaplayground.db.GenreRepository
import com.example.springjpaplayground.db.ReleaseRepository
import com.example.springjpaplayground.db.entities.Release
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
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

        testService.noNeedOfManualSave(savedRelease.id)

        testService.verifyGenres(
            releaseId = savedRelease.id,
            expectedSize = 2
        )

        assertThatThrownBy {
            genreRepository.deleteAll()
        } .isInstanceOf(DataIntegrityViolationException::class.java)
    }
}

@Service
internal class ReleaseService {
    @Autowired
    private lateinit var repository: ReleaseRepository

    /**
     * The .save() method must be called for a modified entity explicitly if:
     * 1. The changing code does not belong to a transaction.
     * 2. The entity is newly created: new Car().
     * 3. The entity object is obtained externally, not from the database within the same transaction.
     */
    @Transactional
    fun noNeedOfManualSave(releaseId: UUID): Release? =
        repository.findByIdOrNull(releaseId)?.apply { genres = genres - genres.last() }

    @Transactional
    fun verifyGenres(releaseId: UUID, expectedSize: Int) {
        val updatedRelease = repository.findByIdOrNull(releaseId)
        assertThat(updatedRelease?.id).isEqualTo(releaseId)
        assertThat(updatedRelease?.genres).hasSize(expectedSize)
    }
}