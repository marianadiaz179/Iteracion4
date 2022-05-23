
package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de Restaurante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomas Angel
 */
public interface VOTienda
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
	/**
	 * @return El id del servicio
	 */
	public long getIdServicio();
	
     /**
	 * @return El id del Tienda
	 */
	public long getId();
	
	/**
	 * @return La capacidad de personas del lugar
	 */
	public String getTipoTienda();
	
	/**
	 * @return El horario del Tienda
	 */
	public String getHorario();
	
	/**
	 * @return el nombre del Tienda
	 */
	public String getNombre();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
