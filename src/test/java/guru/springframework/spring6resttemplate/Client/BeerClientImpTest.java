package guru.springframework.spring6resttemplate.Client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerClientImpTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void listBeers() {
        beerClient.listBeers();
    }
}