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
    Boolean save(E entity);

    /**
     * Egy elem törlése (specifikáción kívüli karbantartáshoz)
     * 
     * @param key
     */
    void delete(K key);
    
    /**
     * Egy entitás módosítása
     *
     * @param entity
     * @return 
     */
    Boolean update(E entity);

    /**
     * Entitás példány visszaadása megadott osztály és kulcs alapján
     *
     * @param key
     * @return
     */
    E findById(K key);

    /**
     * Entitások példányainak visszaadása a megadott osztály szerint
     *
     * @return
     */
    List<E> findAll();

}
