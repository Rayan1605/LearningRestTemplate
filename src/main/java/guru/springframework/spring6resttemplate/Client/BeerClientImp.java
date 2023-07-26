package guru.springframework.spring6resttemplate.Client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import org.springframework.data.domain.Page;

public class BeerClientImp implements BeerClient {
    @Override
    public Page<BeerDTO> listBeers() {
        return null;
    }
}
