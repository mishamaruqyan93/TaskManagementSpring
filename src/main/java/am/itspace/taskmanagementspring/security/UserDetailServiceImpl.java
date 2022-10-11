package am.itspace.taskmanagementspring.security;

import am.itspace.taskmanagementspring.entity.User;
import am.itspace.taskmanagementspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = repository.findByEmail(username);
        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException("username does not exists");
        }
        return new CurrentUser(byEmail.get());
    }
}
