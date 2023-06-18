//https://spring.io/guides/gs/testing-web/

package com.snhu.travel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TravelApplicationTests {
	

	@Value(value="${local.server.port}")
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	// @Test	//This test should fail. Uncomment it to verify that the tests are indeed running properly.
	// public void avoidFalsePass() throws Exception {
	// 	assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test",
	// 			String.class)).contains("please fail");
	// }

	@Test	//This is effectively just a template test. It will work, but it should be used as a base to populate.
	public void testName() throws Exception {
		String endpoint = "http://localhost:" + port + "/";
		String expectedResponse = "";
		assertThat(this.restTemplate.getForObject(endpoint, String.class)).contains(expectedResponse);
	}

	@Test
	public void testEndpointVerbiageShouldBeAccurate() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test",
				String.class)).contains("test string output");
	}

	@Test
	public void securityHealthCheckShouldBeLive() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/security",
				String.class)).contains("test security endpoints");
	}

	@Test	//This is effectively just a template test. It will work, but it should be used as a base to populate.
	public void ableToCreateUser() throws Exception {
		String endpoint = "http://localhost:" + port + "/security/register?uname=unittest&pass=testuser";
		String expectedResponse = null;
		//TODO: Check response code rather than response body.
		assertThat(this.restTemplate.getForObject(endpoint, String.class)).isNullOrEmpty();
	}

	@Test	//This is effectively just a template test. It will work, but it should be used as a base to populate.
	public void ableToValidateUser() throws Exception {
		String endpoint = "http://localhost:" + port + "/security/validate?uname=unittest&pass=testuser";
		String expectedResponse = "";
		assertThat(this.restTemplate.getForObject(endpoint, String.class)).contains(expectedResponse);
	}

	@Test	//This is effectively just a template test. It will work, but it should be used as a base to populate.
	public void ableToDeleteUser() throws Exception {
		String endpoint = "http://localhost:" + port + "/security/delete?uname=unittest&pass=testuser";
		String expectedResponse = null;
		//TODO: Check response code rather than response body.
		assertThat(this.restTemplate.getForObject(endpoint, String.class)).isNullOrEmpty();
	}
	
}
