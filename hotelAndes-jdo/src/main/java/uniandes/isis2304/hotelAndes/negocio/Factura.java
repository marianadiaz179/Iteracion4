package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto FACTURA del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Factura implements VOFactura
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador único de la factura 
	 */
	private long idFactura;
	
	/**
	 * La cedula del cliente, asociada a la factura
	 */
	private long habitacion;
	
	/**
	 * El total a pagar en la factura
	 */
	private double total;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Factura() 
	{
	this.idFactura = 0;
	this.habitacion = 0;
	this.total = 0;

	}

	/**
	 * Constructor con valores
	 */
	public Factura(long idFactura, long habitacion, double total)
	{
		this.idFactura = idFactura;
		this.habitacion = habitacion;
		this.total = total;

	}
	
	//idFactura - Getter y Setter

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	
	//habitacion - Getter y Setter

	public long getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(long habitacion) {
		this.habitacion = habitacion;
	}
	
	
	// total - Getter y Setter

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	// ToString - Factura

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", habitacion=" + habitacion + ", total=" + total + "]";
	}
	

	
}
