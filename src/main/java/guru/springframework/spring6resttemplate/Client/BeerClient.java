package guru.springframework.spring6resttemplate.Client;

import guru.springframework.spring6resttemplate.model.BeerDTO;

public interface BeerClient {

    Page<BeerDTO> listBeers();
}
