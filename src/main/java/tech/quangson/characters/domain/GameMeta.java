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
}
