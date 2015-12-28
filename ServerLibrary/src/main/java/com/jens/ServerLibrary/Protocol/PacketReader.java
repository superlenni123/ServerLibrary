package com.jens.ServerLibrary.Protocol;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public abstract class PacketReader {
	
	protected String packet_Reader = "packet_Reader";
	
	public abstract void onPacketIn(Object packet, Player player);
	
	public abstract void onPacketOut(Object packet, Player player);
	
	public void inject(Player player){
		Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
		
		if(channel.pipeline().get(packet_Reader) == null){
			PacketIncomingReader reader = new PacketIncomingReader();
			reader.player = player;
			
			channel.pipeline().addAfter("decoder", packet_Reader, reader);
		}
	}
	
	public void uninject(Player player){
		Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
		
		if(channel.pipeline().get(packet_Reader) != null){
			channel.pipeline().remove(packet_Reader);
		}
	}
	
    class PacketIncomingReader extends ChannelDuplexHandler {
    	
    	protected Player player;
		
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
			onPacketIn(o, player);
			super.channelRead(ctx, o);
		}
		
		@Override
		public void write(ChannelHandlerContext ctx, Object o, ChannelPromise promise) throws Exception {
			onPacketOut(o, player);
			super.write(ctx, o, promise);
		}
		
	}
}
