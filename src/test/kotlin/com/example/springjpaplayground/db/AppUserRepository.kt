package com.example.springjpaplayground.db

import com.example.springjpaplayground.db.entities.AppUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppUserRepository: JpaRepository<AppUser, UUID>