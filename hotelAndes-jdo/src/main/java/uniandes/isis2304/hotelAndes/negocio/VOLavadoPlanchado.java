package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de LAVADOPLANCHADO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOLavadoPlanchado {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
		// idServicio - Getter 

		public long getIdServicio() ;
		
		// horario - Getter 

		public String getHorario() ;
		
		// tipoServicio - Getter 

		public String getTipoServicio();
		
		// toString - LavadoPlanchado

		@Override
		public String toString();
}