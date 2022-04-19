package tech.quangson.characters.data;

import tech.quangson.characters.data.enums.MoveCategory;
import tech.quangson.characters.data.enums.MoveType;
import tech.quangson.characters.data.enums.Stat;
import tech.quangson.characters.data.enums.TinyInt;

import javax.persistence.*;

@Entity
@Table(name = "rpg_finite_moves")
public final class RpgMoveEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finite_move_id")
    private int moveId;

    @Column(name = "move_name")
    private String moveName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private MoveCategory moveCategory;

    @Column(name = "move_type")
    @Enumerated(EnumType.STRING)
    private MoveType moveType;

    @Column(name = "base_power")
    private int basePower;

    @Column(name = "move_limit")
    private int moveLimit;

    private int cost;

    private int priority;

    @Column(name = "has_buffs")
    @Enumerated(EnumType.ORDINAL)
    private TinyInt hasBuffs;

    @Column(name = "buff_stat_1")
    @Enumerated(EnumType.STRING)
    private Stat buffStat1;

    @Column(name = "buff_amount_1")
    private int buffAmount1;

    @Column(name = "buff_stat_2")
    @Enumerated(EnumType.STRING)
    private Stat buffStat2;

    @Column(name = "buff_amount_2")
    private int buffAmount2;

    @Column(name = "has_debuffs")
    @Enumerated(EnumType.ORDINAL)
    private TinyInt hasDebuffs;

    @Column(name = "debuff_stat_1")
    @Enumerated(EnumType.STRING)
    private Stat debuffStat1;

    @Column(name = "debuff_amount_1")
    private int debuffAmount1;

    @Column(name = "debuff_stat_2")
    @Enumerated(EnumType.STRING)
    private Stat debuffStat2;

    @Column(name = "debuff_amount_2")
    private int debuffAmount2;

    // getters and setters
    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public MoveCategory getMoveCategory() {
        return moveCategory;
    }

    public void setMoveCategory(MoveCategory moveCategory) {
        this.moveCategory = moveCategory;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public int getBasePower() {
        return basePower;
    }

    public void setBasePower(int basePower) {
        this.basePower = basePower;
    }

    public int getMoveLimit() {
        return moveLimit;
    }

    public void setMoveLimit(int moveLimit) {
        this.moveLimit = moveLimit;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TinyInt getHasBuffs() {
        return hasBuffs;
    }

    public void setHasBuffs(TinyInt hasBuffs) {
        this.hasBuffs = hasBuffs;
    }

    public Stat getBuffStat1() {
        return buffStat1;
    }

    public void setBuffStat1(Stat buffStat1) {
        this.buffStat1 = buffStat1;
    }

    public int getBuffAmount1() {
        return buffAmount1;
    }

    public void setBuffAmount1(int buffAmount1) {
        this.buffAmount1 = buffAmount1;
    }

    public Stat getBuffStat2() {
        return buffStat2;
    }

    public void setBuffStat2(Stat buffStat2) {
        this.buffStat2 = buffStat2;
    }

    public int getBuffAmount2() {
        return buffAmount2;
    }

    public void setBuffAmount2(int buffAmount2) {
        this.buffAmount2 = buffAmount2;
    }

    public TinyInt getHasDebuffs() {
        return hasDebuffs;
    }

    public void setHasDebuffs(TinyInt hasDebuffs) {
        this.hasDebuffs = hasDebuffs;
    }

    public Stat getDebuffStat1() {
        return debuffStat1;
    }

    public void setDebuffStat1(Stat debuffStat1) {
        this.debuffStat1 = debuffStat1;
    }

    public int getDebuffAmount1() {
        return debuffAmount1;
    }

    public void setDebuffAmount1(int debuffAmount1) {
        this.debuffAmount1 = debuffAmount1;
    }

    public Stat getDebuffStat2() {
        return debuffStat2;
    }

    public void setDebuffStat2(Stat debuffStat2) {
        this.debuffStat2 = debuffStat2;
    }

    public int getDebuffAmount2() {
        return debuffAmount2;
    }

    public void setDebuffAmount2(int debuffAmount2) {
        this.debuffAmount2 = debuffAmount2;
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpgMoveEntity that)) return false;

        return moveId == that.moveId;
    }

    @Override
    public int hashCode() {
        return moveId;
    }

    @Override
    public String toString() {
        return "RpgMoveEntity{" +
                "moveId=" + moveId +
                ", moveName='" + moveName + '\'' +
                ", moveCategory=" + moveCategory +
                ", moveType=" + moveType +
                ", basePower=" + basePower +
                ", moveLimit=" + moveLimit +
                ", cost=" + cost +
                ", priority=" + priority +
                ", buffStat1=" + buffStat1 +
                ", buffAmount1=" + buffAmount1 +
                ", buffStat2=" + buffStat2 +
                ", buffAmount2=" + buffAmount2 +
                ", debuffStat1=" + debuffStat1 +
                ", debuffAmount1=" + debuffAmount1 +
                ", debuffStat2=" + debuffStat2 +
                ", debuffAmount2=" + debuffAmount2 +
                '}';
    }

    @Transient
    @Override
    public int pullPrimaryKey() {
        return getMoveId();
    }

    @Override
    public void pushPrimaryKey(int key) {
        setMoveId(key);
    }
}
