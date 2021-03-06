package com.osda.tienda.principal;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.util.ResourceBundle;

import com.osda.tienda.Reporte.Reporte;
import com.osda.tienda.login.LoginModel;
import com.osda.tienda.principal.addProducto.AddProdModel;
import com.osda.tienda.principal.addProveedor.addProveedorModel;
import com.osda.tienda.principal.client.newclient.NewClientModel;
import com.osda.tienda.principal.sell.SellModel;
import com.osda.tienda.principal.user.add.AddUserModel;

public class PrincipalController implements Initializable {
	@FXML
	private BorderPane bpRoot;
	@FXML
	private JFXButton btnNewCient;
	@FXML
	private JFXButton btnCloseSesion;
	@FXML
	private JFXButton btnReporte;
	@FXML
	private Label lblIam;
	@FXML
	private JFXButton btnAddProduct;
	@FXML
	private JFXButton btnAddProvedor;
	@FXML
	private JFXButton btnAddUser;
	@FXML
	private TabPane tabPaneTienda;

	private PrincipalModel principalModel = new PrincipalModel();

	@FXML
	void btnAddUserOnActioin(ActionEvent event) {
		new AddUserModel().showWindow(tabPaneTienda);
	}

	@FXML
	void btnReporteOnAction(ActionEvent event) {
		Reporte reporte = new Reporte("Reporte");
		try {
			reporte.generarReporte();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void btnAddProvedorOnAction(ActionEvent event) {
		new addProveedorModel().showWindow(tabPaneTienda);
	}

	@FXML
	void btnNewClientOnAction(ActionEvent e) {
		new NewClientModel().showWindow(tabPaneTienda);
		//new ClientModel().showWindow(tabPaneTienda);
	}

	@FXML
	void btnAddProductOnAction(ActionEvent e) {
		new AddProdModel().showWindow(tabPaneTienda);
	}

	@FXML
	void btnCloseSesion(ActionEvent event) {
		try {
			principalModel.closeSesion(bpRoot);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new LoginModel();
		lblIam.setText(LoginModel.nombre);
		new SellModel().showWindow(tabPaneTienda);
	}
}
