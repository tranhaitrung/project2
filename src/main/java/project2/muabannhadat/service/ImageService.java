package project2.muabannhadat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.muabannhadat.model.Image;
import project2.muabannhadat.repository.ImageRepository;


@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;


    public Image addImage(Image image) {
        return imageRepository.save(image);
    }

    public  Image findImageByImageId(Long id){
        Image image = imageRepository.findById(id).orElse(null);
        return image;
    }

    public void deleteImage(Image image){
        imageRepository.delete(image);
    }
}
