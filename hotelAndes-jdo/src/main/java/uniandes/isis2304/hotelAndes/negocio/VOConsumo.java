package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;

/**
 * Interfaz para los métodos get de CONSUMO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */


public interface VOConsumo 
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return id
	 */
	public long getId() ;
	

	/**
	 * @return capacidadBar
	 */
	public long getHabitacion();
	
	/**
	 * @return idServicio
	 */
	public long getIdServicio() ;
	
	/**
	 * @return producto
	 */
	public long getProducto();
	
	/**
	 * @return estado
	 */
	
	public String getEstado();
	
	/*
	 * cliente
	 */
	
	public long getCliente();
	
	public Date getFechaConsumo();
	
	//ToString
	
	
	@Override
	public String toString();

}
