package com.TrionfiniJuan.demo.controller;

import com.TrionfiniJuan.demo.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate( restTemplateBuilder );
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity(
                        "/api/laptops",
                        Laptop[].class
                );

        assertNotNull( response );
    }

    @Test
    void findOneById() {

        // Tendría que ver mockk o mockito o alguno para mockear el service

        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity(
                "/api/laptops/1",
                Laptop[].class
        );

        if ( response.getBody() == null ){
            assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode() );
        } else {
            assertEquals( HttpStatus.OK, response.getStatusCode() );

        }
    }

    @Test
    void findOneById_NoExistId() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity(
                "/api/laptops/1",
                Laptop[].class
        );


    }

    @Test
    void update() {
        // TODO: deberia poder mockear la respuesta del repository para comparar

    }
    @Test
    void update_NotExist() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType( MediaType.APPLICATION_JSON );
        httpHeaders.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );

        String json = """
                {
                  "id": 99,
                  "model": "Asus mega",
                  "releaseDate": "2023-07-18"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>( json, httpHeaders );

        ResponseEntity<Laptop> response = testRestTemplate
                .exchange(
                        "/api/laptops",
                        HttpMethod.PUT,
                        request,
                        Laptop.class
                );

        Laptop laptopResponse = response.getBody();

        assertNull( laptopResponse );
        assertEquals( HttpStatus.NOT_FOUND,  response.getStatusCode() );

    }
    @Test
    void delete() {
        // TODO: deberia poder mockear la respuesta del repository para eliminar
    }

    @Test
    void deleteAll() {
        // TODO: deberia poder mockear la respuesta del repository para eliminar
    }

    @Test
    void createTest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType( MediaType.APPLICATION_JSON );
        httpHeaders.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );

        String json = """
                {
                  "model": "Asus mega",
                  "releaseDate": "2023-07-18"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>( json, httpHeaders );

        ResponseEntity<Laptop> response = testRestTemplate
                .exchange(
                        "/api/laptops",
                        HttpMethod.POST,
                        request,
                        Laptop.class
                );

        Laptop laptopResponse = response.getBody();

        assertNotNull( laptopResponse );
        assertEquals( "Asus mega", laptopResponse.getModel() );
        assertEquals( HttpStatus.OK,  response.getStatusCode() );
    }

    @Test
    void createTest_IdNotNull() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType( MediaType.APPLICATION_JSON );
        httpHeaders.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );

        String json = """
                {
                  "id" : 1,
                  "model": "Asus mega",
                  "releaseDate": "2023-07-18"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>( json, httpHeaders );

        ResponseEntity<Laptop> response = testRestTemplate
                .exchange(
                        "/api/laptops",
                        HttpMethod.POST,
                        request,
                        Laptop.class
                );

        assertEquals( HttpStatus.BAD_REQUEST,  response.getStatusCode() );
    }

}