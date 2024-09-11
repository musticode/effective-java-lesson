package org.example.item8;

import java.io.*;

public class ItemEightTest {
    public static void main(String[] args) {
        // 12.video

        System.out.println("Test");



    }


    private static final int BUFFER_SIZE = 0;
    static String firstLineOfFile(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));
        try {

            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }






    }


    // try with resource
    //
    static String firstLineOfFileWell(String path, String defaultVal){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            return br.readLine();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // try with resource ex 2
    static void copy(String src, String dst) throws IOException {

        try (InputStream in = new FileInputStream(src);
             OutputStream outputStream = new FileOutputStream(dst)){

            byte [] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf))  >= 0)
                outputStream.write(buf, 0, n);

        }

    }


}
