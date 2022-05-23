package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto Servicio del negocio de los HotelAndes
 *
 * @author Mariana Díaz. Tomás Angel
 */
public class Servicio implements VOServicio
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
	private String nombreServicio;
	
	/**
	 * Cantidad de veces que se ha solicitado un servicio
	 */
	
	private int demanda;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Servicio() 
    {
    	this.id = 0;
		this.nombreServicio = "";
		this.demanda = 0;
	}

	
    public Servicio(long id, String nombre, int demanda)
    {
    	this.id = id;
		this.nombreServicio = nombre;
		this.demanda = demanda;
	}

    /**
	 * @return El id del servicio
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del servicio
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre del servicio
	 */
	public String getNombreServicio() 
	{
		return nombreServicio;
	}
	
	/**
	 * @param nombre El nuevo nombre del servicio
	 */
	public void setNombreServicio(String nombre) 
	{
		this.nombreServicio = nombre;
	}

	public int getDemanda() {
		return demanda;
	}

	public void setDemanda(int demanda) {
		this.demanda = demanda;
	}

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombreServicio=" + nombreServicio + ", demanda=" + demanda + "]";
	}
	
	
	

}
