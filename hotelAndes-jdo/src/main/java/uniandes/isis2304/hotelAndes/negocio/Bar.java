package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de HotelAndes
 *
 * @author Mariana Diaz - Tomás Angel
 */
public class Bar implements VOBar
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	
	/**
	 * El nombre del bar
	 */
	private String nombreBar;

	/**
	 * La capacidad del bar
	 */
	private int capacidadBar;
	
	/**
	 * El id del servicio al que pertenece
	 */
	private long idServicio;
	
	/**
	 * El horario del bar (DIURNO, NOCTURNO, TODO EL DIA)
	 */
	private String horario;
	
	/**
	 * El estilo del bar
	 */
	private String estiloBar;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Bar() 
    {
    	this.id = 0;
		this.nombreBar = "";
		this.idServicio = 0;
		this.capacidadBar = 0;
		this.horario = "";
		this.estiloBar = "";
	}

	/**
	 * Constructor con valores
	 */
    public Bar(long id, String nombreBar, long idServicio, int capacidadBar, String horario, String estiloBar) 
    {
    	this.id = id;
		this.nombreBar = nombreBar;
		this.idServicio = idServicio;
		this.capacidadBar = capacidadBar;
		this.horario = horario;
		this.estiloBar = estiloBar;
	}
    
    //id - Getter y Setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	// nombreBar - Getter y Setter

	public String getNombreBar() {
		return nombreBar;
	}

	public void setNombreBar(String nombreBar) {
		this.nombreBar = nombreBar;
	}
	
	// capacidadBar - Getter y Setter

	public int getCapacidadBar() {
		return capacidadBar;
	}

	public void setCapacidadBar(int capacidadBar) {
		this.capacidadBar = capacidadBar;
	}
	
	// idServicio - Getter y Setter

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
	// horario - Getter y Setter

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	//estiloBar - Getter y Setter

	public String getEstiloBar() {
		return estiloBar;
	}

	public void setEstiloBar(String estiloBar) {
		this.estiloBar = estiloBar;
	}
	
	//ToString
	
	@Override
	public String toString() {
		return "Bar [id=" + id + ", nombreBar=" + nombreBar + ", capacidadBar=" + capacidadBar + ", idServicio="
				+ idServicio + ", horario=" + horario + ", estiloBar=" + estiloBar + "]";
	}
	
    
}