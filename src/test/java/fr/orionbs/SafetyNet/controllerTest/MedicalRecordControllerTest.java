package fr.orionbs.SafetyNet.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalRecords").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMedicalRecord() throws Exception {
        //GIVEN
        List<String> medications = new ArrayList<>();
        medications.add("medicationTest");
        List<String> allergies = new ArrayList<>();
        medications.add("allergieTest");
        MedicalRecord medicalRecord = new MedicalRecord(null,"test11","Test11","03/02/2010",medications,allergies);
        mockMvc.perform(post("/medicalRecord").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateMedicalRecord() throws Exception {

        //GIVEN
        List<String> medications = new ArrayList<>();
        medications.add("medicationTest");
        List<String> allergies = new ArrayList<>();
        medications.add("allergieTest");
        MedicalRecord medicalRecord = new MedicalRecord(null,"test12","Test12","03/02/2010",medications,allergies);
        mockMvc.perform(post("/medicalRecord").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON));
        // WHEN
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("firstName","test12");
        listParams.add("lastName","Test12");
        medicalRecord.setBirthdate("05/02/2010");
        mockMvc.perform(put("/medicalRecord")
                .params(listParams)
                .content(asJsonString(medicalRecord))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("firstName","test11");
        listParams.add("lastName","Test11");
        mockMvc.perform(delete("/medicalRecord")
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
