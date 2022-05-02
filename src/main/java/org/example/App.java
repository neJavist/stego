package org.example;


import org.example.coding.Coding;
import org.example.video.Video;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class App {
    static Charset utf8 = StandardCharsets.UTF_8;
    public static Coding encryption = new Coding();

    public static void main(String[] args) throws IOException {
        encryption.encryption();
        encryption.decryption();
    }
}
