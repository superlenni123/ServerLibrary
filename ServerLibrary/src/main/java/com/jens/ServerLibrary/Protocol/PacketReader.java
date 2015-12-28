package com.jens.ServerLibrary.Protocol;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public abstract class PacketReader {
	
	protected String packet_Reader = "packet_Reader";
	
	/**
	 * Gets the Packets of the injected Client.
	 * @param packet Packet that was catched when arriving the Server
	 * @param player Player/Client where the Packet should arrive the Server
	 */
	
	public abstract void onPacketIn(Object packet, Player player);
	
	/**
	 * Gets the Packets of the injected Client.
	 * @param packet Packet that was catched before sending the specified Player
	 * @param player Player/Client where the Packet should arrive
	 */
	
	public abstract void onPacketOut(Object packet, Player player);
	
	/**
	 * Injects the Player and adds
	 * after the Decoder the Reader into the 
	 * Network Pipiline.
	 * @param player Player that should be injected
	 */
	
	public void inject(Player player){
		Channel channel = ((CraftPlayer)player).getHandle().playerConnection.networkManager.channel;
		
		if(channel.pipeline().get(packet_Reader) == null){
			PacketIncomingReader reader = new PacketIncomingReader();
			reader.player = player;
			
			channel.pipeline().addAfter("decoder", packet_Reader, reader);
		}
	}
	
	/**
	 * Uninjects the Player, removes the
	 * Packet Reader out of his Network
	 * Pipeline.
	 * @param player Player that should be uninjected
	 */
	
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
