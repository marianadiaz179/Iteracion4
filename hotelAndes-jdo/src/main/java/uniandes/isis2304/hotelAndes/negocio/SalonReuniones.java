package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto SalonConferencias del negocio de los HotelAndes
 *
 * @author Tomas Angel
 */
public class SalonReuniones implements VOSalonReuniones
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del salon conferencias
	 */
	private long id;
	
	/**
	 * El identificador ÚNICO del servicio
	 */
	private long idServicio;
	
	/**
	 * La capacidad de personas que caben en el lugar
	 */
	private int capacidadPersonas;

	/**
	 * El nombre del salon de conferencias
	 */
	private String nombre;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public SalonReuniones() 
    {
    	this.id = 0;
		this.idServicio = 0;
		this.capacidadPersonas = 0;
		this.nombre = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del bart
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public SalonReuniones(long id, long idServicio, int capacidadPersonas, String nombre) 
    {
    	this.id = id;
    	this.idServicio = idServicio;
		this.capacidadPersonas = capacidadPersonas;
		this.nombre = nombre;
	}

    /**
	 * @return El id del salon de conferencias
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del salon de conferencias
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return la ciudad del bar
	 */
	public long getIdServicio() 
	{
		return idServicio;
	}
	
	/**
	 * @param ciudad - El nueva id del servicio
	 */
	public void setIdServicio(long idServicio) 
	{
		this.idServicio = idServicio;
	}
	
	/**
	 * @return la capacidad del salon de conferencias
	 */
	public int getCapacidadPersonas() 
	{
		return capacidadPersonas;
	}
	
	/**
	 * @param ciudad - La nueva capacidad de personas del salon de conferencias
	 */
	public void getCapacidadPersonas(int capacidadPersonas) 
	{
		this.capacidadPersonas = capacidadPersonas;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del salon de conferencias
	 */
	public String toString() 
	{
		return "SalonReuniones [id=" + id + ", idServicio=" + idServicio + ", capacidadPersonas=" + capacidadPersonas + ", nombre=" + nombre;
	}
	

}
