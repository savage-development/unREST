package com.savadev.unrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() throws URISyntaxException {
        var uri = new URI("var.ini");
    }

    public static void main(String[] args) throws URISyntaxException {
        var uri = new URI("var.ini");
        System.out.println(uri);
    }

}
