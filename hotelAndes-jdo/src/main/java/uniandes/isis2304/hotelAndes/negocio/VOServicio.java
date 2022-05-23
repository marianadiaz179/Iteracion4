package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de Restaurante.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomas Angel
 */
public interface VOServicio
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

     /**
	 * @return El id del servicio
	 */
	public long getId();
	
	/**
	 * @return el nombre del servicio
	 */
	public String getNombreServicio();
	
	/**
	 * @return demandan del servicio
	 */
	
	public int getDemanda();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}
