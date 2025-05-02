package com.furia.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furia.chat.dto.ChatMessageRequest;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.type.TypeReference;



@SpringBootTest
@AutoConfigureMockMvc
public class ChatMessageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Rollback(false)
    @WithMockUser(username = "furioso1", roles = {"FAN"})
    void testSendMessage() throws Exception {
        ChatMessageRequest message = new ChatMessageRequest("new message");
        mockMvc.perform(post("/chat").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(message))).andExpect(status().isCreated());

        MockHttpServletResponse response =  mockMvc.perform(get("/chat").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
    }

    @Test
    @WithMockUser(username = "furioso1", roles = {"FAN"})
    void testeFanDeletingYourMessage() throws Exception {
        ChatMessageRequest message = new ChatMessageRequest("new message");
        mockMvc.perform(post("/chat").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(message)));

        MockHttpServletResponse response =  mockMvc.perform(get("/chat").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        List<ChatMessage> messages = mapper.readValue(
                response.getContentAsString(),
                new TypeReference<>() {}
        );
        String id = messages.get(messages.size()-1).getId();

        mockMvc.perform(delete("/chat/"+id)).andExpect(status().isOk());
    }

    @Test
    void testeFanCannotDeleteMessageOfAnotherFan() throws Exception {
        Fan fan1 = new Fan("furioso4", "f4@gmail.com", "123456");
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(fan1)));
        MockHttpServletResponse response1 = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(fan1))).andExpect(status().isOk()).andReturn().getResponse();
        String token1 = response1.getContentAsString();

        ChatMessageRequest message = new ChatMessageRequest("new message");
        mockMvc.perform(post("/chat").header("Authorization", "Bearer "+token1)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(message)));

        Fan fan2 = new Fan("furioso5", "f5@gmail.com", "123456");
        mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(fan2)));

        MockHttpServletResponse response2 = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(fan2))).andExpect(status().isOk()).andReturn().getResponse();
        String token2 = response2.getContentAsString();

        MockHttpServletResponse response3 = mockMvc.perform(get("/chat").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+token1)).andExpect(status().isOk()).andReturn().getResponse();
        List<ChatMessage> messages = mapper.readValue(
                response3.getContentAsString(),
                new TypeReference<>() {}
        );
        String id = messages.get(messages.size()-1).getId();

        mockMvc.perform(delete("/chat/"+id).header("Authorization", "Bearer "+token2))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "furioso1", roles = {"ADMIN"})
    void testeRoleAdminCanDeleteMessageOfAnotherFan() throws Exception {
        ChatMessageRequest message = new ChatMessageRequest("new message");
        mockMvc.perform(post("/chat").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(message)));

        MockHttpServletResponse response =  mockMvc.perform(get("/chat").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();
        List<ChatMessage> messages = mapper.readValue(
                response.getContentAsString(),
                new TypeReference<>() {}
        );
        String id = messages.get(messages.size()-1).getId();

        mockMvc.perform(delete("/chat/"+id)).andExpect(status().isOk());
    }


}
