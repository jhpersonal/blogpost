package com.jh.blogpost.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import org.springframework.test.web.servlet.MockMvc

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc

import org.springframework.boot.test.context.SpringBootTest

import org.springframework.test.context.junit4.SpringRunner

import org.junit.runner.RunWith
import org.springframework.http.MediaType
import java.lang.Exception


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerMockTests {
    @Autowired
    lateinit var userController: UserController

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun whenUserControllerInjected_thenNotNull() {
        assertThat(userController).isNotNull()
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRequestToUsersEndPointWithIdPathVariable_thenCorrectResponse() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/users/{id}", "1").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRequestToUsersEndPoint_thenCorrectResponse() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$['pageable']['paged']").value("true"))
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRequestToUserEndPointWithNameRequestParameter_thenCorrectResponse() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/users").param("name", "John").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$['content'][0].['name']").value("John"))
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRequestToSorteredUsersEndPoint_thenCorrectResponse() {
        mockMvc.perform(MockMvcRequestBuilders.get("/sortedusers").contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$['sort']['sorted']").value("true"))
    }

    @Test
    @Throws(Exception::class)
    fun whenGetRequestToFilteredUsersEndPoint_thenCorrectResponse() {
        mockMvc!!.perform(
            MockMvcRequestBuilders.get("/filteredusers").param("name", "John")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John"))
    }
}

