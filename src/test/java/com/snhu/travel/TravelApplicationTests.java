package com.snhu.travel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TravelApplicationTests {
	

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testEndpointShouldReturnSuccessResponseCode() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test",
				String.class)).contains("test string output");
	}

	@Test	//This test should fail. Uncomment it to verify that the tests are indeed running properly.
	public void avoidFalsePass() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test",
				String.class)).contains("please fail");
	}

}
