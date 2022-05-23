package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.sql.DATE;
import uniandes.isis2304.hotelAndes.negocio.Convencion;



/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Convencion de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Tomas Angel y Mariana Díaz
 */
class SQLConvencion
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
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLConvencion (PersistenciaHotelAndes ha)
	{
		this.ha = ha;
	}
	
	
	public long adicionarConvencion (PersistenceManager pm, long id, String nombre, long cedula, Date fInicio, Date fFin, int duracion, String planPago) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + ha.darTablaConvencion () + "(id, nombre, organizador, fechaInicio, fechaFin,duracion, planPago) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, cedula, fInicio, fFin, duracion, planPago);
        return (long) q.executeUnique();
	}

	
	public long eliminarConvencionPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaConvencion () + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();
	}

	
	public long eliminarConvencionPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + ha.darTablaConvencion () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	
	public Convencion darConvencionPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaConvencion () + " WHERE id = ?");
		q.setResultClass(Convencion.class);
		q.setParameters(id);
		return (Convencion) q.executeUnique();
	}
	
	public Date darFechaInicioPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT fechaInicio FROM " + ha.darTablaConvencion () + " WHERE id = ?");
		q.setResultClass(Date.class);
		q.setParameters(id);
		return (Date) q.executeUnique();
	}
	
	public Date darFechaFinPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT fechaFin FROM " + ha.darTablaConvencion () + " WHERE id = ?");
		q.setResultClass(Date.class);
		q.setParameters(id);
		return (Date) q.executeUnique();
	}

	
	public Convencion darConvencionesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaConvencion () + " WHERE nombre = ?");
		q.setResultClass(Convencion.class);
		q.setParameters(nombre);
		return (Convencion) q.executeUnique();
	}

	
	public List<Convencion> darConvenciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + ha.darTablaConvencion ());
		q.setResultClass(Convencion.class);
		return (List<Convencion>) q.executeList();
	}

}
