package am.itspace.taskmanagementspring;

import am.itspace.taskmanagementspring.entity.Role;
import am.itspace.taskmanagementspring.entity.User;
import am.itspace.taskmanagementspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
@EnableAsync
public class TaskManagementSpringApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSpringApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> byEmail = userRepository.findByEmail("admin@email.com");
        if (byEmail.isEmpty()) {
            userRepository.save(User.builder()
                    .name("name")
                    .surname("surname")
                    .email("admin@email.com")
                    .password(passwordEncoder().encode("admin"))
                    .role(Role.MANAGER)
                    .build());
        }
    }
}
