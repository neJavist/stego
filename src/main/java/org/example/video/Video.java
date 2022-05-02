package org.example.video;

import org.example.Property;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Video {

    private static final Property property = new Property();

    public void videoFragmentation(String framesPath, String videoName) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = new ArrayList<>();
        command.add("C:\\ffmpeg\\bin\\ffmpeg.exe");
        command.add("-y");
        command.add("-i");
        command.add(property.getVideoInputPath() + videoName);
        command.add(framesPath + "frame%d.jpg");

        processBuilder.command(command);
        processBuilder.redirectErrorStream(true);
        try {
            Process start = processBuilder.start();
            InputStream inputStream = start.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
            int len = -1;
            char[] c = new char[1024];
            StringBuilder outputString = new StringBuilder();
            while ((len = inputStreamReader.read(c)) != -1) {
                String s = new String(c, 0, len);
                outputString.append(s);
                System.out.print(s);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void videoCompiler() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> command = new ArrayList<>();
        command.add("C:\\ffmpeg\\bin\\ffmpeg.exe");
        command.add("-y");
        command.add("-i");
        command.add(property.getFramesPath() + "frame%d.jpg");
        command.add("-qscale:v");
        command.add("1");
        command.add("-b:v");
        command.add("20M");
        command.add(property.getVideoOutputPath() + "output.mp4");

        processBuilder.command(command);
        processBuilder.redirectErrorStream(true);
        try {
            Process start = processBuilder.start();
            InputStream inputStream = start.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
            int len = -1;
            char[] c = new char[1024];
            StringBuilder outputString = new StringBuilder();
            while ((len = inputStreamReader.read(c)) != -1) {
                String s = new String(c, 0, len);
                outputString.append(s);
                System.out.print(s);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}