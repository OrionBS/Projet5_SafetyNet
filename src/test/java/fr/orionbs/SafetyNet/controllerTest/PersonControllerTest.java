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
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
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

    public static String asJsonString(final Person person) {
        try {
            return new ObjectMapper().writeValueAsString(person);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
