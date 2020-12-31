package service;

import java.util.ArrayList;
import java.util.List;

import Model.Empleado;

public class EmployeeServiceMock implements EmployeeService {
	
	private List<Empleado> empleados;
	private static EmployeeServiceMock instance;
	
	private EmployeeServiceMock() {
		empleados =  new ArrayList<>();
		empleados.add(new Empleado(100, "Steven", "King", 24000));
		empleados.add(new Empleado(101, "Neena", "Kochhar", 17000));
		empleados.add(new Empleado(102, "Lex", "De Haan", 17000));
		empleados.add(new Empleado(103, "Alexander", "Hunold", 9000));
		empleados.add(new Empleado(104, "Bruce", "Ernst", 6000));
		empleados.add(new Empleado(105, "David", "Austin", 4800));
		empleados.add(new Empleado(106, "Valli", "Pataballa", 4800));
		empleados.add(new Empleado(107, "Diana", "Lorentz", 4200));
		empleados.add(new Empleado(108, "Nancy", "Greenberg", 12000));
		empleados.add(new Empleado(109, "Daniel", "Faviet", 9000));
		empleados.add(new Empleado(110, "John", "Chen", 8000));
	}
	
	public static EmployeeServiceMock getInstance() {
		if (instance == null) {
			instance =  new EmployeeServiceMock();
		}
		return instance;
	}

	@Override
	public List<Empleado> buscarPorNombre(String nombre) {
		List<Empleado> resultados = new ArrayList<>();
		for (Empleado empleado : empleados) {
			if (empleado.getNombre().equals(nombre) || empleado.getApellido().equals(nombre)) {
				resultados.add(empleado);
			}
		}
		return resultados;
	}

}
