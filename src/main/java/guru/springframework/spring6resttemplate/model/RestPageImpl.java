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
