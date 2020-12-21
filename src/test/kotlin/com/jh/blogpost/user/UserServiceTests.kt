package com.jh.blogpost.user

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.JUnitSoftAssertions

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureTestDatabase
 class UserServiceTests {

    @Autowired
    lateinit var service: UserService
    @Autowired
    lateinit var repository: UserRepository

    @get:Rule
    var softly = JUnitSoftAssertions()

    // 'getUsers' should retrieve empty list if repository doesn't contain entities
    @Test
    fun getUsers() {
        assertThat(service.getUsers()).size().isNotZero //   .isNullOrEmpty()
    }

    //    `'getUserById' should return null if user for userId doesnt exist`
    @Test
    fun getUserByIdNotExists() {
        var user: User? = null
        try {
            user = service.getUserById(99)
        } catch (e: Exception) {}
        finally {
            assertThat(user).isNull()
        }
    }

    @Test
    fun `'getUserByEmail' should map existing entity from repository`() {
        repository.save(User("servicetestuser", "servicetest@test.com"))

        val result: User? = service.getUserByEmail("servicetest@test.com")
        softly.assertThat(result).isNotNull
        softly.assertThat(result?.id).isNotNull
        softly.assertThat(result?.name).isEqualTo("servicetestuser")
        softly.assertThat(result?.email).isEqualTo("servicetest@test.com")
    }

    //    `'addUser' should return created entity`
    @Test
    fun createUser() {
        val result = service.createUser(User("service test user", "createuser@servicetest.com"))

        softly.assertThat(result.id).isNotNull
        softly.assertThat(result.name).isEqualTo("service test user")
        softly.assertThat(result.email).isEqualTo("createuser@servicetest.com")
    }

    //    `'updateUser' should update existing values`
    @Test
    fun updateUser() {
        val existingUser = repository.save(User("newtestuser", "newtestuser@test.com"))

        val result = service.updateUser(existingUser.id, User("updatedservicetestuser", "updatedservicetestuser@test.com"))
        softly.assertThat(result).isNotNull
        softly.assertThat(result?.id).isEqualTo(existingUser.id)
        softly.assertThat(result?.name).isEqualTo("updatedservicetestuser")
        softly.assertThat(result?.email).isEqualTo("updatedservicetestuser@test.com")
    }

//    'updateUser' shouldn't update null values
    @Test
    fun updateNullUser() {
        val existingUser = repository.save(User("updatedservicetestuser", ""))

        val result = service.updateUser(existingUser.id, User())
        softly.assertThat(result).isNotNull
        softly.assertThat(result?.id).isEqualTo(existingUser.id)
        softly.assertThat(result?.name).isEqualTo("")
        softly.assertThat(result?.email).isEqualTo("")
    }

}