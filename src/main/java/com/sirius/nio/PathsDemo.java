package com.sirius.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsDemo {
    public static void main(String[] args) {
        final Path relative = Paths.get("src");
        System.out.println("Relative:" + relative.toAbsolutePath());
        
        final Path absolute = Paths.get("/Users/ovchval/Documents/sirius");
        System.out.println("Absolute: " + absolute);
        
        final Path longPath = Paths.get("/Users/ovchval/Downloads", "..", "Documents", ".", "sirius/seminar3_fs_nio");
        System.out.println("Concatenated: " + longPath.toAbsolutePath());
        
        System.out.println("Normalized: " + longPath.normalize());
    }
}
