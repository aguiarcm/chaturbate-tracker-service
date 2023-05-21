package com.prismastudio.chaturbatetracker.service

import com.prismastudio.chaturbatetracker.entity.ChaturbateAccount
import com.prismastudio.chaturbatetracker.entity.ChaturbateAccountHistory
import com.prismastudio.chaturbatetracker.repository.ChaturbateAccountHistoryRepository
import com.prismastudio.chaturbatetracker.repository.ChaturbateAccountRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.util.*

@Service
class ChaturbateAccountHistoryService(
        private val chaturbateAccountHistoryRepository: ChaturbateAccountHistoryRepository,
        private val chaturbateAccountRepository: ChaturbateAccountRepository,
        private val restTemplate: RestTemplate) {

    fun searchByUsernameAndLastBroadcastRange(username: String, startDate: LocalDateTime, endDate: LocalDateTime): List<ChaturbateAccountHistory> {
        //return chaturbateAccountHistoryRepository.findByUserNameAndLastBroadcastBetween(username, startDate, endDate)
        return Collections.emptyList()
    }

    fun getChaturbateAccountHistoryList() : List<ChaturbateAccountHistory> {
        val accounts = chaturbateAccountRepository.findAll()
        val list = ArrayList<ChaturbateAccountHistory>()
        for (account in accounts) {
            val fetched = fetchAccountInfo(account)
            list.add(fetched)
        }
        return list
    }

    private fun fetchAccountInfo(account: ChaturbateAccount): ChaturbateAccountHistory {
        val responseEntity = restTemplate.getForEntity(account.url, ChaturbateAccountHistory::class.java)

        if (responseEntity.statusCode.is2xxSuccessful) {
            return responseEntity.body ?: throw RuntimeException("Empty response body")
        } else {
            throw RuntimeException("Failed to fetch Chaturbate stats for username: $account.username. Status code: ${responseEntity.statusCode}")
        }
    }

}