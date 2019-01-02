/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author czakot
 * @param <E>
 * @param <K>
 */
public abstract class GenericDao<E, K> implements IGenericDao<E, K> {
    
    protected final String tableName;
    protected final String keyName;
    protected Connection connection;

    protected GenericDao(Connection connection, String tableName, String keyName) {
        this.connection = connection;
        this.tableName = tableName;
        this.keyName = keyName;
    }

    @Override
    public abstract E save(E entity);

    @Override
    public abstract void update(E entity);

    @Override
    public abstract E findById(K key);

    @Override
    public abstract List<E> findAll();

    final void close(PreparedStatement statement){
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void setConnection(Connection connection) {
        this.connection = connection;
    }
}
