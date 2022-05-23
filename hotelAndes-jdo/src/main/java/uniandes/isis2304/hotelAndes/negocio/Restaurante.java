package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Restaurante implements VORestaurante
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * El identificador ÚNICO del servicio
	 */
	private long idServicio;
	
	/**
	 * El identificador ÚNICO de los restaurantes
	 */
	private long id;
	
	/**
	 * La capacidad de personas del restaurante
	 */
	private int capacidadRestaurante;

	/**
	 * El horario de atencion del restaurante
	 */
	private String horario;
	
	/**
	 * Estilo del restaurante
	 */
	private String estiloRestaurante;
	
	/**
	 * El nombre del bar
	 */
	private String nombre;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Restaurante() 
    {
    	this.idServicio = 0;
    	this.id = 0;
		this.capacidadRestaurante = 0;
		this.horario = "";
		this.estiloRestaurante = "";
		this.nombre = "";
	}

	/**
	 * Constructor con valores
	 * @param idServicio - El id del servicio
	 * @param id - el id del restaurante
	 * @param capacidadRestauratne - La capacidad de personas que tiene el lugar
	 * @param horario - El horario de atencion del restaurante
	 * @param estiloRestauranate - El estilo que tiene el restaurante (tematica)
	 * @param nombre - El nombre del bar
	 */
    public Restaurante(long idServicio, long id, int capacidadRestaurante, String horario, String estiloRestaurante, String nombre) 
    {
    	this.idServicio = idServicio;
    	this.id = id;
		this.capacidadRestaurante = capacidadRestaurante;
		this.horario = horario;
		this.estiloRestaurante = estiloRestaurante;
		this.nombre = nombre;
	}

    /**
	 * @return El id del servicio
	 */
	public long getIdServicio() 
	{
		return idServicio;
	}
	
	/**
	 * @param id - El nuevo id del restaurante
	 */
	public void setIdServicio(long idServicio) 
	{
		this.idServicio = idServicio;
	}
	
    /**
	 * @return El id del restaurante
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del restaurante
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return El id del restaurante
	 */
	public int getCapacidadRestaurante() 
	{
		return capacidadRestaurante;
	}
	
	/**
	 * @param id - El nuevo id del restaurante
	 */
	public void setCapacidadRestaurante(int capacidadRestaurante) 
	{
		this.capacidadRestaurante = capacidadRestaurante;
	}
	
	/**
	 * @return el horario del restaurante
	 */
	public String getHorario() 
	{
		return horario;
	}
	
	/**
	 * @param horario - El nuevo horario del restaurante
	 */
	public void setHorario(String horario) 
	{
		this.horario = horario;
	}
	
	/**
	 * @return El presupuesto del bar
	 */
	public String getEstiloRestaurante() 
	{
		return estiloRestaurante;
	}
	
	/**
	 * @param presupuesto - El nuevo presupuesto del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setEstiloRestaurante(String estiloRestaurante) 
	{
		this.estiloRestaurante = estiloRestaurante;
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
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Restaurante [idServicio=" + idServicio + ", id=" + id + ", capacidadRestaurante=" + capacidadRestaurante + ", horario=" + horario
				+ ", estiloRestaurante=" + estiloRestaurante + ", nombre=" + nombre + "]";
	}
	

}


