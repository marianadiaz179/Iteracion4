package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Tomas Angel
 */
public class Spa implements VOSpa
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
	private int capacidadSpa;

	/**
	 * El horario de atencion del restaurante
	 */
	private String horario;
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
	public Spa() 
    {
    	this.idServicio = 0;
    	this.id = 0;
		this.capacidadSpa = 0;
		this.horario = "";
		this.nombre = "";
	}

	/**
	 * Constructor con valores
	 * @param idServicio - El id del servicio
	 * @param id - el id del restaurante
	 * @param capacidadRestauratne - La capacidad de personas que tiene el lugar
	 * @param horario - El horario de atencion del restaurante
	 * @param nombre - El nombre del bar
	 */
    public Spa(long idServicio, long id, int capacidadSpa, String horario, String nombre) 
    {
    	this.idServicio = idServicio;
    	this.id = id;
		this.capacidadSpa = capacidadSpa;
		this.horario = horario;
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
	 * @param id - El nuevo id del spa
	 */
	public void setIdServicio(long idServicio) 
	{
		this.idServicio = idServicio;
	}
	
    /**
	 * @return El id del spa
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del spa
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return El id del spa
	 */
	public int getCapacidadSpa() 
	{
		return capacidadSpa;
	}
	
	/**
	 * @param id - El nuevo id del spa
	 */
	public void setCapacidadSpa(int capacidadSpa) 
	{
		this.capacidadSpa = capacidadSpa;
	}
	
	/**
	 * @return el horario del spa
	 */
	public String getHorario() 
	{
		return horario;
	}
	
	/**
	 * @param horario - El nuevo horario del spa
	 */
	public void setHorario(String horario) 
	{
		this.horario = horario;
	}
	
	/**
	 * @return el nombre del spa
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del spa
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
		return "Spa [idServicio=" + idServicio + ", id=" + id + ", capacidadSpa=" + capacidadSpa + ", horario=" + horario
			 + ", nombre=" + nombre + "]";
	}
	

}


