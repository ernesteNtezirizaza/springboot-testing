package rw.ac.rca.termOneExam.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findById_Success() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/101", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findById_NotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/160", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAll_Success() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAll_NotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void saveCity_Success() {
        City theCity = new City(105L, "Kayonza", 30);
        ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", theCity, City.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Kayonza", Objects.requireNonNull(response.getBody()).getName());
    }
    



}
