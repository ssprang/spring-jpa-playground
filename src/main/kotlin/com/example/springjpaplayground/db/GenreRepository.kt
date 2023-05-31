package com.example.springjpaplayground.db

import com.example.springjpaplayground.db.entities.Genre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface GenreRepository: JpaRepository<Genre, UUID>