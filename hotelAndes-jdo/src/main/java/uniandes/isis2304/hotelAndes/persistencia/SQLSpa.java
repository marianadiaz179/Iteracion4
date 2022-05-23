package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Spa;

class SQLSpa {
	

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
	public SQLSpa (PersistenciaHotelAndes ha)
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
	public long adicionarSpa (PersistenceManager pm, long idServicio, long id, int capacidadSpa, String horario, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaSpa () + "(idServicio, id, capacidadSpa, horario, nombre) values (?, ?, ?, ?, ?)");
        q.setParameters(idServicio, id, capacidadSpa, horario, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreRestaurante - El nombre del restaurante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSpaPorNombre (PersistenceManager pm, String nombreSpa)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaSpa () + " WHERE nombre = ?");
        q.setParameters(nombreSpa);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idRestaurante - El identificador del restaurante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSpaPorId (PersistenceManager pm, long idSpa)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaSpa () + " WHERE id = ?");
        q.setParameters(idSpa);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Spa darSpaPorId (PersistenceManager pm, long idSpa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSpa () + " WHERE id = ?");
		q.setResultClass(Spa.class);
		q.setParameters(idSpa);
		return (Spa) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<Spa> darSpasPorNombre (PersistenceManager pm, String nombreSpa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSpa () + " WHERE nombre = ?");
		q.setResultClass(Spa.class);
		q.setParameters(nombreSpa);
		return (List<Spa>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Spa> darSpas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaSpa ());
		q.setResultClass(Spa.class);
		return (List<Spa>) q.executeList();
	}

}
