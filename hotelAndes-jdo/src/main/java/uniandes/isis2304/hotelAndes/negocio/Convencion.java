package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.time.LocalDate;


/**
 * Clase para modelar el concepto Servicio del negocio de los HotelAndes
 *
 * @author Mariana Díaz. Tomás Angel
 */
public class Convencion implements VOConvencion
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	
	/**
	 * El nombre de la convencion
	 */
	private String nombre;
	
	/**
	 * Ccedula del organizador
	 */
	
	private long organizador;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private int duracion;
	
	private long planPago;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     * 
     */
	public Convencion() 
    {
    	this.id = 0;
		this.nombre = "";
		this.organizador = 0;
		this.fechaFin = new Date(0);
		this.fechaInicio = new Date(0);
		this.duracion = 0;
		this.planPago = 0;
	}

    public Convencion(long id, String nombre, long cedula, Date fechaI, Date fechaF, int duracion, long plan)
    {
    	this.id = id;
		this.nombre = nombre;
		this.organizador = cedula;
		this.fechaInicio = fechaI;
		this.fechaFin = fechaF;
		this.duracion = duracion;
		this.planPago = plan;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreConvencion) {
		this.nombre = nombreConvencion;
	}

	public long getOrganizador() {
		return organizador;
	}

	public void setOrganizador(long organizador) {
		this.organizador = organizador;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaI) {
		System.out.println(fechaI);
		this.fechaInicio = fechaI;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaF) {
		this.fechaFin = fechaF;
	}

	public long getPlanPago() {
		return planPago;
	}

	public void setPlanPago(long planPago) {
		this.planPago = planPago;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Convencion [id=" + id + ", nombre=" + nombre + ", organizador=" + organizador + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", duracion=" + duracion + ", planPago=" + planPago + "]";
	}


	

	
    
}