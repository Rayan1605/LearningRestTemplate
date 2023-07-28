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
