package com.example.springjpaplayground.db.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
class Release(
    val title: String,
    @ManyToMany
    @JoinTable(name="release_genre")
    val genres: List<Genre>
) {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null
}