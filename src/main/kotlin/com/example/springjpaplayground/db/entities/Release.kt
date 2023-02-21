package com.example.springjpaplayground.db.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.util.UUID

@Entity
@EntityListeners(AuditingEntityListener::class)
class Release(
    val title: String,
    @ManyToMany
    @JoinTable(name="release_genre")
    var genres: List<Genre>,
    @ManyToOne
    @JoinColumn(name="created_by")
    val createdBy: AppUser
) {
    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null
    @CreatedDate
    var createdAt: Instant? = null
    @LastModifiedDate
    var updatedAt: Instant? = null
}