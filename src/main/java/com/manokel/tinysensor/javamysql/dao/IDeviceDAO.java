package com.manokel.tinysensor.javamysql.dao;

import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.model.Device;

import java.util.List;

/**
 * Public DAO API methods for {@link Device} class.
 */
public interface IDeviceDAO {
    Device insert(Device device) throws DAOException;
    Device update(Device device) throws DAOException;
    void delete(Device device) throws DAOException;
    List<Device> getByDeviceModel(String model) throws DAOException;
    Device getByDeviceId(int deviceId) throws DAOException;
}
