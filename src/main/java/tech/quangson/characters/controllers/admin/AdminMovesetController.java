package tech.quangson.characters.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.quangson.characters.data.RpgCharacterMovesetEntity;
import tech.quangson.characters.data.RpgMovesetDao;

import java.util.List;

@RestController
@RequestMapping("/admin/movesets")
public class AdminMovesetController extends AdminAbstractController<RpgCharacterMovesetEntity> {

    public AdminMovesetController(RpgMovesetDao dao) {
        super(new AdminCrudImpl<>(dao));
    }


    @GetMapping
    public List<RpgCharacterMovesetEntity> getAllMovesets(){
        return getAllEntities();
    }

    @PutMapping
    public ResponseEntity<?> saveAll(@RequestBody List<RpgCharacterMovesetEntity> movesets){
        return updateAll(movesets);
    }

    @GetMapping("/{movesetId}")
    public ResponseEntity<?> getMoveset(@PathVariable int movesetId){
        return getEntity(movesetId);
    }

    @PostMapping
    public ResponseEntity<?> createMoveset(@RequestBody RpgCharacterMovesetEntity newMoveset){
        return createEntity(newMoveset);
    }

    @PutMapping("/{movesetId}")
    public ResponseEntity<?> updateMoveset(@PathVariable int movesetId, @RequestBody RpgCharacterMovesetEntity existingMoveset){
        return updateEntity(movesetId, existingMoveset);
    }

    @DeleteMapping("/{movesetId}")
    public ResponseEntity<?> deleteMoveset(@PathVariable int movesetId){
        return deleteEntity(movesetId);
    }
}
