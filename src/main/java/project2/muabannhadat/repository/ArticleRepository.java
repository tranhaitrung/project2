package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.Article;

import java.util.List;
import java.util.Set;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByArticleId(Long id);

    //Tìm bởi tên quận
    List<Article> findArticlesByDistrictIgnoreCase(String district);
    //Tìm bởi tên TP
    List<Article> findArticlesByCityIgnoreCase(String city);
    //Tìm bởi tên đường
    List<Article> findArticlesByWardIgnoreCase(String ward);
    //Tìm bởi giá, trong khoảng giá
    List<Article> findArticlesByPriceBetween(int a, int b);
    //Tìm theo diện tích
    List<Article> findArticlesByAreaBetween(float a, float b);
    //Tìm theo hình thức form
    List<Article> findArticlesByForm(String form);
    //Tìm theo loại species
    List<Article> findArticlesBySpecies(String species);

    @Query("SELECT a FROM Article a WHERE a.form LIKE  concat('%',:hinhthuc,'%') and a.species LIKE CONCAT('%',:loai,'%')" +
            " AND a.city_unsigned LIKE CONCAT('%',:city,'%')" +
            " AND a.price BETWEEN :gia1 AND :gia2 " +
            " AND a.area BETWEEN  :dientich1 AND :dientich2 ")
    List<Article> findArticle(@Param("hinhthuc") String hinhthuc, @Param("loai") String loai,
                             @Param("city") String city,
                             @Param("gia1") Long gia1, @Param("gia2") Long gia2,
                             @Param("dientich1") Float dientich1, @Param("dientich2") Float dientich2);
}
