package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.InformationUser;

import java.util.List;

@Repository
public interface InformationUserRepository extends JpaRepository<InformationUser, Long>, JpaSpecificationExecutor{
    InformationUser findInformationUserByUserName(String username);

    @Query("SELECT a from InformationUser a where a.inforId = :id ")
    InformationUser findByInforId(@Param("id") Long id);

    @Query("SELECT a from InformationUser a where a.inforId = :id ")
    List<InformationUser> findByInforIdIsLike(@Param("id") Long id);

    @Query("SELECT u from InformationUser  u where u.phone like concat('%',:phone,'%')")
    List<InformationUser> findByPhone(@Param("phone") String phone);

    @Query("SELECT u from InformationUser  u where u.email like concat('%',:email,'%')")
    List<InformationUser> findByEmailIsLikeIgnoreCase(@Param("email") String email);
    InformationUser findByAddressLike(String address);

    @Query("SELECT u from InformationUser  u where u.fullName like concat('%',:fullname,'%')")
    List<InformationUser> findByFullNameIgnoreCase(@Param("fullname") String fullname);

    @Query("SELECT u from InformationUser  u where u.userName like concat('%',:username,'%')")
    List<InformationUser> findByUserNameIsLikeIgnoreCase(@Param("username") String username);

    List<InformationUser> findByDeleted(int dlt);

}
