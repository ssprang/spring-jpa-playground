package com.example.springjpaplayground.db

import com.example.springjpaplayground.db.entities.Release
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ReleaseRepository: JpaRepository<Release, UUID>