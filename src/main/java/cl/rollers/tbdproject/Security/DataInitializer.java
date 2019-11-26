package cl.rollers.tbdproject.Security;

import cl.rollers.tbdproject.SQL.dao.UserDao;
import cl.rollers.tbdproject.SQL.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/*@Component
@Slf4j
@Order(1)*/
public class DataInitializer implements CommandLineRunner {
    @Autowired
    UserDao users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        /*List<User> userList = users.findAll();

        if(userList.size() == 0){
            this.users.save(User.builder()
                .username("user")
                .name("user")
                .lastName("user")
                .age(77)
                .rut("77.777.777-7")
                .password(this.passwordEncoder.encode("password"))
                .rolesList(Arrays.asList( "normalUser"))
                .build()
            );
            this.users.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .name("admin")
                .lastName("admin")
                .age(77)
                .rut("77.777.777-7")
                .rolesList(Arrays.asList("normalUser", "adminUser"))
                .build()
            );
        }*/
    }
}
