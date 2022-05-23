package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto HABITACION del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Habitacion implements VOHabitacion
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El número ÚNICO de una habitación
	 */
	private long numHabitacion;
	
	/**
	 * La capacidad que tiene una habitación
	 */
	private int capacidad;

	/**
	 * El costo por noche de dicha habitación
	 */
	private double costoNoche;
	
	/**
	 * El tipo de habitacion ('SuitePresidencial', 'Suite','Familiar','DobleConJacuzzi','DobleSinJacuzzi','Sencilla')
	 */
	private long tipoHabitacion;
	
	/**
	 * El hotel al que pertenece la habitación
	 */
	private long idHotel;
	
	/**
	 * Estado de la habitacion
	 */
	
	private String estado;
	/**
	 * Cliente asociado
	 */
	
	private long cliente;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
/**
 * Constructor por defecto
 */
	public Habitacion() 
	{
		this.numHabitacion = 0;
		this.capacidad = 0;
		this.costoNoche = 0;
		this.tipoHabitacion = 0;
		this.idHotel = 0;
		this.estado = "";
		this.cliente = 0;
	}

	/**
	 * Constructor con valores
	 */
	public Habitacion(long numHabitacion, int capacidad, double costoNoche,
			long tipoHabitacion, long idHotel, String estado, long cedula)
	{
		this.numHabitacion = numHabitacion;
		this.capacidad = capacidad;
		this.costoNoche = costoNoche;
		this.tipoHabitacion = tipoHabitacion;
		this.idHotel = idHotel;
		this.estado = estado;
		this.cliente = cedula;
	}

	public long getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(long numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public double getCostoNoche() {
		return costoNoche;
	}

	public void setCostoNoche(double costoNoche) {
		this.costoNoche = costoNoche;
	}

	public long getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(long tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getCliente() {
		return cliente;
	}

	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Habitacion [numHabitacion=" + numHabitacion + ", capacidad=" + capacidad + ", costoNoche=" + costoNoche
				+ ", tipoHabitacion=" + tipoHabitacion + ", idHotel=" + idHotel + ", estado=" + estado + ", cliente="
				+ cliente + "]";
	}

	
}