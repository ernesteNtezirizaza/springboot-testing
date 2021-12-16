package rw.ac.rca.termOneExam.service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepositoryMock;

    @InjectMocks
    private CityService cityService;

    @Test
    public void getAllTest() {
        when(cityRepositoryMock.findAll()).thenReturn(Arrays.asList(new City(105L, "Huye", 130),
                new City(106L, "Nyabihu",40)));
        assertEquals(130, cityService.getAll().get(0).getName());
    }

    @Test
    public void getOneCityWhenIdIsFound() {
        when(cityRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new City(105L, "Huye", 130)));

        assertEquals("Huye", cityService.getById(105L).get());
    }

    @Test
    public void getById_fail() {
        Long id = 3000L;
        when(cityRepositoryMock.findById(id)).thenReturn(Optional.empty());
        Optional<City> city = cityService.getById(id);
        assertTrue(city == null);
    }

    @Test
    public void saveCityTest() {
        CreateCityDTO dto = new CreateCityDTO("Nyamagabe" , 130);
        City city = new City(109L,"Nyamagabe", 130);
        when(cityRepositoryMock.save(any(City.class))).thenReturn(city);
        assertEquals(130, cityService.save(dto).getName());
    }

    @Test
    public void saveDuplicateCityTest() {
        when(cityRepositoryMock.existsByName(anyString())).thenReturn(true);
        CreateCityDTO dto = new CreateCityDTO("Nyamagabe" , 130);
        Exception exception = assertThrows(APICustomResponse.class, () -> cityService.save(dto));

        assertEquals("City already taken", exception.getMessage());
    }



}
