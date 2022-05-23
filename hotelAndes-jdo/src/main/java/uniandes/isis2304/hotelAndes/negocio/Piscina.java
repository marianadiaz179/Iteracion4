package uniandes.isis2304.hotelAndes.negocio;

//import uniandes.isis2304.parranderos.negocio.VOPiscina;

/**
* Clase para modelar el concepto PISCINA del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Piscina implements VOPiscina
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del servicio al que pertenece
	 */
	private long idServicio;
	
	/**
	 * El identificador de la piscina
	 */
	private long id;

	/**
	 * La capacidad de la piscina
	 */
	private int capacidadPiscina;
	
	/**
	 * El nombre de la piscina
	 */
	private String nombrePiscina;
	
	/**
	 * La profundidad de la piscina
	 */
	private int profundidad;
	
	/**
	 * El horario de la piscina (DIURNO,NOCTURNO,TODO EL DIA)
	 */
	private String horario;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
/**
* Constructor por defecto
*/
	public Piscina() 
	{
		this.capacidadPiscina = 0;
		this.horario = "";
		this.id = 0;
		this.idServicio = 0;
		this.nombrePiscina = "";
		this.profundidad = 0;
	}

	/**
	 * Constructor con valores
	 */
	public Piscina(long idServicio, long id, int capacidadPiscina,
			String nombrePiscina, String horario, int profundidad) 
	{
		this.capacidadPiscina = capacidadPiscina;
		this.horario = horario;
		this.id = id;
		this.idServicio = idServicio;
		this.nombrePiscina = nombrePiscina;
		this.profundidad = profundidad;
	}
	
	// idServicio - Getter y Setter

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
	// id - Getter y Setter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	//capacidadPiscina - Getter y Setter

	public int getCapacidadPiscina() {
		return capacidadPiscina;
	}

	public void setCapacidadPiscina(int capacidadPiscina) {
		this.capacidadPiscina = capacidadPiscina;
	}
	
	//nombrePiscina - Getter y Setter

	public String getNombrePiscina() {
		return nombrePiscina;
	}

	public void setNombrePiscina(String nombrePiscina) {
		this.nombrePiscina = nombrePiscina;
	}
	
	// profundidad - Getter y Setter

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	
	// horario - Getter y Setter

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	// toString - Piscina

	@Override
	public String toString() {
		return "Piscina [idServicio=" + idServicio + ", id=" + id + ", capacidadPiscina=" + capacidadPiscina
				+ ", nombrePiscina=" + nombrePiscina + ", profundidad=" + profundidad + ", horario=" + horario + "]";
	}
	
	
}
	