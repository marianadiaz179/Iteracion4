package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.sql.DATE;
import uniandes.isis2304.hotelAndes.negocio.ReservaHabitacion;

class SQLReservaHabitacion {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaHotelAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaHotelAndes ha;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLReservaHabitacion (PersistenciaHotelAndes ha)
	{
		this.ha = ha;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una RESERVAHABITACION a la base de datos de HotelAndes
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la reserva
	 * @param fechaIngreso - La fecha de ingreso del cliente
	 * @param fechaSalida - La fecha de salida del cliente
	 * @param cantidadPersonas - La cantidad de personas asociados con el cliente que hace la reserva
	 * @param planPago - El plan de pago del cliente
	 * @param cliente - La cedula del cliente (FK)
	 * @param habitacion - Numero de la habitacion (FK)
	 * @param totalCompras - El total de los servicios adquiridos durante esta reserva
	 * @return El número de tuplas insertadas
	 */
	public long adicionarReservaHabitacion (PersistenceManager pm, long idReserva, Date fechaIngreso, Date fechaSalida, int duracion, int cantidadPersonas, String planPago, long cliente, long habitacion, double totalCompras) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaReservaHabitacion () + "(idReserva, fechaIngreso, fechaSalida, duracion, cantidadPersonas, planPago, cedulacliente, numhabitacion, totalCompras) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idReserva, fechaIngreso, fechaSalida, duracion, cantidadPersonas,planPago, cliente, habitacion, totalCompras);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una RESERVAHABITACION de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaHabitacionPorId (PersistenceManager pm, long idReserva)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaReservaHabitacion () + " WHERE id = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una RESERVAHABITACION de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idReserva - El identificador de la reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaHabitacionPorCliente (PersistenceManager pm, long cliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaReservaHabitacion () + " WHERE cedulaCliente = ?");
        q.setParameters(cliente);
        return (long) q.executeUnique();
	}
	
	public long eliminarReservaHabitacionPorHabitacion(PersistenceManager pm, long habitacion, long cliente)
	{
		 Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaReservaHabitacion () + " WHERE cedulaCliente = ? AND numHabitacion = ?");
	     q.setParameters(cliente, habitacion);
	     return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una RESERVAHABITACION de la 
	 * base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public ReservaHabitacion darReservaHabitacionPorId (PersistenceManager pm, long idReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaReservaHabitacion () + " WHERE id = ?");
		q.setResultClass(ReservaHabitacion.class);
		q.setParameters(idReserva);
		return (ReservaHabitacion) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de la RESERVA HABITACION de la 
	 * base de datos de HotelAndes, por su cliente
	 * @param pm - El manejador de persistencia
	 * @param cliente - El identificador asociado al cliente
	 * @return Una lista de objetos RESERVA HABITACION que tienen el numero dado
	 */
	public List<ReservaHabitacion> darReservaHabitacionPorCliente (PersistenceManager pm, long cliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaReservaHabitacion () + " WHERE cedulacliente = ?");
		q.setResultClass(ReservaHabitacion.class);
		q.setParameters(cliente);
		return (List<ReservaHabitacion>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de la RESERVA HABITACION de la 
	 * base de datos de HotelAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos RESERVA HABITACION
	 */
	public List<ReservaHabitacion> darReservasHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaReservaHabitacion ());
		q.setResultClass(ReservaHabitacion.class);
		return (List<ReservaHabitacion>) q.executeList();
	}
	
}
