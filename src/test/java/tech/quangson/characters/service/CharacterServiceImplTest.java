package tech.quangson.characters.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.quangson.characters.data.*;
import tech.quangson.characters.data.enums.MoveCategory;
import tech.quangson.characters.data.enums.MoveType;
import tech.quangson.characters.data.enums.Stat;
import tech.quangson.characters.domain.GameCharacter;
import tech.quangson.characters.domain.GameMeta;
import tech.quangson.characters.domain.GameMove;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class CharacterServiceImplTest {
    CharacterService serviceTest;
    @Mock
    private RpgCharactersDao charactersDao;
    @Mock
    private RpgMovesetDao movesetDao;
    @Mock
    private RpgStatSetDao statSetDao;

    @BeforeEach
    void setup(){
        serviceTest = new CharacterServiceImpl(charactersDao, movesetDao, statSetDao);
    }

    @Test
    void getAllCharactersTestCharacterDaoBehavior(){
        // given
        var rpgEntity = new RpgCharactersEntity();
        rpgEntity.setName("Test");
        Iterable<RpgCharactersEntity> entities = Arrays.asList(rpgEntity, rpgEntity, rpgEntity);
        given(charactersDao.findAll())
                .willReturn(entities);

        //when
        var characterList = serviceTest.getAllCharacters();

        //then
        then(charactersDao).should(times(1)).findAll();
        then(charactersDao).should(never()).findByName(anyString());
        then(charactersDao).should(never()).findById(anyInt());
        Assertions.assertEquals(characterList.size(), 3);
    }
    /* Test notes
    Results here revealed issues with the process creating a GameCharacter from a RpgCharactersEntity
    - an entity may or may not have moves
    - a move may or may not have buffs/debuffs
     */

    @Test
    void GameCharacterMappingTest(){
        // given
        var rpgEntity =
                setupCharacterEntity(1,"Test","Test Class",
                        new byte[]{0,1,2,3},new byte[]{10,20,30,40});
        Iterable<RpgCharactersEntity> entities = Arrays.asList(rpgEntity, rpgEntity, rpgEntity);
        given(charactersDao.findAll())
                .willReturn(entities);

        GameCharacter expectedGC = new GameCharacter.CharacterBuilder("Test")
                .characterClass("Test Class")
                .avatar(new byte[]{0,1,2,3})
                .profile(new byte[]{10,20,30,40})
                .build();
        //when
        var characterList = serviceTest.getAllCharacters();

        //then
        Assertions.assertEquals(expectedGC, characterList.get(0));
    }

    @Test
    void getCharacterMetaTest(){
        //given
        final int characterId = 88;
        final String testName = "Bob Burgers"; final String characterClass = "Cook";
        final byte[] avatar = new byte[]{1,2,3}; final byte[] profile = new byte[] {10,20,30,40};
        var characterEntity = setupCharacterEntity(characterId, testName, characterClass, avatar, profile);
        given(charactersDao.findByName(testName))
                .willReturn(Optional.of(characterEntity));

        final String moveName1 = "Flip";
        final int bp1 = 10; final int cost1 = 20; final int limit1 = 30; final int priority1 = 40;
        RpgMoveEntity moveEntity = setupMoveEntity(7,moveName1, bp1, cost1, limit1, priority1,
                MoveCategory.Combat, MoveType.Damage);

        final String moveName2 = "Grill"; //final String cat2 = "category"; final String type2 = "type";
        final int bp2 = 10; final int cost2 = 20; final int limit2 = 30; final int priority2 = 40;
        var moveEntity2 = setupMoveEntity(8,moveName2, bp2, cost2, limit2, priority2,
                MoveCategory.Combat, MoveType.Damage);

        RpgCharacterMovesetEntity moveset1 = setupMovesetEntity(characterId, moveEntity);
        RpgCharacterMovesetEntity moveset2 = setupMovesetEntity(characterId, moveEntity2);
        var movesEntities = List.of(moveset1, moveset2);
        given(movesetDao.findAllByCharacterId(characterId))
                .willReturn(Optional.of(movesEntities));

        RpgStatSetEntity stats = setupStatsEntity(characterId);
        given(statSetDao.findByCharacterId(characterId))
                .willReturn(Optional.of(stats));

        //when
        var bobMeta = serviceTest.getCharacterMeta(testName);

        //then
        then(charactersDao).should(times(1)).findByName(anyString());
        then(movesetDao).should(times(1)).findAllByCharacterId(anyInt());
        then(statSetDao).should(times(1)).findByCharacterId(anyInt());

        var gc = new GameCharacter.CharacterBuilder(testName)
                .characterClass(characterClass)
                .avatar(avatar)
                .profile(profile)
                .build();
        var gcs = gc.getStats();
        gcs.putAll(Map.of(Stat.Agility,1,Stat.Energy,2,Stat.Evasion,3,Stat.Focus,4,
                Stat.Force,5,Stat.Health,6,Stat.Reflex,7,Stat.Spirit,8));
        var gm1 = new GameMove.MoveBuilder(moveName1)
                .category("Combat")
                .type("Damage")
                .basePower(bp1)
                .cost(cost1)
                .limit(limit1)
                .priority(priority1)
                .buffs(Map.of())
                .debuffs(Map.of())
                .build();
        var gm2 = new GameMove.MoveBuilder(moveName2)
                .category("Combat")
                .type("Damage")
                .basePower(bp2)
                .cost(cost2)
                .limit(limit2)
                .priority(priority2)
                .buffs(Map.of())
                .debuffs(Map.of())
                .build();
        var gcm = List.of(gm1,gm2);
        var expected = new GameMeta(gc, gcm);
        Assertions.assertEquals(expected, bobMeta);

    }

    private RpgCharacterMovesetEntity setupMovesetEntity(int characterId, RpgMoveEntity moveEntity) {
        var moveset1 = new RpgCharacterMovesetEntity();
        moveset1.setCharacterId(characterId);
        moveset1.setRpgMove(moveEntity);
        return moveset1;
    }

    private RpgStatSetEntity setupStatsEntity(int characterId) {
        var stats = new RpgStatSetEntity();
        stats.setCharacterId(characterId);
        stats.setAgility(1);
        stats.setEnergy(2);
        stats.setEvasion(3);
        stats.setFocus(4);
        stats.setForce(5);
        stats.setHealth(6);
        stats.setReflex(7);
        stats.setSpirit(8);
        return stats;
    }

    private RpgMoveEntity setupMoveEntity(int id, String moveName1, int bp1, int cost1, int limit1, int priority1,
                                          MoveCategory category, MoveType type) {
        var moveEntity = new RpgMoveEntity();
        moveEntity.setMoveId(id);
        moveEntity.setMoveName(moveName1);
        moveEntity.setBasePower(bp1);
        moveEntity.setCost(cost1);
        moveEntity.setMoveLimit(limit1);
        moveEntity.setPriority(priority1);
        moveEntity.setMoveCategory(category);
        moveEntity.setMoveType(type);
        return moveEntity;
    }

    private RpgCharactersEntity setupCharacterEntity(int characterId, String testName, String characterClass,
                                                     byte[] avatar, byte[] profile) {
        var characterEntity = new RpgCharactersEntity();
        characterEntity.setCharacterId(characterId);
        characterEntity.setName(testName);
        characterEntity.setClazz(characterClass);
        characterEntity.setAvatar(avatar);
        characterEntity.setProfile(profile);
        return characterEntity;
    }
}
