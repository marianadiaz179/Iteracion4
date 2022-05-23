package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Tienda;

class SQLTienda {
	

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
	public SQLTienda (PersistenciaHotelAndes ha)
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
	public long adicionarTienda (PersistenceManager pm, long idServicio, long id, String tipoTienda, String horario, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaTienda () + "(idServicio, id, tipoTienda, horario, nombre) values (?, ?, ?, ?, ?)");
        q.setParameters(idServicio, id, tipoTienda, horario, nombre);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreRestaurante - El nombre del restaurante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTiendaPorNombre (PersistenceManager pm, String nombreTienda)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaTienda () + " WHERE nombre = ?");
        q.setParameters(nombreTienda);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idRestaurante - El identificador del restaurante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTiendaPorId (PersistenceManager pm, long idTienda)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaTienda () + " WHERE id = ?");
        q.setParameters(idTienda);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Tienda darTiendaPorId (PersistenceManager pm, long idTienda) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaTienda () + " WHERE id = ?");
		q.setResultClass(Tienda.class);
		q.setParameters(idTienda);
		return (Tienda) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreBar - El nombre de bar buscado
	 * @return Una lista de objetos BAR que tienen el nombre dado
	 */
	public List<Tienda> darTiendaPorNombre (PersistenceManager pm, String nombreTienda) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaTienda () + " WHERE nombre = ?");
		q.setResultClass(Tienda.class);
		q.setParameters(nombreTienda);
		return (List<Tienda>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Tienda> darTiendas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaTienda());
		q.setResultClass(Tienda.class);
		return (List<Tienda>) q.executeList();
	}

}
