
package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de Restaurante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomas Angel
 */
public interface VORestaurante 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * @return El id del servicio
	 */
	public long getIdServicio();
	
     /**
	 * @return El id del bar
	 */
	public long getId();
	
	/**
	 * @return La capacidad de personas del lugar
	 */
	public int getCapacidadRestaurante();
	
	/**
	 * @return El horario del restaurante
	 */
	public String getHorario();
	
	/**
	 * @return El estilo del restaurante
	 */
	public String getEstiloRestaurante();
	
	/**
	 * @return el nombre del bar
	 */
	public String getNombre();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
