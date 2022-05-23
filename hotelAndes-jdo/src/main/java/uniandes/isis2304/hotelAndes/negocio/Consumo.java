package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;

/**
 * Clase para modelar el concepto CONSUMO del negocio de HotelAndes
 *
 * @author Mariana Diaz - Tomás Angel
 */

public class Consumo implements VOConsumo 
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	
	/**
	 * El identificador del tipo de servicio
	 */
	private long idServicio;

	/**
	 * Identificador de la habitacion que hace el consumo
	 */
	private long habitacion;
	/**
	 * Producto de consuo
	 */
	
	private long producto;
	
	/**
	 * Estado del consumo
	 */
	
	private String estado;
	
	private long cliente;
	
	private Date fechaConsumo;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Consumo() 
    {
    	this.id = 0;
		this.idServicio = 0;
		this.habitacion = 0;
		this.producto = 0;
		this.estado = "";
		this.cliente = 0;
		this.fechaConsumo = new Date(0);
	}

	/**
	 * Constructor con valores
	 */
    public Consumo(long id, long idTipo, long cedula, long producto, String estado, long cliente,Date fecha) 
    {
    	this.id = id;
		this.idServicio = idTipo;
		this.habitacion = cedula;
		this.producto = producto;
		this.estado = estado;
		this.fechaConsumo = fecha;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	public long getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(long cedula) {
		this.habitacion = cedula;
	}

	public long getProducto() {
		return producto;
	}

	public void setProducto(long producto) {
		this.producto = producto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getCliente() {
		return cliente;
	}

	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

	public Date getFechaConsumo() {
		return fechaConsumo;
	}

	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}

	@Override
	public String toString() {
		return "Consumo [id=" + id + ", idServicio=" + idServicio + ", habitacion=" + habitacion + ", producto="
				+ producto + ", estado=" + estado + ", cliente=" + cliente + ", fechaConsumo=" + fechaConsumo + "]";
	}

	
	

	

	
}
