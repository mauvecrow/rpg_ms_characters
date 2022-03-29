package tech.quangson.characters.data.enums;

public enum TinyInt {
    False(0),
    True(1);
    private final int value;

    private TinyInt(int value){
        this.value = value;
    }

    public int databaseValue(){
        return value;
    }

    public boolean booleanValue(){
        return this.value == 1;
    }
}
