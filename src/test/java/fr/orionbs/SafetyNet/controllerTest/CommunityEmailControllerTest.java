package fr.orionbs.SafetyNet.controllerTest;

import fr.orionbs.SafetyNet.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static fr.orionbs.SafetyNet.controllerTest.PersonControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CommunityEmailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetEmailsFilterByTown() throws Exception {
        Person person = new Person(null, "test4", "Test4", "testAddress", "testCity", 1, "testPhone", "testMail");
        mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON));

        MultiValueMap<String,String> listParams = new LinkedMultiValueMap<>();
        listParams.add("city","testCity");
        mockMvc.perform(get("/communityEmail")
                .params(listParams))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
