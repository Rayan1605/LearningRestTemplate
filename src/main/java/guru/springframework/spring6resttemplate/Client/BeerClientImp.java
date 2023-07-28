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
        //So with String.class:
        //
        //You will get the raw JSON text as a String object.
        //You will need to parse it yourself, for example into a JSONObject.
        //Useful if you want to parse the JSON manually.
        //With Map.class:
        //
        //The JSON is automatically parsed and deserialized into a Map.
        //Nested JSON objects become nested Maps.
        //JSON arrays become Lists.
        //Less code, but you lose some control over parsing.
        //Easier if you just need to access the data.
        //So in summary:
        //
        //String returns raw JSON text
        //Map parses the JSON automatically into a nested data structure
        System.out.println(stringResponse.getBody());
        return null;
    }
}
