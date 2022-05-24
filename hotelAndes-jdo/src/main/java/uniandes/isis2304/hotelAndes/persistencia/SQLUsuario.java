package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Usuario;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de HotelAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Mariana Díaz y Tomás Angel
 */
class SQLUsuario
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
	public SQLUsuario (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	

	public long adicionarUsuario (PersistenceManager pm, long tipoUsuario, String nombreEmpleado, long cedulaEmpleado, String correo, long idHotel, double gastos, int estadia) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + "(tipoUsuario, nombre, cedula, correo, idHotel, gastosHotel, estadia) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(tipoUsuario, nombreEmpleado, cedulaEmpleado, correo, idHotel, gastos, estadia);
        return (long) q.executeUnique();
	}

	
	public long eliminarUsuarioPorNombre (PersistenceManager pm, String nombreEmpleado)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario () + " WHERE nombre = ?");
        q.setParameters(nombreEmpleado);
        return (long) q.executeUnique();
	}

	
	public long eliminarUsuarioPorCedula (PersistenceManager pm, long cedula)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario () + " WHERE cedula = ?");
        q.setParameters(cedula);
        return (long) q.executeUnique();
	}
	
	public long actualizarEstadiaCliente (PersistenceManager pm,int duracion, long cedula)
	{
        Query q = pm.newQuery(SQL, "UPDATE USUARIO SET ESTADIA = ESTADIA + ? WHERE cedula = ?");
        q.setParameters(duracion, cedula);
        return (long) q.executeUnique();
	}


	public Usuario darUsuarioPorCedula (PersistenceManager pm, long cedulaEmpleado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE cedula = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(cedulaEmpleado);
		return (Usuario) q.executeUnique();
	}


	public List<Usuario> darUsuariosPorNombre (PersistenceManager pm, String nombreEmpleado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE nombre = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(nombreEmpleado);
		return (List<Usuario>) q.executeList();
	}
	
	public List<Usuario> darClientePorNombre (PersistenceManager pm, String nombreEmpleado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE nombre = ? AND tipoUsuario = 3");
		q.setResultClass(Usuario.class);
		q.setParameters(nombreEmpleado);
		return (List<Usuario>) q.executeList();
	}
	
	public List<Usuario> encontrarBuenosClientes (PersistenceManager pm ) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE (estadia >= 15 OR gastosHotel > 15000000) AND TIPOUSUARIO = 3");
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
	
	public List<Usuario> consumidoresHotelAndes (PersistenceManager pm, Date fechaI, Date fechaF, long id ) 
	{
		Query q = pm.newQuery(SQL, "SELECT DISTINCT(USUARIO.CEDULA), USUARIO.*\r\n"
				+ "FROM CONSUMO, USUARIO\r\n"
				+ "WHERE ESTADO = 'Pago' AND IDSERVICIO = ? AND FECHACONSUMO BETWEEN ? AND ? AND CONSUMO.CLIENTE = USUARIO.CEDULA AND USUARIO.TIPOUSUARIO = 3");
		q.setResultClass(Usuario.class);
		q.setParameters(id, fechaI, fechaF);
		return (List<Usuario>) q.executeList();
	}
	
	public List<Object> cantidadConsumosConsumidores (PersistenceManager pm, Date fechaI, Date fechaF, long id ) 
	{
		Query q = pm.newQuery(SQL, "SELECT count(consumo.idservicio), idServicio, usuario.nombre\n"
				+ "FROM CONSUMO, USUARIO\n"
				+ "WHERE ESTADO = 'Pago' AND IDSERVICIO = ? AND FECHACONSUMO BETWEEN ? AND ?\n"
				+ "AND CONSUMO.CLIENTE = USUARIO.CEDULA AND USUARIO.TIPOUSUARIO = 3\n"
				+ "GROUP BY IDSERVICIO,USUARIO.NOMBRE");
		q.setParameters(id, fechaI, fechaF);
		return q.executeList();
	}
	
	public List<Usuario> consumidoresHotelAndes2 (PersistenceManager pm, Date fechaI, Date fechaF, long id ) 
	{
		Query q = pm.newQuery(SQL, "SELECT usuario.*\r\n"
				+ "FROM usuario, servicio, consumo\r\n"
				+ "WHERE consumo.cliente = usuario.cedula\r\n"
				+ "    AND consumo.idServicio <> servicio.id\r\n"
				+ "    AND consumo.fechaconsumo BETWEEN ? AND ?\r\n"
				+ "    AND servicio.id = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(fechaI, fechaF,id);
		return (List<Usuario>) q.executeList();
	}

	public List<Usuario> darUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}

}

