package rw.ac.rca.termOneExam.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CityUtilTest {

    @Autowired
    private ICityRepository cityRepository;

    @Test
    public void noCityWithWeatherMoreThan40_test(){
        int citiesWithWeatherMoreThan40 = 0;
        List<City> cities = cityRepository.findAll();
        for (City city: cities)
            if(city.getWeather() > 40)
                citiesWithWeatherMoreThan40 ++;


        assertEquals(0, citiesWithWeatherMoreThan40);
    }

    @Test
    public void noCityWithWeatherLessThan10_test(){
        int citiesWithWeatherLessThan10 = 0;
        List<City> cities = cityRepository.findAll();
        for (City city: cities)
            if(city.getWeather() < 10)
                citiesWithWeatherLessThan10 --;


        assertEquals(0, citiesWithWeatherLessThan10);
    }

    @Test
    public void CitiesContainMusanze() {
        boolean found = false;
        List<City> cities = cityRepository.findAll();
        assertEquals(true, cityRepository.existsByName("Musanze"));
    }

    @Test
    public void CitiesContainKigali() {
        boolean found = false;
        List<City> cities = cityRepository.findAll();
        assertEquals(true, cityRepository.existsByName("Kigali"));
    }






}
