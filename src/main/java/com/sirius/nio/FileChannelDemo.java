package com.sirius.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelDemo {
    public static void main(final String[] args) throws Exception {
        writeToFile("test-file.txt");
        readFromFile("test-file.txt");
    }
    
    private static void writeToFile(final String path) throws IOException {
        final FileChannel outChannel = FileChannel.open(Paths.get(path), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        
        final byte[] bytes = "Here's a short, I'd say even very short note\nNevertheless it will be multiline\n".getBytes();
        final ByteBuffer buf = ByteBuffer.allocate(bytes.length);
        buf.put(bytes);
        
        buf.flip();
        
        outChannel.position(bytes.length);
        while (buf.hasRemaining()) {
            outChannel.write(buf);
        }
        
        buf.clear();
        outChannel.close();
    }
    
    private static void readFromFile(final String path) throws IOException {
        final FileChannel inChannel = FileChannel.open(Paths.get(path), StandardOpenOption.READ);
        
        final ByteBuffer buf = ByteBuffer.allocate(48);
        
        inChannel.position(16);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.err.println("Read " + bytesRead);
            buf.flip();
            
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        inChannel.close();
    }
}
