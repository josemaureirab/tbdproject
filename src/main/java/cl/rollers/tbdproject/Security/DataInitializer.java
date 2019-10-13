package cl.rollers.tbdproject.Security;

import cl.rollers.tbdproject.SQL.firstDataSource.dao.FDSUserDao;
import cl.rollers.tbdproject.SQL.firstDataSource.models.FDSUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Order(1)
public class DataInitializer implements CommandLineRunner {
    @Autowired
    FDSUserDao FDSusers;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<FDSUser> FDSuserList = FDSusers.findAll();

        if(FDSuserList.size() == 0){
            this.FDSusers.save(FDSUser.builder()
                .username("user")
                .name("user")
                .lastName("user")
                .age(77)
                .rut("77.777.777-7")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList( "ROLE_USER"))
                .build()
            );
            this.FDSusers.save(FDSUser.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .name("admin")
                .lastName("admin")
                .age(77)
                .rut("77.777.777-7")
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build()
            );
        }
    }
}
