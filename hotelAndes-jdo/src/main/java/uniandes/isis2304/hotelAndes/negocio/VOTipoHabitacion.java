package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de HABITACION.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOTipoHabitacion {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	// id  - Getter 

		public long getIdTipoHabitacion() ;
		
		// tipo - Getter 

		public String getTipoHabitacion() ;
		
		
		// toString - TipoHabitacion

		@Override
		public String toString() ;

}
