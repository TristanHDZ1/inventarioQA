package inventarioQA.mx.InventarioMVC.Security;

import inventarioQA.mx.InventarioMVC.Models.User;
import inventarioQA.mx.InventarioMVC.Models.Role;
import inventarioQA.mx.InventarioMVC.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(" Autenticando usuario: " + username);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println(" Usuario NO encontrado en la base de datos.");
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        System.out.println(" Usuario encontrado: " + user.getUsername());
        System.out.println(" Roles encontrados: " + user.getRoles());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
            .map(role -> {
                System.out.println(" Rol: " + role.getName()); // muestra cada rol
                return new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase());
            })
            .collect(Collectors.toList());
    }


    
}
