package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Tienda implements VOTienda
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	/**
	 * El identificador ÚNICO del servicio
	 */
	private long idServicio;
	
	/**
	 * El identificador ÚNICO de los Tienda
	 */
	private long id;
	
	/**
	 * El tipo de la tienda Tienda
	 */
	private String tipoTienda;

	/**
	 * El horario de atencion del Tienda
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
	public Tienda() 
    {
    	this.idServicio = 0;
    	this.id = 0;
		this.tipoTienda = "";
		this.horario = "";
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
    public Tienda(long idServicio, long id, String tipoTienda, String horario, String nombre) 
    {
    	this.idServicio = idServicio;
    	this.id = id;
		this.tipoTienda = tipoTienda;
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
	 * @param id - El nuevo id del Tienda
	 */
	public void setIdServicio(long idServicio) 
	{
		this.idServicio = idServicio;
	}
	
    /**
	 * @return El id del Tienda
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del Tienda
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return El id del Tienda
	 */
	public String getTipoTienda() 
	{
		return tipoTienda;
	}
	
	/**
	 * @param id - El nuevo id del Tienda
	 */
	public void setTipoTienda(String tipoTienda) 
	{
		this.tipoTienda = tipoTienda;
	}
	
	/**
	 * @return el horario del Tienda
	 */
	public String getHorario() 
	{
		return horario;
	}
	
	/**
	 * @param horario - El nuevo horario del Tienda
	 */
	public void setHorario(String horario) 
	{
		this.horario = horario;
	}
	
	/**
	 * @return el nombre del Tienda
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del Tienda
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
		return "Tienda [idServicio=" + idServicio + ", id=" + id + ", tipoTienda=" + tipoTienda + ", horario=" + horario
				+ ", nombre=" + nombre + "]";
	}
	

}


