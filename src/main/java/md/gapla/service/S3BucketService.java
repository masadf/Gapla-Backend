package md.gapla.service;

import md.gapla.model.entity.lessons.LessonMaterialsEntity;
import org.springframework.web.multipart.MultipartFile;

public interface S3BucketService {

    public String uploadFileToS3(MultipartFile multipartfile, LessonMaterialsEntity lessonMaterialsEntity, String filePathName);
    public byte[] downloadFileFromS3(String key) ;
}
