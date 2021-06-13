package fr.orionbs.SafetyNet.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orionbs.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/persons").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPerson() throws Exception {

        Person person = new Person(null, "test", "Test", "testAddress", "testCity", 1, "testPhone", "testMail");
        mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdatePerson() throws Exception {

        Person person = new Person(null, "test1", "Test1", "testAddress", "testCity", 1, "testPhone", "testMail");
        mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON));
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("firstName","test1");
        listParams.add("lastName","Test1");
        person.setAddress("testAddressModified");
        mockMvc.perform(put("/person")
                .params(listParams)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testNotFoundUpdatePerson() throws Exception {
        //GIVEN

        //WHEN

        //THEN
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("firstName","testDoesn'tExist");
        listParams.add("lastName","testDoesn'tExist");
        mockMvc.perform(put("/person")
                .params(listParams)
                .content(asJsonString(Person.builder().build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePerson() throws Exception {
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("firstName","test");
        listParams.add("lastName","Test");
        mockMvc.perform(delete("/person")
                .params(listParams))
                .andExpect(status().isAccepted());
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
