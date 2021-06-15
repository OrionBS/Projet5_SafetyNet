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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ChildAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetChildFilterByAddress() throws Exception {
        // GIVEN
        Person person = new Person(null, "test7", "Test7", "testAddress77", "testCity", 1, "testPhone", "testMail");
        mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON));
        List<String> medications = new ArrayList<>();
        medications.add("medicationTest");
        List<String> allergies = new ArrayList<>();
        allergies.add("allergieTest");
        MedicalRecord medicalRecord = new MedicalRecord(null,"test7","Test7","03/02/2010",medications,allergies);
        mockMvc.perform(post("/medicalRecord").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON));

        // WHEN
        // THEN
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("address","testAddress77");
        mockMvc.perform(get("/childAlert")
                .params(listParams))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testNotFoundGetChildFilterByAddress() throws Exception {
        //GIVEN

        //WHEN

        //THEN
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("address","testAddressDoesn'tExist");
        mockMvc.perform(get("/childAlert")
                .params(listParams))
                .andExpect(status().isNotFound());
    }
}
