package inventarioQA.mx.InventarioMVC.Service;
import inventarioQA.mx.InventarioMVC.Models.Usuario;
import inventarioQA.mx.InventarioMVC.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticar(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            // Compara con BCrypt (más abajo te muestro cómo guardar encriptado)
            return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder()
                    .matches(password, usuario.getPassword());
        }
        return false;
    }
}
