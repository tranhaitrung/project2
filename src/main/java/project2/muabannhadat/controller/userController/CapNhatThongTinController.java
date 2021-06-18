package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.model.NotificationConnect;
import project2.muabannhadat.service.*;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RestControllerAdvice
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

    @Autowired
    UserService userService;

    @Autowired
    private NotificationConnectService connectService;

    private String username;

    @GetMapping(value = "/cap-nhat-thong-tin", produces = MediaType.IMAGE_PNG_VALUE)
    public ModelAndView sign() throws IOException, LoginException {
        username = AuthenticationSystem.getUsernameLogined();
        ModelAndView modelAndView = new ModelAndView();
        InformationUser informationUser = informationUserService.findInforUserByUsername(username);
        Avatar avatar = avatarService.findByUserName(username);
        String avt =  avatar.getImage();

        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();;
        Avatar avatar1 = new Avatar();
        avatar1.setImage("abc");
        if (!username1.equals("anonymousUser")){
            System.out.println("logined : " + username1);
            avatar1 = avatarService.findByUserName(username1);
            System.out.println("get role");
            int roleid = userService.getRoleUser(username1);
            modelAndView.addObject("role", roleid);
            int countNew = connectService.countNotiNew(username1);
            List<NotificationConnect> connectList = connectService.getByUsername(username1);
            modelAndView.addObject("notis", connectList);
            modelAndView.addObject("countNew", countNew);
            modelAndView.addObject("avatar1", avatar1.getImage());
        }else {
            username1 = null;
        }
        modelAndView.addObject("username", username1);

        modelAndView.addObject("avatars",avt );
        modelAndView.addObject("inforUser",informationUser);
        modelAndView.setViewName("user/cap-nhat-thong-tin");
        return modelAndView;
    }

    @PostMapping("/cap-nhat-thong-tin")
    public ModelAndView resignInfor(@Valid InformationUser informationUser, @RequestParam(name = "files") MultipartFile avatar, BindingResult bindingResult) throws IOException, LoginException {
        username = AuthenticationSystem.getUsernameLogined();
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

            /**
             * Đoạn kiểm tra login
             */
            String username1 = AuthenticationSystem.getUsernameLogined();;
            Avatar avatar1 = new Avatar();
            avatar1.setImage("abc");
            if (!username1.equals("anonymousUser")){
                System.out.println("logined : " + username1);
                avatar1 = avatarService.findByUserName(username1);
                System.out.println("get role");
                int roleid = userService.getRoleUser(username1);
                modelAndView.addObject("role", roleid);
                modelAndView.addObject("avatar1", avatar1.getImage());
            }else {
                username1 = null;
            }

            modelAndView.addObject("username", username1);

            modelAndView.addObject("avatars", avt.getImage());
            modelAndView.addObject("inforUser",informationUser);
            modelAndView.addObject("successMessage","Cập nhật thông tin thành công");
            modelAndView.setViewName("user/cap-nhat-thong-tin");

        }
        return modelAndView;
    }

}
