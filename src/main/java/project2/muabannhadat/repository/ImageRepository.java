package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.Image;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByImageId(Long id);

}
