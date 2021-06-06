package project2.muabannhadat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    Boolean deleteUserByUserName(String username);
}