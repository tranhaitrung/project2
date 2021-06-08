package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;

import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@RestControllerAdvice
public class SuaBaiController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private InformationUserService informationUserService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostArticleService postArticleService;

    @Autowired
    private AvatarService avatarService;

    private String username;

    private Long articleId;
    @GetMapping("/sua-bai-viet/{id}")
    public ModelAndView suaBaiViet(@PathVariable("id") Long id){
        username = AuthenticationSystem.getUsernameLogined();

        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleById(id);
        String username = postService.findUsername(id);
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(article == null || informationUser == null){
            System.out.println("có cái null");
        }
        System.out.println("vào đây nè");
        modelAndView.addObject("article",article);
        modelAndView.addObject("inforUser", informationUser);
        modelAndView.setViewName("user/sua-bai");
        return modelAndView;
    }


    @PostMapping("/chi-tiet-bai-viet/{id}")
    public ModelAndView suaBaiViet(@PathVariable("id") Long id ,Article article, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult) throws RoleNotFoundException {
        System.out.println("update article");

        ModelAndView modelAndView = new ModelAndView();
        // Lấy tên tài khoản đã đăng nhập
        username = AuthenticationSystem.getUsernameLogined();

        List<Image> images = new ArrayList<>();

        // Lấy thông tin tài khoản
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);

        //Lấy post để kiểm tra xem tài khoản đăng nhập là chủ bài viết không
        Post p = postService.findByArticleId(id);
        if (!p.getUserName().equals(username)){
            throw new RoleNotFoundException("don't has role to edit this article");
        }

        if(bindingResult.hasErrors() || informationUser == null){
            modelAndView.setViewName("user/sua-bai");
        }else{
            /**
             * Sửa bài viết cần cập nhật ảnh
             * Cập nhật bài viết
             */
            article.setArticleId(id);
            articleService.updateArticle(article);

            System.out.println("Update done!");

            //Nếu có ảnh truyền vào thì sẽ xóa hết ảnh cũ, cập nhật ảnh mơi
            if (files.length > 0){
                System.out.println("Into file!");
                System.out.println(files.length);

                List<PostArticle> postArticles = postArticleService.findByArticleId(id);
                if (!postArticles.isEmpty()){
                    for (PostArticle pa : postArticles){
                        Image image = imageService.findImageByImageId(pa.getImageId());
                        imageService.deleteImage(image);
                        postArticleService.delelePostArticle(pa);
                    }
                }
                System.out.println("delete images done!");

                Arrays.asList(files)
                        .forEach(f ->{
                            try {
                                byte[] fileContent = f.getBytes();
                                String content = Base64.getEncoder().encodeToString(fileContent);
                                Image image = new Image();
                                image.setImageContent(content);
                                imageService.addImage(image);
                                PostArticle postArticle = new PostArticle();
                                postArticle.setArticleId(article.getArticleId());
                                postArticle.setImageId(image.getImageId());
                                postArticleService.addPostArticle(postArticle);
                                images.add(image);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
            }


            Avatar avatar = avatarService.findByUserName(username);

            /**
             * Đoạn kiểm tra login
             */
            String username1 = AuthenticationSystem.getUsernameLogined();;
            Avatar avatar1 = new Avatar();
            avatar1.setImage("abc");
            if (!username1.equals("anonymousUser")){
                System.out.println("logined : " + username1);
                avatar1 = avatarService.findByUserName(username1);
                modelAndView.addObject("avatar1", avatar1.getImage());
            }else {
                username1 = null;
            }
            modelAndView.addObject("username", username1);


            modelAndView.addObject("inforUser",informationUser);
            modelAndView.addObject("article",article);
            modelAndView.addObject("images", images);
            modelAndView.addObject("avatar", avatar);
            modelAndView.addObject("successMessage","Bài viết đã được cập nhật");
            modelAndView.setViewName("user/chi-tiet-bai-viet");
            System.out.println("success!!");
        }

        return modelAndView;
    }
}
