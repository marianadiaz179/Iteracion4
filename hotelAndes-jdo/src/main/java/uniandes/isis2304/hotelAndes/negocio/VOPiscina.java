package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de PISCINA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOPiscina {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
		
		// idServicio - Getter 

		public long getIdServicio();
		
		// id - Getter 

		public long getId() ;
		
		//capacidadPiscina - Getter 

		public int getCapacidadPiscina() ;
		
		//nombrePiscina - Getter 

		public String getNombrePiscina() ;
		
		// profundidad - Getter 

		public int getProfundidad() ;
		
		// horario - Getter 

		public String getHorario();
		
		// toString - Piscina

		@Override
		public String toString() ;
	
}