package com.jh.blogpost.user

import org.junit.Assert
import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@DataJpaTest
class UserRepositoryTests {
//    @Autowired
//    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        // given
        userRepository.save(User("Midnight", "midnight@test.com"))
        userRepository.save(User("Starlight", "startlight@test.com"))
        userRepository.save(User("Glimmer", "glimmer@test.com"))
        userRepository.save(User("Sparkle", "sparkle@test.com"))
    }

    @After
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Test
    fun injectedComponentsAreNotNull() {
        assertThat(userRepository).isNotNull
    }

    @Test
    fun whenFindByName_thenReturnUser() {
        val users: List<User> = userRepository.findByName("Sparkle")
        assertEquals(1, users.count())
        assertThat(users).extracting<String> { it.name }.contains("Sparkle")
    }

    @Test
    fun whenFindByEmail_thenReturnUser() {
        val email ="sparkle@test.com"
        // when
        val queryResult:User? = userRepository.findByEmail(email)
        Assert.assertNotNull(queryResult)
        // then
        Assert.assertEquals(queryResult?.email, email)
    }

    @Test
    fun whenFindAll_thenReturnUserList() {
        // when
        val users:List<User> = userRepository.findAll()
        //then
        Assert.assertNotNull(users)
        assertThat(users).hasSize(4);
    }

    @Test
    fun whenCreate_thenReturnUser() {
        // when
        val user: User = userRepository.save(User("Glow", "glow@test.com"))
        //then
        Assert.assertNotNull(user)
        Assert.assertEquals(user.email, "glow@test.com")
    }

    @Test
    fun whenUpdate_thenReturnUser() {
        // when
        val user: User = userRepository.save(User("Glow", "glow@test.com"))
        user.email = "glowModified@test.com"
        val userUpdate: User = userRepository.save(user)
        //then
        Assert.assertNotNull(userUpdate)
        Assert.assertEquals(user.email, "glowModified@test.com")
    }

    @Test
    fun whenDelete_thenReturnNoUser() {
        // when
        val user: User = userRepository.save(User("DeleteUser", "deleteUser@test.com"))
        val userDeleted = userRepository.deleteById(user.id)
        val users: List<User> = userRepository.findAll()
        print(userDeleted)
        //then
        assertThat(users).extracting<String> { it.name }.doesNotContain("DeleteUser")
    }
}