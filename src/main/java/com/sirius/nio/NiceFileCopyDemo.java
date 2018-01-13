package com.sirius.nio;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class NiceFileCopyDemo {
    public static void main(String[] args) throws IOException {
        final FileChannel from = FileChannel.open(Paths.get("test-file.txt"));
        final FileChannel to = FileChannel.open(Paths.get("to.txt"));
        from.transferTo(0, from.size(), to);
        //        to.transferFrom(from, 0, from.size());
    }
}
