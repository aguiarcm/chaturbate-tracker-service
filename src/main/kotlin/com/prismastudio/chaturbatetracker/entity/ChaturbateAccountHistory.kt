package com.prismastudio.chaturbatetracker.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Fetch
import java.time.LocalDateTime

@Entity
@Table(name = "chaturbate_account_history")
data class ChaturbateAccountHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val uid: Long? = null,

        @JsonProperty("username")
        @Column(nullable = false)
        val userName: String,

        @JsonProperty("token_balance")
        @Column(nullable = false)
        val tokenBalance: Int,

        @JsonProperty("tips_in_last_hour")
        @Column(nullable = false)
        val tipsInLastHour: Int,

        @JsonProperty("last_broadcast")
        @Column(nullable = false)
        val lastBroadcast : LocalDateTime
)
