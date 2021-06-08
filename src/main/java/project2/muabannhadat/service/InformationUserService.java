package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.Article;
import project2.muabannhadat.model.InformationUser;
import project2.muabannhadat.repository.InformationUserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class InformationUserService {
    @Autowired
    private InformationUserRepository informationUserRepository;


    public InformationUser findInforUserByUsername(String username){
        return informationUserRepository.findInformationUserByUserName(username);
    }

    public InformationUser saveInforUser(InformationUser informationUser){
        return informationUserRepository.save(informationUser);
    }

    public InformationUser setAvatar(InformationUser informationUser, String avatar){
        informationUser.setAvatar(avatar);
        return informationUserRepository.save(informationUser);
    }

    public void deleteInformationUserByUsername(String username){
        InformationUser informationUser = informationUserRepository.findInformationUserByUserName(username);
        informationUserRepository.delete(informationUser);
    }

    public List<InformationUser> getAll(){
        List<InformationUser> list = informationUserRepository.findAll();
        return list;
    }

    public List<InformationUser> findByIdLike(Long id){
        return informationUserRepository.findByInforIdIsLike(id);
    }

    public List<InformationUser> findByUsername(String username){
        return informationUserRepository.findByUserNameIsLikeIgnoreCase(username);
    }

    public List<InformationUser> findByPhone(int phone){
        return informationUserRepository.findByPhone(phone);
    }

    public List<InformationUser> findByFullName(String fullName){
        return informationUserRepository.findByFullNameIgnoreCase(fullName);
    }

    public List<InformationUser> findByEmail(String email){
        return informationUserRepository.findByEmailIsLikeIgnoreCase(email);
    }


}
