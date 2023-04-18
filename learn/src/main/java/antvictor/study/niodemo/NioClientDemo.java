package antvictor.study.niodemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


import java.net.InetSocketAddress;

public class NioClientDemo {
    public static void main(String [] args) throws InterruptedException {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(clientGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new ClientTestInHandler())
                                .addLast(new StringEncoder())
                                .addLast(new StringDecoder());
                    }
                });
        Channel channel = b.remoteAddress(new InetSocketAddress(8999)).connect().sync().channel();
        ByteBuf byteBuf = Unpooled.copiedBuffer("服务端，我发送消息了".getBytes());
        channel.writeAndFlush("服务端，我发消息了");



    }
}
