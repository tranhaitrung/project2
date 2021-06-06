package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.service.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;

@Controller
public class CapNhatThongTinController {
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

}
