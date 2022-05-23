package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto GIMNASIO del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Gimnasio implements VOGimnasio
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del servicio al que pertenece
	 */
	private long idServicio;
	
	/**
	 * El identificador del gimnasio
	 */
	private long id;

	/**
	 * La capacidad del gimnasio
	 */
	private int capacidadGimnasio;
	
	/**
	 * El nombre del gimnasio
	 */
	private String nombreGimnasio;
	
	/**
	 * El horario del gimnasio (DIURNO,NOCTURNO,TODO EL DIA)
	 */
	private String horario;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
/**
 * Constructor por defecto
 */
	public Gimnasio() 
	{
		this.idServicio = 0;
		this.id = 0;
		this.capacidadGimnasio = 0;
		this.nombreGimnasio = "";
		this.horario = "";
	}

	/**
	 * Constructor con valores
	 */
	public Gimnasio(long idServicio, long id, int capacidadGimnasio,
			String nombreGimnasio, String horario) 
	{
		this.idServicio = idServicio;
		this.id = id;
		this.capacidadGimnasio = capacidadGimnasio;
		this.nombreGimnasio = nombreGimnasio;
		this.horario = horario;
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

	// capacidadGimnasio - Getter y Setter
	
	public int getCapacidadGimnasio() {
		return capacidadGimnasio;
	}

	public void setCapacidadGimnasio(int capacidadGimnasio) {
		this.capacidadGimnasio = capacidadGimnasio;
	}
	
	//nombreGimnasio - Getter y Setter

	public String getNombreGimnasio() {
		return nombreGimnasio;
	}

	public void setNombreGimnasio(String nombreGimnasio) {
		this.nombreGimnasio = nombreGimnasio;
	}
	
	// horario - Getter y Setter

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	// toString - Gimnasio

	@Override
	public String toString() {
		return "Gimnasio [idServicio=" + idServicio + ", id=" + id + ", capacidadGimnasio=" + capacidadGimnasio
				+ ", nombreGimnasio=" + nombreGimnasio + ", horario=" + horario + "]";
	}
	
	
	
}

