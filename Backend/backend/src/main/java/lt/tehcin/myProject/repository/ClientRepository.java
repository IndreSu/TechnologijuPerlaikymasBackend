package lt.tehcin.myProject.repository;

import lt.tehcin.myProject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

//    List<Client> findAllByOrderByCreatedDateDesc();
    boolean existsByName(String name);
    boolean existsBySurname(String surname);

    boolean existsByDateOfBirth(String dateOfBirth);

}
