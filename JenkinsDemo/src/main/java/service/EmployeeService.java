package service;

import java.util.List;

import Model.Empleado;

public interface EmployeeService {

	List<Empleado> buscarPorNombre(String nombre);
}
