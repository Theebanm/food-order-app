package orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orderapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
