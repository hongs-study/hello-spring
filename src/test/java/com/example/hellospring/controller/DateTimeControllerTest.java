package com.example.hellospring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@AutoConfigureMockMvc
@WebMvcTest(DateTimeController.class)
class DateTimeControllerTest {

    @Autowired MockMvc mockMvc;

    @DisplayName("1.GET @RequestParam 사용시 @DateTimeFormat 로 변환된다")
    @Test
    void testGet1() throws Exception {
        //given
        MockHttpServletRequestBuilder url = get("/date/get/1")
            .param("date", "2021-11-11")
            .param("dateTime", "2021-11-11T13:00:01");
        
        //when then
        String contentAsString = mockMvc.perform(url)
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertThat(contentAsString).isEqualTo("1. get @RequestParam @DateTimeFormat OK");
    }

    @DisplayName("2.GET @ModelAttribute 사용시 @DateTimeFormat 로 변환된다")
    @Test
    void testGet2() throws Exception {
        //given
        MockHttpServletRequestBuilder url = get("/date/get/2")
            .param("date", "2021-11-11")
            .param("dateTime", "2021-11-11T13:00:01");

        //when then
        String contentAsString = mockMvc.perform(url)
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(contentAsString).isEqualTo("2. get @ModelAttribute @DateTimeFormat OK");
    }

    @DisplayName("3.GET @ModelAttribute 사용시 @JsonFormat 로 변환되지 않는다.")
    @Test
    void testGet3() throws Exception {
        //given
        MockHttpServletRequestBuilder url = get("/date/get/3")
            .param("date", "2021-11-11")
            .param("dateTime", "2021-11-11T13:00:01");

        //when then
        String contentAsString = mockMvc.perform(url)
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(contentAsString).isEqualTo("3. get @ModelAttribute @JsonFormat OK?");
    }

    @DisplayName("4.POST @RequestBody 사용시 @JsonFormat 로 변환된다.")
    @Test
    void testGet4() throws Exception {
        String jsonData = """
            {
                "date" : "2021-11-11",
                "dateTime" : "2021-11-11T13:00:01"
            }
            """;

        //given
        MockHttpServletRequestBuilder url = post("/date/post/4")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonData);

        //when then
        String contentAsString = mockMvc.perform(url)
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(contentAsString).isEqualTo("4. post @RequestBody @JsonFormat OK");
    }
    @DisplayName("5.POST @RequestBody 사용시 @DateTimeFormat 로 변환된다.")
    @Test
    void testGet5() throws Exception {
        String jsonData = """
            {
                "date" : "2021-11-11",
                "dateTime" : "2021-11-11T13:00:01"
            }
            """;

        //given
        MockHttpServletRequestBuilder url = post("/date/post/5")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonData);

        //when then
        String contentAsString = mockMvc.perform(url)
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString(StandardCharsets.UTF_8);

        Assertions.assertThat(contentAsString).isEqualTo("5. post @RequestBody @DateTimeFormat OK");
    }
}