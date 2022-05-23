package uniandes.isis2304.hotelAndes.negocio;
/**
* Clase para modelar el concepto HOTEL del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Hotel implements VOHotel
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador único del hotel
	 */
	private long idHotel;
	
	/**
	 * El nombre del hotel
	 */
	private String nombreHotel;

	/**
	 * La cantidad de estrellas dadas al hotel
	 */
	private int numeroEstrellas;
	
	/**
	 * El pais en el que está el hotel
	 */
	private String paisHotel;
	
	/**
	 * La ciudad en la que está el hotel
	 */
	private String ciudadHotel;
	
	/**
	 * La cadena hotelera a la que pertenece el hotel
	 */
	private String cadenaHotelera;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
/**
 * Constructor por defecto
 */
	public Hotel() 
	{
		this.idHotel = 0;
		this.nombreHotel = "";
		this.numeroEstrellas = 0;
		this.paisHotel = "";
		this.ciudadHotel = "";
		this.cadenaHotelera = "";
	}

	/**
	 * Constructor con valores
	 */
	public Hotel(long idHotel, String nombreHotel, int numeroEstrellas,
			String paisHotel, String ciudadHotel, String cadenaHotelera) 
	{
		this.idHotel = idHotel;
		this.nombreHotel = nombreHotel;
		this.numeroEstrellas = numeroEstrellas;
		this.paisHotel = paisHotel;
		this.ciudadHotel = ciudadHotel;
		this.cadenaHotelera = cadenaHotelera;
	}
	
	// idHotel - Getter y Setter

	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}
	
	// nombreHotel - Getter y Setter

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
	
	// numeroEstrellas - Getter y Setter

	public int getNumeroEstrellas() {
		return numeroEstrellas;
	}

	public void setNumeroEstrellas(int numeroEstrellas) {
		this.numeroEstrellas = numeroEstrellas;
	}
	
	// paisHotel - Getter y Setter

	public String getPaisHotel() {
		return paisHotel;
	}

	public void setPaisHotel(String paisHotel) {
		this.paisHotel = paisHotel;
	}
	
	// ciudadHotel - Getter y Setter

	public String getCiudadHotel() {
		return ciudadHotel;
	}

	public void setCiudadHotel(String ciudadHotel) {
		this.ciudadHotel = ciudadHotel;
	}
	
	// cadenaHotelera - Getter y Setter

	public String getCadenaHotelera() {
		return cadenaHotelera;
	}

	public void setCadenaHotelera(String cadenaHotelera) {
		this.cadenaHotelera = cadenaHotelera;
	}
	
	// toString - Hotel

	@Override
	public String toString() {
		return "Hotel [idHotel=" + idHotel + ", nombreHotel=" + nombreHotel + ", numeroEstrellas=" + numeroEstrellas
				+ ", paisHotel=" + paisHotel + ", ciudadHotel=" + ciudadHotel + ", cadenaHotelera=" + cadenaHotelera
				+ "]";
	}
	
	
}
