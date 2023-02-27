package lt.tehcin.myProject.repository;

import lt.tehcin.myProject.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //    List<Inventory> findAllByOrderByCreatedDateDesc();

}
