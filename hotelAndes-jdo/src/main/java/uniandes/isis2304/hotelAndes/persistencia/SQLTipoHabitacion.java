package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.TipoHabitacion;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto TipoHabitacion de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLTipoHabitacion 
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
	public SQLTipoHabitacion (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarTipoHabitacion (PersistenceManager pm, long idTipoHabitacion, String tipoHabitacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoHabitacion () + "(idTipoHabitacion, tipoHabitacion) values (?, ?)");
        q.setParameters(idTipoHabitacion, tipoHabitacion);
        return (long) q.executeUnique();
	}

	public long eliminarTipoHabitacionPorTipo (PersistenceManager pm, String tipoHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoHabitacion () + " WHERE tipoHabitacion = ?");
        q.setParameters(tipoHabitacion);
        return (long) q.executeUnique();
	}

	public long eliminarTipoHabitacionPorId (PersistenceManager pm, long idTipoHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoHabitacion () + " WHERE idTipoHabitacion = ?");
        q.setParameters(idTipoHabitacion);
        return (long) q.executeUnique();
	}

	public TipoHabitacion darTipoHabitacionPorId (PersistenceManager pm, long idTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion () + " WHERE idTipoHabitacion = ?");
		q.setResultClass(TipoHabitacion.class);
		q.setParameters(idTipoHabitacion);
		return (TipoHabitacion) q.executeUnique();
	}

	public List<TipoHabitacion> darTipoHabitacionPorTipo (PersistenceManager pm, String tipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion () + " WHERE tipoHabitacion = ?");
		q.setResultClass(TipoHabitacion.class);
		q.setParameters(tipoHabitacion);
		return (List<TipoHabitacion>) q.executeList();
	}

	public List<TipoHabitacion> darTipoHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoHabitacion ());
		q.setResultClass(TipoHabitacion.class);
		return (List<TipoHabitacion>) q.executeList();
	}

}

