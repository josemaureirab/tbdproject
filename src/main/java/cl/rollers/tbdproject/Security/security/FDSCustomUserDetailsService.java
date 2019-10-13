package cl.rollers.tbdproject.Security.security;

import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSUserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FDSCustomUserDetailsService implements UserDetailsService {

    private FDSUserDao users;

    public FDSCustomUserDetailsService(FDSUserDao users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
}
