package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de GIMNASIO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOGimnasio {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	// idServicio - Getter 

		public long getIdServicio() ;
		
		// id - Getter

		public long getId();

		// capacidadGimnasio - Getter 
		
		public int getCapacidadGimnasio() ;
		
		//nombreGimnasio - Getter

		public String getNombreGimnasio() ;
		
		// horario - Getter 

		public String getHorario() ;
		
		// toString - Gimnasio

		@Override
		public String toString();
}
