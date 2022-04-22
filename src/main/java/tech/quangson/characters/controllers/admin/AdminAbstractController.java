package tech.quangson.characters.controllers.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AdminAbstractController<T>{

    private final AdminCrud<T> adminCrud;

    public AdminAbstractController(AdminCrud<T> adminCrud) {
        this.adminCrud = adminCrud;
    }

    public List<T> getAllEntities(){
        return adminCrud.getAll();
    }

    public ResponseEntity<?> createEntity(T newEntity){
        try {
            var result = adminCrud.create(newEntity);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getEntity(int entityId){
        var result = adminCrud.read(entityId);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new MessageResponse("No entity found with id: " + entityId), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> updateEntity(int entityId, T updatedEntity){
        try {
            var result = adminCrud.update(entityId, updatedEntity);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteEntity(int entityId){
        try{
            adminCrud.delete(entityId);
            return new ResponseEntity<>(new MessageResponse("Entity deleted"), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<?> updateAll(List<T> entities){
        try {
            var result = adminCrud.updateAll(entities);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    record MessageResponse(String message){}
}
