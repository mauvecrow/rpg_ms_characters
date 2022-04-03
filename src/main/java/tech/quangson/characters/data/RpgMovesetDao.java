package tech.quangson.characters.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RpgMovesetDao extends CrudRepository<RpgCharacterMovesetEntity, Integer> {

    Optional<List<RpgCharacterMovesetEntity>> findAllByCharacterId(int characterId);
}
