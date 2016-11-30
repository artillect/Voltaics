package com.artillect.voltaics.network;

import com.artillect.voltaics.Voltaics;
import com.artillect.voltaics.network.message.MessageTEUpdate;

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
