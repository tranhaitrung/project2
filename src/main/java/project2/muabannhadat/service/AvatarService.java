package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.Avatar;
import project2.muabannhadat.repository.AvatarRepository;

@Service
public class AvatarService {
    @Autowired
    AvatarRepository repository;

    public void setAvatar(String username, String image){
        Avatar avatar = repository.findByUsername(username);
        avatar.setImage(image);
        repository.save(avatar);
    }

    public Avatar findByUserName(String username){
        return repository.findByUsername(username);
    }

    public void save(Avatar avatar){
        repository.save(avatar);
    }

    public Avatar findById(Long id){
        return  repository.findById(id).orElse(null);
    }

}
