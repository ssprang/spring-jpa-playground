package com.example.springjpaplayground

import com.example.springjpaplayground.db.DBUtils
import com.example.springjpaplayground.db.ReleaseRepository
import com.example.springjpaplayground.db.entities.Release
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringJpaPlaygroundApplicationTests {

    @Autowired
    private lateinit var dbUtils: DBUtils
    @Autowired
    private lateinit var repository: ReleaseRepository

    @Test
    fun test() {
        val genres = dbUtils.generateGenres()
        val release = Release(
            title = "",
            genres = genres
        )
        val savedRelease = repository.save(release)
        assert(savedRelease.id != null)
    }

}
