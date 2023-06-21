package com.rp.assignment.sec01;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtil {

    private static FileUtil instance = null;

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        return instance = Objects.isNull(instance) ? new FileUtil() : instance;
    }

    public String read(String fileName) {

        ClassLoader loader = this.getClass().getClassLoader();
        InputStream is = loader.getResourceAsStream(fileName);

        if (Objects.isNull(is)) throw new RuntimeException("Could not find file " + fileName);

        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultStringBuilder.toString();

    }

    public void write(String fileName, String content) {

        URL resource = this.getClass().getResource(fileName);
        File file;
        if (Objects.isNull(resource)) file = Paths.get(URI.create(fileName)).toFile();
        else file = new File(resource.getPath());

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Wrote file successfully");

    }

    public void delete(String fileName) {
        File file = new File(fileName);
        boolean deleted = file.delete();
        if (!deleted) throw new RuntimeException("Could not delete!");
        System.out.println("Deleted file successfully");
    }
}
