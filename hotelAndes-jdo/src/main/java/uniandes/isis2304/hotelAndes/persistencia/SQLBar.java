package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Bar;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLBar 
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
	public SQLBar (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarBar (PersistenceManager pm, long id, String nombreBar, long idServicio, int capacidadBar, String horario, String estiloBar) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBar () + "(idServicio, id, nombreBar, capacidadBar, horario, estiloBar) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idServicio, id, nombreBar, capacidadBar, horario,estiloBar);
        return (long) q.executeUnique();
	}

	public long eliminarBaresPorNombre (PersistenceManager pm, String nombreBar)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE nombreBar = ?");
        q.setParameters(nombreBar);
        return (long) q.executeUnique();
	}

	public long eliminarBarPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Bar darBarPorId (PersistenceManager pm, long idBar) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
		q.setResultClass(Bar.class);
		q.setParameters(idBar);
		return (Bar) q.executeUnique();
	}

	public List<Bar> darBaresPorNombre (PersistenceManager pm, String nombreBar) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE nombreBar = ?");
		q.setResultClass(Bar.class);
		q.setParameters(nombreBar);
		return (List<Bar>) q.executeList();
	}

	public List<Bar> darBares (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		q.setResultClass(Bar.class);
		return (List<Bar>) q.executeList();
	}

}

