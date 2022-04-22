package tech.quangson.characters.controllers.admin;

import org.springframework.data.repository.CrudRepository;
import tech.quangson.characters.data.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

public class AdminCrudImpl<T extends AbstractEntity> implements AdminCrud<T>{

    private final CrudRepository<T, Integer> dao;

    public AdminCrudImpl(CrudRepository<T, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public T create(T entity) {
        return dao.save(entity);
    }

    @Override
    public T read(int entityId) {
        return dao.findById(entityId).orElse(null);
    }

    @Override
    public T update(int entityId, T entity) {
        var search = dao.findById(entityId).orElse(null);
        if(search == null){
            return null;
        }
        var updated = updateEntityId(entity, search);
        return dao.save(updated);
    }

    @Override
    public boolean delete(int entityId) {
        var search = dao.findById(entityId);
        if(search.isPresent()){
            dao.deleteById(entityId);
            return true;
        }
        return false;
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        dao.findAll().forEach(list::add);
        return list;
    }

    @Override
    public T updateEntityId(T newEntity, T oldEntity) {
        newEntity.pushPrimaryKey(oldEntity.pullPrimaryKey());
        return newEntity;
    }

    @Override
    public List<T> updateAll(List<T> entities) {
        return (List<T>) dao.saveAll(entities);
    }
}
