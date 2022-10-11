package am.itspace.taskmanagementspring.repository;

import am.itspace.taskmanagementspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    List<User> findTop10ByNameOrderByIdDesc(String name);
}
