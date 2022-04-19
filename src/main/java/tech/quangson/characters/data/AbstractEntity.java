package tech.quangson.characters.data;

public abstract class AbstractEntity {
    public abstract int pullPrimaryKey();
    public abstract void pushPrimaryKey(int key);
}

