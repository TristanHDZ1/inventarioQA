package inventarioQA.mx.InventarioMVC.Repository;

import inventarioQA.mx.InventarioMVC.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
