package tech.quangson.characters.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.quangson.characters.data.RpgMoveDao;
import tech.quangson.characters.data.RpgMoveEntity;

import java.util.List;

@RestController
@RequestMapping("/admin/moves")
public class AdminMovesController extends AdminAbstractController<RpgMoveEntity>{

    public AdminMovesController(RpgMoveDao dao) {
        super(new AdminCrudImpl<>(dao));
    }

    @GetMapping()
    public List<RpgMoveEntity> getAllMoves(){
        return getAllEntities();
    }

    @GetMapping("/{moveId}")
    public ResponseEntity<?> getMove(@PathVariable int moveId){
        return getEntity(moveId);
    }

    @PostMapping()
    public ResponseEntity<?> createMove(@RequestBody RpgMoveEntity newMove){
        return createEntity(newMove);
    }

    @PutMapping("/{moveId}")
    public ResponseEntity<?> updateMove(@PathVariable int moveId, @RequestBody RpgMoveEntity existingMove){
        return updateEntity(moveId, existingMove);
    }

    @DeleteMapping("/{moveId}")
    public ResponseEntity<?> deleteMove(@PathVariable int moveId){
        return deleteEntity(moveId);
    }

    @PutMapping()
    public ResponseEntity<?> saveAll(@RequestBody List<RpgMoveEntity> moves){
        return updateAll(moves);
    }
}
