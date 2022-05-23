
package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de Restaurante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomas Angel
 */
public interface VOSpa
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * @return El id del servicio
	 */
	public long getIdServicio();
	
     /**
	 * @return El id del Spa
	 */
	public long getId();
	
	/**
	 * @return La capacidad de personas del lugar
	 */
	public int getCapacidadSpa();
	
	/**
	 * @return El horario del Spa
	 */
	public String getHorario();
	
	
	/**
	 * @return el nombre del Spa
	 */
	public String getNombre();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Spa
	 */
	public String toString();

}
