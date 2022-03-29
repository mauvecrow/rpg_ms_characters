package tech.quangson.characters.data;

import javax.persistence.*;

@Entity
@Table(name = "rpg_stat_sets")
public final class RpgStatSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_sets_id")
    private int statSetsId;

    @Column(name = "character_id")
    private int characterId;

    private int force;
    private int reflex;
    private int focus;
    private int spirit;
    private int agility;
    private int evasion;
    private int energy;
    private int health;

    // getters and setters

    public int getStatSetsId() {
        return statSetsId;
    }

    public void setStatSetsId(int statSetsId) {
        this.statSetsId = statSetsId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getReflex() {
        return reflex;
    }

    public void setReflex(int reflex) {
        this.reflex = reflex;
    }

    public int getFocus() {
        return focus;
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpgStatSetEntity)) return false;

        RpgStatSetEntity that = (RpgStatSetEntity) o;

        if (getStatSetsId() != that.getStatSetsId()) return false;
        return getCharacterId() == that.getCharacterId();
    }

    @Override
    public int hashCode() {
        int result = getStatSetsId();
        result = 31 * result + getCharacterId();
        return result;
    }
}
