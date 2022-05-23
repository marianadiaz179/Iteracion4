package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Habitacion;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLHabitacion 
{
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
	private PersistenciaHotelAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLHabitacion (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarHabitacion (PersistenceManager pm, long numHabitacion, int capacidad, double costoNoche, long tipoHabitacion, long idHotel,String estado, long cedula) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion () + "(numHabitacion, capacidad, costoNoche, tipoHabitacion, idHotel, estado, cliente) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(numHabitacion, capacidad, costoNoche, tipoHabitacion, idHotel, estado, cedula );
        return (long) q.executeUnique();
	}

	public long eliminarHabitacionPorTipo (PersistenceManager pm, long tipoHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion () + " WHERE tipoHabitacion = ?");
        q.setParameters(tipoHabitacion);
        return (long) q.executeUnique();
	}

	public long eliminarHabitacionPorId (PersistenceManager pm, long numHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion () + " WHERE numHabitacion = ?");
        q.setParameters(numHabitacion);
        return (long) q.executeUnique();
	}
	
	public long actualizarEstadoHabitacion (PersistenceManager pm, String estado, long numHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHabitacion () + " SET estado = ? WHERE numHabitacion = ?");
        q.setParameters(estado, numHabitacion);
        return (long) q.executeUnique();
	}
	
	public long actualizarClienteHabitacion (PersistenceManager pm, long cliente, long numHabitacion)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHabitacion () + " SET cliente = ? WHERE numHabitacion = ?");
        q.setParameters(cliente, numHabitacion);
        return (long) q.executeUnique();
	}

	public Habitacion darHabitacionPorId (PersistenceManager pm, long numHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE numHabitacion = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(numHabitacion);
		return (Habitacion) q.executeUnique();
	}

	public List<Habitacion> darHabitacionPorTipo (PersistenceManager pm, long tipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE tipoHabitacion = ? and estado = 'Vacia'");
		q.setResultClass(Habitacion.class);
		q.setParameters(tipoHabitacion);
		return (List<Habitacion>) q.executeList();
	}
	
	public List<Habitacion> darHabitacionPorCliente (PersistenceManager pm, long cedulaCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE cliente = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(cedulaCliente);
		return (List<Habitacion>) q.executeList();
	}

	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion ());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}

}

