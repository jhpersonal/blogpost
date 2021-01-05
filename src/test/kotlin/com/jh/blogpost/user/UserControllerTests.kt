package com.jh.blogpost.user

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.http.ResponseEntity
import java.lang.Exception


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate
    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeAll
    fun setUp() {
//        userRepository.deleteAll()
        val user = User("scooter", "scooter@controllertest.com")
        testRestTemplate.withBasicAuth("editor", "pass")
            .postForEntity(uri, user, User::class.java)
    }

    private fun saveOneUser() = userRepository.save(User("scooter", "scooter@test.com"))
    val uri = "/users"

    @Test
    fun authenticatedCreateUser() {
        val user = User("testuser", "testuser@controllertest.com")
        val result: ResponseEntity<User> = testRestTemplate.withBasicAuth("editor", "pass")
            .postForEntity(uri, user, User::class.java)
        println(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
    }

    @Test
    @Throws(Exception::class)
    fun unauthenticatedGetUserShouldFailWith401() {
        val result: ResponseEntity<User> = testRestTemplate.getForEntity("/users", User::class.java)
        assertEquals(HttpStatus.UNAUTHORIZED, result.statusCode)
    }

    @Test
    fun authenticatedGetUserShouldSucceedWith200() {
//        saveOneUser()
        val result: ResponseEntity<Array<User>> = testRestTemplate.withBasicAuth("editor", "pass")
            .getForEntity("/users", Array<User>::class.java)
        println(result)
        assertEquals(HttpStatus.OK, result.statusCode)
    }
}


/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PatientControllerIntTest @Autowired constructor(
        private val patientRepository: PatientRepository,
        private val restTemplate: TestRestTemplate
) {
    private val defaultPatientId = ObjectId.get()

    @LocalServerPort
    protected var port: Int = 0

    @BeforeEach
    fun setUp() {
        patientRepository.deleteAll()
    }


    private fun getRootUrl(): String? = "http://localhost:$port/patients"

    private fun saveOnePatient() = patientRepository.save(Patient(defaultPatientId, "Name", "Description"))

    private fun preparePatientRequest() = PatientRequest("Name", "Default description")

    @Test
    fun `should return all patients`() {
        saveOnePatient()

        val response = restTemplate.getForEntity(
                getRootUrl(),
                List::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(1, response.body?.size)
    }

    @Test
    fun `should return single patient by id`() {
        saveOnePatient()

        val response = restTemplate.getForEntity(
                getRootUrl() + "/$defaultPatientId",
                Patient::class.java
        )

        assertEquals(200, response.statusCode.value())
        assertNotNull(response.body)
        assertEquals(defaultPatientId, response.body?.id)
    }

    // delete Patient
    @Test
    fun `should delete existing patient`() {
        saveOnePatient()

        val delete = restTemplate.exchange(
                getRootUrl() + "/$defaultPatientId",
                HttpMethod.DELETE,
                HttpEntity(null, HttpHeaders()),
                ResponseEntity::class.java
        )

        assertEquals(204, delete.statusCode.value())
        assertThrows(EmptyResultDataAccessException::class.java) { patientRepository.findOneById(defaultPatientId) }
    }
    // update operation
    @Test
    fun `should update existing patient`() {
        saveOnePatient()
        val patientRequest = preparePatientRequest()

        val updateResponse = restTemplate.exchange(
                getRootUrl() + "/$defaultPatientId",
                HttpMethod.PUT,
                HttpEntity(patientRequest, HttpHeaders()),
                Patient::class.java
        )
        val updatedTask = patientRepository.findOneById(defaultPatientId)

        assertEquals(200, updateResponse.statusCode.value())
        assertEquals(defaultPatientId, updatedTask.id)
        assertEquals(patientRequest.description, updatedTask.description)
        assertEquals(patientRequest.name, updatedTask.name)
    }

    @Test
    fun `should create new patient`() {
        val patientRequest = preparePatientRequest()

        val response = restTemplate.postForEntity(
                getRootUrl(),
                patientRequest,
                Patient::class.java
        )


        assertEquals(201, response.statusCode.value())
        assertNotNull(response.body)
        assertNotNull(response.body?.id)
        assertEquals(patientRequest.description, response.body?.description)
        assertEquals(patientRequest.name, response.body?.name)
    }

}


 */