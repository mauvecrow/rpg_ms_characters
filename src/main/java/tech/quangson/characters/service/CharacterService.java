package tech.quangson.characters.service;

import tech.quangson.characters.domain.GameCharacter;
import tech.quangson.characters.domain.GameMeta;

import java.util.List;

public interface CharacterService {

    List<GameCharacter> getAllCharacters();
    GameMeta getCharacterMeta(String characterName);
}
