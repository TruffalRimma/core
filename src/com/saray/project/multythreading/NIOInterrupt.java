package com.saray.project.multythreading;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class NIOBlocked implements Runnable {
    private final SocketChannel socketChannel;
    public NIOBlocked(SocketChannel sc) {
        socketChannel = sc;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read in " + this);
            socketChannel.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            System.out.println("ClosedByInterruptException");
        } catch (AsynchronousCloseException a) {
            System.out.println("AsynchronousCloseException");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("NIO.run() " + this);
    }
}

public class NIOInterrupt {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost", 8080);

        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);

        Future<?> f = service.submit(new NIOBlocked(sc1));
        service.submit(new NIOBlocked(sc2));

        service.shutdown();

        // прерывание через cancel
        TimeUnit.SECONDS.sleep(1);
        f.cancel(true);

        // разблокирование посредством закрытия канала
        TimeUnit.SECONDS.sleep(1);
        sc2.close();
    }
}