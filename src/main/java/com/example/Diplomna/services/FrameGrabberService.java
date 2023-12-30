package com.example.Diplomna.services;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.bytedeco.javacv.FFmpegFrameGrabber;

import static com.example.Diplomna.Utils.removeFileExt;


//сервіс бере файли з відео і за допомогою бібліотеки проходиться по першим 50 фреймам і зберігає картинку для відео
@Service
public class FrameGrabberService {

    public long generatePreviewPictures(Path filePath) throws IOException {
        try (FFmpegFrameGrabber g = new FFmpegFrameGrabber(filePath.toString())) {
            Java2DFrameConverter converter = new Java2DFrameConverter();

            g.start();
            BufferedImage image;
            for (int i = 0; i < 50; i++) {
                image = converter.convert(g.grabKeyFrame());
                if (image != null) {
                    File file = Path.of( removeFileExt(filePath.toString()) + ".jpeg").toFile();
                    ImageIO.write(image, "jpeg", file);
                    break;
                }
            }
            long lengthInTime = g.getLengthInTime();
            g.stop();
            return lengthInTime;
        }
    }

}
