package com.sirius.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsyncFileChannelDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousFileChannel fileChannel =
                AsynchronousFileChannel.open(Paths.get("test-file.txt"), StandardOpenOption.READ);
        final int position = 0;
        readWithFuture(fileChannel, position);
        readWithCompletionHandler(fileChannel, position);
    }
    
    private static int readWithFuture(final AsynchronousFileChannel fileChannel, final int position) {
        final ByteBuffer buf = ByteBuffer.allocate(1024);
        final Future<Integer> read = fileChannel.read(buf, position);
    
        while (!read.isDone()) {
            ;
        }
        
        buf.flip();
        final byte[] data = new byte[buf.limit()];
        buf.get(data);
        System.out.println(new String(data));
        return position;
    }
    
    private static void readWithCompletionHandler(final AsynchronousFileChannel fileChannel, final int position) throws InterruptedException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
                
                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
                
            }
            
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.err.println(exc.getMessage());
            }
        });
        Thread.sleep(2_000);
    }
}
