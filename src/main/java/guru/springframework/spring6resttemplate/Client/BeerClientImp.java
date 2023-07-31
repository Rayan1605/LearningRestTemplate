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

import java.net.URI;
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
//    private static final String GET_BEER_PATH  = "/api/v1/beer";
    @Override
    public BeerDTO CreateBeer(BeerDTO beerDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//Let's say we want to create a new beer and then get the beer we just created.
//First we need to create the beer.
//To create it, we use RestTemplate's postForLocation method.
//
//We pass it the URL path to post to, like "/beer".
//We also pass it the Beer object we want to create.
//This will POST the Beer to the server and create it.
//The postForLocation method returns a URI object.
//This URI contains the URL to the created beer resource.
//Next we want to get the beer we just created using the URI.
//We use RestTemplate's getForObject method:
//We pass it the URI's path, uri.getPath()
//This points to the new beer URL that was returned.
//We also say to convert the response to a Beer object.
//This will return the Beer object that was created.
//So in simple terms:
//First we POST a new beer to create it
//This returns a URI to that new beer resource
//Then we GET that URI to fetch the created beer
//We use RestTemplate to easily call the API to create and then read the beer.
        URI uri = restTemplate.postForLocation(GET_BEER_PATH, beerDTO);
        return restTemplate.getForObject(uri.getPath(), BeerDTO.class);


    }

    //So we are getting the updated beer by ID.
    //We are using RestTemplate's put method to update the beer.
    //We pass it the URL path to PUT to, like "/beer/{beerId}".
    //Second we pass it the updated Beer object.
    //Third we pass it the ID of the beer to update.
    @Override
    public BeerDTO updateBeer(BeerDTO beerDTO) {
RestTemplate restTemplate = restTemplateBuilder.build();
  restTemplate.put(GET_BEER_BY_ID_PATH, beerDTO, beerDTO.getId());

   return getBeerById(beerDTO.getId());
    }

    @Override
    public void deleteById(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //We are using RestTemplate's delete method to delete the beer.
        //We pass it the URL path to DELETE to, like "/beer/{beerId}".
        //Second we pass it the ID of the beer to delete.
        restTemplate.delete(GET_BEER_BY_ID_PATH, id);
    }
}



