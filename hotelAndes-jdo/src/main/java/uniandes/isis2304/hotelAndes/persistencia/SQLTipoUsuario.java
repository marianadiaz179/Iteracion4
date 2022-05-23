package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLTipoUsuario
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
	public SQLTipoUsuario (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarTipoUsuario (PersistenceManager pm, long idEmpleo, String tipoEmpleo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTipoUsuario () + "(idEmpleo, tipoEmpleo) values (?, ?)");
        q.setParameters(idEmpleo, tipoEmpleo);
        return (long) q.executeUnique();
	}

	public long eliminarTipoPorId (PersistenceManager pm, long idEmpleo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoUsuario () + " WHERE idEmpleo = ?");
        q.setParameters(idEmpleo);
        return (long) q.executeUnique();
	}

	
	public long eliminarTipoPorNombre (PersistenceManager pm, String tipoEmpleo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoUsuario () + " WHERE tipoEmpleo = ?");
        q.setParameters(tipoEmpleo);
        return (long) q.executeUnique();
	}
	
	public TipoUsuario darTipoPorNombre (PersistenceManager pm, String tipoEmpleo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoUsuario() + " WHERE tipoEmpleo = ?");
		q.setResultClass(TipoUsuario.class);
		q.setParameters(tipoEmpleo);
		return (TipoUsuario) q.executeUnique();
	}


	public TipoUsuario darTipoPorId (PersistenceManager pm, long idEmpleo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoUsuario() + " WHERE idEmpleo = ?");
		q.setResultClass(TipoUsuario.class);
		q.setParameters(idEmpleo);
		return (TipoUsuario) q.executeUnique();
	}


	public List<TipoUsuario> darTiposUsuario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTipoUsuario());
		q.setResultClass(TipoUsuario.class);
		return (List<TipoUsuario>) q.executeList();
	}

}
