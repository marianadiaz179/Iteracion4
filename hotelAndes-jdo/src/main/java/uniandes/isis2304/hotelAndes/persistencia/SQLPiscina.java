package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Piscina;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLPiscina 
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
	public SQLPiscina (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarPiscina (PersistenceManager pm, long idServicio, long id, int capacidadPiscina, int profundidad, String horario, String nombrePiscina) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPiscina () + "(idServicio, id, capacidadPiscina, profundidad, horario, nombrePiscina) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idServicio, id, capacidadPiscina, profundidad, horario, nombrePiscina);
        return (long) q.executeUnique();
	}

	public long eliminarPiscinaPorNombre (PersistenceManager pm, String nombrePiscina)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPiscina () + " WHERE nombrePiscina = ?");
        q.setParameters(nombrePiscina);
        return (long) q.executeUnique();
	}

	public long eliminarPiscinaPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPiscina () + " WHERE idServicio = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}

	public Piscina darPiscinaPorId (PersistenceManager pm, long idPiscina) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscina () + " WHERE id = ?");
		q.setResultClass(Piscina.class);
		q.setParameters(idPiscina);
		return (Piscina) q.executeUnique();
	}

	public List<Piscina> darPiscinasPorNombre (PersistenceManager pm, String nombrePiscina) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscina () + " WHERE nombrePiscina = ?");
		q.setResultClass(Piscina.class);
		q.setParameters(nombrePiscina);
		return (List<Piscina>) q.executeList();
	}

	public List<Piscina> darPiscinas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscina());
		q.setResultClass(Piscina.class);
		return (List<Piscina>) q.executeList();
	}

}