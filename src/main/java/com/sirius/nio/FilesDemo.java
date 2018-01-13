package com.sirius.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesDemo {
    public static void main(String[] args) throws IOException {
        final Path currentDir = Paths.get(".");
        System.out.println(Files.exists(currentDir));
        
        final Path newDirectory = Paths.get("./non-existent-dir");
        if (Files.exists(newDirectory)) {
            Files.delete(newDirectory);
        } else {
            Files.createDirectory(newDirectory);
        }
        
        final Path file = Paths.get("./test-file.txt");
        final Path to = Paths.get("./copy-of-test-file.txt");
        Files.copy(file, to);
        
        Files.move(to, file, StandardCopyOption.REPLACE_EXISTING);
    }
}
