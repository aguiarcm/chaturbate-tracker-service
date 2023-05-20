package com.prismastudio.chaturbatetracker.controller

import com.prismastudio.chaturbatetracker.entity.ChaturbateAccount
import com.prismastudio.chaturbatetracker.repository.ChaturbateAccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/chaturbate-accounts")
class ChaturbateAccountController(private val chaturbateAccountRepository: ChaturbateAccountRepository) {

    // GET /chaturbate-accounts
    @GetMapping
    fun getAllChaturbateAccounts(): List<ChaturbateAccount> {
        return chaturbateAccountRepository.findAll()
    }

    // GET /chaturbate-accounts/{uid}
    @GetMapping("/{uid}")
    fun getChaturbateAccount(@PathVariable uid: Long): ResponseEntity<ChaturbateAccount> {
        val account = chaturbateAccountRepository.findById(uid)
        return if (account.isPresent) {
            ResponseEntity.ok(account.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // POST /chaturbate-accounts
    @PostMapping
    fun createChaturbateAccount(@RequestBody account: ChaturbateAccount): ResponseEntity<ChaturbateAccount> {
        val createdAccount = chaturbateAccountRepository.save(account)
        return ResponseEntity.created(URI("/chaturbate-accounts/${createdAccount.uid}")).body(createdAccount)
    }

    // PUT /chaturbate-accounts/{uid}
    @PutMapping("/{uid}")
    fun updateChaturbateAccount(
            @PathVariable uid: Long,
            @RequestBody updatedAccount: ChaturbateAccount
    ): ResponseEntity<ChaturbateAccount> {
        val account = chaturbateAccountRepository.findById(uid)
        return if (account.isPresent) {
            val existingAccount = account.get()
            existingAccount.userName = updatedAccount.userName
            existingAccount.url = updatedAccount.url
            ResponseEntity.ok(chaturbateAccountRepository.save(existingAccount))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // DELETE /chaturbate-accounts/{uid}
    @DeleteMapping("/{uid}")
    fun deleteChaturbateAccount(@PathVariable uid: Long): ResponseEntity<Void> {
        chaturbateAccountRepository.deleteById(uid)
        return ResponseEntity.noContent().build()
    }
}
