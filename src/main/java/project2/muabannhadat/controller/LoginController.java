package project2.muabannhadat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import project2.muabannhadat.configuration.AuthenticationSystem;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.model.User;
import project2.muabannhadat.service.AvatarService;
import project2.muabannhadat.service.InformationUserService;
import project2.muabannhadat.service.UserService;


import javax.validation.Valid;
import java.io.IOException;

@Controller
public class LoginController {

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

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("guest/dang-ky-tai-khoan");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("guest/dang-ky-tai-khoan");
        } else {
            Avatar default_avt = avatarService.findById(1L);

            InformationUser u = new InformationUser();
            Avatar avatar = new Avatar();

            u.setUserName(user.getUserName());
            informationUserService.saveInforUser(u);

            avatar.setUsername(user.getUserName());
            avatar.setImage(default_avt.getImage());

            userService.saveUser(user);
            avatarService.save(avatar);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("guest/dang-ky-tai-khoan");

        }
        return modelAndView;
    }

}
