package tech.quangson.characters.service;

import org.springframework.stereotype.Service;
import tech.quangson.characters.data.*;
import tech.quangson.characters.data.enums.Stat;
import tech.quangson.characters.domain.GameCharacter;
import tech.quangson.characters.domain.GameMeta;
import tech.quangson.characters.domain.GameMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CharacterServiceImpl implements CharacterService{

    private final RpgCharactersDao charactersDao;
    private final RpgMovesetDao movesetDao;
    private final RpgStatSetDao statSetDao;

    public CharacterServiceImpl(RpgCharactersDao charactersDao, RpgMovesetDao movesetDao, RpgStatSetDao statSetDao) {
        this.charactersDao = charactersDao;
        this.movesetDao = movesetDao;
        this.statSetDao = statSetDao;
    }

    /**
     *
     * @return a list of all characters and their basic properties. This does not include character stats nor moves.
     */
    @Override
    public List<GameCharacter> getAllCharacters() {
        var iterableEntities =  charactersDao.findAll();
        List<GameCharacter> allCharacters = new ArrayList<>();
        iterableEntities.forEach(e -> {
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
        var gameMoves = assignMoves(entity.getCharacterId());

        return new GameMeta(gameCharacter, gameMoves);

    }

    private GameCharacter characterMapper(RpgCharactersEntity entity){
        var builder = new GameCharacter.CharacterBuilder(entity.getName());
        builder.avatar(entity.getAvatar());
        builder.profile(entity.getProfile());
        builder.characterClass(entity.getClazz());
        GameCharacter gameCharacter = builder.build();
        assignStats(entity.getCharacterId(), gameCharacter);
        return gameCharacter;
    }

    private void assignStats(int characterId, GameCharacter gc) {
        var statsEntityOptional = statSetDao.findByCharacterId(characterId);
        if(statsEntityOptional.isEmpty()) return;
        var statsEntity = statsEntityOptional.get();
        gc.getStats().put(Stat.Force, statsEntity.getForce());
        gc.getStats().put(Stat.Reflex, statsEntity.getReflex());
        gc.getStats().put(Stat.Focus, statsEntity.getFocus());
        gc.getStats().put(Stat.Spirit, statsEntity.getSpirit());
        gc.getStats().put(Stat.Agility, statsEntity.getAgility());
        gc.getStats().put(Stat.Evasion, statsEntity.getEvasion());
        gc.getStats().put(Stat.Health, statsEntity.getHealth());
        gc.getStats().put(Stat.Energy, statsEntity.getEnergy());
    }

    private List<GameMove> assignMoves(int characterId){
        var movesetOptional = movesetDao.findAllByCharacterId(characterId);
        if(movesetOptional.isEmpty()) return null;
        var moveset = movesetOptional.get();
        var moves = new ArrayList<GameMove>();
        moveset.forEach( ms ->
        {
            var moveEntity = ms.getRpgMove();
            GameMove move = new GameMove.MoveBuilder(moveEntity.getMoveName())
                    .category(moveEntity.getMoveCategory().toString())
                    .type(moveEntity.getMoveType().toString())
                    .basePower(moveEntity.getBasePower())
                    .limit(moveEntity.getMoveLimit())
                    .cost(moveEntity.getCost())
                    .priority(moveEntity.getPriority())
                    .buffs(captureBuffs(moveEntity))
                    .debuffs(captureDebuffs(moveEntity))
                    .build();
            moves.add(move);
        });
        return moves;
    }

    private Map<String, Integer> captureBuffs(RpgMoveEntity entity){
        var buff1Stat = entity.getBuffStat1();
        var buff2Stat = entity.getBuffStat2();
        if( buff1Stat == null && buff2Stat == null){
            return Map.of();
        }
        else if( buff1Stat != null && buff2Stat == null){
            String buff1 = buff1Stat.name();
            int buff1Amt = entity.getBuffAmount1();
            return Map.of(buff1, buff1Amt);
        }
        else if(buff1Stat == null){ // implies buff2Stat != null
            String buff2 = buff2Stat.name();
            int buff2Amt = entity.getBuffAmount2();
            return Map.of(buff2, buff2Amt);
        }
        else {
            String buff1 = buff1Stat.name();
            int buff1Amt = entity.getBuffAmount1();
            String buff2 = buff2Stat.name();
            int buff2Amt = entity.getBuffAmount2();
            return Map.of(buff1, buff1Amt, buff2, buff2Amt);
        }
    }

    private Map<String, Integer> captureDebuffs(RpgMoveEntity entity){
        var debuff1Stat = entity.getDebuffStat1();
        var debuff2Stat = entity.getDebuffStat2();
        if( debuff1Stat == null && debuff2Stat == null){
            return Map.of();
        }
        else if( debuff1Stat != null && debuff2Stat == null){
            String debuff1 = debuff1Stat.name();
            int debuff1Amt = entity.getDebuffAmount1();
            return Map.of(debuff1, debuff1Amt);
        }
        else if(debuff1Stat == null){ // implies buff2Stat != null
            String debuff2 = debuff2Stat.name();
            int debuff2Amt = entity.getDebuffAmount2();
            return Map.of(debuff2, debuff2Amt);
        }
        else {
            String debuff1 = debuff1Stat.name();
            int debuff1Amt = entity.getDebuffAmount1();
            String debuff2 = debuff2Stat.name();
            int debuff2Amt = entity.getDebuffAmount2();
            return Map.of(debuff1, debuff1Amt, debuff2, debuff2Amt);
        }
    }


}
