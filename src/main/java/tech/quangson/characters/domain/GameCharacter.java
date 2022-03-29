package tech.quangson.characters.domain;

import tech.quangson.characters.data.enums.Stat;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameCharacter {
    private final String name;
    private final String characterClass;
    private final byte[] avatar;
    private final byte[] profile;
    private final Map<Stat, Integer> stats;

    private GameCharacter(CharacterBuilder builder) {
        this.name = builder.name;
        this.characterClass = builder.characterClass;
        this.avatar = builder.avatar;
        this.profile = builder.profile;
        stats = new LinkedHashMap<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public byte[] getProfile() {
        return profile;
    }

    public Map<Stat, Integer> getStats() {
        return stats;
    }

    // builder pattern
    public static class CharacterBuilder {
        private final String name;
        private String characterClass;
        private byte[] avatar;
        private byte[] profile;

        public CharacterBuilder(String name){
            this.name = name;
        }

        public CharacterBuilder characterClass(String characterClass){
            this.characterClass = characterClass;
            return this;
        }

        public CharacterBuilder avatar(byte[] avatar){
            this.avatar = avatar;
            return this;
        }

        public CharacterBuilder profile(byte[] profile){
            this.profile = profile;
            return this;
        }

        public GameCharacter build(){
            return new GameCharacter(this);
        }
    }
}
