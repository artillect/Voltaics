package com.artillect.voltaics.entity;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityTurretAI extends EntityAIAttackMelee {
	private int targetAcquiredTicks;
	private EntityTurret turret;
	
	public EntityTurretAI(EntityTurret zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.turret = zombieIn;
    }
	
	@Override
    public void startExecuting() {
        super.startExecuting();
        this.targetAcquiredTicks = 0;
    }
	
	@Override
    public void resetTask() {
        super.resetTask();
        this.turret.setTargetAcquired(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.targetAcquiredTicks;

        if (this.targetAcquiredTicks >= 5 && this.attackTick < 10) {
            this.turret.setTargetAcquired(true);
        } else {
            this.turret.setTargetAcquired(false);
        }
    }
}
