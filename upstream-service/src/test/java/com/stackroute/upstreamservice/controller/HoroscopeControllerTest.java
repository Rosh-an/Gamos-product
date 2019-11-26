package com.stackroute.upstreamservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.upstreamservice.exception.GlobalExceptionHandler;
import com.stackroute.upstreamservice.service.AmazonClient;
import com.stackroute.upstreamservice.service.JaveService;
import com.stackroute.upstreamservice.service.RabbitMQSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HoroscopeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Object data;

    @MockBean
    private AmazonClient client;
    @MockBean
    private JaveService javeService;

    @MockBean
    private RabbitMQSender sender;

    @InjectMocks
    private HoroscopeController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();
        data = new String("Febin");
    }

    @Test
    public void sendHoroscopeDetailsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/horoscope")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(data)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test(expected = Exception.class)
    public void sendHoroscopeDetailsFailureTest() throws Exception {
        doThrow(new Exception("Horoscope Details failed to send")).when(sender).sendMessage(any(), any(), any());
        mockMvc.perform(MockMvcRequestBuilders.post("/horoscope")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(data)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}