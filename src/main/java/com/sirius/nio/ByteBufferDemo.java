package com.sirius.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferDemo {
    public static void main(String[] args) throws IOException {
        final RandomAccessFile file = new RandomAccessFile("test-file.txt", "r");
        final FileChannel inChannel = file.getChannel();
        final ByteBuffer buf = ByteBuffer.allocate(80);
        System.out.println("capacity: " + buf.capacity());
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        
        inChannel.read(buf);
        System.out.println("capacity: " + buf.capacity());
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        
        buf.flip();
        System.out.println("capacity: " + buf.capacity());
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        
        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }
        System.out.println("capacity: " + buf.capacity());
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        
        buf.rewind();
        System.out.println("capacity: " + buf.capacity());
        System.out.println("position: " + buf.position());
        System.out.println("limit: " + buf.limit());
        
        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }
        buf.clear();
        file.close();
    }
}
