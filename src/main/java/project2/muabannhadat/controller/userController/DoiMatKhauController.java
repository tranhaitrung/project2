package project2.muabannhadat.controller.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.NotificationConnect;
import project2.muabannhadat.model.User;
import project2.muabannhadat.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DoiMatKhauController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private InformationUserService informationUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private NotificationConnectService connectService;

    private String username;

    ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/doi-mat-khau")
    public ModelAndView doiMatKhau(){
        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();
        Avatar avatar1 = new Avatar();
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

        if (username1 == null){
            System.out.println("Yc đang nhap");
            modelAndView.setViewName("guest/dang-nhap");
            return modelAndView;
        }

        String error =null;
        String success = null;
        modelAndView.addObject("errorMessage", error);
        modelAndView.addObject("successMessage", success);

        modelAndView.addObject("username", username1);
        modelAndView.setViewName("user/doi-mat-khau");
        return modelAndView;
    }

    @PostMapping("/doi-mat-khau")
    public ModelAndView doiMatKhau1(HttpServletRequest request){
        /**
         * Đoạn kiểm tra login
         */
        String username1 = AuthenticationSystem.getUsernameLogined();
        Avatar avatar1 = new Avatar();
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

        if (username1 == null){
            modelAndView.setViewName("guest/dang-nhap");
            return modelAndView;
        }

        String password = request.getParameter("password");
        User user = userService.findUserByUserName(username1);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //password = passwordEncoder.encode(password);

        System.out.println(password);
        System.out.println(user.getPassword());

        String success = null;
        String error = null;
        if (passwordEncoder.matches(password, user.getPassword())){
            String newPass = request.getParameter("new-password");
            newPass = passwordEncoder.encode(newPass);
            user.setPassword(password);
            userService.saveUser(user);
            success = "Đổi thành công!";
            System.out.println(success);

        }
        else {
            error = "Mật khẩu không chính xác!";
            System.out.println(error);

        }
        modelAndView.addObject("errorMessage", error);
        modelAndView.addObject("successMessage", success);
        modelAndView.setViewName("user/doi-mat-khau");
        return modelAndView;
    }

}
