package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import oracle.sql.DATE;
import uniandes.isis2304.hotelAndes.negocio.VOReservaHabitacion;

/**
 * Clase para modelar el concepto RESERVA HABITACION del negocio de los HotelAndes
 */
public class ReservaHabitacion implements VOReservaHabitacion {
	

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de la reserva de habitacion
	 */
	private long idReserva;
	
	/**
	 * La fecha de ingreso por parte del cliente
	 */
	private Date fechaIngreso;
	
	/**
	 * La fecha de salida por parte del cliente
	 */
	private Date fechaSalida;
	
	private int duracion;
	
	/**
	 * La cantidad de personas asociadas al cliente con la reserva de habitacion
	 */
	private int cantidadPersonas;
	
	/**
	 * El plan de pago que tiene el cliente
	 */
	private long planPago;
	
	/**
	 * La cedula del cliente asociado a la reserva de habitacion
	 */
	private long cedulaCliente;
	
	/**
	 * La habitacion relacionada con el cliente
	 */
	private long numHabitacion;
	
	/**
	 * El total de compras asociado al cliente en esa reserva de habitacion
	 */
	private double totalCompras;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 * 
	 */
	public ReservaHabitacion() 
	{
		this.idReserva = 0;
		this.fechaIngreso = new Date(0);
		this.fechaSalida = new Date(0);
		this.duracion = 0;
		this.cantidadPersonas = 0;
		this.planPago = 0;
		this.cedulaCliente = 0;
		this.numHabitacion = 0;
		this.totalCompras = 0.0;
		}

	/**
	 * Constructor con valores
	 * @param id - El id de la bebida
	 * @param nombre - El nombre de la bebida
	 * @param tipo - El identificador del tipo de bebida
	 * @param gradoAlcohol - El graddo de alcohol de la bebida (Mayor que 0)
	 */
	public ReservaHabitacion(long id, Date fechaIngreso,  Date fechaSalida, int duracion, int cantidadPersonas, long planPago, long cliente,
			long habitacion, double comprasTotal) 
	{
		this.idReserva = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.duracion = duracion;
		this.cantidadPersonas = cantidadPersonas;
		this.planPago = planPago;
		this.cedulaCliente = cliente;
		this.numHabitacion = habitacion;
		this.totalCompras = comprasTotal;
	}

	/**
	 * @return El id de la reserva de habitacion
	 */
	public long getIdReserva() 
	{
		return idReserva;
	}

	/**
	 * @param id - El nuevo id de la reserva de habitacion
	 */
	public void setIdReserva(long id) 
	{
		this.idReserva = id;
	}
	
	/**
	 * @return La fehca de ingreso de la reserva de habitacion
	 */
	public Date getFechaIngreso() 
	{
		return fechaIngreso;
	}
	
	/**
	 * @param fechaIngreso - La nueva fecha de ingreso por parte del cliente
	 */
	public void setFechaIngreso(Date fechaIngreso) 
	{
		this.fechaIngreso = fechaIngreso;
	}
	
	/**
	 * @return La fehca de salida de la reserva de habitacion
	 */
	public Date getFechaSalida() 
	{
		return fechaSalida;
	}
	
	/**
	 * @param fechaSalida - La nueva fecha de salida por parte del cliente
	 */
	public void setFechaSalida(Date fechaSalida) 
	{
		this.fechaSalida = fechaSalida;
	}
	
	/**
	 * @return La cantidad de personas que tiene la reserva
	 */
	public int getCantidadPersonas() 
	{
		return cantidadPersonas;
	}

	/**
	 * @param cantidadPersonas - La nueva cantidad de personas que tiene la reserva
	 */
	public void setCantidadPersonas(int cantidadPersonas) 
	{
		this.cantidadPersonas = cantidadPersonas;
	}
	
	/**
	 * @return El plan de pago del cliente que tiene la reserva
	 */
	public long getPlanPago() 
	{
		return planPago;
	}

	/**
	 * @param planPago - El nuevo plan de pago del cliente que tiene la reserva
	 */
	public void setPlanPago(long planPago) 
	{
		this.planPago = planPago;
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
	 * @return La habitacion asociada a la reserva
	 */
	public long getNumHabitacion() 
	{
		return numHabitacion;
	}

	/**
	 * @param habitacion La nueva habitacion asociada a la reserva
	 */
	public void setNumHabitacion(long habitacion) 
	{
		this.numHabitacion = habitacion;
	}
	
	/**
	 * @return El total de compras asociada a la reserva
	 */
	public double getTotalCompras()
	{
		return totalCompras;
	}

	/**
	 * @param comprasTotal El nuevo total de compras asociada a la reserva
	 */
	public void setTotalCompras(double comprasTotal) 
	{
		this.totalCompras = comprasTotal;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "ReservaHabitacion [idReserva=" + idReserva + ", fechaIngreso=" + fechaIngreso + ", fechaSalida="
				+ fechaSalida + ", duracion=" + duracion + ", cantidadPersonas=" + cantidadPersonas + ", planPago="
				+ planPago + ", cedulaCliente=" + cedulaCliente + ", numHabitacion=" + numHabitacion + ", totalCompras="
				+ totalCompras + "]";
	}

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	

	


}
