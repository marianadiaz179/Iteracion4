package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de BAR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOBar {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return id
	 */
	public long getId() ;
	
	/**
	 * @return nombreBar
	 */
	public String getNombreBar();

	/**
	 * @return capacidadBar
	 */
	public int getCapacidadBar();
	
	/**
	 * @return idServicio
	 */
	public long getIdServicio() ;
	
	/**
	 * @return horario
	 */
	public String getHorario() ;
	
	/**
	 * @return estiloBar
	 */
	public String getEstiloBar() ;
	
	//ToString
	
	@Override
	public String toString();
   
}
