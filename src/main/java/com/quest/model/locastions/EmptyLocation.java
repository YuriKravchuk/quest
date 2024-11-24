package com.quest.model.locastions;

import com.quest.model.services.InteractionResult;
import com.quest.model.services.ObjectType;
import com.quest.model.entity.Player;

import java.util.Random;

public class EmptyLocation extends Location {
    private boolean hasMushroom = false;
    private boolean hasBerry = false;
    private boolean hasWater = false;
    private boolean hasNuts = false;

    public boolean isHasMushroom() {
        return hasMushroom;
    }

    public void setHasMushroom(boolean hasMushroom) {
        this.hasMushroom = hasMushroom;
    }

    public boolean isHasBerry() {
        return hasBerry;
    }

    public void setHasBerry(boolean hasBerry) {
        this.hasBerry = hasBerry;
    }

    public boolean isHasWater() {
        return hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean isHasNuts() {
        return hasNuts;
    }

    public void setHasNuts(boolean hasNuts) {
        this.hasNuts = hasNuts;
    }

    @Override
    public String getDescription() {
        if (this.hasMushroom) {
            return "Галявина з грибом. " + ObjectType.MUSHROOM.getSymbol();
        } else if (this.hasBerry) {
            return "Галявина з ягодами. " + ObjectType.BERRY.getSymbol();
        } else if (this.hasWater) {
            return "Галявина зі струмком. " + ObjectType.WATER.getSymbol();
        } else if (this.hasNuts) {
            return "Галявина з лісовим горіхом. " + ObjectType.NUTS.getSymbol();
        }else {
            return "Порожня галявина.";
        }
    }

    public EmptyLocation() {
        this.type = ObjectType.EMPTY;
        Random rand = new Random();
        int chance = rand.nextInt(100);
        if (chance < 15) {
            this.hasBerry = true;
        } else if (chance >=15 && chance < 25) {
            this.hasNuts = true;
        } else if (chance >= 25 && chance < 35) {
            this.hasMushroom = true;
        } else if (chance >= 35 && chance < 50) {
                this.hasWater = true;
        }
    }

    public InteractionResult eat(Player player) {
        if (this.hasMushroom) {
            setHasMushroom(false);
            player.increaseHealth(5);
            return InteractionResult.EAT_MUSHROOM;
        } else if (this.hasBerry) {
            setHasBerry(false);
            player.increaseHealth(3);
            return InteractionResult.EAT_BERRY;
        } else if (this.hasNuts) {
            setHasNuts(false);
            player.increaseHealth(4);
            return InteractionResult.EAT_NUTS;
        } else if (this.hasWater) {
            setHasWater(false);
            player.increaseHealth(5);
            return InteractionResult.DRINK_WATER;
        }
        return null;
    }
}