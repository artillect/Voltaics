package com.artillect.voltaics.lib;

import java.util.ArrayList;
import java.util.List;

import com.artillect.voltaics.capability.JouleCapabilities;
import com.artillect.voltaics.power.IJouleConsumer;
import com.artillect.voltaics.power.IJouleHolder;
import com.artillect.voltaics.power.IJouleProducer;
import com.artillect.voltaics.power.implementation.BaseJouleContainer;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JouleUtils {
    
    /**
     * The smallest unit of power measurement.
     */
    public static final int Joule = 1;
    
    /**
     * The amount of Joule in a KiloJoule. One thousand.
     */
    public static final int KILOJoule = 1000;
    
    /**
     * The amount of Telsa in a MegaJoule. One Million.
     */
    public static final int MEGAJoule = 1000000;
    
    /**
     * The amount of Joule in a GigaJoule. One Billion.
     */
    public static final int GIGAJoule = 1000000000;
    
    /**
     * The amount of Telsa in a TeraJoule. One Trillion.
     */
    public static final long TERAJoule = 1000000000000L;

    /**
     * The amount of Joule in a PentaJoule. One Quadrillion.
     */
    public static final long PETAJoule = 1000000000000000L;
    
    /**
     * The amount of Joule in a ExaJoule. One Quintilian.
     * 
     * The ExaJoule should not be treated as the largest unit of Joule. The naming scheme can
     * go on indefinitely. The next unit would be a ZettaJoule, followed by YottaJoule,
     * BronoJoule, GeopJoule and so on. While it is possible to define these measurements,
     * there really isn't a point.
     */
    public static final long EXAJoule = 1000000000000000000L;
    
    /**
     * Converts an amount of Joule units into a human readable String. The string amount is
     * only rounded to one decimal place.
     * 
     * @param Joule The amount of Joule units to display.
     * @return A human readable display of the Joule units.
     */
    public static String getDisplayableJouleCount (long Joule) {
        
        if (Joule < 1000)
            return Joule + " T";
            
        final int exp = (int) (Math.log(Joule) / Math.log(1000));
        final char unitType = "KMGTPE".charAt(exp - 1);
        return String.format("%.1f %sT", Joule / Math.pow(1000, exp), unitType);
    }
    
    /**
     * Gets the abbreviated name of the best unit to describe the provided power. For example,
     * anything less than 1000 will return t for Joule, while anything between 999 and one
     * million will return kt for kilo Joule. This method has support for up to ExaJoule.
     * 
     * @param Joule The amount of Joule to get the unit for.
     * @return The abbreviated name for the unit used to describe the provided power amount.
     */
    public static String getUnitType (long Joule) {
        
        if (Joule < 1000)
            return Joule + "t";
            
        final int exp = (int) (Math.log(Joule) / Math.log(1000));
        return "kmgtpe".charAt(exp - 1) + "t";
    }
    
    /**
     * Gets the name of the the power unit that best represents the amount of provided power.
     * The name will be translated to the local language, or english if that language is not
     * yet supported.
     * 
     * @param Joule The amount of Joule to get the unit name for.
     * @return The name of the power unit that best represents the amount of power provided.
     */
    public static String getLocalizedUnitType (long Joule) {
        
        return I18n.format("unit.Joule." + getUnitType(Joule));
    }
    
    /**
     * Checks if a capability provider has support for Joule.
     * 
     * @param provider The provider to check. ItemStack/TileEntity/Entity and so on.
     * @param side The side to check.
     * @return Whether or not the provider has support for Joule.
     */
    public static boolean hasJouleSupport (ICapabilityProvider provider, EnumFacing side) {
        
        return isJouleHolder(provider, side) || isJouleConsumer(provider, side) || isJouleProducer(provider, side);
    }
    
    /**
     * Checks if a capability provider is a Joule holder.
     * 
     * @param provider The provider to check. ItemStack/TileEntity/Entity and so on.
     * @param side The side to check.
     * @return Whether or not the provider is a Joule holder.
     */
    public static boolean isJouleHolder (ICapabilityProvider provider, EnumFacing side) {
        
        return provider.hasCapability(JouleCapabilities.CAPABILITY_HOLDER, side);
    }
    
    /**
     * Checks if a capability provider is a Joule consumer.
     * 
     * @param provider The provider to check. ItemStack/TileEntity/Entity and so on.
     * @param side The side to check.
     * @return Whether or not the provider is a Joule consumer.
     */
    public static boolean isJouleConsumer (ICapabilityProvider provider, EnumFacing side) {
        
        return provider.hasCapability(JouleCapabilities.CAPABILITY_CONSUMER, side);
    }
    
    /**
     * Checks if a capability provider is a Joule producer.
     * 
     * @param provider The provider to check. ItemStack/TileEntity/Entity and so on.
     * @param side The side to check.
     * @return Whether or not the provider is a Joule producer.
     */
    public static boolean isJouleProducer (ICapabilityProvider provider, EnumFacing side) {
        
        return provider.hasCapability(JouleCapabilities.CAPABILITY_PRODUCER, side);
    }
    
    /**
     * Gets an IJouleHolder implementation from the capability provider.
     * 
     * @param provider The provider to get the capability from.
     * @param side The side to access;
     * @return A Joule holder implementation, or null if none could be found.
     */
    public static IJouleHolder getJouleHolder (ICapabilityProvider provider, EnumFacing side) {
        
        return (IJouleHolder) provider.getCapability(JouleCapabilities.CAPABILITY_HOLDER, side);
    }
    
    /**
     * Gets an IJouleConsumer implementation from the capability provider.
     * 
     * @param provider The provider to get the capability from.
     * @param side The side to access;
     * @return A Joule consumer implementation, or null if none could be found.
     */
    public static IJouleConsumer getJouleConsumer (ICapabilityProvider provider, EnumFacing side) {
        
        return (IJouleConsumer) provider.getCapability(JouleCapabilities.CAPABILITY_CONSUMER, side);
    }
    
    /**
     * Gets an IJouleProducer implementation from the capability provider.
     * 
     * @param provider The provider to get the capability from.
     * @param side The side to access;
     * @return A Joule producer implementation, or null if none could be found.
     */
    public static IJouleProducer getJouleProducer (ICapabilityProvider provider, EnumFacing side) {
        
        return (IJouleProducer) provider.getCapability(JouleCapabilities.CAPABILITY_PRODUCER, side);
    }
    
    /**
     * Gets the capacity of a provider.
     * 
     * @param provider The provider to access.
     * @param side The side to access.
     * @return The capacity of the provider. 0 if it has no capacity.
     */
    public static long getCapacity (ICapabilityProvider provider, EnumFacing side) {
        
        return isJouleHolder(provider, side) ? getJouleHolder(provider, side).getCapacity() : 0;
    }
    
    /**
     * Gets the stored power of a provider.
     * 
     * @param provider The provider to access.
     * @param side The side to access.
     * @return The amount of power stored.
     */
    public static long getStoredPower (ICapabilityProvider provider, EnumFacing side) {
        
        return isJouleHolder(provider, side) ? getJouleHolder(provider, side).getStoredPower() : 0;
    }
    
    /**
     * Attempt to give power to a provider.
     * 
     * @param power The amount of power to offer.
     * @param simulated Whether or not this is being called as part of a simulation.
     *        Simulations are used to get information without affecting the Joule Producer.
     * @return The amount of power that the consumer accepts.
     */
    public static long givePower (ICapabilityProvider provider, EnumFacing side, long power, boolean simulated) {
        
        return isJouleConsumer(provider, side) ? getJouleConsumer(provider, side).givePower(power, simulated) : 0;
    }
    
    /**
     * Attempts to take power from a provider.
     * 
     * @param power The amount of power to request.
     * @param simulated Whether or not this is being called as part of a simulation.
     *        Simulations are used to get information without affecting the Joule Producer.
     * @return The amount of power that the Joule Producer will give.
     */
    public static long takePower (ICapabilityProvider provider, EnumFacing side, long power, boolean simulated) {
        
        return isJouleProducer(provider, side) ? getJouleProducer(provider, side).takePower(power, simulated) : 0;
    }
    
    /**
     * Gets a list of all capabilities that touch a BlockPos. This will search for tile
     * entities touching the BlockPos and then query them for access to their capabilities.
     * 
     * @param capability The capability you want to retrieve.
     * @param world The world that this is happening in.
     * @param pos The position to search around.
     * @return A list of all capabilities that are being held by connected blocks.
     */
    public static <T> List<T> getConnectedCapabilities (Capability<T> capability, World world, BlockPos pos) {
        
        final List<T> capabilities = new ArrayList<T>();
        
        for (final EnumFacing side : EnumFacing.values()) {
            
            final TileEntity tile = world.getTileEntity(pos.offset(side));
            
            if (tile != null && !tile.isInvalid() && tile.hasCapability(capability, side.getOpposite()))
                capabilities.add(tile.getCapability(capability, side.getOpposite()));
        }
        
        return capabilities;
    }
    
    /**
     * Attempts to give power to all consumers touching the given BlockPos.
     * 
     * @param world The world that this is happening in.
     * @param pos The position to search around.
     * @param amount The amount of power to offer to each individual face.
     * @param simulated Whether or not this is being ran as part of a simulation.
     * @return The amount of power that was consumed.
     */
    public static long distributePowerToAllFaces (World world, BlockPos pos, long amount, boolean simulated) {
        
        long consumedPower = 0L;
        
        for (final IJouleConsumer consumer : getConnectedCapabilities(JouleCapabilities.CAPABILITY_CONSUMER, world, pos))
            consumedPower += consumer.givePower(amount, simulated);
            
        return consumedPower;
    }
    
    /**
     * Attempts to consume power from all producers touching the given BlockPos.
     * 
     * @param world The world that this is happening in.
     * @param pos The position to search around.
     * @param amount The amount of power to request from each individual face.
     * @param simulated Whether or not this is being ran as part of a simulation.
     * @return The amount of power that was successfully consumed.
     */
    public static long consumePowerFromAllFaces (World world, BlockPos pos, long amount, boolean simulated) {
        
        long recievedPower = 0L;
        
        for (final IJouleProducer producer : getConnectedCapabilities(JouleCapabilities.CAPABILITY_PRODUCER, world, pos))
            recievedPower += producer.takePower(amount, simulated);
            
        return recievedPower;
    }
    
    /**
     * Checks if a capability is for the Joule holder.
     * 
     * @param capability The capability to check.
     * @return Whether or not the capability is for the Joule holder.
     */
    public static boolean isHolderCapability (Capability<?> capability) {
        
        return capability == JouleCapabilities.CAPABILITY_HOLDER;
    }
    
    /**
     * Checks if a capability is for the Joule consumer.
     * 
     * @param capability The capability to check.
     * @return Whether or not the capability is for the Joule consumer.
     */
    public static boolean isConsumerCapability (Capability<?> capability) {
        
        return capability == JouleCapabilities.CAPABILITY_CONSUMER;
    }
    
    /**
     * Checks if a capability is for the Joule producer.
     * 
     * @param capability The capability to check.
     * @return Whether or not the capability is for the Joule producer.
     */
    public static boolean isProducerCapability (Capability<?> capability) {
        
        return capability == JouleCapabilities.CAPABILITY_PRODUCER;
    }
    
    /**
     * Checks if a capability is a Joule capability
     * 
     * @param capability The capability to check.
     * @return Whether or not the capability is a Joule capability.
     */
    public static boolean isJouleCapability (Capability<?> capability) {
        
        return isHolderCapability(capability) || isConsumerCapability(capability) || isProducerCapability(capability);
    }
    
    /**
     * Generates tooltip data for an ItemStack that has the IJouleHolder interface.
     * Additionally, if the holder is a BaseJouleContainer, input/output rates will be shown.
     * 
     * @param stack The ItemStack to make the tooltip for.
     * @param tooltip A list of strings which represent the lines of the tooltip.
     */
    @SideOnly(Side.CLIENT)
    public static void createTooltip (ItemStack stack, List<String> tooltip) {
        
        if (isJouleHolder(stack, EnumFacing.DOWN)) {
            
            final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
            final IJouleHolder holder = JouleUtils.getJouleHolder(stack, EnumFacing.DOWN);
            
            if (GameSettings.isKeyDown(keyBindSneak)) {
                
                addHolderInfo(holder, tooltip);
                
                if (holder instanceof BaseJouleContainer) {
                    
                    final BaseJouleContainer container = (BaseJouleContainer) holder;
                    tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.Joule.input", Long.toString(container.getInputRate())));
                    tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.Joule.output", Long.toString(container.getOutputRate())));
                }
            }
            
            else
                tooltip.add(I18n.format("tooltip.Joule.showinfo", ChatFormatting.DARK_AQUA, keyBindSneak.getDisplayName(), ChatFormatting.GRAY));
        }
    }
    
    /**
     * Adds Joule holder info to a tooltip.
     * 
     * @param stack The ItemStack to display info for.
     * @param tooltip The tooltip to add the info to.
     */
    @SideOnly(Side.CLIENT)
    public static void addHolderInfo (ItemStack stack, List<String> tooltip) {
        
        if (isJouleHolder(stack, EnumFacing.DOWN))
            addHolderInfo(getJouleHolder(stack, EnumFacing.DOWN), tooltip);
    }
    
    /**
     * Adds Joule holder info to a tooltip.
     * 
     * @param holder The IJouleHolder to display info for.
     * @param tooltip The tooltip to add the info to.
     */
    @SideOnly(Side.CLIENT)
    public static void addHolderInfo (IJouleHolder holder, List<String> tooltip) {
        
        addHolderInfo(holder.getStoredPower(), holder.getCapacity(), tooltip);
    }
    
    /**
     * Adds Joule holder info to a tooltip.
     * 
     * @param stored The amount of stored power.
     * @param capacity The max capacity.
     * @param tooltip The tooltip to add the info to.
     */
    @SideOnly(Side.CLIENT)
    public static void addHolderInfo (long stored, long capacity, List<String> tooltip) {
        
        tooltip.add(ChatFormatting.DARK_AQUA + I18n.format("tooltip.Joule.powerinfo", Long.toString(stored), Long.toString(capacity)));
    }
}