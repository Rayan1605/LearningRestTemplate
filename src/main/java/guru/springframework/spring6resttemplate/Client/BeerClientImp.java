package guru.springframework.spring6resttemplate.Client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor

public class BeerClientImp implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;
    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();
//restTemplate.getForEntity()
// makes a GET request to the "https://localhost:8080/api/v1/beer" endpoint.

    //The first request specifies String.class as the response type,
        // so the response body is returned as a String.
        //The second request specifies Map.class, so the response is deserialized into a
        // Map.
        //The ResponseEntity contains both the response body and additional metadata
        // like status code.
        //So the first call gets the beer API response as a String, the second as a Map.
        ResponseEntity<String> stringResponse = restTemplate.getForEntity(
                "https://localhost:8080/api/v1/beer", String.class
        );

        ResponseEntity<Map> entity = restTemplate.getForEntity(
                "https://localhost:8080/api/v1/beer", Map.class
        );
        System.out.println(stringResponse.getBody());
        return null;
    }
}
