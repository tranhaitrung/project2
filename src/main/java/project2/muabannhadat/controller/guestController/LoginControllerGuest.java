package project2.muabannhadat.controller.guestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.model.User;
import project2.muabannhadat.service.AvatarService;
import project2.muabannhadat.service.InformationUserService;
import project2.muabannhadat.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class LoginControllerGuest {
    @Autowired
    private UserService userService;

    @Autowired
    private InformationUserService informationUserService;

    @Autowired
    private AvatarService avatarService;

    @GetMapping(value={"/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        if (AuthenticationSystem.isLogged()){
        }
        modelAndView.setViewName("guest/dang-nhap");
        return modelAndView;
    }

    @GetMapping(value="/dang-ky-tai-khoan")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        InformationUser informationUser = new InformationUser();
        modelAndView.addObject("user", user);
        modelAndView.addObject("inforUser", informationUser);
        modelAndView.setViewName("guest/dang-ky-tai-khoan");
        return modelAndView;
    }



    @PostMapping(value = "/dang-ky-tai-khoan")
    public ModelAndView createNewUser(@Valid User user,InformationUser informationUser, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(user.getUserName());
        User userExists = userService.findUserByUserName(user.getUserName());

        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(informationUser.getFullName());
        System.out.println(informationUser.getDob());
        System.out.println(informationUser.getPhone());
        System.out.println(informationUser.getEmail());
        System.out.println(informationUser.getAddress());

        if (userExists != null) {
            modelAndView.addObject("user", new User());
            modelAndView.addObject("inforUser", new InformationUser());
            modelAndView.addObject("errorMessage", "Tên tài khoản đã tồn tại!");
            modelAndView.setViewName("guest/dang-ky-tai-khoan");
            return modelAndView;
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("guest/dang-ky-tai-khoan");
        } else {
            Avatar default_avt = avatarService.findById(1L);

            Avatar avatar = new Avatar();

            informationUser.setUserName(user.getUserName());
            informationUserService.saveInforUser(informationUser);

            avatar.setUsername(user.getUserName());
            avatar.setImage(default_avt.getImage());

            userService.saveUser(user);
            avatarService.save(avatar);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.addObject("inforUser", new InformationUser());
            modelAndView.setViewName("guest/dang-ky-tai-khoan");

        }
        return modelAndView;
    }
}
