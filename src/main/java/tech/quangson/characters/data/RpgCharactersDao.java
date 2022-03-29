package tech.quangson.characters.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RpgCharactersDao extends CrudRepository<RpgCharactersEntity, Integer> {
    Optional<RpgCharactersEntity> findByName(String name);
}
