package com.savadev.unrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigTest {

    private WebTestClient webTestClient;

    private final String jwt = "eyJhbGciOiJFUzI1NiJ9.eyJzdWIiOiJyb290Iiwicm9sZXMiOlsiUk9MRV9TWVMiLCJST0xFX1dIRUVMIiwiUk9MRV9BRE0iLCJST0xFX0RBRU1PTiIsIlJPTEVfRE9DS0VSIiwiUk9MRV9ST09UIiwiUk9MRV9CSU4iLCJST0xFX0RJU0siLCJST0xFX0FVRElPIl19.QaYRj166XOfuIIT7d_39rU3xj7mO_JZHZ4rSd1ajWKY95KyNhR5qMomiEPqch33JExoJOjbIJOYhnxlbfoU6cQ";

    @BeforeEach
    public void setUp(ApplicationContext applicationContext, RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void example() {
        this.webTestClient.get().uri("/api/config")
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers -> headers.setBearerAuth(jwt))
                .exchange().expectStatus().isOk()
                .expectBody().consumeWith(document("index"));
    }

}
