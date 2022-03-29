package tech.quangson.characters.controllers;


import org.springframework.web.bind.annotation.*;
import tech.quangson.characters.domain.GameMeta;
import tech.quangson.characters.service.CharacterService;

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
}
