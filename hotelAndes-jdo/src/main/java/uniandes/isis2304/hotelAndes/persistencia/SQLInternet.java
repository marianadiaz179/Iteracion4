package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Internet;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLInternet
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
	public SQLInternet (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarInternet (PersistenceManager pm, double capacidadInternet, long idServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaInternet () + "(capacidadInternet, idServicio) values (?, ?)");
        q.setParameters(capacidadInternet, idServicio);
        return (long) q.executeUnique();
	}

	public long eliminarInternetPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInternet () + " WHERE idServicio = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}


	public Internet darInternetPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInternet() + " WHERE idServicio = ?");
		q.setResultClass(Internet.class);
		q.setParameters(idServicio);
		return (Internet) q.executeUnique();
	}


	public List<Internet> darInternet (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInternet());
		q.setResultClass(Internet.class);
		return (List<Internet>) q.executeList();
	}

}
