package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.LavadoPlanchado;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLLavadoPlanchado
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
	public SQLLavadoPlanchado (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarLavadoPlanchado (PersistenceManager pm, String tipoServicio, String horario, long idServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLavadoPlanchado () + "(tipoServicio, horario, idServicio) values (?, ?, ?)");
        q.setParameters(tipoServicio, horario, idServicio);
        return (long) q.executeUnique();
	}

	public long eliminarLavadoPlanchadoPorTipo (PersistenceManager pm, String tipoServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLavadoPlanchado () + " WHERE tipoServicio = ?");
        q.setParameters(tipoServicio);
        return (long) q.executeUnique();
	}

	
	public long eliminarLavadoPlanchadoPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLavadoPlanchado () + " WHERE idServicio = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}


	public LavadoPlanchado darLavadoPlanchadoPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavadoPlanchado() + " WHERE idServicio = ?");
		q.setResultClass(LavadoPlanchado.class);
		q.setParameters(idServicio);
		return (LavadoPlanchado) q.executeUnique();
	}


	public List<LavadoPlanchado> darLavadoPlanchadoPorTipo (PersistenceManager pm, String tipoServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavadoPlanchado() + " WHERE tipoServicio = ?");
		q.setResultClass(LavadoPlanchado.class);
		q.setParameters(tipoServicio);
		return (List<LavadoPlanchado>) q.executeList();
	}


	public List<LavadoPlanchado> darLavadoPlanchado (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavadoPlanchado());
		q.setResultClass(LavadoPlanchado.class);
		return (List<LavadoPlanchado>) q.executeList();
	}

}