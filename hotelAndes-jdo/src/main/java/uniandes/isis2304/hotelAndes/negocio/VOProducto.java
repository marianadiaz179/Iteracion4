package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de PRODUCTO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 **/

public interface VOProducto {
	
	/**
	 * @return El id del producto
	 */
	public long getId();

	/**
	 * @return El nombre del producto
	 */
	public String getNombreProducto();

	/**
	 * @return El id del servicio
	 */
	public long getIdServicio();

	/**
	 * @return El costo del producto
	 */
	public float getCostoProducto();

	/**
	 * @return Una cadena con la información básica del producto
	 */
	@Override
	public String toString();


}
