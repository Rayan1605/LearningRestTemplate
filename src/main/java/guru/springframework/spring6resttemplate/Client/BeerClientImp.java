package guru.springframework.spring6resttemplate.Client;

import com.fasterxml.jackson.databind.JsonNode;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.RestPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class BeerClientImp implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;
    private static final String Beer_URL = "https://localhost:8080";
    private static final String GET_BEER_PATH  = "/api/v1/beer";
    private static final String GET_BEER_BY_ID_PATH  = "/api/v1/beer/{beerId}";
    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(Beer_URL + "/api/v1/beer")
                .queryParam("pageNumber", 0)
                .queryParam("pageSize", 25);
//restTemplate.getForEntity()
// makes a GET request to the "https://localhost:8080/api/v1/beer" endpoint.

    //The first request specifies String.class as the response type,
        // so the response body is returned as a String.
        //The second request specifies Map.class, so the response is deserialized into a
        // Map.
        //The ResponseEntity contains both the response body and additional metadata
        // like status code.
        //So the first call gets the beer API response as a String, the second as a Map.
        ResponseEntity<RestPageImpl> stringResponse = restTemplate.getForEntity(
                Beer_URL +"/api/v1/beer", RestPageImpl.class
        );
            return null;
        /*
        ResponseEntity<Map> entity = restTemplate.getForEntity(
                 Beer_URL +"/api/v1/beer", Map.class
        );
//This is using the Jackson JSON parser to deserialize the response into a JsonNode.
        //Jackson is a popular open source Java library used for:
        //
        //JSON serialization/deserialization - converting between JSON strings and Java objects.
        // Jackson provides simple annotations for mapping Java classes to JSON and vice versa.
        //JSON parsing - reading JSON into Java objects and writing Java objects into JSON.

        ResponseEntity<JsonNode> JsonResponse = restTemplate.getForEntity(
                Beer_URL +"/api/v1/beer", JsonNode.class
        );
        //So getting the

        JsonResponse.getBody().get("content").forEach(node ->
                System.out.println(node.get("beerName").asText()));
//Gets the body of the JsonResponse which contains the parsed JSON content.
//.get("content")
//
//Calls get() on the body to retrieve the value of the "content" field.
//This drills into the JSON structure to get the "content" array.
//.forEach(node -> ...)
//
//Loops through each element in the "content" array.
//The lambda parameter node refers to the current element.
//node.get("beerName")
//
//For each node/element, get the value of the "beerName" field.
//.asText()
//
//Converts the beerName value to a String.




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
    */
    }
//The RestTemplate makes the GET request to the specified endpoint path
//The id parameter value gets substituted into the path
//The JSON response body gets deserialized into a BeerDTO
//And the BeerDTO is returned containing the response data
//This allows making a GET call and parsing the JSON result in one line with RestTemplate.
// The id parameter also demonstrates a variable path segment.
//    private static final String GET_BEER_BY_ID_PATH  = "/api/v1/beer/{beerId}";
    @Override
    public BeerDTO getBeerById(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForObject(GET_BEER_BY_ID_PATH, BeerDTO.class, id);
    }

    @Override
    public BeerDTO CreateBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<BeerDTO> responseEntity = restTemplate.postForEntity(
                GET_BEER_PATH, beerDTO, BeerDTO.class
        );


    }


}
