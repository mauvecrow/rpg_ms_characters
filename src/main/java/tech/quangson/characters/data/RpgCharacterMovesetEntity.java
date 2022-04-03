package tech.quangson.characters.data;

import javax.persistence.*;

@Entity
@Table(name="rpg_character_moveset")
public class RpgCharacterMovesetEntity {

    @Id
    @Column(name = "moveset_id")
    private int movesetId;

    @Column(name = "character_id")
    private int characterId;

//    @Column(name = "finite_move_id")
//    private int moveId;
    @OneToOne
    @JoinColumn(name = "finite_move_id", referencedColumnName = "finite_move_id")
    private RpgMoveEntity rpgMove;

    @Column(name = "is_default")
    private Character isDefault;

    @Column(name = "is_unlockable")
    private Character isUnlockable;

    public int getMovesetId() {
        return movesetId;
    }

    public void setMovesetId(int movesetId) {
        this.movesetId = movesetId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

//    public int getMoveId() {
//        return moveId;
//    }
//
//    public void setMoveId(int moveId) {
//        this.moveId = moveId;
//    }


    public RpgMoveEntity getRpgMove() {
        return rpgMove;
    }

    public void setRpgMove(RpgMoveEntity rpgMove) {
        this.rpgMove = rpgMove;
    }

    public Character getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Character isDefault) {
        this.isDefault = isDefault;
    }

    public Character getIsUnlockable() {
        return isUnlockable;
    }

    public void setIsUnlockable(Character isUnlockable) {
        this.isUnlockable = isUnlockable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpgCharacterMovesetEntity)) return false;

        RpgCharacterMovesetEntity that = (RpgCharacterMovesetEntity) o;

        return getMovesetId() == that.getMovesetId();
    }

    @Override
    public int hashCode() {
        return getMovesetId();
    }
}
