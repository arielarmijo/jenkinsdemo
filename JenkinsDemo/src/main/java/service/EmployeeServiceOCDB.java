package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Model.Empleado;

public class EmployeeServiceOCDB implements EmployeeService {

	@Override
	public List<Empleado> buscarPorNombre(String nombre) {
		List<Empleado> empleados = new ArrayList<>();
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/PruebaDS");
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from employees where first_name = ? or last_name = ?");
			ps.setString(1, nombre);
			ps.setString(2, nombre);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				empleados.add(new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(8)));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}

}
