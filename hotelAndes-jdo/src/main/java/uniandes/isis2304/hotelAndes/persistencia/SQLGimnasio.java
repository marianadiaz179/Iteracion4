package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Gimnasio;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLGimnasio 
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
	public SQLGimnasio (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarGimnasio (PersistenceManager pm, long idServicio, long id, int capacidadGimnasio, String nombreGimnasio, String horario) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaGimnasio () + "(idServicio, id, capacidadGimnasio, nombreGimnasio, horario) values (?, ?, ?, ?, ?)");
        q.setParameters(idServicio, id, capacidadGimnasio, nombreGimnasio, horario);
        return (long) q.executeUnique();
	}

	public long eliminarGimnasioPorNombre (PersistenceManager pm, String nombreGimnasio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGimnasio () + " WHERE nombreGimnasio = ?");
        q.setParameters(nombreGimnasio);
        return (long) q.executeUnique();
	}

	public long eliminarGimnasioPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGimnasio () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Gimnasio darGimnasioPorId (PersistenceManager pm, long idGimnasio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasio () + " WHERE id = ?");
		q.setResultClass(Gimnasio.class);
		q.setParameters(idGimnasio);
		return (Gimnasio) q.executeUnique();
	}

	public List<Gimnasio> darGimnasioPorNombre (PersistenceManager pm, String nombreGimnasio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasio () + " WHERE nombreGimnasio = ?");
		q.setResultClass(Gimnasio.class);
		q.setParameters(nombreGimnasio);
		return (List<Gimnasio>) q.executeList();
	}

	public List<Gimnasio> darGimnasios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasio());
		q.setResultClass(Gimnasio.class);
		return (List<Gimnasio>) q.executeList();
	}

}