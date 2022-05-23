package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import oracle.sql.DATE;

/**
 * Interfaz para los métodos get de BEBIDA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */

public interface VOReservaHabitacion {

	/**
	 * @return El id de la reserva de habitacion
	 */
	public long getIdReserva();

	/**
	 * @return La fecha de ingreso del cliente
	 */
	public Date getFechaIngreso();

	/**
	 * @return La fecha de salida del cliente
	 */
	public Date getFechaSalida();
	
	public int getDuracion();

	/**
	 * @return La cantidad de personas asociadas a la reserva
	 */
	public int getCantidadPersonas();
	
	/**
	 * @return El plan de pago del cliente que tiene la reserva
	 */
	public long getPlanPago();
	
	/**
	 * @return El cliente que tiene la reserva
	 */
	public long getCedulaCliente();
	
	/**
	 * @return La habitacion asociada a la reserva
	 */
	public long getNumHabitacion();
	
	/**
	 * @return El total de compras asociada a la reserva
	 */
	public double getTotalCompras();

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString();


}
