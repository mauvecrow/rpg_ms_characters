package tech.quangson.characters.service;

import org.springframework.stereotype.Service;
import tech.quangson.characters.data.RpgCharactersDao;
import tech.quangson.characters.data.RpgCharactersEntity;
import tech.quangson.characters.data.RpgMoveDao;
import tech.quangson.characters.data.RpgStatSetDao;
import tech.quangson.characters.data.enums.Stat;
import tech.quangson.characters.domain.GameCharacter;
import tech.quangson.characters.domain.GameMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CharacterServiceImpl implements CharacterService{

    private final RpgCharactersDao charactersDao;
    private final RpgMoveDao moveDao;
    private final RpgStatSetDao statSetDao;

    public CharacterServiceImpl(RpgCharactersDao charactersDao, RpgMoveDao moveDao, RpgStatSetDao statSetDao) {
        this.charactersDao = charactersDao;
        this.moveDao = moveDao;
        this.statSetDao = statSetDao;
    }

    /**
     *
     * @return a list of all characters and their basic properties. This does not include character stats nor moves.
     */
    @Override
    public List<GameCharacter> getAllCharacters() {
        var iteratorableEntities =  charactersDao.findAll();
        List<GameCharacter> allCharacters = new ArrayList<>();
        iteratorableEntities.forEach(e -> {
            GameCharacter gc = characterMapper(e);
            allCharacters.add(gc);
        });
        return allCharacters;
    }


    @Override
    public GameMeta getCharacterMeta(String characterName) {
        var entity = charactersDao.findByName(characterName).orElse(null);
        if(entity == null){
            return null;
        }
        // build character
        var gameCharacter = characterMapper(entity);

        // get and assign moves
        return new GameMeta(gameCharacter, List.of());

    }

    private GameCharacter characterMapper(RpgCharactersEntity entity){
        var builder = new GameCharacter.CharacterBuilder(entity.getName());
        builder.avatar(entity.getAvatar());
        builder.profile(entity.getProfile());
        builder.characterClass(entity.getClazz());
        GameCharacter gameCharacter = builder.build();
        assignStats(entity, gameCharacter);
        return gameCharacter;
    }

    private void assignStats(RpgCharactersEntity entity, GameCharacter gc) {
        var statsEntity = statSetDao.findByCharacterId(entity.getCharacterId());
        gc.getStats().put(Stat.Force, statsEntity.getForce());
        gc.getStats().put(Stat.Reflex, statsEntity.getReflex());
        gc.getStats().put(Stat.Focus, statsEntity.getFocus());
        gc.getStats().put(Stat.Spirit, statsEntity.getSpirit());
        gc.getStats().put(Stat.Agility, statsEntity.getAgility());
        gc.getStats().put(Stat.Evasion, statsEntity.getEvasion());
        gc.getStats().put(Stat.Health, statsEntity.getHealth());
        gc.getStats().put(Stat.Energy, statsEntity.getEnergy());
    }


}
