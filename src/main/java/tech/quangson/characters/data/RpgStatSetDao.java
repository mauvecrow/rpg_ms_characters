package tech.quangson.characters.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RpgStatSetDao extends CrudRepository<RpgStatSetEntity, Integer> {
    Optional<RpgStatSetEntity> findByCharacterId(int id);
}
