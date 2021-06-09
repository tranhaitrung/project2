package project2.muabannhadat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project2.muabannhadat.model.Article;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findByArticleId(Long id);

    //Tìm bởi tên quận
    List<Article> findArticlesByDistrictIgnoreCase(String district);

    //Tìm bởi tên TP
    @Query("SELECT a from Article  a where a.city_unsigned like concat('%',:city,'%')")
    List<Article> findArticlesByCityIgnoreCase(@Param("city") String city);

    //Tìm bởi tên đường
    List<Article> findArticlesByWardIgnoreCase(String ward);
    //Tìm bởi giá, trong khoảng giá
    List<Article> findArticlesByPriceBetween(Long a, Long b);
    //Tìm theo diện tích
    List<Article> findArticlesByAreaBetween(float a, float b);
    //Tìm theo hình thức form
    List<Article> findArticlesByForm(String form);
    //Tìm theo loại species
    List<Article> findArticlesBySpecies(String species);

    @Query("SELECT a from Article  a where a.title_unsigned like concat('%',:title,'%')")
    List<Article> findByTitleLIke(@Param("title") String title);

    List<Article> findArticlesByDeleted(int dlt);


    @Query("SELECT a FROM Article a WHERE a.form LIKE  concat('%',:hinhthuc,'%') and a.species LIKE CONCAT('%',:loai,'%')" +
            " AND a.city_unsigned LIKE CONCAT('%',:city,'%')" +
            " AND a.price BETWEEN :gia1 AND :gia2 " +
            " AND a.area BETWEEN  :dientich1 AND :dientich2 ")
    List<Article> findArticle(@Param("hinhthuc") String hinhthuc, @Param("loai") String loai,
                             @Param("city") String city,
                             @Param("gia1") Long gia1, @Param("gia2") Long gia2,
                             @Param("dientich1") Float dientich1, @Param("dientich2") Float dientich2);

    @Query("UPDATE Article a SET a.area = :area, a.city=:city, a.date_up=:date_up, a.detail =:detail, a.district=:district," +
            "a.form = :form, a.price = :price, a.species = :species, a.title = :title, a.ward= :ward," +
            "a.city_unsigned = :city_u, a.detail_unsigned= :detail_u, a.district_unsigned = :district_u," +
            "a.title_unsigned = :title_u, a.ward_unsigned = :ward_u WHERE a.articleId = :id")
    Article update(@Param("area") Float area, @Param("city") String city, @Param("date_up") Date date_up,
                   @Param("detail") String detail,
                   @Param("district") String district, @Param("form") String form, @Param("price") Long price,
                   @Param("species") String species, @Param("title") String title, @Param("ward") String ward,
                   @Param("city_u") String city_u, @Param("detail_u") String detail_u, @Param("district_u") String district_u,
                   @Param("title_u") String title_u, @Param("ward_u") String ward_u, @Param("id") Long id);

}
