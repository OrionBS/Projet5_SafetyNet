package fr.orionbs.SafetyNet.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.orionbs.SafetyNet.model.Firestation;
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
public class FirestationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetFirestations() throws Exception {
        mockMvc.perform(get("/firestations").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddFirestation() throws Exception {
        //GIVEN

        Firestation firestation = new Firestation(null,"testAddress11",1);
        mockMvc.perform(post("/firestation").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(firestation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateFirestation() throws Exception {

        //GIVEN
        Firestation firestation = new Firestation(null,"testAddress12",2);
        mockMvc.perform(post("/firestation").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(firestation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // WHEN
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("address","testAddress12");
        firestation.setStation(2);
        mockMvc.perform(put("/firestation")
                .params(listParams)
                .content(asJsonString(firestation))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testNotFoundUpdateFirestation() throws Exception {
        //GIVEN

        //WHEN

        //THEN
        mockMvc.perform(put("/firestation")
                .param("address","testAddressDoesn'tExist")
                .content(asJsonString(Firestation.builder().build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteFirestationByAddress() throws Exception {
        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("address","testAddress11");
        mockMvc.perform(delete("/firestation")
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
