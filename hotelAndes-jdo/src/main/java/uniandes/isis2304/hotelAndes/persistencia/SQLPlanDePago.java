package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.PlanDePago;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLPlanDePago
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
	public SQLPlanDePago (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarPlanDePago (PersistenceManager pm, long id, String tipoPlan, String caracteristicas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPlanPago () + "(id, tipoPlan, caracteristicas) values (?, ?, ?)");
        q.setParameters(tipoPlan, caracteristicas);
        return (long) q.executeUnique();
	}

	public long eliminarPlanPorTipo (PersistenceManager pm, String tipoPlan)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPlanPago () + " WHERE tipoPlan = ?");
        q.setParameters(tipoPlan);
        return (long) q.executeUnique();
	}


	public PlanDePago darPlanPorTipo (PersistenceManager pm, String tipoPlan) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPlanPago() + " WHERE tipoPlan = ?");
		q.setResultClass(PlanDePago.class);
		q.setParameters(tipoPlan);
		return (PlanDePago) q.executeUnique();
	}
	
	public PlanDePago darPlanPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPlanPago() + " WHERE id = ?");
		q.setResultClass(PlanDePago.class);
		q.setParameters(id);
		return (PlanDePago) q.executeUnique();
	}


	public List<PlanDePago> darPlanDePago (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPlanPago());
		q.setResultClass(PlanDePago.class);
		return (List<PlanDePago>) q.executeList();
	}

}