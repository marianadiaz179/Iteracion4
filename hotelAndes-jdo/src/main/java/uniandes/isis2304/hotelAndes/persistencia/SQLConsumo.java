package uniandes.isis2304.hotelAndes.persistencia;
import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Consumo;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLConsumo 
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
	public SQLConsumo (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarConsumo (PersistenceManager pm, long id, long idServicio, long cedula, long producto, String estado, long cliente, Date fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaConsumo () + "(id, idServicio, habitacion, producto, estado, cliente, fechaConsumo) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, idServicio, cedula, producto, estado, cliente, fecha);
        return (long) q.executeUnique();
	}

	public long eliminarConsumoPorHabitacion (PersistenceManager pm, long habitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConsumo () + " WHERE habitacion = ?");
        q.setParameters(habitacion);
        return (long) q.executeUnique();
	}

	public long eliminarConsumoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConsumo () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	
	public long actualizarEstadoConsumo (PersistenceManager pm,String estado, long id)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaConsumo () + " SET estado = ? WHERE id = ?");
        q.setParameters(estado,id);
        return (long) q.executeUnique();
	}

	public Consumo darConsumoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaConsumo () + " WHERE id = ?");
		q.setResultClass(Consumo.class);
		q.setParameters(id);
		return (Consumo) q.executeUnique();
	}

	public List<Consumo> darConsumoPorHabitacion (PersistenceManager pm, long habitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaConsumo () + " WHERE habitacion = ? and estado = 'Pendiente'");
		q.setResultClass(Consumo.class);
		q.setParameters(habitacion);
		return (List<Consumo>) q.executeList();
	}

	public List<Consumo> darConsumos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaConsumo ());
		q.setResultClass(Consumo.class);
		return (List<Consumo>) q.executeList();
	}
}
