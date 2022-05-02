package org.example.byte_conversion;

import org.example.loader.Loader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ByteTransformation {
    private final Loader loader = new Loader();


    private byte[] fileToByteArray() throws IOException {
        return Files.readAllBytes(Paths.get(loader.loadFile().getPath()));
    }

    public String[] byteArrayToStringBitsArray() throws IOException {
        byte[] byteArray = fileToByteArray();
        String[] bitArray = new String[byteArray.length];

        for (int i = 0; i < byteArray.length; i++)
            bitArray[i] = String.format("%8s", Integer.toBinaryString(byteArray[i] & 0xFF)).replace(" ", "0");

        return bitArray;
    }

    public byte[] bitsArrayToByteArray(String[] bitsArray) {
        byte[] byteArray = new byte[bitsArray.length];

        for (int i = 0; i < bitsArray.length; i++)
            byteArray[i] = (byte) Integer.parseInt(bitsArray[i], 2);

        return byteArray;
    }

    public void byteArrayToFile(byte[] byteArray, String fileName) throws IOException {
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(byteArray);
        out.close();
    }

}
