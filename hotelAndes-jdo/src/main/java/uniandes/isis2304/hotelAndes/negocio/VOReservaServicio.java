package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Interfaz para los métodos get de BEBIDA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 */

public interface VOReservaServicio {

	/**
	 * @return El id de la reserva de habitacion
	 */
	public long getId();

	/**
	 * @return La fecha de ingreso del cliente
	 */
	public String getTipoServicio();

	/**
	 * @return La fecha de salida del cliente
	 */
	public Date getDiaReserva();

	/**
	 * @return La cantidad de personas asociadas a la reserva
	 */
	public String getHoraReserva();
	
	/**
	 * @return El plan de pago del cliente que tiene la reserva
	 */
	public int getDuracion();
	
	/**
	 * @return El cliente que tiene la reserva
	 */
	public long getCedulaCliente();
	
	/**
	 * @return La habitacion asociada a la reserva
	 */
	public long getServicio();

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString();


}

