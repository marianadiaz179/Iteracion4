package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de EMPLEADOHOTEL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOUsuario {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
		//cedulaEmpleado - Getter 

		public long getCedula();
		
		// nombreEmpleado - Getter 

		public String getNombre() ;
		
		// nombreHotel - Getter }

		public long getIdHotel() ;
		
		// tipoUsuario - Getter 

		public long getTipoUsuario() ;
		
		// correo - Getter 

		public String getCorreo() ;
		
		//gastosHotel - Getter
		public double getGastosHotel();
		
		//estadia - Getter
		
		public int getEstadia();
		
		//ToString - EmpleadoHotel
		
		@Override
		public String toString() ;
		
		
	

}
