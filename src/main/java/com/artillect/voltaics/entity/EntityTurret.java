package com.artillect.voltaics.entity;

import com.artillect.voltaics.Voltaics;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTurret extends EntityCreature {
    private static final DataParameter<Boolean> TARGET_ACQUIRED = EntityDataManager.createKey(EntityTurret.class, DataSerializers.BOOLEAN);

    public static final ResourceLocation LOOT = new ResourceLocation(Voltaics.modId, "entities/weird_zombie");
    
    private double armour = 2.0D;
    private double damage = 2.0D;
    
	public EntityTurret(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
		
        setSize(0.6F, 1.95F);
	}
	
	@Override
    protected void entityInit() {
        super.entityInit();
        this.getDataManager().register(TARGET_ACQUIRED, Boolean.valueOf(false));
    }
	
	@SideOnly(Side.CLIENT)
    public boolean isTargetAcquired() {
        return this.getDataManager().get(TARGET_ACQUIRED).booleanValue();
    }
	
	@Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
//        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }
	
	@Override
    protected void initEntityAI() {
//        this.tasks.addTask(0, new EntityAISwimming(this));
//        this.tasks.addTask(2, new EntityTurretAI(this, 1.0D, false));
//        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
//        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityMob.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
	
	public void setTargetAcquired(boolean ta) {
        this.getDataManager().set(TARGET_ACQUIRED, Boolean.valueOf(ta));
    }
	
	private void applyEntityAI() {
//        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityMob.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
//        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
//        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }
	
    @Override
    public int getMaxSpawnedInChunk() {
        return 5;
    }
	
	protected ResourceLocation getLootTable() {
        return LOOT;
    }
}
