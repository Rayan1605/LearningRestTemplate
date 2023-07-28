package guru.springframework.spring6resttemplate.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

//This is telling to ignore pageable property
@JsonIgnoreProperties(ignoreUnknown = true, value = "pageable")
public class RestPageImpl<BeerDto> extends PageImpl<BeerDto> {
//This class represents a single page in a paginated list of data.
// It is used to return paginated results from an API.
//The constructor takes in:
//content - The data on this page
//page - The page number
//size - Number of items per page
//totalElements - Total number of items overall
//These values are mapped automatically from JSON using the @JsonProperty annotations.
//For example, if requesting page 2 of beers API:
//The JSON  contain:
//{
//  "content": [/* beers here */],
//  "number": 2,
//  "size": 10,
//  "totalElements": 52
//}
//The constructor would map these properties to the RestPageImpl to construct it with the
// provided pagination metadata and beer data.
//Then this RestPageImpl can be returned directly from a Spring REST controller to
// provide the paginated response model.
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPageImpl(@JsonProperty("content") List<BeerDto> content,
                        @JsonProperty("number") int page,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") long totalElements) {
        super((List<BeerDto>) content, PageRequest.of(page, size), totalElements);
    }


    public RestPageImpl(List<BeerDto> content, Pageable pageable, long total) {
        super((List<BeerDto>) content, pageable, total);
    }

    public RestPageImpl(List<BeerDto> content) {
        super((List<BeerDto>) content);
    }
}
