package uniandes.isis2304.hotelAndes.negocio;

//import uniandes.isis2304.parranderos.negocio.VOInternet;

/**
* Clase para modelar el concepto INTERNET del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Internet implements VOInternet
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El nombre del servicio incluido en la factura
	 */
	private long idServicio;
	
	/**
	 * La capacidad del internet
	 */
	private double capacidadInternet;



	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Internet() 
	{
		this.capacidadInternet = 0;
		this.idServicio = 0;
	}

	/**
	 * Constructor con valores
	 */
	public Internet(double capacidadInternet, long idServicio)
	{
		this.capacidadInternet = capacidadInternet;
		this.idServicio = idServicio;
	}
	
	//idServicio - Getter y Setter
	
	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	// capacidadInternet - Getter y Setter

	public double getCapacidadInternet() {
		return capacidadInternet;
	}

	public void setCapacidadInternet(double capacidadInternet) {
		this.capacidadInternet = capacidadInternet;
	}
	
	// toString - Internet

	@Override
	public String toString() {
		return "Internet [idServicio=" + idServicio + ", capacidadInternet=" + capacidadInternet + "]";
	}
	
	
}
