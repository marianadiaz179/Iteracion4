package uniandes.isis2304.hotelAndes.negocio;

/**
 * Clase para modelar el concepto Producto del negocio de HotelAndes
 */
public class Producto implements VOProducto{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del producto
	 */
	private long id;
	
	/**
	 * El nombre del producto
	 */
	private String nombreProducto;
	
	/**
	 * El costo que tiene el producto
	 */
	private float costoProducto;
	
	/**
	 * El identificador del servicio al que hace parte el producto. Debe existir en la tabla de Servicio
	 */
	private long idServicio;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Producto() 
	{
		this.id = 0;
		this.nombreProducto = "";
		this.costoProducto = 0;
		this.idServicio = 0;
	}

	/**
	 * Constructor con valores
	 * @param id - El id del producto
	 * @param nombre - El nombre del producto
	 * @param costoProducto - El costo que tiene el producto
	 * @param servicio - El identificador del servicio al que esta asociado el producto
	 */
	public Producto(long id, String nombre, float costoProducto, long servicio) 
	{
		this.id = id;
		this.nombreProducto = nombre;
		this.costoProducto = costoProducto;
		this.idServicio = servicio;
	}

	/**
	 * @return El id del producto
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del producto
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del producto
	 */
	public String getNombreProducto() 
	{
		return nombreProducto;
	}

	/**
	 * @param nombre - El nuevo nombre del producto
	 */
	public void setNombreProducto(String nombre) 
	{
		this.nombreProducto = nombre;
	}

	/**
	 * @return El id del servicio
	 */
	public long getIdServicio() 
	{
		return idServicio;
	}

	/**
	 * @param idServicio El nuevo identificador del servicio
	 */
	public void setIdServicio(long servicio) 
	{
		this.idServicio = servicio;
	}

	/**
	 * @return El costo del producto
	 */
	public float getCostoProducto() 
	{
		return costoProducto;
	}

	/**
	 * @param costo El nuevo costo del producto
	 */
	public void setCostoProducto(float costo) 
	{
		this.costoProducto = costo;
	}

	/**
	 * @return Una cadena con la información básica del producto
	 */
	@Override
	public String toString() 
	{
		return "Bebida [id=" + id + ", nombre=" + nombreProducto + ", costoProducto=" + costoProducto + ", idServicio=" + idServicio + "]";
	}


}
