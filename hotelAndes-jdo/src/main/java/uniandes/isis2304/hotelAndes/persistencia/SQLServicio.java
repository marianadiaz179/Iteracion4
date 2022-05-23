package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Servicio;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Tomas Angel
 */
class SQLServicio
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
	private PersistenciaHotelAndes ha;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLServicio (PersistenciaHotelAndes ha)
	{
		this.ha = ha;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @param nombre - El nombre del bar
	 * @param ciudad - La ciudad del bar
	 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del bar
	 * @return El número de tuplas insertadas
	 */
	public long adicionarServicio (PersistenceManager pm, long id, String nombre, int demanda) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaServicio () + "(id, nombreServicio, demanda) values (?, ?, ?)");
        q.setParameters(id, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPorNombre (PersistenceManager pm, String nombreServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaServicio () + " WHERE nombreServicio = ?");
        q.setParameters(nombreServicio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaServicio () + " WHERE id = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Servicio darServicioPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaServicio () + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (Servicio) q.executeUnique();
	}
	
	public long actualizarDemandaPorId(PersistenceManager pm, long idServicio)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + ha.darTablaServicio () + " SET demanda = demanda + 1 WHERE id = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<Servicio> darServiciosPorNombre (PersistenceManager pm, String nombreServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaServicio () + " WHERE nombreServicio = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(nombreServicio);
		return (List<Servicio>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaServicio ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}

}
