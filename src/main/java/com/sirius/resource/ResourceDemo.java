package com.sirius.resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class ResourceDemo {
    public static void main(String[] args) throws IOException {
        classAbsolutePath();
        classRelativePath();
        
        System.out.println("\nFrom instances:");
        instanceAbsolutePath();
        instanceRelativePath();
        
        System.out.println("\nFrom class loader:");
        classLoader();
        
        System.out.println("\nStream examples:");
        classAbsolutePathStream();
        classRelativePathStream();
        classLoaderStream();
    }
    
    private static void classAbsolutePath() throws IOException {
        final URL resource = ResourceDemo.class.getResource("/path/root.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(resource.openStream()));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
    
    private static void classRelativePath() throws IOException {
        final URL resource = ResourceDemo.class.getResource("in_package.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(resource.openStream()));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
    
    private static void instanceAbsolutePath() throws IOException {
        final URL resource = new ResourceDemo().getClass().getResource("/path/root.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(resource.openStream()));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
    
    private static void instanceRelativePath() throws IOException {
        final URL resource = new ResourceDemo().getClass().getResource("in_package.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(resource.openStream()));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
    
    private static void classLoader() throws IOException {
        final URL resource = Thread.currentThread().getContextClassLoader().getResource("path/root.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(resource.openStream()));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        
        final URL resourceInPackage = Thread.currentThread().getContextClassLoader().getResource("com/sirius/resource/in_package.txt");
        final Scanner otherScanner = new Scanner(new BufferedInputStream(resourceInPackage.openStream()));
        while (otherScanner.hasNext()) {
            System.out.println(otherScanner.nextLine());
        }
    }
    
    private static void classAbsolutePathStream() throws IOException {
        final InputStream stream = ResourceDemo.class.getResourceAsStream("/path/root.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(stream));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
    
    private static void classRelativePathStream() throws IOException {
        final InputStream stream = ResourceDemo.class.getResourceAsStream("in_package.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(stream));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
    
    private static void classLoaderStream() throws IOException {
        final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("path/root.txt");
        final Scanner scanner = new Scanner(new BufferedInputStream(stream));
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        
        final URL resourceInPackage = Thread.currentThread().getContextClassLoader().getResource("com/sirius/resource/in_package.txt");
        final Scanner otherScanner = new Scanner(new BufferedInputStream(resourceInPackage.openStream()));
        while (otherScanner.hasNext()) {
            System.out.println(otherScanner.nextLine());
        }
    }
}
