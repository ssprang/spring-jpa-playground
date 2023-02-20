package com.example.springjpaplayground.db.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*


@Entity
class Genre(
    val name: String,
    @OneToMany
    @JoinTable(name = "sub_genre")
    var subGenres: List<Genre>,
    @OneToMany
    @JoinTable(name = "influencing_genre")
    var influencedBy: List<Genre>
) {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null
}