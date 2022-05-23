package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de INTERNET.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOInternet {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

		//nombreServicio - Getter 

		public long getIdServicio() ;
		
		// capacidadInternet - Getter 

		public double getCapacidadInternet();
		// toString - Internet

		@Override
		public String toString();
}