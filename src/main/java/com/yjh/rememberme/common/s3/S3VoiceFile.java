package com.yjh.rememberme.common.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Original Code
 * https://github.com/SybooSyboo782/comproject-network
 */
@Component
@RequiredArgsConstructor
public class S3VoiceFile {
    private final AmazonS3Client amazonS3Client;

    @Value("mtvs-rememberme")
    private String bucket;

    public String upload(MultipartFile voice, String voiceName, String dirName) throws IOException, UnsupportedAudioFileException {

        ByteArrayInputStream input_stream= new ByteArrayInputStream(voice.getBytes());

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(input_stream);

        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("./" + voiceName));

        File newFile = new File("./" + voiceName);

        return upload(newFile, voiceName, dirName);
    }
    private String upload(File uploadFile, String voiceName, String dirName) {

        String fileName = dirName + "/" + voiceName;
        String uploadImageUrl = putS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImageUrl;
    }

    private String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            System.out.println("파일이 삭제되었습니다.");
        } else {
            System.out.println("파일이 삭제되지 못하였습니다.");
        }
    }
}
