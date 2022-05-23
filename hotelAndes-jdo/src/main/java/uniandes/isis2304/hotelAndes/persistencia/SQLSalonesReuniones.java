package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.SalonReuniones;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SalonConferencias de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Tomas Angel
 */
class SQLSalonReuniones 
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
	public SQLSalonReuniones (PersistenciaHotelAndes ha)
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
	public long adicionarSalonReuniones (PersistenceManager pm, long id, long idServicio, int capacidadPersonas, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaSalonReuniones () + "(id, idServicio, capacidadPersonas, nombre) values (?, ?, ?, ?)");
        q.setParameters(id, idServicio, capacidadPersonas, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar SalonesConferenciasa de la base de datos de HotelAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreSalonConferencias - El nombre del SalonConferencias
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSalonReunionesPorNombre (PersistenceManager pm, String nombreSalonReuniones)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaSalonReuniones () + " WHERE nombre = ?");
        q.setParameters(nombreSalonReuniones);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SalonesConferencias de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSalonReunionesPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaSalonReuniones () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN SalonesConferenciasa de la 
	 * base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del bar
	 * @return El objeto SalonConferencias que tiene el identificador dado
	 */
	public SalonReuniones darSalonReunionesPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSalonReuniones () + " WHERE id = ?");
		q.setResultClass(SalonReuniones.class);
		q.setParameters(id);
		return (SalonReuniones) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<SalonReuniones> darSalonReunionesPorNombre (PersistenceManager pm, String nombreSalonReuniones) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSalonReuniones () + " WHERE nombre = ?");
		q.setResultClass(SalonReuniones.class);
		q.setParameters(nombreSalonReuniones);
		return (List<SalonReuniones>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<SalonReuniones> darSalonesReuniones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSalonReuniones ());
		q.setResultClass(SalonReuniones.class);
		return (List<SalonReuniones>) q.executeList();
	}

}
