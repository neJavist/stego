package org.example.loader;

import org.example.Property;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Loader {
    Property property = new Property();

    public File loadFile() throws IOException {
        return new File(property.getFileToHidePath());
    }

    public BufferedImage loadFrames(int i) throws IOException {
        return ImageIO.read(new File(property.getFramesPath() + "frame" + i + ".jpg"));
    }

    public BufferedImage loadEncryptionFrames(int i) throws IOException {
        return ImageIO.read(new File(property.getEncryptionFramesPath() + "frame" + i + ".jpg"));
    }

}
