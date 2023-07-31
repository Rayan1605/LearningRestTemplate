package guru.springframework.spring6resttemplate.Client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {

    Page<BeerDTO> listBeers();

    BeerDTO getBeerById(UUID id);
    BeerDTO CreateBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(BeerDTO beerDTO);
}
