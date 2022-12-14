package com.example.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/bookstore")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void shouldReturnDefaultMessageForIdNotFound() throws Exception {
		this.mockMvc.perform(get("/api/bookstore/6304b7c4ba712f15c72466ce1")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturnDefaultMessageForId() throws Exception {
		this.mockMvc.perform(get("/api/bookstore/6304b7c4ba712f15c72466ce")).andDo(print()).andExpect(status().isOk());
	}

}