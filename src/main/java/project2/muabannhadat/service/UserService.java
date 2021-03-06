package project2.muabannhadat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.Role;
import project2.muabannhadat.model.User;
import project2.muabannhadat.repository.RoleRepository;
import project2.muabannhadat.repository.UserRepository;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User findUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user != null) return user;
        return null;
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        return userRepository.save(user);
    }

    public Long countUser(){
        return userRepository.count();
    }

    public boolean deleteByUsername(String username){
        return userRepository.deleteUserByUserName(username);
    }

    public void setActice(String username, Boolean active){
        User user = userRepository.findByUserName(username);
        user.setActive(active);
        userRepository.save(user);
    }

    public Integer getRoleUser(String username){
        User user = userRepository.findByUserName(username);
        Set<Role> roles = user.getRoles();
        int r = -1000;
        for (Role role : roles){
            System.out.println(role);
            r = role.getId();
        }
        System.out.println(r);
        return r;
    }
}