package larry.study.niodemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class NioServerDemo {
     public static void main(String [] args) throws InterruptedException {

         // 线程连接组
         NioEventLoopGroup bossGroup = new NioEventLoopGroup();
         // 工作组
         NioEventLoopGroup workGroup = new NioEventLoopGroup(100);

         // NioServerSocket
         ServerBootstrap b = new ServerBootstrap();
         b.group(bossGroup, workGroup);
         b.channel(NioServerSocketChannel.class);
         b.localAddress(new InetSocketAddress(8999));
         b.childHandler(new ChannelInitializer<SocketChannel>() {
             @Override
             protected void initChannel(SocketChannel socketChannel) throws Exception {
                 socketChannel.pipeline()
                         .addLast(new TestInHandler())
                         .addLast(new StringDecoder())
                         .addLast(new StringEncoder())
                         .addLast(new ServerOutHandler());
             }
         });
         ChannelFuture future = b.bind().sync();
         Channel channel = future.channel();

     }
}
