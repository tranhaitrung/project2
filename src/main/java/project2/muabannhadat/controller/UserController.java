package project2.muabannhadat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.*;
import project2.muabannhadat.service.*;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;


@Controller
public class UserController {
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

    @GetMapping(value = "/cap-nhat-thong-tin", produces = MediaType.IMAGE_PNG_VALUE)
    public ModelAndView sign() throws IOException {
        username = AuthenticationSystem.getUsernameLogined();
        ModelAndView modelAndView = new ModelAndView();
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        Avatar avatar = avatarService.findByUserName(username);
        String avt =  avatar.getImage();
        modelAndView.addObject("avatars",avt );
        modelAndView.addObject("inforUser",informationUser);
        modelAndView.setViewName("user/cap-nhat-thong-tin");
        return modelAndView;
    }

    @PostMapping("/cap-nhat-thong-tin")
    public ModelAndView resignInfor(@Valid InformationUser informationUser, @RequestParam(name = "files") MultipartFile avatar, BindingResult bindingResult) throws IOException {

        System.out.println("Vào đăng ký thông tin");
        ModelAndView modelAndView = new ModelAndView();
        InformationUser u = informationUserService.findInforUserByUsername(username);
        informationUser.setUserName(username);
        informationUser.setInforId(u.getInforId());
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/cap-nhat-thong-tin");
        } else {

                System.out.println("Có vào đây");
                if (avatar.getBytes().length > 0){
                    System.out.println("thay đổi ảnh");
                    byte[] file = avatar.getBytes();
                    String image = Base64.getEncoder().encodeToString(file);
                    avatarService.setAvatar(username, image);
                    modelAndView.addObject("avatars", image);
                }else {
                    Avatar avt = avatarService.findByUserName(username);
                    modelAndView.addObject("avatars", avt.getImage());
                }
            informationUserService.saveInforUser(informationUser);
            Avatar avt = avatarService.findByUserName(username);
            modelAndView.addObject("avatars", avt.getImage());
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.addObject("successMessage","Cập nhật thông tin thành công");
            modelAndView.setViewName("capnhatthongtin");

        }
        return modelAndView;
    }


    @GetMapping("/dang-tin-nha-dat")
    public ModelAndView dangTinThueNha(){
        username = AuthenticationSystem.getUsernameLogined();
        ModelAndView modelAndView = new ModelAndView();
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(informationUser.getFullName() == null){
            Avatar avatar = avatarService.findByUserName(username);
            String avt = avatar.getImage();
            modelAndView.addObject("avatars",avt);
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.setViewName("user/cap-nhat-thong-tin");
        }
        else {
            Article article = new Article();
            modelAndView.addObject("inforUser", informationUser);
            modelAndView.addObject("article", article);
            modelAndView.setViewName("user/dang-bai");
        }
        return modelAndView;
    }

    @PostMapping("/dang-tin-nha-dat")
    @Transactional
    public ModelAndView dangtin(Article article, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(bindingResult.hasErrors()){
            System.out.println("lỗi null");
            modelAndView.setViewName("user/dang-bai");
        }else{
            String p = VNCharacterUtils.removeAccent(article.getCity());
            article.setCity_unsigned(p);

            p = VNCharacterUtils.removeAccent(article.getTitle());
            article.setTitle_unsigned(p);

            p = VNCharacterUtils.removeAccent(article.getDistrict());
            article.setDistrict_unsigned(p);

            p = VNCharacterUtils.removeAccent(article.getWard());
            article.setWard_unsigned(p);

            System.out.println(article.getDetail());
            p = VNCharacterUtils.removeAccent(article.getDetail());
            article.setDetail_unsigned(p);


            articleService.addArticle(article);
            Post post = new Post();
            post.setUserName(username);
            post.setArticleId(article.getArticleId());
            postService.addPost(post);
            if (files.length >= 1){
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

                            } catch (IOException e) {
                                e.printStackTrace();

                            }

                        });
            }
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.addObject("article",new Article());
            modelAndView.addObject("successMessage","Đăng tin thành công!");
            modelAndView.setViewName("user/dang-bai");
            System.out.println("success!!");
        }

        return modelAndView;
    }

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


    @PostMapping("/sua-bai-viet")
    public ModelAndView suaBaiViet(Article article, @RequestParam("files") MultipartFile[] files, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        if(bindingResult.hasErrors() || informationUser == null){
            System.out.println("lỗi null");
            modelAndView.addObject("successMessage","Vui lòng đăng nhập");
            modelAndView.setViewName("user/sua-bai");
        }else{
            article.setArticleId(articleId);
            article.setDate_up(DateUtils.createNow().getTime());
            articleService.addArticle(article);
            Post post = new Post();
            post.setUserName(username);
            post.setArticleId(articleId);
            postService.addPost(post);
            if (files.length >= 1){
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

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
            }
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.addObject("article",article);
            modelAndView.addObject("successMessage","Bài viết đã được cập nhật");
            modelAndView.setViewName("user/sua-bai");
            System.out.println("success!!");
        }

        return modelAndView;
    }

}
