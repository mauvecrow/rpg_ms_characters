package tech.quangson.characters.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;

@Entity
@Table(name = "rpg_characters", schema = "jakarta")
public final class RpgCharactersEntity extends AbstractEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "character_id")
    private int characterId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "class")
    private String clazz;
    @Basic
    @Column(name = "avatar")
    private byte[] avatar;
    @Basic
    @Column(name = "profile")
    private byte[] profile;
    @Basic
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Basic
    @Column(name = "created_by")
    private String createdBy;
    @Basic
    @Column(name = "last_update_date")
    private Timestamp lastUpdateDate;
    @Basic
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RpgCharactersEntity that = (RpgCharactersEntity) o;

        return characterId == that.characterId;
    }

    @Override
    public int hashCode() {
        return getCharacterId();
    }

    @Override
    public String toString() {
        return "RpgCharactersEntity{" +
                "characterId=" + characterId +
                ", name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                ", profile=" + Arrays.toString(profile) +
                ", creationDate=" + creationDate +
                ", createdBy='" + createdBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }

    @Override
    public int pullPrimaryKey() {
        return getCharacterId();
    }

    @Override
    public void pushPrimaryKey(int key) {
        setCharacterId(key);
    }
}
