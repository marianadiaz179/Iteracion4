package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Hotel;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLHotel 
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
	public SQLHotel (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarHotel (PersistenceManager pm, long idHotel,String nombreHotel,
			int numeroEstrellas, String paisHotel, String ciudadHotel, String cadenaHotelera) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel () + "(idHotel, nombreHotel, numeroEstrellas, paisHotel, ciudadHotel, cadenaHotelera) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(idHotel, nombreHotel, numeroEstrellas, paisHotel, ciudadHotel, cadenaHotelera);
        return (long) q.executeUnique();
	}

	public long eliminarHotelPorNombre (PersistenceManager pm, String nombreHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE nombreHotel = ?");
        q.setParameters(nombreHotel);
        return (long) q.executeUnique();
	}

	public long eliminarHotelPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE idHotel = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public Hotel darHotelPorId (PersistenceManager pm, long idHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE idHotel = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(idHotel);
		return (Hotel) q.executeUnique();
	}

	public List<Hotel> darHotelPorNombre (PersistenceManager pm, String nombreHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE nombreHotel = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(nombreHotel);
		return (List<Hotel>) q.executeList();
	}

	public List<Hotel> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel ());
		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}

}