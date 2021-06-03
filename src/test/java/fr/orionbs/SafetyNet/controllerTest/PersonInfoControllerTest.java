package fr.orionbs.SafetyNet.controllerTest;

import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static fr.orionbs.SafetyNet.controllerTest.PersonControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetPersonInfo() throws Exception {
        Person person = new Person(null, "test3", "Test3", "testAddress", "testCity", 1, "testPhone", "testMail");
        mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON));
        List<String> medications = new ArrayList<>();
        medications.add("medicationTest");
        List<String> allergies = new ArrayList<>();
        medications.add("allergieTest");
        MedicalRecord medicalRecord = new MedicalRecord(null,"test3","Test3","03/02/2000",medications,allergies);
        mockMvc.perform(post("/medicalRecord").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON));
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("firstName","test3");
        listParams.add("lastName","Test3");
        mockMvc.perform(get("/personInfo")
                .params(listParams))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
