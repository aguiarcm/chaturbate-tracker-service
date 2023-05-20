package com.prismastudio.chaturbatetracker.repository

import com.prismastudio.chaturbatetracker.entity.ChaturbateAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChaturbateAccountRepository  : JpaRepository<ChaturbateAccount, Long> {
}