package com.sample.microservices.common.versions.v17;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedFile {
    public static void main(String[] args) throws Exception {
        // Create a random access file for read and write operations
        RandomAccessFile file = new RandomAccessFile("memoryMappedFile.txt", "rw");

        // Get the file channel
        FileChannel channel = file.getChannel();

        // Map the file into memory
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        // Write data to the memory-mapped file
        String data = "Welcome, Memory-Mapped File!";
        buffer.put(data.getBytes());

        // Read data from the memory-mapped file
        buffer.flip();
        byte[] readData = new byte[data.length()];
        buffer.get(readData);

        System.out.println(new String(readData));

        // Close the file and channel
        channel.close();
        file.close();
    }
}
