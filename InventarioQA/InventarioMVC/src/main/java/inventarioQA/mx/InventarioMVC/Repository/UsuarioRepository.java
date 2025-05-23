package inventarioQA.mx.InventarioMVC.Repository;

import inventarioQA.mx.InventarioMVC.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
