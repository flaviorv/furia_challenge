package com.furia.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furia.chat.model.ChatMessage;
import com.furia.chat.model.Fan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class FanTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Rollback(false)
    void testRegisterFan() throws Exception {
        Fan fan = new Fan("furioso3", "f3@gmail.com", "1231231");
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(fan))).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "furioso4", roles = {"FAN"})
    void testRoleFanCannotEditAnotherFan() throws Exception {

        Fan furioso1 = new Fan(
                "furioso1",
                "new_email@gmail.com",
                "new_password",
                "new_bio",
                "new_profile_image",
                "new_role"
        );

        mockMvc.perform(put("/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(furioso1)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "furioso4", roles = {"ADMIN"})
    void testRoleAdminCannotEditAnotherFan() throws Exception {

        Fan furioso1 = new Fan(
                "furioso1",
                "new_email@gmail.com",
                "new_password",
                "new_bio",
                "new_profile_image",
                "new_role"
        );

        mockMvc.perform(put("/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(furioso1)))
                .andExpect(status().isForbidden());
    }

    @Test
    @Rollback(false)
    @WithMockUser(username = "furioso4", roles = {"ADMIN"})
    void testRoleAdminCanDeleteAnotherFan() throws Exception {

        Fan furioso1 = new Fan(
                "furioso1",
                "new_email@gmail.com",
                "new_password",
                "new_bio",
                "new_profile_image",
                "new_role"
        );

        mockMvc.perform(delete("/delete-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(furioso1)))
                .andExpect(status().isOk());
    }

}
