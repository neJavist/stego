package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    FileInputStream fis;
    Properties property = new Properties();


    public String getFileToHidePath() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);

        return property.getProperty("fileToHidePath");
    }

    public String getFramesPath() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);

        return property.getProperty("framesPath");
    }

    public String getEncryptionFramesPath() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);

        return property.getProperty("framesEncryptionPath");
    }

    public String getEmptyFramesPath() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);

        return property.getProperty("emptyFramesPath");
    }

    public String getVideoInputPath() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);

        return property.getProperty("videoInputPath");
    }

    public String getVideoOutputPath() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);

        return property.getProperty("videoOutputPath");
    }


}
