package com.prismastudio.chaturbatetracker.service

import com.prismastudio.chaturbatetracker.entity.ChaturbateAccount
import com.prismastudio.chaturbatetracker.repository.ChaturbateAccountRepository
import mu.KLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader


@Component
class ChaturbateAccountJob(private val chaturbateAccountRepository: ChaturbateAccountRepository) {


    companion object : KLogging()

    @Scheduled(fixedDelay = 50000) // Run the job every 5 seconds, you can adjust the schedule as per your needs
    fun processCsvData() {
        val csvFilePath = "chaturbate-links.csv" // Replace with the actual path to your CSV file

        try {
            val classLoader = object {}.javaClass.classLoader
            val inputStream = classLoader.getResourceAsStream(csvFilePath)
            val csvReader = BufferedReader(InputStreamReader(inputStream))
            val csvParser = CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader())

            csvParser.forEach { csvRecord ->
                val userName = csvRecord["username"]
                val url = csvRecord["url"]

                val existingAccount = chaturbateAccountRepository.findByUserName(userName)

                if (existingAccount != null) {
                    existingAccount.url = url
                    chaturbateAccountRepository.save(existingAccount)
                } else {
                    chaturbateAccountRepository.save(ChaturbateAccount(userName = userName, url = url))
                }
            }
            csvReader.close()
        } catch (e: Exception) {
            logger.error("Error reading CSV file, error: $e")
        }
    }
}