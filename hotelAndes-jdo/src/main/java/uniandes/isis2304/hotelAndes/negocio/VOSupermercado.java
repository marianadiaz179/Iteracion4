
package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de Restaurante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomas Angel
 */
public interface VOSupermercado
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * @return El id del servicio
	 */
	public long getIdServicio();
	
     /**
	 * @return El id del Supermercado
	 */
	public long getId();
	
	/**
	 * @return El horario del Supermercado
	 */
	public String getHorario();
	
	
	/**
	 * @return el nombre del Supermercado
	 */
	public String getNombre();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Supermercado
	 */
	public String toString();

}
