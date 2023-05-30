package com.prismastudio.chaturbatetracker.service


import com.prismastudio.chaturbatetracker.repository.ChaturbateAccountHistoryRepository
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class ChaturbateAccountHistoryScheduler (
        private val chaturbateAccountHistoryService: ChaturbateAccountHistoryService,
        private val chaturbateAccountHistoryRepository: ChaturbateAccountHistoryRepository
) {

    //@Scheduled(cron = "1 * * * * *") // Run every minute
    fun updateChaturbateAccountHistory() {
        val list = chaturbateAccountHistoryService.getChaturbateAccountHistoryList()

        for (chaturbateAccountHistory in list) {
            chaturbateAccountHistoryRepository.save(chaturbateAccountHistory)
        }
    }


}