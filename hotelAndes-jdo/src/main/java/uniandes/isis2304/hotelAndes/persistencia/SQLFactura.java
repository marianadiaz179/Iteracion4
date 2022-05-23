package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Factura;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLFactura
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
	public SQLFactura (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarFactura (PersistenceManager pm, long idFactura, long habitacion, double total) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFacturas () + "(idFactura, habitacion, montoTotal) values (?, ?, ?)");
        q.setParameters(idFactura, habitacion, total);
        return (long) q.executeUnique();
	}

	public long eliminarFacturaPorCliente (PersistenceManager pm, long cedulaCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturas () + " WHERE cedulaCliente = ?");
        q.setParameters(cedulaCliente);
        return (long) q.executeUnique();
	}

	
	public long eliminarFacturaPorId (PersistenceManager pm, long idFactura)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturas () + " WHERE idFactura = ?");
        q.setParameters(idFactura);
        return (long) q.executeUnique();
	}


	public Factura darFacturaPorId (PersistenceManager pm, long idFactura) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturas() + " WHERE idFactura = ?");
		q.setResultClass(Factura.class);
		q.setParameters(idFactura);
		return (Factura) q.executeUnique();
	}


	public List<Factura> darFacturaPorCliente (PersistenceManager pm, long cedulaCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturas() + " WHERE cedulaCliente = ?");
		q.setResultClass(Factura.class);
		q.setParameters(cedulaCliente);
		return (List<Factura>) q.executeList();
	}


	public List<Factura> darFacturas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFacturas());
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}

}

