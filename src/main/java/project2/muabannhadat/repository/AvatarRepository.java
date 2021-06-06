package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findByUsername(String username);

    Boolean deleteAvatarByUsername(String username);
}
