package tech.quangson.characters.controllers.admin;

import java.util.List;

public interface AdminCrud<T> {

    T create(T entity);
    T read(int entityId);
    T update(int entityId, T entity);
    boolean delete(int entityId);

    List<T> getAll();
    T updateEntityId(T newEntity, T oldEntity);
    List<T> updateAll(List<T> entities);
}
