/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: HotelAndes 
 * @version 1.0
 * @author Mariana Díaz - Tomas Angel
 * Abril 2022
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.hotelAndes.persistencia;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import oracle.sql.DATE;
import uniandes.isis2304.hotelAndes.negocio.Bar;
import uniandes.isis2304.hotelAndes.negocio.Consumo;
import uniandes.isis2304.hotelAndes.negocio.Convencion;
import uniandes.isis2304.hotelAndes.negocio.Usuario;
import uniandes.isis2304.hotelAndes.negocio.VOConvencion;
import uniandes.isis2304.hotelAndes.negocio.Factura;
import uniandes.isis2304.hotelAndes.negocio.Gimnasio;
import uniandes.isis2304.hotelAndes.negocio.Habitacion;
import uniandes.isis2304.hotelAndes.negocio.Hotel;
import uniandes.isis2304.hotelAndes.negocio.Internet;
import uniandes.isis2304.hotelAndes.negocio.LavadoPlanchado;
import uniandes.isis2304.hotelAndes.negocio.Piscina;
import uniandes.isis2304.hotelAndes.negocio.PlanDePago;
import uniandes.isis2304.hotelAndes.negocio.Producto;
import uniandes.isis2304.hotelAndes.negocio.ReservaHabitacion;
import uniandes.isis2304.hotelAndes.negocio.ReservaServicio;
import uniandes.isis2304.hotelAndes.negocio.Restaurante;
import uniandes.isis2304.hotelAndes.negocio.SalonConferencias;
import uniandes.isis2304.hotelAndes.negocio.SalonReuniones;
import uniandes.isis2304.hotelAndes.negocio.Servicio;
import uniandes.isis2304.hotelAndes.negocio.Spa;
import uniandes.isis2304.hotelAndes.negocio.Supermercado;
import uniandes.isis2304.hotelAndes.negocio.Tienda;
import uniandes.isis2304.hotelAndes.negocio.TipoHabitacion;
import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;


/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Tomas Angel - Mariana Díaz
 */
public class PersistenciaHotelAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaHotelAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaHotelAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	public List <String> tablas;
	
	
	private SQLBar sqlBar;
	
	private SQLUsuario sqlUsuario;
	
	private SQLFactura sqlFactura;
	
	private SQLGimnasio sqlGimnasio;
	
	private SQLHabitacion sqlHabitacion;
	
	private SQLHotel sqlHotel;
	
	private SQLInternet sqlInternet;
	
	private SQLLavadoPlanchado sqlLavadoPlanchado;
	
	private SQLPiscina sqlPiscina;
	
	private SQLPlanDePago sqlPlanDePago;
	
	private SQLProducto sqlProducto;
	
	private SQLReservaHabitacion sqlReservaHabitacion;
	
	private SQLReservaServicio sqlReservaServicio;
	
	private SQLRestaurante sqlRestaurante;
	
	private SQLSalonConferencias sqlSalonConferencias;
	
	private SQLSalonReuniones sqlSalonReuniones;
	
	private SQLServicio sqlServicio;
	
	private SQLSpa sqlSpa;
	
	private SQLSupermercado sqlSupermercado;
	
	private SQLTienda sqlTienda;
	
	private SQLTipoUsuario sqlTipoUsuario;

	private SQLUtil sqlUtil;
	
	private SQLTipoHabitacion sqlTipoHabitacion;
	
	private SQLConsumo sqlConsumo;
	
	private SQLConvencion sqlConvencion;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaHotelAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("HotelAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("hotelAndes_sequence");
		tablas.add ("BAR");
		tablas.add ("USUARIO");
		tablas.add ("FACTURA");
		tablas.add ("GIMNASIO");
		tablas.add ("HABITACION");
		tablas.add ("HOTEL");
		tablas.add ("INTERNET");
		tablas.add ("LAVADOPLANCHADO");
		tablas.add ("PISCINA");
		tablas.add ("PLANDEPAGO");
		tablas.add ("PRODUCTO");
		tablas.add ("RESERVAHABITACION");
		tablas.add ("RESERVASERVICIO");
		tablas.add ("RESTAURANTE");
		tablas.add ("SALONCONFERENCIAS");
		tablas.add ("SALONREUNIONES");
		tablas.add ("SERVICIO");
		tablas.add ("SPA");
		tablas.add ("SUPERMERCADO");
		tablas.add ("TIENDA");
		tablas.add ("TIPOUSUARIO");
		tablas.add("TIPOHABITACION");
		tablas.add("CONSUMO");
		tablas.add("CONVENCION");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaHotelAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaHotelAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaHotelAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaHotelAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaHotelAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlUtil = new SQLUtil(this);
		sqlBar = new SQLBar(this);
		sqlUsuario = new SQLUsuario (this);
		sqlFactura = new SQLFactura (this);
		sqlGimnasio = new SQLGimnasio (this);
		sqlHabitacion = new SQLHabitacion (this);
		sqlHotel = new SQLHotel (this);
		sqlInternet = new SQLInternet (this);
		sqlLavadoPlanchado = new SQLLavadoPlanchado (this);
		sqlPiscina = new SQLPiscina (this);
		sqlPlanDePago = new SQLPlanDePago (this);
		sqlProducto = new SQLProducto (this);
		sqlReservaHabitacion = new SQLReservaHabitacion(this);
		sqlReservaServicio = new SQLReservaServicio(this);
		sqlRestaurante = new SQLRestaurante(this);
		sqlSalonConferencias = new SQLSalonConferencias(this);
		sqlSalonReuniones = new SQLSalonReuniones(this);
		sqlServicio = new SQLServicio(this);
		sqlSupermercado = new SQLSupermercado(this);
		sqlTienda = new SQLTienda(this);
		sqlTipoUsuario = new SQLTipoUsuario(this);
		sqlTipoHabitacion = new SQLTipoHabitacion(this);
		sqlConsumo = new SQLConsumo(this);
		sqlConvencion = new SQLConvencion(this);
	}
	
	
	public String darSeqHotelAndes ()
	{
		return tablas.get (0);
	}


	public String darTablaBar ()
	{
		return "BAR";
	}

	
	public String darTablaUsuario ()
	{
		return "USUARIO";
	}

	
	public String darTablaFacturas ()
	{
		return "FACTURA";
	}

	
	public String darTablaGimnasio ()
	{
		return "GIMNASIO";
	}

	
	public String darTablaHabitacion ()
	{
		return "HABITACION";
	}

	
	public String darTablaHotel ()
	{
		return "HOTEL";
	}
	
	public String darTablaInternet ()
	{
		return "INTERNET";
	}
	
	public String darTablaLavadoPlanchado ()
	{
		return "LAVADOPLANCHADO";
	}
	
	public String darTablaPiscina ()
	{
		return "PISCINA";
	}
	
	public String darTablaPlanPago ()
	{
		return "PLANDEPAGO";
	}
	
	public String darTablaProducto ()
	{
		return "PRODUCTO";
	}
	
	public String darTablaReservaHabitacion()
	{
		return "RESERVAHABITACION";
	}
	
	public String darTablaReservaServicio()
	{
		return "RESERVASERVICIO";
	}
	
	public String darTablaRestaurante ()
	{
		return "RESTAURANTE";
	}
	
	public String darTablaSalonConferencias ()
	{
		return "SALONCONFERENCIAS";
	}
	
	public String darTablaSalonReuniones ()
	{
		return "SALONREUNIONES";
	}
	
	public String darTablaServicio()
	{
		return "SERVICIO";
	}
	
	public String darTablaSpa ()
	{
		return "SPA";
	}
	
	public String darTablaSupermercado ()
	{
		return "SUPERMERCADO";
	}
	
	public String darTablaTienda ()
	{
		return "TIENDA";
	}
	
	public String darTablaTipoUsuario ()
	{

		return "TIPOUSUARIO";
		
	}
	
	public String darTablaTipoHabitacion()
	{
		return "TIPOHABITACION";
	}
	
	public String darTablaConsumo()
	{
		return "CONSUMO";
	}
	
	public String darTablaConvencion()
	{
		return "CONVENCION";
	}
	
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }

	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los BARES
	 *****************************************************************/

	public Bar adicionarBar (String nombreBar, long idServicio, int capacidadBar, String horario, String estiloBar) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idBar = nextval ();
            long tuplasInsertadas = sqlBar.adicionarBar (pm,idBar, nombreBar, idServicio, capacidadBar, horario, estiloBar) ;
            tx.commit();
            
            log.trace ("Inserción de bar: " + nombreBar + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Bar (idBar, nombreBar, idServicio, capacidadBar, horario, estiloBar) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarBaresPorNombre (String nombreBar)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlBar.eliminarBaresPorNombre(pm, nombreBar);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarBarPorId (long idBar)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlBar.eliminarBarPorId(pm, idBar);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Bar> darBares ()
	{
		return sqlBar.darBares (pmf.getPersistenceManager());
	}
 

	public List<Bar> darBaresPorNombre (String nombreBar) 
	{
		return sqlBar.darBaresPorNombre (pmf.getPersistenceManager(), nombreBar) ;
	}
 
	public Bar  darBarPorId (long idBar) 
	{
		return sqlBar. darBarPorId (pmf.getPersistenceManager(), idBar) ;
	}


	
	
	/* ****************************************************************
	 * 			Métodos para manejar los CONSUMOS
	 *****************************************************************/

	public Consumo adicionarConsumo (long idServicio, long habitacion, long producto, String estado, long cliente, Date fecha) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idConsumo = nextval ();
            long tuplasInsertadas = sqlConsumo.adicionarConsumo (pm,idConsumo, idServicio, habitacion, producto, estado, cliente, fecha) ;
            tx.commit();
            
            log.trace ("Inserción de consumo: " + String.valueOf(idConsumo) + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Consumo (idConsumo,  idServicio, habitacion, producto, estado, cliente, fecha) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarConsumoPorHabitacion (long habitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConsumo.eliminarConsumoPorHabitacion(pm, habitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarConsumoPorId (long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConsumo.eliminarConsumoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarEstadoConsumo (String estado, long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConsumo.actualizarEstadoConsumo(pm, estado, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Consumo> darConsumos ()
	{
		return sqlConsumo.darConsumos (pmf.getPersistenceManager());
	}
 

	public List<Consumo> darConsumoPorHabitacion (long habitacion) 
	{
		return sqlConsumo.darConsumoPorHabitacion (pmf.getPersistenceManager(), habitacion) ;
	}
 
	public Consumo  darConsumoPorId (long id) 
	{
		return sqlConsumo. darConsumoPorId (pmf.getPersistenceManager(), id) ;
	}
	

	/* ****************************************************************
	 * 			Métodos para manejar las CONVENCIONES
	 *****************************************************************/

	public Convencion adicionarConvencion (String nombre, long cedula, Date fechaI, Date fechaF,int dur, long plan) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlConvencion.adicionarConvencion (pm,id, nombre, cedula, fechaI, fechaF,dur, plan) ;
            tx.commit();
            
            log.trace ("Inserción de convención: " + String.valueOf(id) + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Convencion (id,  nombre, cedula, fechaI, fechaF,dur, plan) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarConvencionPorNombre (String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConvencion.eliminarConvencionPorNombre(pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarConvencionPorId (long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlConvencion.eliminarConvencionPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Convencion> darConvenciones()
	{
		return sqlConvencion.darConvenciones (pmf.getPersistenceManager());
	}
 

	public Convencion darConvencionesPorNombre (String nombre) 
	{
		return sqlConvencion.darConvencionesPorNombre (pmf.getPersistenceManager(), nombre) ;
	}
 
	public Convencion  darConvencionPorId (long id) 
	{
		return sqlConvencion. darConvencionPorId (pmf.getPersistenceManager(), id) ;
	}
	
	public Date darFechaInicioPorId (long id) 
	{
		return sqlConvencion. darFechaInicioPorId (pmf.getPersistenceManager(), id) ;
	}
	
	public Date darFechaFinPorId (long id) 
	{
		return sqlConvencion. darFechaFinPorId (pmf.getPersistenceManager(), id) ;
	}


	
	/* ****************************************************************
	 * 			Métodos para manejar los Usuarios
	 *****************************************************************/

	public Usuario adicionarUsuario (long tipoUsuario, String nombreEmpleado, long cedulaEmpleado, String correo, long idHotel, double gastos, int estadia) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario  (pm, tipoUsuario, nombreEmpleado, cedulaEmpleado, correo, idHotel, gastos, estadia) ;
            tx.commit();
            
            log.trace ("Inserción de Empleado: " + nombreEmpleado + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(cedulaEmpleado, nombreEmpleado, idHotel, tipoUsuario, correo, gastos, estadia) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarUsuarioPorNombre (String nombreEmpleado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuarioPorNombre(pm, nombreEmpleado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarUsuarioPorCedula ( long cedula)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuarioPorCedula(pm, cedula);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarEstadiaCliente ( int dur, long cedula)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUsuario.actualizarEstadiaCliente(pm,dur, cedula);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Usuario> darUsuarios()
	{
		return sqlUsuario.darUsuarios (pmf.getPersistenceManager());
	}
 

	public List<Usuario> darUsuariosPorNombre (String nombreEmpleado) 
	{
		return sqlUsuario.darUsuariosPorNombre (pmf.getPersistenceManager(), nombreEmpleado) ;
	}
	
	public List<Usuario> encontrarBuenosClientes () 
	{
		return sqlUsuario.encontrarBuenosClientes (pmf.getPersistenceManager()) ;
	}
	
	public List<Usuario> consumidoresHotelAndes (Date fechaI, Date fechaF, long id) 
	{
		return sqlUsuario.consumidoresHotelAndes (pmf.getPersistenceManager(), fechaI, fechaF, id) ;
	}
	
	public List<Usuario> darClientePorNombre (String nombreCliente) 
	{
		return sqlUsuario.darClientePorNombre (pmf.getPersistenceManager(), nombreCliente) ;
	}
 
	public Usuario  darUsuarioPorCedula (long cedulaEmpleado) 
	{
		return sqlUsuario. darUsuarioPorCedula (pmf.getPersistenceManager(), cedulaEmpleado)	;
	}
	


	/* ****************************************************************
	 * 			Métodos para manejar las FACTURAS
	 *****************************************************************/

	public Factura adicionarFactura (long habitacion, double total) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idFactura = nextval ();
            long tuplasInsertadas = sqlFactura.adicionarFactura (pm, idFactura, habitacion, total)  ;
            tx.commit();
            
            log.trace ("Inserción de Factura: " + idFactura + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Factura( idFactura,  habitacion,  total ) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarFacturaPorCliente ( long cedulaCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlFactura.eliminarFacturaPorCliente(pm, cedulaCliente);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarFacturaPorId  ( long idFactura)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlFactura.eliminarFacturaPorId(pm, idFactura);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Factura> darFacturas()
	{
		return sqlFactura.darFacturas (pmf.getPersistenceManager());
	}
 

	public List<Factura> darFacturaPorCliente (long cedulaCliente) 
	{
		return sqlFactura.darFacturaPorCliente (pmf.getPersistenceManager(), cedulaCliente) ;
	}
 
	public Factura  darFacturaPorId (long facturaId) 
	{
		return sqlFactura. darFacturaPorId (pmf.getPersistenceManager(), facturaId)	;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los GIMNASIO
	 *****************************************************************/

	public Gimnasio adicionarGimnasio ( long idServicio, int capacidadGimnasio, String nombreGimnasio, String horario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlGimnasio.adicionarGimnasio( pm, idServicio, id, capacidadGimnasio, nombreGimnasio, horario)   ;
            tx.commit();
            
            log.trace ("Inserción de Gimnasio: " + nombreGimnasio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Gimnasio (idServicio, id, capacidadGimnasio,
        			 nombreGimnasio,  horario)  ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarGimnasioPorNombre  (String nombreGimnasio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlGimnasio.eliminarGimnasioPorNombre(pm, nombreGimnasio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarGimnasioPorId  (long id)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlGimnasio.eliminarGimnasioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Gimnasio> darGimnasios()
	{
		return sqlGimnasio.darGimnasios (pmf.getPersistenceManager());
	}
 

	public List<Gimnasio> darGimnasioPorNombre (String nombreGimnasio) 
	{
		return sqlGimnasio.darGimnasioPorNombre (pmf.getPersistenceManager(), nombreGimnasio) ;
	}
 
	public Gimnasio  darGimnasioPorId (long idGimnasio) 
	{
		return sqlGimnasio. darGimnasioPorId (pmf.getPersistenceManager(), idGimnasio)	;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las HABITACION
	 *****************************************************************/

	public Habitacion adicionarHabitacion ( int capacidad, double costoNoche, long tipoHabitacion, long idHotel, String estado, long cedula) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long numHabitacion = nextval ();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion (pm, numHabitacion, capacidad, costoNoche, tipoHabitacion, idHotel, estado, cedula) ;
            tx.commit();
            
            log.trace ("Inserción de habitacion: " + numHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Habitacion( numHabitacion,  capacidad,  costoNoche,
        			 tipoHabitacion,  idHotel, estado, cedula);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarHabitacionPorTipo (long tipoHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.eliminarHabitacionPorTipo(pm, tipoHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarHabitacionPorId (long numHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.eliminarHabitacionPorId(pm, numHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarEstadoHabitacion (String estado, long numHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.actualizarEstadoHabitacion(pm, estado, numHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarClienteHabitacion (long cliente, long numHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.actualizarClienteHabitacion(pm, cliente, numHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Habitacion> darHabitaciones()
	{
		return sqlHabitacion.darHabitaciones (pmf.getPersistenceManager());
	}
 

	public List<Habitacion> darHabitacionPorTipo (long tipoHabitacion) 
	{
		return sqlHabitacion.darHabitacionPorTipo (pmf.getPersistenceManager(), tipoHabitacion) ;
	}
	
	public List<Habitacion> darHabitacionPorCliente (long cedula) 
	{
		return sqlHabitacion.darHabitacionPorCliente (pmf.getPersistenceManager(), cedula) ;
	}
 
 
	public Habitacion  darHabitacionPorId (long numHabitacion) 
	{
		return sqlHabitacion. darHabitacionPorId (pmf.getPersistenceManager(), numHabitacion)	;
	}	

	/* ****************************************************************
	 * 			Métodos para manejar los HOTEL
	 *****************************************************************/

	public Hotel adicionarHotel (String nombreHotel, int numeroEstrellas, String paisHotel,
			String ciudadHotel, String cadenaHotelera) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idHotel = nextval ();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idHotel, nombreHotel, numeroEstrellas, paisHotel, 
            										ciudadHotel, cadenaHotelera) ;
            tx.commit();
            
            log.trace ("Inserción de hotel: " + nombreHotel + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hotel( idHotel,  nombreHotel,  numeroEstrellas,
        			 paisHotel,  ciudadHotel,  cadenaHotelera) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarHotelPorNombre (String nombreHotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorNombre(pm, nombreHotel);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarHotelPorId (long hotelId)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorId(pm, hotelId);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Hotel> darHoteles()
	{
		return sqlHotel.darHoteles (pmf.getPersistenceManager());
	}
 

	public List<Hotel> darHotelPorNombre (String nombreHotel) 
	{
		return sqlHotel.darHotelPorNombre (pmf.getPersistenceManager(), nombreHotel) ;
	}
 
	public Hotel  darHotelPorId (long idHotel) 
	{
		return sqlHotel. darHotelPorId (pmf.getPersistenceManager(), idHotel)	;
	}		
	
	/* ****************************************************************
	 * 			Métodos para manejar INTERNET
	 *****************************************************************/

	public Internet adicionarInternet (double capacidadInternet, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlInternet.adicionarInternet ( pm,  capacidadInternet,  idServicio) ;
            tx.commit();
            
            log.trace ("Inserción de Internet: " + capacidadInternet + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Internet( capacidadInternet,  idServicio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarInternetPorId (long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlInternet.eliminarInternetPorId(pm, idServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Internet> darInternet()
	{
		return sqlInternet.darInternet (pmf.getPersistenceManager());
	}

 
	public Internet  darInternetPorId (long idServicio) 
	{
		return sqlInternet. darInternetPorId (pmf.getPersistenceManager(), idServicio)	;
	}		

	/* ****************************************************************
	 * 			Métodos para manejar LAVADOPLANCHADO
	 *****************************************************************/

	public LavadoPlanchado adicionarLavadoPlanchado (String tipoServicio, String horario, long idServicio)  
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlLavadoPlanchado.adicionarLavadoPlanchado ( pm, tipoServicio, horario, idServicio); 
            tx.commit();
            
            log.trace ("Inserción de Lavado/Planchado: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new LavadoPlanchado( tipoServicio, horario, idServicio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarLavadoPlanchadoPorTipo (String tipoServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlLavadoPlanchado.eliminarLavadoPlanchadoPorTipo(pm, tipoServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarLavadoPlanchadoPorId (long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlLavadoPlanchado.eliminarLavadoPlanchadoPorId(pm, idServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<LavadoPlanchado> darLavadoPlanchado()
	{
		return sqlLavadoPlanchado.darLavadoPlanchado (pmf.getPersistenceManager());
	}
 

	public List<LavadoPlanchado> darLavadoPlanchadoPorTipo (String tipoServicio) 
	{
		return sqlLavadoPlanchado.darLavadoPlanchadoPorTipo (pmf.getPersistenceManager(), tipoServicio) ;
	}
 
	public LavadoPlanchado  darLavadoPlanchadoPorId (long idServicio) 
	{
		return sqlLavadoPlanchado. darLavadoPlanchadoPorId (pmf.getPersistenceManager(), idServicio)	;
	}	

	/* ****************************************************************
	 * 			Métodos para manejar PISCINAA
	 *****************************************************************/

	public Piscina adicionarPiscina ( long idServicio,int capacidadPiscina, int profundidad, String horario, String nombrePiscina) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval();
            long tuplasInsertadas = sqlPiscina.adicionarPiscina  (pm, idServicio, id,  capacidadPiscina,
            		 profundidad,  horario,  nombrePiscina) ;
            tx.commit();
            
            log.trace ("Inserción de Piscina: " + nombrePiscina + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Piscina( idServicio,  id,  capacidadPiscina,
        			 nombrePiscina,  horario,  profundidad);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarPiscinaPorNombre (String nombrePiscina)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPiscina.eliminarPiscinaPorNombre(pm, nombrePiscina);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarPiscinaPorId (long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPiscina.eliminarPiscinaPorId(pm, idServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Piscina> darPiscinas()
	{
		return sqlPiscina.darPiscinas (pmf.getPersistenceManager());
	}
 

	public List<Piscina> darPiscinasPorNombre (String nombrePiscina) 
	{
		return sqlPiscina.darPiscinasPorNombre (pmf.getPersistenceManager(), nombrePiscina) ;
	}
 
	public Piscina  darPiscinaPorId (long idPiscina) 
	{
		return sqlPiscina. darPiscinaPorId (pmf.getPersistenceManager(), idPiscina)	;
	}	
	
	/* ****************************************************************
	 * 			Métodos para manejar PLANPAGO
	 *****************************************************************/

	public PlanDePago adicionarPlanDePago (String tipoPlan, String caracteristicas) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval();
            long tuplasInsertadas = sqlPlanDePago.adicionarPlanDePago ( pm, id, tipoPlan,  caracteristicas) ;
            tx.commit();
            
            log.trace ("Inserción de PlanDePago: " + tipoPlan + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PlanDePago( id, tipoPlan,  caracteristicas);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarPlanPorTipo (String tipoPlan)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPlanDePago.eliminarPlanPorTipo(pm, tipoPlan);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<PlanDePago> darPlanDePago()
	{
		return sqlPlanDePago.darPlanDePago (pmf.getPersistenceManager());
	}

 
	public PlanDePago  darPlanPorTipo (String tipoPlan) 
	{
		return sqlPlanDePago. darPlanPorTipo (pmf.getPersistenceManager(), tipoPlan)	;
	}
	
	public PlanDePago  darPlanPorId (long id) 
	{
		return sqlPlanDePago. darPlanPorId (pmf.getPersistenceManager(), id)	;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO
	 *****************************************************************/
	
	public Producto adicionarProducto (long idProducto, String nombreProducto, float costoProducto, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idProducto1 = nextval ();
            long tuplasInsertadas = sqlProducto.adicionarProducto (pm, idProducto1, nombreProducto, costoProducto, idServicio) ;
            tx.commit();
            
            log.trace ("Inserción de producto: " + nombreProducto + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Producto (idProducto, nombreProducto, costoProducto, idServicio) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarProductoPorNombre (String nombreProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProducto.eliminarProductoPorNombre(pm, nombreProducto);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarProductoPorId (long idProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProducto.eliminarProductoPorId(pm, idProducto);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Producto darProductoPorId (long idProducto) 
	{
		return sqlProducto.darProductoPorId (pmf.getPersistenceManager(), idProducto) ;
	}
	
	public List<Producto> darProductosPorNombre (String nombreProducto) 
	{
		return sqlProducto.darProductosPorNombre (pmf.getPersistenceManager(), nombreProducto) ;
	}
 
	
	public List<Producto> darProductos ()
	{
		return sqlProducto.darProductos (pmf.getPersistenceManager());
	}
 
	
	/* ****************************************************************
	 * 			Métodos para manejar RESERVAHABITACION
	 *****************************************************************/

	public ReservaHabitacion adicionarReservaHabitacion (  Date fechaIngreso, Date fechaSalida,int dur, int cantidadPersonas, long planPago, long cliente, long habitacion, float totalCompras) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idReserva = nextval ();
            long tuplasInsertadas = sqlReservaHabitacion.adicionarReservaHabitacion (pm,idReserva, fechaIngreso, fechaSalida, dur,cantidadPersonas, planPago, cliente, habitacion, totalCompras);
            tx.commit();
            
            log.trace ("Inserción de reserva habitacion: " + idReserva + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ReservaHabitacion (idReserva, fechaIngreso, fechaSalida, dur, cantidadPersonas, planPago, cliente, habitacion, totalCompras) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaHabitacionPorId (long idReserva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaHabitacion.eliminarReservaHabitacionPorId(pm, idReserva);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaHabitacionPorHabitacion (long habitacion, long cliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaHabitacion.eliminarReservaHabitacionPorHabitacion(pm, habitacion,cliente);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaHabitacionPorCliente (long cliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaHabitacion.eliminarReservaHabitacionPorCliente(pm, cliente);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<ReservaHabitacion> darReservaHabitacion ()
	{
		return sqlReservaHabitacion.darReservasHabitacion (pmf.getPersistenceManager());
	}
 

	public List<ReservaHabitacion> darReservaHabitacionPorCliente (long cliente) 
	{
		return sqlReservaHabitacion.darReservaHabitacionPorCliente (pmf.getPersistenceManager(), cliente) ;
	}
 
	public ReservaHabitacion  darReservaHabitacionPorId (long idReserva) 
	{
		return sqlReservaHabitacion.darReservaHabitacionPorId (pmf.getPersistenceManager(), idReserva) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar RESERVASERVICIO
	 *****************************************************************/
	
	public ReservaServicio adicionarReservaServicio (String tipoServicio, Date diaReserva, String horaReserva, int duracion, long cliente, long servicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlReservaServicio.adicionarReservaServicio(pm, id, tipoServicio, diaReserva, horaReserva, duracion, cliente, servicio);
            tx.commit();
            
            log.trace ("Inserción de reserva servicio: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ReservaServicio (id, tipoServicio, diaReserva, horaReserva, duracion, cliente, servicio) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaServicioPorId (long idReserva)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaServicio.eliminarReservaServicioPorId(pm, idReserva);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaServicioPorTipoServicio (long cliente, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaServicio.eliminarReservaServicioPorTipoServicio(pm, cliente, tipo);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaServicioPorCliente (long cliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReservaServicio.eliminarReservaServicioPorCliente(pm, cliente);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<ReservaServicio> darReservasServicios ()
	{
		return sqlReservaServicio.darReservasServicios (pmf.getPersistenceManager());
	}
 

	public List<ReservaServicio> darReservaServicioPorCliente (long cliente) 
	{
		return sqlReservaServicio.darReservaServicioPorCliente (pmf.getPersistenceManager(), cliente) ;
	}
 
	public ReservaServicio  darReservaServicioPorId (long idReserva) 
	{
		return sqlReservaServicio.darReservaServicioPorId (pmf.getPersistenceManager(), idReserva) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar RESTAURANTE
	 *****************************************************************/
	
	public Restaurante adicionarRestaurante (long idServicio, long idRestaurante, int capacidadRestaurante, String horario, String estiloRestaurante, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRestaurante1 = nextval ();
            long tuplasInsertadas = sqlRestaurante.adicionarRestaurante (pm, idServicio, idRestaurante1, capacidadRestaurante, horario, estiloRestaurante, nombre) ;
            tx.commit();
            
            log.trace ("Inserción de restaurante: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Restaurante (idServicio, idRestaurante1, capacidadRestaurante, horario, estiloRestaurante, nombre) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarRestaurantesPorNombre (String nombreRestaurante)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlRestaurante.eliminarRestaurantesPorNombre(pm, nombreRestaurante);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarRestaurantePorId (long idRestaurante)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlRestaurante.eliminarRestaurantePorId(pm, idRestaurante);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Restaurante> darRestaurantes ()
	{
		return sqlRestaurante.darRestaurantes (pmf.getPersistenceManager());
	}
 

	public List<Restaurante> darRestaurantesPorNombre (String nombreRestaurante) 
	{
		return sqlRestaurante.darRestaurantesPorNombre (pmf.getPersistenceManager(), nombreRestaurante) ;
	}
 
	public Restaurante  darRestaurantePorId (long idRestaurante) 
	{
		return sqlRestaurante.darRestaurantePorId (pmf.getPersistenceManager(), idRestaurante) ;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SALONCONFERENCIAS
	 *****************************************************************/
	
	public SalonConferencias adicionarSalonConferencias (long idServicio, int capacidadPersonas, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSalonConferencias.adicionarSalonConferencias (pm, id, idServicio, capacidadPersonas, nombre) ;
            tx.commit();
            
            log.trace ("Inserción de restaurante: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new SalonConferencias (id, idServicio, capacidadPersonas, nombre) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarSalonConferenciaPorNombre (String nombreSalonConferencias)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSalonConferencias.eliminarSalonConferenciaPorNombre(pm, nombreSalonConferencias);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarSalonConferenciasPorId (long idSalonConferencias)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSalonConferencias.eliminarSalonConferenciasPorId(pm, idSalonConferencias);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<SalonConferencias> darSalonesConferencias ()
	{
		return sqlSalonConferencias.darSalonesConferencias (pmf.getPersistenceManager());
	}
 

	public List<SalonConferencias> darSalonConferenciaPorNombre (String nombreSalonConferencias) 
	{
		return sqlSalonConferencias.darSalonConferenciaPorNombre (pmf.getPersistenceManager(), nombreSalonConferencias) ;
	}
 
	public SalonConferencias  darSalonConferenciasPorId (long idSalonConferencias) 
	{
		return sqlSalonConferencias.darSalonConferenciasPorId (pmf.getPersistenceManager(), idSalonConferencias) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SALONREUNIONES
	 *****************************************************************/
	
	public SalonReuniones adicionarSalonReuniones (long idServicio, int capacidadPersonas, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSalonReuniones.adicionarSalonReuniones (pm, id, idServicio, capacidadPersonas, nombre) ;
            tx.commit();
            
            log.trace ("Inserción de restaurante: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new SalonReuniones (id, idServicio, capacidadPersonas, nombre) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarSalonReunionesPorNombre (String nombreSalonReuniones)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSalonReuniones.eliminarSalonReunionesPorNombre(pm, nombreSalonReuniones);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarSalonReunionesPorId (long idSalonReuniones)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSalonReuniones.eliminarSalonReunionesPorId(pm, idSalonReuniones);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<SalonReuniones> darSalonesReuniones ()
	{
		return sqlSalonReuniones.darSalonesReuniones (pmf.getPersistenceManager());
	}
 

	public List<SalonReuniones> darSalonReunionesPorNombre (String nombreSalonReuniones) 
	{
		return sqlSalonReuniones.darSalonReunionesPorNombre (pmf.getPersistenceManager(), nombreSalonReuniones) ;
	}
 
	public SalonReuniones  darSalonReunionesPorId (long idSalonReuniones) 
	{
		return sqlSalonReuniones.darSalonReunionesPorId (pmf.getPersistenceManager(), idSalonReuniones) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/
	
	public Servicio adicionarServicio (String nombre, int demanda) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio (pm, id, nombre, demanda) ;
            tx.commit();
            
            log.trace ("Inserción de servicio: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio (id, nombre, demanda) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarServicioPorNombre (String nombreServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorNombre(pm, nombreServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarServicioPorId (long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorId(pm, idServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long actualizarDemandaPorId (long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.actualizarDemandaPorId(pm, idServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	
	public List<Servicio> darServicios ()
	{
		return sqlServicio.darServicios (pmf.getPersistenceManager());
	}

 

	public List<Servicio> darServiciosPorNombre (String nombreServicio) 
	{
		return sqlServicio.darServiciosPorNombre (pmf.getPersistenceManager(), nombreServicio) ;
	}
 
	public Servicio  darServicioPorId (long idServicio) 
	{
		return sqlServicio.darServicioPorId (pmf.getPersistenceManager(), idServicio) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SPA
	 *****************************************************************/
	
	public Spa adicionarSpa (long idServicio, int capacidadSpa, String horario, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSpa.adicionarSpa (pm, idServicio, id, capacidadSpa, horario, nombre) ;
            tx.commit();
            
            log.trace ("Inserción de spa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Spa (idServicio, id, capacidadSpa, horario, nombre) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarSpaPorNombre (String nomrbeSpa)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSpa.eliminarSpaPorNombre(pm, nomrbeSpa);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarSpaPorId (long idSpa)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSpa.eliminarSpaPorId(pm, idSpa);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Spa> darSpas ()
	{
		return sqlSpa.darSpas (pmf.getPersistenceManager());
	}
 

	public List<Spa> darSpasPorNombre (String nombreSpa) 
	{
		return sqlSpa.darSpasPorNombre (pmf.getPersistenceManager(), nombreSpa) ;
	}
 
	public Spa  darSpaPorId (long idSpa) 
	{
		return sqlSpa.darSpaPorId (pmf.getPersistenceManager(), idSpa) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SUPERMERCADO
	 *****************************************************************/
	
	public Supermercado adicionarSupermercado (long idServicio, String horario, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSupermercado.adicionarSupermercado (pm, idServicio, id, horario, nombre) ;
            tx.commit();
            
            log.trace ("Inserción de spa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Supermercado (idServicio, id, horario, nombre) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarSupermercadoPorNombre (String nombreSupermercado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSupermercado.eliminarSupermercadoPorNombre(pm, nombreSupermercado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarSupermercadoPorId (long idSupermercado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSupermercado.eliminarSupermercadoPorId(pm, idSupermercado);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Supermercado> darSupermercado ()
	{
		return sqlSupermercado.darSupermercado (pmf.getPersistenceManager());
	}
 

	public List<Supermercado> darSupermercadosPorNombre (String nombreSpa) 
	{
		return sqlSupermercado.darSupermercadosPorNombre (pmf.getPersistenceManager(), nombreSpa) ;
	}
 
	public Supermercado  darSupermercadoPorId (long idSupermercado) 
	{
		return sqlSupermercado.darSupermercadoPorId (pmf.getPersistenceManager(), idSupermercado) ;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar TIENDA
	 *****************************************************************/
	
	public Tienda adicionarTienda (long idServicio, String tipoTienda, String horario, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlTienda.adicionarTienda (pm, idServicio, id, tipoTienda, horario, nombre) ;
            tx.commit();
            
            log.trace ("Inserción de tienda: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Tienda (idServicio, id,tipoTienda, horario, nombre) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarTiendaPorNombre (String nombreTienda)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTienda.eliminarTiendaPorNombre(pm, nombreTienda);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarTiendaPorId (long idTienda)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTienda.eliminarTiendaPorId(pm, idTienda);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Tienda> darTiendas ()
	{
		return sqlTienda.darTiendas (pmf.getPersistenceManager());
	}
 

	public List<Tienda> darTiendaPorNombre (String nombreTienda) 
	{
		return sqlTienda.darTiendaPorNombre (pmf.getPersistenceManager(), nombreTienda) ;
	}
 
	public Tienda  darTiendaPorId (long idTienda) 
	{
		return sqlTienda.darTiendaPorId (pmf.getPersistenceManager(), idTienda) ;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar TIPOUSUARIO
	 *****************************************************************/

	public TipoUsuario adicionarTipoUsuario (String tipoEmpleo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idEmpleo = nextval();
            long tuplasInsertadas = sqlTipoUsuario.adicionarTipoUsuario ( pm,  idEmpleo,  tipoEmpleo) ;
            tx.commit();
            
            log.trace ("Inserción de TipoUsuario: " + tipoEmpleo + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoUsuario( idEmpleo,  tipoEmpleo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarTipoPorNombre (String tipoEmpleo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoUsuario.eliminarTipoPorNombre(pm, tipoEmpleo);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarTipoPorId (long idEmpleo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoUsuario.eliminarTipoPorId(pm, idEmpleo);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<TipoUsuario> darTiposUsuario()
	{
		return sqlTipoUsuario.darTiposUsuario (pmf.getPersistenceManager());
	}
 

	public TipoUsuario darTipoPorNombre (String tipoEmpleo) 
	{
		return sqlTipoUsuario.darTipoPorNombre (pmf.getPersistenceManager(), tipoEmpleo) ;
	}
 
	public TipoUsuario  darTipoPorId (long idEmpleo) 
	{
		return sqlTipoUsuario. darTipoPorId (pmf.getPersistenceManager(), idEmpleo);
	}	
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOHABITACION
	 *****************************************************************/

	public TipoHabitacion adicionarTipoHabitacion ( String tipoHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idTipoHabitacion = nextval ();
            long tuplasInsertadas = sqlTipoHabitacion.adicionarTipoHabitacion (pm,idTipoHabitacion, tipoHabitacion);
            tx.commit();
            
            log.trace ("Inserción de tipoHabitacion: " + tipoHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new TipoHabitacion (idTipoHabitacion, tipoHabitacion) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	public long eliminarTipoHabitacionPorTipo (String tipoHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoHabitacion.eliminarTipoHabitacionPorTipo(pm, tipoHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarTipoHabitacionPorId (long idTipoHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlTipoHabitacion.eliminarTipoHabitacionPorId(pm, idTipoHabitacion);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<TipoHabitacion> darTipoHabitaciones ()
	{
		return sqlTipoHabitacion.darTipoHabitaciones (pmf.getPersistenceManager());
	}
 

	public List<TipoHabitacion> darTipoHabitacionPorTipo (String tipoHabitacion) 
	{
		return sqlTipoHabitacion.darTipoHabitacionPorTipo (pmf.getPersistenceManager(), tipoHabitacion) ;
	}
	
	
 
	public TipoHabitacion  darTipoHabitacionPorId (long idTipoHabitacion) 
	{
		return sqlTipoHabitacion.darTipoHabitacionPorId (pmf.getPersistenceManager(), idTipoHabitacion) ;
	}
	
	//Otros
	
	public long [] limpiarHotelAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarHotelAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	
}