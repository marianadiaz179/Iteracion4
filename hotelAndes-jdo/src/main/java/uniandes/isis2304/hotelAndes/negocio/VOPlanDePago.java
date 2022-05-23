package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de PLANDEPAGO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOPlanDePago {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	public long getId();
	// tipoPlan - Getter 

		public String getTipoPlan();
		
		// caracteristicas - Getter 

		public String getCaracteristicas() ;
		
		// toString - PlanDePago

		@Override
		public String toString();
}
