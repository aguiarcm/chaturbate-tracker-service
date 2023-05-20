package com.prismastudio.chaturbatetracker.controller

import com.prismastudio.chaturbatetracker.entity.ChaturbateAccountHistory
import com.prismastudio.chaturbatetracker.repository.ChaturbateAccountHistoryRepository
import com.prismastudio.chaturbatetracker.service.ChaturbateAccountHistoryService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@RestController
@RequestMapping("/chaturbate-history")
class ChaturbateAccountHistoryController (
        private val chaturbateAccountHistoryService: ChaturbateAccountHistoryService,
        private val chaturbateAccountHistoryRepository: ChaturbateAccountHistoryRepository,
        private val restTemplate: RestTemplate
) {

    @GetMapping("/search")
    fun searchChaturbateAccountHistory(
            @RequestParam username: String,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate
    ): List<ChaturbateAccountHistory> {
        val startDateTime = startDate.atStartOfDay()
        val endDateTime = endDate.atStartOfDay().plusDays(1).minusSeconds(1)
        return chaturbateAccountHistoryService.searchByUsernameAndLastBroadcastRange(username, startDateTime, endDateTime)
    }

    @GetMapping("/on-demand")
    fun onDemand(): List<ChaturbateAccountHistory> {
        return chaturbateAccountHistoryService.getChaturbateAccountHistoryList()
    }

}