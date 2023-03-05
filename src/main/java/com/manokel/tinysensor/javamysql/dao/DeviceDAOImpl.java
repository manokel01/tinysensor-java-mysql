package com.manokel.tinysensor.javamysql.dao;

import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.model.Device;
import com.manokel.tinysensor.javamysql.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeviceDAOImpl implements IDeviceDAO {
    @Override
    public Device insert(Device device) throws DAOException {
        String sql = "INSERT INTO DEVICES (MODEL, INPUTS)" +
                "VALUES (?, ?)";
        int n;  // count row entries affected
        // close with resources to close connection and prepared statement.
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String model = device.getModel();
            int inputs = device.getInputs();

            if (model.equals("")) {
                System.err.println("Please enter all fields.");
                return null;
            }

            p.setString(1, model);
            p.setInt(2, inputs);
            p.executeUpdate();
            return device;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in Device" + device + "insertion.");
        }
    }

    @Override
    public Device update(Device device) throws DAOException {
        // search with unique key (surrogate opr natural key)
        String sql = "UPDATE DEVICES SET MODEL = ?, INPUTS = ?, WHERE ID = ?";
        // close with resources to close connection and prepared statement.
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String model = device.getModel();
            int inputs = device.getInputs();
            int deviceId = device.getDeviceId();

            if (model.equals("")) {
                System.err.println("Please enter new model name.");
                return null;
            }

            p.setString(1, model);
            p.setInt(2, inputs);
            p.setInt(3, deviceId);
            p.executeUpdate();
            return device;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Error in User" + device + "update.");
        }
    }

    @Override
    public void delete(Device device) throws DAOException {

    }

    @Override
    public List<Device> getByDeviceModel(String model) throws DAOException {
        return null;
    }

    @Override
    public Device getByDeviceId(int deviceId) throws DAOException {
        return null;
    }
}
