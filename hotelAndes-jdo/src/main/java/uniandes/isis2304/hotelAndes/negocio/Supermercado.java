package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Tomas Angel
 */
public class Supermercado implements VOSupermercado
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * El identificador ÚNICO de los restaurantes
	 */
	private long id;
	
	/**
	 * El identificador ÚNICO del servicio
	 */
	private long idServicio;
	
	/**
	 * El nombre del bar
	 */
	private String nombre;

	/**
	 * El horario de atencion del restaurante
	 */
	private String horario;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Supermercado() 
    {
    	this.id = 0;
    	this.idServicio = 0;
		this.nombre = "";
		this.horario = "";
	}

	/**
	 * Constructor con valores
	 * @param idServicio - El id del servicio
	 * @param id - el id del restaurante
	 * @param capacidadRestauratne - La capacidad de personas que tiene el lugar
	 * @param horario - El horario de atencion del restaurante
	 * @param nombre - El nombre del bar
	 */
    public Supermercado(long id, long idServicio, String nombre, String horario) 
    {
    	this.id = 0;
    	this.idServicio = 0;
		this.nombre = "";
		this.horario = "";
	}

    /**
	 * @return El id del servicio
	 */
	public long getIdServicio() 
	{
		return idServicio;
	}
	
	/**
	 * @param id - El nuevo id del Supermercado
	 */
	public void setIdServicio(long idServicio) 
	{
		this.idServicio = idServicio;
	}
	
    /**
	 * @return El id del Supermercado
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del Supermercado
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el horario del Supermercado
	 */
	public String getHorario() 
	{
		return horario;
	}
	
	/**
	 * @param horario - El nuevo horario del Supermercado
	 */
	public void setHorario(String horario) 
	{
		this.horario = horario;
	}
	
	/**
	 * @return el nombre del Supermercado
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del Supermercado
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
		return "Supermercado [id=" + id + ", idServicio=" + idServicio + ", nombre=" + nombre
			 + ", horario=" + horario + "]";
	}
	

}


