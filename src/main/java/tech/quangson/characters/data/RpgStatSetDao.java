package tech.quangson.characters.data;

import org.springframework.data.repository.CrudRepository;

public interface RpgStatSetDao extends CrudRepository<RpgStatSetEntity, Integer> {
    RpgStatSetEntity findByCharacterId(int id);
}
