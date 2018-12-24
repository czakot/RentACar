/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.util.List;

/**
 * Data Access Object Interfész
 * (create, update, findAll)
 * 
 * @author czakot
 * @param <E>
 * @param <K>
 */
public interface IGenericDao<E, K> {

    /**
     * Egy új entitás elmentése
     *
     * @param entity
     * @return
     */
    E save(E entity);

    /**
     * Egy entitás módosítása
     *
     * @param entity
     */
    void update(E entity);

    /**
     * Entitások plédányainak visszaadása a megadott osztály szerint
     *
     * @return
     */
    List<E> findAll();
}
