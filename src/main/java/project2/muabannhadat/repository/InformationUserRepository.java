package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import project2.muabannhadat.model.InformationUser;

import java.util.List;


public interface InformationUserRepository extends JpaRepository<InformationUser, Long>, JpaSpecificationExecutor{
    InformationUser findInformationUserByUserName(String username);
    InformationUser findByInforId(Long id);
    List<InformationUser> findByInforIdIsLike(Long id);
    List<InformationUser> findByPhone(int phone);
    List<InformationUser> findByEmailIsLikeIgnoreCase(String email);
    InformationUser findByAddressLike(String address);
    List<InformationUser> findByFullNameIgnoreCase(String fullname);
    List<InformationUser> findByUserNameIsLikeIgnoreCase(String username);
}
