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
public class ServicesCommon {
    
    private static final DaoManager daoManager = new DaoManager();

    public static DaoManager getDaoManager() {
        return daoManager;
    }

    public static void closeDB() {
        daoManager.closeDB();
    }
    
}
