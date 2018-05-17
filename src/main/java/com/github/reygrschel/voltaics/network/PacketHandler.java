package com.github.reygrschel.voltaics.network;

import com.github.reygrschel.voltaics.Voltaics;
import com.github.reygrschel.voltaics.network.message.MessageTEUpdate;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Voltaics.modId);

    private static int id = 0;

    public static void registerMessages(){
        INSTANCE.registerMessage(MessageTEUpdate.MessageHolder.class, MessageTEUpdate.class,id ++,Side.CLIENT);
       }
}
