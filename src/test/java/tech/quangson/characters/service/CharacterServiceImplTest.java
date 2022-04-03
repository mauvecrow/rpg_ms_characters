package tech.quangson.characters.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.quangson.characters.data.*;
import tech.quangson.characters.domain.GameCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        rpgEntity.setAvatar(new byte[]{0,1,2,3});
        rpgEntity.setProfile(new byte[]{10,20,30,40});
        Iterable<RpgCharactersEntity> entities = Arrays.asList(rpgEntity, rpgEntity, rpgEntity);
        given(charactersDao.findAll())
                .willReturn(entities);

        //when
        var characterList = serviceTest.getAllCharacters();

        //then
        then(charactersDao).should(times(1)).findAll();
        then(charactersDao).should(never()).findByName(anyString());
        then(charactersDao).should(never()).findById(anyInt());
    }
}
