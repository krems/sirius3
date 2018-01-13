package com.sirius.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelsDemo {
    public static void main(final String[] args) throws Exception {
        writeToFile("test-file.txt");
        readFromFile("test-file.txt");
    }
    
    private static void writeToFile(final String path) throws IOException {
        final RandomAccessFile file = new RandomAccessFile(path, "rw");
        final FileChannel outChannel = file.getChannel();
        
        final byte[] bytes = "Here's a short, I'd say even very short note\nNevertheless it will be multiline\n".getBytes();
        final ByteBuffer buf = ByteBuffer.allocate(bytes.length);
        buf.put(bytes);
        
        buf.flip();
        
        while (buf.hasRemaining()) {
            outChannel.write(buf);
        }
        
        buf.clear();
        file.close();
    }
    
    private static void readFromFile(final String path) throws IOException {
        final RandomAccessFile file = new RandomAccessFile(path, "r");
        final FileChannel inChannel = file.getChannel();
        
        final ByteBuffer buf = ByteBuffer.allocate(48);
        
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
        file.close();
    }
}
