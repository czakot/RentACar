/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentacar.backend.service;

import rentacar.backend.dao.DaoManager;

/**
 *
 * @author czakot
 */
public abstract class AService {

    protected static final DaoManager daoManager = ServicesCommon.getDaoManager();
    
}
