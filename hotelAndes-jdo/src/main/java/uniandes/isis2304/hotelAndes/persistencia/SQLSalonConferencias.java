package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.SalonConferencias;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SalonConferencias de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Tomas Angel
 */
class SQLSalonConferencias 
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
	 * @param ha - El Manejador de persistencia de la aplicación
	 */
	public SQLSalonConferencias (PersistenciaHotelAndes ha)
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
	public long adicionarSalonConferencias (PersistenceManager pm, long id, long idServicio, int capacidadPersonas, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaSalonConferencias () + "(id, idServicio, capacidadPersonas, nombre) values (?, ?, ?, ?)");
        q.setParameters(id, idServicio, capacidadPersonas, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar SalonesConferenciasa de la base de datos de HotelAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreSalonConferencias - El nombre del SalonConferencias
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSalonConferenciaPorNombre (PersistenceManager pm, String nombreSalonConferencias)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaSalonConferencias () + " WHERE nombre = ?");
        q.setParameters(nombreSalonConferencias);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SalonesConferencias de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSalonConferenciasPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaSalonConferencias () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN SalonesConferenciasa de la 
	 * base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSalonConferencias - El identificador del salon de conferencias
	 * @return El objeto SalonConferencias que tiene el identificador dado
	 */
	public SalonConferencias darSalonConferenciasPorId (PersistenceManager pm, long idSalonConferencias) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSalonConferencias () + " WHERE id = ?");
		q.setResultClass(SalonConferencias.class);
		q.setParameters(idSalonConferencias);
		return (SalonConferencias) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<SalonConferencias> darSalonConferenciaPorNombre (PersistenceManager pm, String nombreSalonConferencias) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSalonConferencias () + " WHERE nombre = ?");
		q.setResultClass(SalonConferencias.class);
		q.setParameters(nombreSalonConferencias);
		return (List<SalonConferencias>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<SalonConferencias> darSalonesConferencias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSalonConferencias ());
		q.setResultClass(SalonConferencias.class);
		return (List<SalonConferencias>) q.executeList();
	}

}
