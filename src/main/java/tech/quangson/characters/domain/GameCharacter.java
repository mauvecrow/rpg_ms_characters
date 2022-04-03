package tech.quangson.characters.domain;

import tech.quangson.characters.data.enums.Stat;

import java.util.Arrays;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameCharacter)) return false;

        GameCharacter that = (GameCharacter) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getCharacterClass() != null ? !getCharacterClass().equals(that.getCharacterClass()) : that.getCharacterClass() != null)
            return false;
        if (!Arrays.equals(getAvatar(), that.getAvatar())) return false;
        if (!Arrays.equals(getProfile(), that.getProfile())) return false;
        return getStats() != null ? getStats().equals(that.getStats()) : that.getStats() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getCharacterClass() != null ? getCharacterClass().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getAvatar());
        result = 31 * result + Arrays.hashCode(getProfile());
        result = 31 * result + (getStats() != null ? getStats().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameCharacter{" +
                "name='" + name + '\'' +
                ", characterClass='" + characterClass + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                ", profile=" + Arrays.toString(profile) +
                ", stats=" + stats +
                '}';
    }
}
