package com.prismastudio.chaturbatetracker.repository

import com.prismastudio.chaturbatetracker.entity.ChaturbateAccount
import com.prismastudio.chaturbatetracker.entity.ChaturbateAccountHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ChaturbateAccountHistoryRepository  : JpaRepository<ChaturbateAccountHistory, Long> {
    //fun findByUserNameAndLastBroadcastBetween(username: String, startDate: LocalDateTime, endDate: LocalDateTime): List<ChaturbateAccountHistory>
}