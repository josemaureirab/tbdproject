package cl.rollers.tbdproject;

import cl.rollers.tbdproject.DB.Seeder;
import cl.rollers.tbdproject.SQL.controllers.TaskController;
import cl.rollers.tbdproject.SQL.controllers.UserController;
import cl.rollers.tbdproject.SQL.controllers.VoluntaryController;
import cl.rollers.tbdproject.SQL.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
public class TbdprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TbdprojectApplication.class, args);

        Sql2o sql2o[] = new Sql2o[2];
        sql2o[0] = new Sql2o("jdbc:postgresql://localhost:5432/tbd1","postgres","secret");
        sql2o[1] = new Sql2o("jdbc:postgresql://localhost:5432/tbd2","postgres","secret");

        /*TaskController taskController = new TaskController(sql2o);
        UserController userController = new UserController(sql2o);
        VoluntaryController voluntaryController = new VoluntaryController(sql2o);*/

        Seeder seeder = new Seeder(sql2o);
        seeder.seedTasks();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
@Configuration
@EnableJpaAuditing
class DataJpaConfig {

    @Bean
    public AuditorAware<User> auditor() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getPrincipal)
            .map(User.class::cast);
    }
}
