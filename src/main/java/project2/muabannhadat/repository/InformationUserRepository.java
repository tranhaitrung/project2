package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project2.muabannhadat.model.InformationUser;


public interface InformationUserRepository extends JpaRepository<InformationUser, String> {
    InformationUser findInformationUserByUserName(String username);
}
