package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.ReservaServicio;

class SQLReservaServicio {
	
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
	public SQLReservaServicio (PersistenciaHotelAndes ha)
	{
		this.ha = ha;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una RESERVAHABITACION a la base de datos de HotelAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la reserva de servicio
	 * @param tipoServicio - El tipo de servicio que se toma
	 * @param diaReserva - El dia agendado
	 * @param horaReserva - La hora agendada
	 * @param duracion - La duracion que tiene el servicio
	 * @param cliente - La cedula asociada al cliente que hizo la reserva de servicio
	 * @param servicio - El servicio que se toma
	 * @return El número de tuplas insertadas
	 */
	public long adicionarReservaServicio (PersistenceManager pm, long id, String tipoServicio, Date diaReserva, String horaReserva, int duracion, long cliente, long servicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaReservaServicio () + "(id, tipoServicio, diaReserva, horaReserva, duracion, cedulaCliente, servicio) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipoServicio, diaReserva, horaReserva, duracion, cliente, servicio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una RESERVASERVICIO de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaServicioPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaReservaServicio () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una RESERVASERVICIO de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaServicioPorCliente (PersistenceManager pm, long cliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaReservaServicio () + " WHERE id = ?");
        q.setParameters(cliente);
        return (long) q.executeUnique();
	}
	
	public long eliminarReservaServicioPorTipoServicio(PersistenceManager pm, long cliente, String tipo)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaReservaServicio () + " WHERE cedulaCliente = ? AND tipoServicio = ?");
        q.setParameters(cliente, tipo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de una RESERVA SERVICIO de la 
	 * base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la reserva del servicio
	 * @return El objeto RESERVA SERVICIO que tiene el identificador dado
	 */
	public ReservaServicio darReservaServicioPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaReservaServicio () + " WHERE id = ?");
		q.setResultClass(ReservaServicio.class);
		q.setParameters(id);
		return (ReservaServicio) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de la RESERVA SERVICIO de la 
	 * base de datos de HotelAndes, por su cliente
	 * @param pm - El manejador de persistencia
	 * @param cliente - El identificador asociado al cliente
	 * @return Una lista de objetos RESERVA SERVICIO que tienen el numero dado
	 */
	public List<ReservaServicio> darReservaServicioPorCliente (PersistenceManager pm, long cliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaReservaServicio () + " WHERE nombre = ?");
		q.setResultClass(ReservaServicio.class);
		q.setParameters(cliente);
		return (List<ReservaServicio>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de la RESERVA HABITACION de la 
	 * base de datos de HotelAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos RESERVA HABITACION
	 */
	public List<ReservaServicio> darReservasServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaReservaServicio ());
		q.setResultClass(ReservaServicio.class);
		return (List<ReservaServicio>) q.executeList();
	}
	
}
