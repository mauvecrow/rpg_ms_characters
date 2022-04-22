package tech.quangson.characters.domain;

import java.util.List;

public class GameMeta {

    private final GameCharacter gameCharacter;
    private final List<GameMove> moves;

    public GameMeta(GameCharacter gameCharacter, List<GameMove> moves) {
        this.gameCharacter = gameCharacter;
        this.moves = moves;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public List<GameMove> getMoves() {
        return List.copyOf(moves);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameMeta)) return false;

        GameMeta gameMeta = (GameMeta) o;

        if (getGameCharacter() != null ? !getGameCharacter().equals(gameMeta.getGameCharacter()) : gameMeta.getGameCharacter() != null)
            return false;
        return getMoves() != null ? getMoves().equals(gameMeta.getMoves()) : gameMeta.getMoves() == null;
    }

    @Override
    public int hashCode() {
        int result = getGameCharacter() != null ? getGameCharacter().hashCode() : 0;
        result = 31 * result + (getMoves() != null ? getMoves().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameMeta{" +
                "gameCharacter=" + gameCharacter +
                ", moves=" + moves +
                '}';
    }
}
