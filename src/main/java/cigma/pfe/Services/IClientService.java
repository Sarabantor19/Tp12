package cigma.pfe.Services;

import cigma.pfe.Models.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface IClientService {
 Client save(Client clt);

 Client modify(Client clt);
 void remove(long idClt);

 Client getOne(long idClt);

 List<Client> getAll();


}
