package tech.quangson.characters.controllers;


import org.springframework.web.bind.annotation.*;
import tech.quangson.characters.domain.GameCharacter;
import tech.quangson.characters.domain.GameMeta;
import tech.quangson.characters.service.CharacterService;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharactersController {

    private final CharacterService characterService;

    public CharactersController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping(path = "/{name}")
    public GameMeta getCharacterMeta(@PathVariable String name){
        return characterService.getCharacterMeta(name);
    }

    @GetMapping(path = "/all")
    public List<GameCharacter> getAllCharacters(){
        return characterService.getAllCharacters();
    }
}
