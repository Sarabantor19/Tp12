package cigma.pfe.Repositories;

import cigma.pfe.Models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientDao extends CrudRepository<Client,Long> {
  //  List<Client> findByName(String name);


}
