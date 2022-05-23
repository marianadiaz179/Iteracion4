package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de FACTURA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOFactura {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	//idFactura - Getter 

	public long getIdFactura() ;
	
	//habitacion - Getter 

	public long getHabitacion() ;
	
	// total - Getter 

	public double getTotal() ;
	
	
	// ToString - Factura

	@Override
	public String toString();
}
