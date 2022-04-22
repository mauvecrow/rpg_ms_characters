package tech.quangson.characters.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.quangson.characters.data.RpgCharactersDao;
import tech.quangson.characters.data.RpgCharactersEntity;

import java.util.List;

@RestController
@RequestMapping("/admin/characters")
public class AdminCharactersController extends AdminAbstractController<RpgCharactersEntity> {

    public AdminCharactersController(RpgCharactersDao dao) {
        super(new AdminCrudImpl<>(dao));
    }

    @GetMapping
    public List<RpgCharactersEntity> getAllCharacters(){
        return getAllEntities();
    }

    @PutMapping
    public ResponseEntity<?> saveAll(@RequestBody List<RpgCharactersEntity> characters){
        return updateAll(characters);
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<?> getCharacter(@PathVariable int characterId){
        return getEntity(characterId);
    }

    @PostMapping
    public ResponseEntity<?> createCharacter(@RequestBody RpgCharactersEntity newCharacter){
        return createEntity(newCharacter);
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<?> updateCharacter(@PathVariable int characterId, @RequestBody RpgCharactersEntity existingCharacter){
        return updateEntity(characterId, existingCharacter);
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<?> deleteCharacter(@PathVariable int characterId){
        return deleteEntity(characterId);
    }

    @GetMapping("/min")
    public List<MinCharacter> getAllMinimum(){
        return getAllEntities()
                .stream()
                .map( c -> new MinCharacter(c.getCharacterId(), c.getName(), c.getClazz()))
                .toList();
    }

    record MinCharacter(int characterId, String name, String clazz){}
}
