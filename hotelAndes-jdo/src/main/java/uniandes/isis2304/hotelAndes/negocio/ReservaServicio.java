package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import uniandes.isis2304.hotelAndes.negocio.VOReservaServicio;

/**
 * Clase para modelar el concepto RESERVA Servicio del negocio de los HotelAndes
 */
public class ReservaServicio implements VOReservaServicio {
	

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de la reserva de habitacion
	 */
	private long id;
	
	/**
	 * La fecha de ingreso por parte del cliente
	 */
	private String tipoServicio;
	
	/**
	 * La fecha de salida por parte del cliente
	 */
	private Date diaReserva;
	
	/**
	 * La cantidad de personas asociadas al cliente con la reserva de habitacion
	 */
	private String horaReserva;
	
	/**
	 * El plan de pago que tiene el cliente
	 */
	private int duracion;
	
	/**
	 * El plan de pago que tiene el cliente
	 */
	private long cedulaCliente;
	
	/**
	 * La cedula del cliente asociado a la reserva de habitacion
	 */
	private long servicio;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ReservaServicio() 
	{
		this.id = 0;
		this.tipoServicio = "";
		this.diaReserva = new Date(0);
		this.horaReserva = "";
		this.duracion = 0;
		this.cedulaCliente = 0;
		this.servicio = 0;
		}

	/**
	 * Constructor con valores
	 * @param id - El id de la bebida
	 * @param nombre - El nombre de la bebida
	 * @param tipo - El identificador del tipo de bebida
	 * @param gradoAlcohol - El graddo de alcohol de la bebida (Mayor que 0)
	 */
	public ReservaServicio(long id, String tipoServicio, Date diaReserva, String horaReserva, int duracion, long cliente, long servicio) 
	{
		this.id = 0;
		this.tipoServicio = tipoServicio;
		this.diaReserva = diaReserva;
		this.horaReserva = horaReserva;
		this.duracion = duracion;
		this.cedulaCliente = cliente;
		this.servicio = servicio;
	}

	/**
	 * @return El id de la reserva de habitacion
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id de la reserva de habitacion
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return El tipo de servicio de la reserva
	 */
	public String getTipoServicio() 
	{
		return tipoServicio;
	}
	
	/**
	 * @param fechaIngreso - La nueva fecha de ingreso por parte del cliente
	 */
	public void setTipoServicio(String tipoServicio) 
	{
		this.tipoServicio = tipoServicio;
	}
	
	/**
	 * @return La hora de la reserva 
	 */
	public Date getDiaReserva() 
	{
		return diaReserva;
	}
	
	/**
	 * @param diaReserva - El nueva dia de la reserva
	 */
	public void setDiaReserva(Date diaReserva) 
	{
		this.diaReserva = diaReserva;
	}
	
	public String getHoraReserva() 
	{
		return horaReserva;
	}
	
	/**
	 * @param horaReserva - La nueva hora de la reserva
	 */
	public void setHoraReserva(String horaReserva) 
	{
		this.horaReserva = horaReserva;
	}
	
	/**
	 * @return La duracion de la reserva
	 */
	public int getDuracion() 
	{
		return duracion;
	}
	
	/**
	 * @param duracion - La nueva duracion de la reserva
	 */
	public void setDuracion(int duracion) 
	{
		this.duracion = duracion;
	}
	
	/**
	 * @return La cedula del cliente que tiene la reserva
	 */
	public long getCedulaCliente() 
	{
		return cedulaCliente;
	}

	/**
	 * @param cliente El nuevo cliente que tiene la reserva
	 */
	public void setCedulaCliente(long cliente) 
	{
		this.cedulaCliente = cliente;
	}

	/**
	 * @return El servicio asociado a la reserva
	 */
	public long getServicio() 
	{
		return servicio;
	}

	/**
	 * @param habitacion La nueva habitacion asociada a la reserva
	 */
	public void setServicio(long servicio) 
	{
		this.servicio = servicio;
	}
	

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString() 
	{
		return "ReservaServicio [id=" + id + ", tipoServicio=" + tipoServicio + ", diaReserva=" + diaReserva + ", horaReserva=" + horaReserva + ", duracion=" + duracion + ", cliente=" + cedulaCliente + ", servicio=" + servicio + "]";
	}



}
