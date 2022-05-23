
package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Germán Bravo
 */
public interface VOSalonConferencias
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del salon de conferencias
	 */
	public long getId();
	
	 /**
	  * @return El id del servicio
	 */
	public long getIdServicio();
	
	/**
	  * @return la capacidad de personas
	 */
	public int getCapacidadPersonas();
	
	/**
	 * @return el nombre del salon de conferencias
	 */
	public String getNombre();
	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
