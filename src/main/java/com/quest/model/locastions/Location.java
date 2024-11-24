package com.quest.model.locastions;

import com.quest.model.services.ObjectType;

public abstract class Location {
    protected ObjectType type;

    public ObjectType getType() {
        return type;
    }

    public abstract String getDescription();

    public String getMapSymbol() {
        return type.getSymbol();
    }
}