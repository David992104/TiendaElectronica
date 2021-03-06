package com.osda.tienda.dbconection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import com.osda.tienda.principal.addProducto.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoCRUD extends ConnectionDB {

	public ProductoCRUD() {
		
			getConnection("Desde ProductoCrud");
		
	}

	public ObservableList<Producto> getProductos() throws SQLException {
		ResultSet result = null;
		ObservableList<Producto> listaProducto = FXCollections.observableArrayList();

		CallableStatement call = (CallableStatement) connection.prepareCall("{CALL getProduct()}");
		call.execute();
		result = call.getResultSet();

		while (result.next()) {
			listaProducto
					.add(new Producto(result.getString(1), result.getString(2), result.getDouble(3), result.getInt(4)));

		}

		closeConnection();
		return listaProducto;
	}

	public ResultSet addProducto(String codigoB, String descripccion, double precio, int existencias)
			throws SQLException {
		ResultSet result = null;
		CallableStatement call = (CallableStatement) connection.prepareCall("{CALL addProductoSp(?,?,?,?)}");
		call.setString(1, codigoB);
		call.setString(2, descripccion);
		call.setDouble(3, precio);
		call.setInt(4, existencias);
		call.execute();
		result = call.getResultSet();

		return result;

	}

	public boolean Producto(String codigoB, String descripccion, double precio, int existencias) throws SQLException {
		CallableStatement call = (CallableStatement) connection.prepareCall("{CALL modifProd(?,?,?,?)}");
		call.setString(1, codigoB);
		call.setString(2, descripccion);
		call.setDouble(3, precio);
		call.setInt(4, existencias);
		call.execute();

		return false;
	}

}
