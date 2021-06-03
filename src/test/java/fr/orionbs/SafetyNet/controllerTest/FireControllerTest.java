package fr.orionbs.SafetyNet.controllerTest;

import fr.orionbs.SafetyNet.model.Firestation;
import fr.orionbs.SafetyNet.model.MedicalRecord;
import fr.orionbs.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static fr.orionbs.SafetyNet.controllerTest.PersonControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FireControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetPersonsAndFirestationAtAddress() throws Exception {
        // GIVEN
        Person person = new Person(null, "test5", "Test5", "testAddress3", "testCity", 1, "testPhone", "testMail");
        mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON));
        List<String> medications = new ArrayList<>();
        medications.add("medicationTest");
        List<String> allergies = new ArrayList<>();
        medications.add("allergieTest");
        MedicalRecord medicalRecord = new MedicalRecord(null,"test5","Test5","03/02/2010",medications,allergies);
        mockMvc.perform(post("/medicalRecord").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON));
        Firestation firestation = new Firestation(null,"testAddress3",3);
        mockMvc.perform(post("/firestation").accept(MediaType.APPLICATION_JSON).content(asJsonString(firestation)).contentType(MediaType.APPLICATION_JSON));

        // WHEN THEN
        mockMvc.perform(get("/fire").accept(MediaType.APPLICATION_JSON)
                .param("address","testAddress3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
