package tech.quangson.characters.domain;

import java.util.Map;

public class GameMove {
    private final String moveName;
    private final String category;
    private final String type;
    private final int basePower;
    private final int cost;
    private final int limit;
    private final int priority;
    private final Map<String, Integer> buffs;
    private final Map<String, Integer> debuffs;

    private GameMove(MoveBuilder builder){
        moveName = builder.moveName;
        category = builder.category;
        type = builder.type;
        basePower = builder.basePower;
        cost = builder.cost;
        limit = builder.limit;
        priority = builder.priority;
        buffs = builder.buffs;
        debuffs = builder.debuffs;
    }

    public String getMoveName() {
        return moveName;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public int getBasePower() { return basePower; }

    public int getCost() {
        return cost;
    }

    public int getLimit() {
        return limit;
    }

    public int getPriority() {
        return priority;
    }

    public Map<String, Integer> getBuffs() {
        return buffs;
    }

    public Map<String, Integer> getDebuffs() {
        return debuffs;
    }

    public static class MoveBuilder{

        private final String moveName;
        private String category;
        private String type;
        private int basePower;
        private int cost;
        private int limit;
        private int priority;
        private Map<String, Integer> buffs;
        private Map<String, Integer> debuffs;

        public MoveBuilder(String name){
            moveName = name;
        }

        public MoveBuilder category(String category){
            this.category = category;
            return this;
        }

        public MoveBuilder type(String type){
            this.type = type;
            return this;
        }

        public MoveBuilder basePower(int basePower){
            this.basePower = basePower;
            return this;
        }

        public MoveBuilder cost(int cost){
            this.cost = cost;
            return this;
        }

        public MoveBuilder limit(int limit){
            this.limit = limit;
            return this;
        }

        public MoveBuilder priority(int priority){
            this.priority = priority;
            return this;
        }

        public MoveBuilder buffs(Map<String, Integer> buffs){
            this.buffs = buffs;
            return this;
        }

        public MoveBuilder debuffs(Map<String, Integer> debuffs){
            this.debuffs = debuffs;
            return this;
        }

        public GameMove build(){
            GameMove gameMove= new GameMove(this);
            if(validate()) return gameMove;
            else return null;
        }

        private boolean validate(){
            return category != null && type != null && basePower >= 0
                    && cost >= 0 && limit > 0 && priority > 0;
        }
    }
}
