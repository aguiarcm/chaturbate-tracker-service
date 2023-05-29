package com.prismastudio.chaturbatetracker.entity


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "chaturbate_account")
data class ChaturbateAccount(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val uid: Long? = null,

        @Column(nullable = false, unique = true)
        var userName: String,

        @Column(nullable = false)
        var url: String
)