package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.time.LocalDate;



/**
 * Interfaz para los métodos get de Convencion.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomas Angel y Mariana Díaz
 */

public interface VOConvencion {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	
	public long getId() ;

	public String getNombre();

	public long getOrganizador() ;
	
	public Date getFechaInicio();
	
	public Date getFechaFin();
	
	public int getDuracion();
	
	public long getPlanPago();
	
	public String toString();

}
