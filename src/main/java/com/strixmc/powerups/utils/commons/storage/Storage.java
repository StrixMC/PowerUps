package com.strixmc.powerups.utils.commons.storage;

import java.util.Optional;

public interface Storage<T> {

    Optional<T> find(String id);

    void loadAll();

    boolean exist(String id);

    void save(T object);

    void saveAll();

    void delete(String id);

}
