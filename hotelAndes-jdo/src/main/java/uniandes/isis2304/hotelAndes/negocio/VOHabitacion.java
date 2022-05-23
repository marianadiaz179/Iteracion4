package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de HABITACION.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOHabitacion {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	// numHabitacion  - Getter 

		public long getNumHabitacion() ;
		
		// capacidad - Getter 

		public int getCapacidad() ;
		
		// costoNoche - Getter 

		public double getCostoNoche() ;
		
		// tipoHabitacion - Getter 

		public long getTipoHabitacion() ;
		
		// nombreHotel - Getter 

		public long getIdHotel();
		
		// estado - Getter
		
		public String getEstado();
		
		public long getCliente();
		
		// toString - Habitacion

		@Override
		public String toString() ;

}
