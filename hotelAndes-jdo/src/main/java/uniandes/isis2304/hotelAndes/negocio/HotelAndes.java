/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import oracle.sql.DATE;
import uniandes.isis2304.hotelAndes.persistencia.PersistenciaHotelAndes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class HotelAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(HotelAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	public PersistenciaHotelAndes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public HotelAndes ()
	{
		pp = PersistenciaHotelAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public HotelAndes (JsonObject tableConfig)
	{
		pp = PersistenciaHotelAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los BARES
	 *****************************************************************/

	public Bar adicionarBar (String nombreBar, long idServicio, int capacidadBar, String horario, String estiloBar) 
	{
        log.info ("Adicionando Bar: " + nombreBar);
        Bar bar = pp.adicionarBar ( nombreBar,  idServicio,  capacidadBar,  horario,  estiloBar);		
        log.info ("Adicionando Bar: " + bar);
        return bar;
	}
	
	
	public long eliminarBaresPorNombre(String nombreBar)
	{
		log.info ("Eliminando Bar por nombre: " + nombreBar);
        long resp = pp.eliminarBaresPorNombre( nombreBar);		
        log.info ("Eliminando Bar por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarBarPorId (long idBar)
	{
		log.info ("Eliminando Bar por id: " + idBar);
        long resp = pp.eliminarBarPorId ( idBar);
        log.info ("Eliminando Bar por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Bar> darBares ()
	{
		log.info ("Consultando Bares");
        List<Bar> bares = pp.darBares ();	
        log.info ("Consultando Bares : " + bares.size() + " existentes");
        return bares;
	}

	
	public List<VOBar> darVOBar ()
	{
		log.info ("Generando los VO de Bar");        
        List<VOBar> voBar = new LinkedList<VOBar> ();
        for (Bar b : pp.darBares ())
        {
        	voBar.add (b);
        }
        log.info ("Generando los VO de Bar: " + voBar.size() + " existentes");
        return voBar;
	}

	
	public Bar darBaresPorNombre (String nombreBar) 
	{
		log.info ("Buscando Bar por nombre: " + nombreBar);
		List<Bar> b = pp.darBaresPorNombre ( nombreBar) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los CONSUMOS
	 *****************************************************************/

	public Consumo adicionarConsumo (long idServicio, long habitacion, long producto, String estado, long cliente, Date fecha) 
	{
        log.info ("Adicionando Consumo: " + String.valueOf(habitacion));
        Consumo consumo = pp.adicionarConsumo ( idServicio, habitacion, producto, estado, cliente, fecha );		
        log.info ("Adicionando Consumo: " + consumo);
        return consumo;
	}
	
	
	public long eliminarConsumoPorHabitacion (long habitacion)
	{
		log.info ("Eliminando Consumo por habitacion: " + habitacion);
        long resp = pp.eliminarConsumoPorHabitacion ( habitacion);		
        log.info ("Eliminando Consumo por habitacion: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarConsumoPorId (long id)
	{
		log.info ("Eliminando Consumo por id: " + id);
        long resp = pp.eliminarConsumoPorId ( id);
        log.info ("Eliminando Consumo por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public long actualizarEstadoConsumo (String estado, long id)
	{
		log.info ("Eliminando Consumo por id: " + id);
        long resp = pp.actualizarEstadoConsumo (estado, id);
        log.info ("Eliminando Consumo por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Consumo> darConsumos ()
	{
		log.info ("Consultando Consumos");
        List<Consumo> Consumos = pp.darConsumos ();	
        log.info ("Consultando Consumos : " + Consumos.size() + " existentes");
        return Consumos;
	}

	
	public List<VOConsumo> darVOConsumo ()
	{
		log.info ("Generando los VO de Bar");        
        List<VOConsumo> voConsumo = new LinkedList<VOConsumo> ();
        for (Consumo b : pp.darConsumos ())
        {
        	voConsumo.add (b);
        }
        log.info ("Generando los VO de Consumo: " + voConsumo.size() + " existentes");
        return voConsumo;
	}
	
	public List<VOConsumo> darVOConsumoPorHabitacion(long habitacion)
	{
		log.info ("Generando los VO de Bar");        
        List<VOConsumo> voConsumo = new LinkedList<VOConsumo> ();
        for (Consumo b : pp.darConsumoPorHabitacion (habitacion))
        {
        	voConsumo.add (b);
        }
        log.info ("Generando los VO de Consumo: " + voConsumo.size() + " existentes");
        return voConsumo;
	}

	
	public List<Consumo> darConsumoPorHabitacion (long habitacion) 
	{
		log.info ("Buscando Consumo por habitacion: " + String.valueOf(habitacion));
		List<Consumo> b = pp.darConsumoPorHabitacion ( habitacion) ;
		return b;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar CONVENCION
	 *****************************************************************/
	
	public Convencion adicionarConvencion (String nombre, long cedula, Date fechaI, Date fechaF, int dur, String plan) 
	{
        log.info ("Adicionando convencion: " + nombre);
        Convencion convencion = pp.adicionarConvencion (nombre, cedula, fechaI, fechaF, dur, plan );		
        log.info ("Adicionando convencion: " + convencion);
        return convencion;
	}
	
	
	public long eliminarConvencionPorNombre(String nombre)
	{
		log.info ("Eliminando Convencion por nombre: " + nombre);
        long resp = pp.eliminarConvencionPorNombre( nombre);		
        log.info ("Eliminando Convencion por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarConvencionPorId (long id)
	{
		log.info ("Eliminando Convencion por id: " + id);
        long resp = pp.eliminarServicioPorId ( id);
        log.info ("Eliminando Convencion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Convencion> darConvenciones ()
	{
		log.info ("Consultando Convenciones");
        List<Convencion> convenciones = pp.darConvenciones ();	
        log.info ("Consultando Convenciones : " + convenciones.size() + " existentes");
        return convenciones;
	}

	
	public List<VOConvencion> darVOConvencion ()
	{
		log.info ("Generando los VO de convención");        
        List<VOConvencion> voConvencion = new LinkedList<VOConvencion> ();
        for (Convencion b : pp.darConvenciones ())
        {
        	voConvencion.add(b);
        }
        log.info ("Generando los VO de Servicio: " + voConvencion.size() + " existentes");
        return voConvencion;
	}

	
	public Convencion darConvencionesPorNombre (String nombre) 
	{
		log.info ("Buscando Convencion por nombre: " + nombre);
		Convencion b = pp.darConvencionesPorNombre(nombre) ;
		return b;
	}
	
	public Convencion darConvencionPorId( long id)
	{
		log.info("Buscando convención por id");
		Convencion b = pp.darConvencionPorId(id) ;
		return b;
	}
	
	public Date darFechaInicioPorId( long id)
	{
		log.info("Buscando fecha incio de la convención por id");
		Date b = pp.darFechaInicioPorId(id) ;
		return b;
	}
	
	public Date darFechaFinPorId( long id)
	{
		log.info("Buscando fecha fin de la convencion por id");
		Date b = pp.darFechaFinPorId(id) ;
		return b;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Usuarios
	 *****************************************************************/

	public Usuario adicionarUsuario ( long tipoUsuario, String nombreEmpleado, long cedulaEmpleado, String correo, long idHotel, double gastos, int estadia) 
	{
		log.info ("Adicionando usuario " + nombreEmpleado);
		Usuario empleado = pp. adicionarUsuario ( tipoUsuario, nombreEmpleado,cedulaEmpleado ,correo, idHotel, gastos, estadia)  ;
        log.info ("Adicionando usuario: " + empleado);
        return empleado;
	}
	
	
	public long eliminarUsuarioPorNombre (String nombreEmpleado)
	{
        log.info ("Eliminando usuario por nombre: " + nombreEmpleado);
        long resp = pp.eliminarUsuarioPorNombre ( nombreEmpleado);
        log.info ("Eliminando usuario por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarUsuarioPorCedula ( long cedula)
	{
        log.info ("Eliminando usuario por id: " + cedula);
        long resp = pp.eliminarUsuarioPorCedula ( cedula);
        log.info ("Eliminando usuario por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public long actualizarEstadiaCliente ( int dur, long cedula)
	{
        log.info ("Actualizando estadia usuario por id: " + cedula);
        long resp = pp.actualizarEstadiaCliente ( dur, cedula);
        log.info ("Actualizando estadia usuario por id: " + resp );
        return resp;
	}
	

	public List<Usuario> darUsuarios ()
	{
        log.info ("Consultando usuarios");
        List<Usuario> usuarios = pp.darUsuarios ();	
        log.info ("Consultando usuarios: " + usuarios.size() + " usuarios existentes");
        return usuarios;
	}


	public List<VOUsuario> darVOUsuarios ()
	{
		log.info ("Generando los VO de los usuarios");       
        List<VOUsuario> voUsuario = new LinkedList<VOUsuario> ();
        for (VOUsuario em : pp.darUsuarios ())
        {
        	voUsuario.add (em);
        }
        log.info ("Generando los VO de los usuarios: " + voUsuario.size() + " existentes");
        return voUsuario;
	}
	
	public List<VOUsuario> darVOBuenosClientes ()
	{
		log.info ("Generando los VO de los usuarios");       
        List<VOUsuario> voUsuario = new LinkedList<VOUsuario> ();
        for (VOUsuario em : pp.encontrarBuenosClientes ())
        {
        	voUsuario.add (em);
        }
        log.info ("Generando los VO de los usuarios: " + voUsuario.size() + " existentes");
        return voUsuario;
	}
	
	public List<VOUsuario> darVOConsumidoresHotelAndes (Date fechaI, Date fechaF, long id)
	{
		log.info ("Generando los VO de los usuarios");       
        List<VOUsuario> voUsuario = new LinkedList<VOUsuario> ();
        for (VOUsuario em : pp.consumidoresHotelAndes (fechaI, fechaF, id))
        {
        	voUsuario.add (em);
        }
        log.info ("Generando los VO de los usuarios: " + voUsuario.size() + " existentes");
        return voUsuario;
	}
	
	


	public Usuario darUsuariosPorNombre (String nombreEmpleado)  
	{
		log.info ("Buscando Usuario por nombre: " + nombreEmpleado);
		List<Usuario> c = pp.darUsuariosPorNombre(nombreEmpleado)  ;
		return !c.isEmpty () ? c.get (0) : null;
	}
	
	public Usuario darClientePorNombre (String nombreCliente)  
	{
		log.info ("Buscando Cliente por nombre: " + nombreCliente);
		List<Usuario> c = pp.darClientePorNombre(nombreCliente)  ;
		return !c.isEmpty () ? c.get (0) : null;
	}


	/* ****************************************************************
	 * 			Métodos para manejar las FACTURAS
	 *****************************************************************/
	public Factura adicionarFactura (long habitacion, double total) 
	{
		log.info ("Adicionando factura " );
		Factura factura = pp. adicionarFactura ( habitacion,  total) ;
        log.info ("Adicionando factura: " + factura);
        return factura;
	}
	
	
	public long eliminarFacturaPorCliente ( long cedulaCliente)
	{
        log.info ("Eliminando factura por cliente" + cedulaCliente);
        long resp = pp.eliminarFacturaPorCliente ( cedulaCliente);
        log.info ("Eliminando factura por cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarFacturaPorId  ( long idFactura)
	{
        log.info ("Eliminando factura por id: " + idFactura);
        long resp = pp.eliminarFacturaPorId  ( idFactura);
        log.info ("Eliminando factura por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Factura> darFacturas ()
	{
        log.info ("Consultando facturas");
        List<Factura> facturas = pp.darFacturas ();	
        log.info ("Consultando facturas: " + facturas.size() + " facturas existentes");
        return facturas;
	}


	public List<VOFactura> darVOFactura ()
	{
		log.info ("Generando los VO de los empleados");       
        List<VOFactura> voFactura = new LinkedList<VOFactura> ();
        for (VOFactura f : pp.darFacturas ())
        {
        	voFactura.add (f);
        }
        log.info ("Generando los VO de los empleados: " + voFactura.size() + " existentes");
        return voFactura;
	}


	public Factura darFacturaPorCliente (long cedulaCliente)
	{
		log.info ("Buscando factura por cliente: " + cedulaCliente);
		List<Factura> c = pp.darFacturaPorCliente ( cedulaCliente) ;
		return !c.isEmpty () ? c.get (0) : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar GIMNASIO
	 *****************************************************************/

	public Gimnasio adicionarGimnasio ( long idServicio, int capacidadGimnasio, String nombreGimnasio, String horario) 
	{
		log.info ("Adicionando gimnasio " );
		Gimnasio gimnasio = pp.adicionarGimnasio ( idServicio, capacidadGimnasio, nombreGimnasio, horario) ;
        log.info ("Adicionando gimnasio: " + gimnasio);
        return gimnasio;
	}
	
	
	public long eliminarGimnasioPorNombre  (String nombreGimnasio)
	{
        log.info ("Eliminando gimnasio por cliente" + nombreGimnasio);
        long resp = pp.eliminarGimnasioPorNombre ( nombreGimnasio);
        log.info ("Eliminando gimnasio por cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarGimnasioPorId  (long id)
	{
        log.info ("Eliminando gimnasio por id: " + id);
        long resp = pp.eliminarGimnasioPorId( id);
        log.info ("Eliminando gimnasio por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Gimnasio> darGimnasios ()
	{
        log.info ("Consultando facturas");
        List<Gimnasio> gimnasios = pp.darGimnasios ();	
        log.info ("Consultando facturas: " + gimnasios.size() + " gimnasios existentes");
        return gimnasios;
	}


	public List<VOGimnasio> darVOGimnasio ()
	{
		log.info ("Generando los VO de los empleados");       
        List<VOGimnasio> voGimnasio = new LinkedList<VOGimnasio> ();
        for (VOGimnasio g : pp.darGimnasios ())
        {
        	voGimnasio.add (g);
        }
        log.info ("Generando los VO de los gimnasios: " + voGimnasio.size() + " existentes");
        return voGimnasio;
	}


	public Gimnasio darGimnasioPorNombre (String nombreGimnasio) 
	{
		log.info ("Buscando gimnasio por nombre: " + nombreGimnasio);
		List<Gimnasio> c = pp.darGimnasioPorNombre ( nombreGimnasio) ;
		return !c.isEmpty () ? c.get (0) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION
	 *****************************************************************/
	
	public Habitacion adicionarHabitacion ( int capacidad, double costoNoche, long tipoHabitacion, long idHotel, String estado, long cedula) 
	{
		log.info ("Adicionando habitacion " );
		Habitacion habitacion = pp.adicionarHabitacion ( capacidad, costoNoche, tipoHabitacion,  idHotel, estado, cedula) ;
        log.info ("Adicionando habitacion: " + habitacion);
        return habitacion;
	}
	
	
	public long eliminarHabitacionPorTipo (long tipoHabitacion)
	{
        log.info ("Eliminando habitacion por tipo" + tipoHabitacion);
        long resp = pp.eliminarHabitacionPorTipo ( tipoHabitacion);
        log.info ("Eliminando habitacion por tipo: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarHabitacionPorId (long numHabitacion)
	{
        log.info ("Eliminando habitacion por id: " + numHabitacion);
        long resp = pp.eliminarGimnasioPorId( numHabitacion);
        log.info ("Eliminando habitacion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public long actualizarEstadoHabitacion (String estado, long numHabitacion)
	{
        log.info ("Actualizando habitacion por estado: " + numHabitacion);
        long resp = pp.actualizarEstadoHabitacion(estado, numHabitacion);
        log.info ("Actualizando habitacion por estado: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public long actualizarClienteHabitacion (long cliente, long numHabitacion)
	{
        log.info ("Actualizando habitacion por cliente: " + numHabitacion);
        long resp = pp.actualizarClienteHabitacion(cliente, numHabitacion);
        log.info ("Actualizando habitacion por cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Habitacion> darHabitaciones()
	{
        log.info ("Consultando habitaciones");
        List<Habitacion> habitaciones = pp.darHabitaciones ();	
        log.info ("Consultando habitaciones: " + habitaciones.size() + " habitaciones existentes");
        return habitaciones;
	}


	public List<VOHabitacion> darVOHabitacion ()
	{
		log.info ("Generando los VO de las habitaciones");       
        List<VOHabitacion> voHabitacion = new LinkedList<VOHabitacion> ();
        for (VOHabitacion g : pp.darHabitaciones ())
        {
        	voHabitacion.add (g);
        }
        log.info ("Generando los VO de las habitaciones: " + voHabitacion.size() + " existentes");
        return voHabitacion;
	}
	
	public List<VOHabitacion> darVOHabitacionPorTipo (long tipoHabitacion)
	{
		log.info ("Generando los VO de las habitaciones de tipo ");       
        List<VOHabitacion> voHabitacion = new LinkedList<VOHabitacion> ();
        for (VOHabitacion g : pp.darHabitacionPorTipo (tipoHabitacion))
        {
        	voHabitacion.add (g);
        }
        log.info ("Generando los VO de las habitaciones: " + voHabitacion.size() + " existentes");
        return voHabitacion;
	}
	
	public List<VOHabitacion> darVOHabitacionPorCliente (long cedula)
	{
		log.info ("Generando los VO de las habitaciones de cliente ");       
        List<VOHabitacion> voHabitacion = new LinkedList<VOHabitacion> ();
        for (VOHabitacion g : pp.darHabitacionPorCliente (cedula))
        {
        	voHabitacion.add (g);
        }
        log.info ("Generando los VO de las habitaciones: " + voHabitacion.size() + " existentes");
        return voHabitacion;
	}



	public Habitacion darHabitacionPorTipo (long tipoHabitacion) 
	{
		log.info ("Buscando Habitacion por tipo: " + tipoHabitacion);
		List<Habitacion> c = pp.darHabitacionPorTipo ( tipoHabitacion) ;
		log.info ("Consultando habitaciones: " + c.size() + " habitaciones existentes");
		return !c.isEmpty () ? c.get (0) : null;
	}
	
	public Habitacion darHabitacionPorId (long num) 
	{
		log.info ("Buscando Habitacion por id: " + num);
		Habitacion c = pp.darHabitacionPorId ( num) ;
		log.info ("Buscando Habitacion por id: " + num);
		return c;
	}

	/* ****************************************************************
	 * 			Métodos para manejar HOTEL
	 *****************************************************************/

	public Hotel adicionarHotel (String nombreHotel, int numeroEstrellas, String paisHotel,
			String ciudadHotel, String cadenaHotelera)
	{
		log.info ("Adicionando hotel " );
		Hotel hotel = pp.adicionarHotel ( nombreHotel,  numeroEstrellas,  paisHotel,
				 ciudadHotel,  cadenaHotelera);
        log.info ("Adicionando hotel: " + hotel);
        return hotel;
	}
	
	
	public long eliminarHotelPorNombre (String nombreHotel)
	{
        log.info ("Eliminando hotel por nombre" + nombreHotel);
        long resp = pp.eliminarHotelPorNombre ( nombreHotel);
        log.info ("Eliminando hotel por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarHotelPorId (long hotelId)
	{
        log.info ("Eliminando hotel por id: " + hotelId);
        long resp = pp.eliminarHotelPorId( hotelId);
        log.info ("Eliminando hotel por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Hotel> darHoteles()
	{
        log.info ("Consultando hoteles");
        List<Hotel> hoteles = pp.darHoteles ();	
        log.info ("Consultando hoteles: " + hoteles.size() + " hoteles existentes");
        return hoteles;
	}


	public List<VOHotel> darVOHotel ()
	{
		log.info ("Generando los VO de los hoteles");       
        List<VOHotel> voHotel = new LinkedList<VOHotel> ();
        for (VOHotel g : pp.darHoteles ())
        {
        	voHotel.add (g);
        }
        log.info ("Generando los VO de las hoteles: " + voHotel.size() + " existentes");
        return voHotel;
	}


	public Hotel  darHotelPorNombre (String nombreHotel) 
	{
		log.info ("Buscando Hotel por nombreHotel: " + nombreHotel);
		List<Hotel> c = pp.darHotelPorNombre ( nombreHotel) ;
		return !c.isEmpty () ? c.get (0) : null;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar INTERNET
	 *****************************************************************/

	public Internet adicionarInternet (double capacidadInternet, long idServicio) 
	{
		log.info ("Adicionando Internet " );
		Internet internet = pp.adicionarInternet ( capacidadInternet,  idServicio);
        log.info ("Adicionando Internet: " + internet);
        return internet;
	}
	

	public long eliminarInternetPorId (long idServicio)
	{
        log.info ("Eliminando Internet por id: " + idServicio);
        long resp = pp.eliminarInternetPorId( idServicio);
        log.info ("Eliminando Internet por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Internet> darInternet()
	{
        log.info ("Consultando Internet");
        List<Internet> internet = pp.darInternet ();	
        log.info ("Consultando Internet: " + internet.size() + " internet existentes");
        return internet;
	}


	public List<VOInternet> darVOInternet ()
	{
		log.info ("Generando los VO del internet");       
        List<VOInternet> voInternet = new LinkedList<VOInternet> ();
        for (VOInternet g : pp.darInternet ())
        {
        	voInternet.add (g);
        }
        log.info ("Generando los VO de las hoteles: " + voInternet.size() + " existentes");
        return voInternet;
	}
	
	public Internet  darInternetPorId (long idServicio)
	{
		log.info ("Buscando Internet por id: " + idServicio);
		Internet c = pp.darInternetPorId ( idServicio) ;
		return c;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar LAVADOPLANCHADO
	 *****************************************************************/

	public LavadoPlanchado adicionarLavadoPlanchado (String tipoServicio, String horario, long idServicio)  
	{
		log.info ("Adicionando LavadoPlanchado " );
		LavadoPlanchado lavadoPlanchado = pp.adicionarLavadoPlanchado (tipoServicio,  horario,  idServicio)  ;
        log.info ("Adicionando LavadoPlanchado: " + lavadoPlanchado);
        return lavadoPlanchado;
	}
	
	
	public long eliminarLavadoPlanchadoPorTipo (String tipoServicio)
	{
        log.info ("Eliminando LavadoPlanchado por tipoServicio" + tipoServicio);
        long resp = pp.eliminarLavadoPlanchadoPorTipo ( tipoServicio);
        log.info ("Eliminando LavadoPlanchado por tipoServicio: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarLavadoPlanchadoPorId (long idServicio)
	{
        log.info ("Eliminando LavadoPlanchado por id: " + idServicio);
        long resp = pp.eliminarLavadoPlanchadoPorId( idServicio);
        log.info ("Eliminando LavadoPlanchado por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<LavadoPlanchado> darLavadoPlanchado()
	{
        log.info ("Consultando LavadoPlanchado");
        List<LavadoPlanchado> lavadoPlanchado = pp.darLavadoPlanchado ();	
        log.info ("Consultando LavadoPlanchado: " + lavadoPlanchado.size() + " lavadoPlanchado existentes");
        return lavadoPlanchado;
	}


	public List<VOLavadoPlanchado> darVOLavadoPlanchado ()
	{
		log.info ("Generando los VO de los lavadoPlanchado");       
        List<VOLavadoPlanchado> voLavadoPlanchado = new LinkedList<VOLavadoPlanchado> ();
        for (VOLavadoPlanchado g : pp.darLavadoPlanchado ())
        {
        	voLavadoPlanchado.add (g);
        }
        log.info ("Generando los VO de los lavadoPlanchado: " + voLavadoPlanchado.size() + " existentes");
        return voLavadoPlanchado;
	}


	public LavadoPlanchado darLavadoPlanchadoPorTipo (String tipoServicio) 
	{
		log.info ("Buscando Hotel por nombreHotel: " + tipoServicio);
		List<LavadoPlanchado> c = pp.darLavadoPlanchadoPorTipo ( tipoServicio)  ;
		return !c.isEmpty () ? c.get (0) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PISCINA
	 *****************************************************************/

	public Piscina adicionarPiscina ( long idServicio,int capacidadPiscina, int profundidad, String horario, String nombrePiscina) 
	{
		log.info ("Adicionando Piscina " );
		Piscina piscina = pp.adicionarPiscina ( idServicio, capacidadPiscina, profundidad, horario, nombrePiscina) ;
        log.info ("Adicionando Piscina: " + piscina);
        return piscina;
	}
	
	
	public long eliminarPiscinaPorNombre (String nombrePiscina)
	{
        log.info ("Eliminando Piscina por nombrePiscina" + nombrePiscina);
        long resp = pp.eliminarPiscinaPorNombre ( nombrePiscina);
        log.info ("Eliminando Piscina por nombrePiscina: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarPiscinaPorId (long idServicio)
	{
        log.info ("Eliminando Piscina por id: " + idServicio);
        long resp = pp.eliminarPiscinaPorId( idServicio);
        log.info ("Eliminando Piscina por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Piscina> darPiscinas()
	{
        log.info ("Consultando Piscina");
        List<Piscina> piscina = pp.darPiscinas ();	
        log.info ("Consultando Piscina: " + piscina.size() + " piscina existentes");
        return piscina;
	}


	public List<VOPiscina> darVOPiscina ()
	{
		log.info ("Generando los VO de las piscinas");       
        List<VOPiscina> voPiscina = new LinkedList<VOPiscina> ();
        for (VOPiscina g : pp.darPiscinas ())
        {
        	voPiscina.add (g);
        }
        log.info ("Generando los VO de las piscinas: " + voPiscina.size() + " existentes");
        return voPiscina;
	}


	public Piscina darPiscinasPorNombre (String nombrePiscina) 
	{
		log.info ("Buscando Piscina por nombrePiscina: " + nombrePiscina);
		List<Piscina> c = pp.darPiscinasPorNombre ( nombrePiscina)  ;
		return !c.isEmpty () ? c.get (0) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar PLANDEPAGO
	 *****************************************************************/

	public PlanDePago adicionarPlanDePago (String tipoPlan, String caracteristicas) 
	{
		log.info ("Adicionando PlanDePago " );
		PlanDePago planDePago = pp.adicionarPlanDePago ( tipoPlan,  caracteristicas) ;
        log.info ("Adicionando PlanDePago: " + planDePago);
        return planDePago;
	}
	
	
	public long eliminarPlanPorTipo (String tipoPlan)
	{
        log.info ("Eliminando PlanDePago por tipoPlan" + tipoPlan);
        long resp = pp.eliminarPlanPorTipo ( tipoPlan);
        log.info ("Eliminando PlanDePago por tipoPlan: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	

	public List<PlanDePago> darPlanDePago()
	{
        log.info ("Consultando PlanDePago");
        List<PlanDePago> planDePago = pp.darPlanDePago ();	
        log.info ("Consultando PlanDePago: " + planDePago.size() + " planDePago existentes");
        return planDePago;
	}


	public List<VOPlanDePago> darVOPlanDePago ()
	{
		log.info ("Generando los VO de planDePago");       
        List<VOPlanDePago> voPlanDePago = new LinkedList<VOPlanDePago> ();
        for (VOPlanDePago g : pp.darPlanDePago ())
        {
        	voPlanDePago.add (g);
        }
        log.info ("Generando los VO de los planDePago: " + voPlanDePago.size() + " existentes");
        return voPlanDePago;
	}


	public PlanDePago  darPlanPorTipo (String tipoPlan) 
	{
		log.info ("Buscando planDePago por tipoPlan: " + tipoPlan);
		PlanDePago c = pp.darPlanPorTipo ( tipoPlan)  ;
		return c;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar PRODUCTO
	 *****************************************************************/
	
	public Producto adicionarProducto (long idProducto, String nombreProducto, float costoProducto, long idServicio) 
	{
		log.info ("Adicionando Producto " );
		Producto producto = pp.adicionarProducto (idProducto, nombreProducto, costoProducto, idServicio) ;
        log.info ("Adicionando Producto: " + producto);
        return producto;
	}
	
	
	public long eliminarProductoPorNombre (String nombreProducto)
	{
        log.info ("Eliminando Producto por nombre" + nombreProducto);
        long resp = pp.eliminarProductoPorNombre ( nombreProducto);
        log.info ("Eliminando Producto por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarProductoPorId (long idProducto)
	{
        log.info ("Eliminando Producto por id" + idProducto);
        long resp = pp.eliminarProductoPorId ( idProducto);
        log.info ("Eliminando Producto por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Producto> darProductos()
	{
        log.info ("Consultando Producto");
        List<Producto> producto = pp.darProductos ();	
        log.info ("Consultando producto: " + producto.size() + " producto existentes");
        return producto;
	}


	public List<VOProducto> darVOProducto ()
	{
		log.info ("Generando los VO de producto");       
        List<VOProducto> voProducto = new LinkedList<VOProducto> ();
        for (VOProducto g : pp.darProductos ())
        {
        	voProducto.add (g);
        }
        log.info ("Generando los VO de los productos: " + voProducto.size() + " existentes");
        return voProducto;
	}


	public Producto  darProductoPorId (long idProducto) 
	{
		log.info ("Buscando Producto por idProducto: " + idProducto);
		Producto c = pp.darProductoPorId ( idProducto)  ;
		return c;
	}
	
	public Producto darProductoPorNombre (String nombreProducto) 
	{
		log.info ("Buscando producto por nombreProducto: " + nombreProducto);
		List<Producto> c = pp.darProductosPorNombre ( nombreProducto)  ;
		return !c.isEmpty () ? c.get (0) : null;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar RESERVAHABITACION
	 *****************************************************************/
	
	public ReservaHabitacion adicionarReservaHabitacion ( Date fechaIngreso, Date fechaSalida, int dur, int cantidadPersonas, String planPago, long cliente, long habitacion, float totalCompras) 
	{
		log.info ("Adicionando reserva habitacion " );
		ReservaHabitacion reservaHabitacion = pp.adicionarReservaHabitacion ( fechaIngreso, fechaSalida,dur, cantidadPersonas, planPago, cliente, habitacion, totalCompras);
        log.info ("Adicionando reserva habitacion: " + reservaHabitacion);
        return reservaHabitacion;
	}
	
	
	public long eliminarReservaHabitacionPorCliente (long cliente)
	{
        log.info ("Eliminando reserva habitacion por cliente" + cliente);
        long resp = pp.eliminarReservaHabitacionPorCliente (cliente);
        log.info ("Eliminando reserva habitacion por cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarReservaHabitacionPorHabitacion (long habitacion, long cliente)
	{
        log.info ("Eliminando reserva habitacion por cliente" + habitacion);
        long resp = pp.eliminarReservaHabitacionPorHabitacion (habitacion,cliente);
        log.info ("Eliminando reserva habitacion por cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarReservaHabitacionPorId (long idReserva)
	{
        log.info ("Eliminando reserva habitacion por id" + idReserva);
        long resp = pp.eliminarReservaHabitacionPorId ( idReserva);
        log.info ("Eliminando reserva habitacion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<ReservaHabitacion> darReservaHabitacion()
	{
        log.info ("Consultando reservaHabitacion");
        List<ReservaHabitacion> reservaHabitacion = pp.darReservaHabitacion ();	
        log.info ("Consultando reservaHabitacion: " + reservaHabitacion.size() + " reservaHabitacion existentes");
        return reservaHabitacion;
	}


	public List<VOReservaHabitacion> darVOReservaHabitacion ()
	{
		log.info ("Generando los VO de la reserva habitacion");       
        List<VOReservaHabitacion> voReservaHabitacion = new LinkedList<VOReservaHabitacion> ();
        for (VOReservaHabitacion g : pp.darReservaHabitacion ())
        {
        	voReservaHabitacion.add (g);
        }
        log.info ("Generando los VO de las reserva habitacion: " + voReservaHabitacion.size() + " existentes");
        return voReservaHabitacion;
	}


	public ReservaHabitacion  darReservaHabitacionPorId (long idReserva) 
	{
		log.info ("Buscando ReservaHabitacion por idReserva: " + idReserva);
		ReservaHabitacion c = pp.darReservaHabitacionPorId ( idReserva)  ;
		return c;
	}
	
	public ReservaHabitacion darReservaHabitacionPorCliente (long cedula) 
	{
		log.info ("Buscando Reserva por Cliente: " + cedula);
		List<ReservaHabitacion> c = pp.darReservaHabitacionPorCliente( cedula)  ;
		return !c.isEmpty () ? c.get (0) : null;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar RESERVASERVICIO
	 *****************************************************************/
	
	public ReservaServicio adicionarReservaServicio (String tipoServicio, Date diaReserva, String horaReserva, int duracion, long cliente, long servicio) 
	{
		log.info ("Adicionando reserva servicio " );
		ReservaServicio reservaServicio = pp.adicionarReservaServicio (tipoServicio, diaReserva, horaReserva, duracion, cliente, servicio);
        log.info ("Adicionando reserva servicio: " + reservaServicio);
        return reservaServicio;
	}
	
	
	public long eliminarReservaServicioPorCliente (long cliente)
	{
        log.info ("Eliminando reserva servicio por cliente" + cliente);
        long resp = pp.eliminarReservaServicioPorCliente (cliente);
        log.info ("Eliminando reserva servicio por cliente: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarReservaServicioPorId (long idReserva)
	{
        log.info ("Eliminando reserva servicio por id" + idReserva);
        long resp = pp.eliminarReservaServicioPorId ( idReserva);
        log.info ("Eliminando reserva servicio por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public long eliminarReservaServicioPorTipoServicio (long cliente, String tipo)
	{
        log.info ("Eliminando reserva servicio por tipo" + tipo);
        long resp = pp.eliminarReservaServicioPorTipoServicio (cliente,tipo);
        log.info ("Eliminando reserva servicio por tipo: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<ReservaServicio> darReservasServicios()
	{
        log.info ("Consultando ReservasServicios");
        List<ReservaServicio> reservasServicios = pp.darReservasServicios ();	
        log.info ("Consultando reservasServicios: " + reservasServicios.size() + " reservasServicios existentes");
        return reservasServicios;
	}


	public List<VOReservaServicio> darVOReservaServicio ()
	{
		log.info ("Generando los VO de la reserva servicio");       
        List<VOReservaServicio> voReservaServicio = new LinkedList<VOReservaServicio> ();
        for (VOReservaServicio g : pp.darReservasServicios ())
        {
        	voReservaServicio.add (g);
        }
        log.info ("Generando los VO de las reserva servicio: " + voReservaServicio.size() + " existentes");
        return voReservaServicio;
	}


	public ReservaServicio  darReservaServicioPorId (long idReserva) 
	{
		log.info ("Buscando ReservaServicio por idReserva: " + idReserva);
		ReservaServicio c = pp.darReservaServicioPorId ( idReserva)  ;
		return c;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar RESTAURANTE
	 *****************************************************************/
	
	public Restaurante adicionarRestaurante (long idServicio, long id, int capacidadRestaurante, String horario, String estiloRestaurante, String nombre) 
	{
        log.info ("Adicionando Restaurante: " + nombre);
        Restaurante restaurante = pp.adicionarRestaurante (idServicio, id, capacidadRestaurante, horario, estiloRestaurante, nombre);		
        log.info ("Adicionando Restaurante: " + restaurante);
        return restaurante;
	}
	
	
	public long eliminarRestaurantesPorNombre(String nombreRestaurante)
	{
		log.info ("Eliminando restaurante por nombre: " + nombreRestaurante);
        long resp = pp.eliminarRestaurantesPorNombre( nombreRestaurante);		
        log.info ("Eliminando restaurante por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarRestaurantePorId (long idRestaurante)
	{
		log.info ("Eliminando restaurante por id: " + idRestaurante);
        long resp = pp.eliminarRestaurantePorId ( idRestaurante);
        log.info ("Eliminando restaurante por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Restaurante> darRestaurantes ()
	{
		log.info ("Consultando Restaurante");
        List<Restaurante> restaurante = pp.darRestaurantes ();	
        log.info ("Consultando Restaurante : " + restaurante.size() + " existentes");
        return restaurante;
	}

	
	public List<VORestaurante> darVORestaurante ()
	{
		log.info ("Generando los VO de Restaurante");        
        List<VORestaurante> voRestaurante = new LinkedList<VORestaurante> ();
        for (Restaurante b : pp.darRestaurantes ())
        {
        	voRestaurante.add (b);
        }
        log.info ("Generando los VO de Restaurante: " + voRestaurante.size() + " existentes");
        return voRestaurante;
	}

	
	public Restaurante darRestaurantesPorNombre (String nombreRestaurante) 
	{
		log.info ("Buscando Restaurante por nombre: " + nombreRestaurante);
		List<Restaurante> b = pp.darRestaurantesPorNombre ( nombreRestaurante) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	public Restaurante  darRestaurantePorId (long idRestaurante) 
	{
		log.info ("Buscando restaurante por id: " + idRestaurante);
		Restaurante c = pp.darRestaurantePorId ( idRestaurante)  ;
		return c;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SALONCONFERENCIAS
	 *****************************************************************/
	
	public SalonConferencias adicionarSalonConferencias (long idServicio, int capacidadPersonas, String nombre) 
	{
        log.info ("Adicionando SalonConferencias: " + nombre);
        SalonConferencias salonConferencias = pp.adicionarSalonConferencias (idServicio, capacidadPersonas, nombre);		
        log.info ("Adicionando salonConferencias: " + salonConferencias);
        return salonConferencias;
	}
	
	
	public long eliminarSalonConferenciaPorNombre(String nombreSalonConferencias)
	{
		log.info ("Eliminando Salon Conferencias por nombre: " + nombreSalonConferencias);
        long resp = pp.eliminarSalonConferenciaPorNombre( nombreSalonConferencias);		
        log.info ("Eliminando Salon Conferencias por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarSalonConferenciasPorId (long idSalonConferencias)
	{
		log.info ("Eliminando salon conferencias por id: " + idSalonConferencias);
        long resp = pp.eliminarSalonConferenciasPorId ( idSalonConferencias);
        log.info ("Eliminando salon conferencias por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<SalonConferencias> darSalonesConferencias ()
	{
		log.info ("Consultando salonConferencias");
        List<SalonConferencias> salonConferencias = pp.darSalonesConferencias ();	
        log.info ("Consultando salonConferencias : " + salonConferencias.size() + " existentes");
        return salonConferencias;
	}

	
	public List<VOSalonConferencias> darVOSalonesConferencias ()
	{
		log.info ("Generando los VO de SalonesConferencias");        
        List<VOSalonConferencias> voSalonesConferencias = new LinkedList<VOSalonConferencias> ();
        for (SalonConferencias b : pp.darSalonesConferencias ())
        {
        	voSalonesConferencias.add(b);
        }
        log.info ("Generando los VO de SalonesConferencias: " + voSalonesConferencias.size() + " existentes");
        return voSalonesConferencias;
	}

	
	public SalonConferencias darSalonConferenciasPorNombre (String nombreSalonConferencias) 
	{
		log.info ("Buscando SalonConferencias por nombre: " + nombreSalonConferencias);
		List<SalonConferencias> b = pp.darSalonConferenciaPorNombre(nombreSalonConferencias) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SALONREUNIONES
	 *****************************************************************/
	
	public SalonReuniones adicionarSalonReuniones (long idServicio, int capacidadPersonas, String nombre) 
	{
        log.info ("Adicionando SalonReuniones: " + nombre);
        SalonReuniones salonReuniones = pp.adicionarSalonReuniones (idServicio, capacidadPersonas, nombre);		
        log.info ("Adicionando salonReuniones: " + salonReuniones);
        return salonReuniones;
	}
	
	
	public long eliminarSalonReunionesPorNombre(String nombreSalonReuniones)
	{
		log.info ("Eliminando SalonReuniones por nombre: " + nombreSalonReuniones);
        long resp = pp.eliminarSalonReunionesPorNombre( nombreSalonReuniones);		
        log.info ("Eliminando SalonReuniones por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarSalonReunionesPorId (long idSalonReuniones)
	{
		log.info ("Eliminando SalonReuniones por id: " + idSalonReuniones);
        long resp = pp.eliminarSalonConferenciasPorId ( idSalonReuniones);
        log.info ("Eliminando SalonReuniones por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<SalonReuniones> darSalonesReuniones ()
	{
		log.info ("Consultando SalonReuniones");
        List<SalonReuniones> salonReuniones = pp.darSalonesReuniones ();	
        log.info ("Consultando salonReuniones : " + salonReuniones.size() + " existentes");
        return salonReuniones;
	}

	
	public List<VOSalonReuniones> darVOSalonesReuniones ()
	{
		log.info ("Generando los VO de SalonesReuniones");        
        List<VOSalonReuniones> voSalonReuniones = new LinkedList<VOSalonReuniones> ();
        for (SalonReuniones b : pp.darSalonesReuniones ())
        {
        	voSalonReuniones.add(b);
        }
        log.info ("Generando los VO de SalonReuniones: " + voSalonReuniones.size() + " existentes");
        return voSalonReuniones;
	}

	
	public SalonReuniones darSalonReunionesPorNombre (String nombreSalonReuniones) 
	{
		log.info ("Buscando SalonReuniones por nombre: " + nombreSalonReuniones);
		List<SalonReuniones> b = pp.darSalonReunionesPorNombre(nombreSalonReuniones) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIO
	 *****************************************************************/
	
	public Servicio adicionarServicio (String nombre, int demanda) 
	{
        log.info ("Adicionando servicio: " + nombre);
        Servicio servicio = pp.adicionarServicio (nombre, demanda);		
        log.info ("Adicionando servicio: " + servicio);
        return servicio;
	}
	
	
	public long eliminarServicioPorNombre(String nombreServicio)
	{
		log.info ("Eliminando Servicio por nombre: " + nombreServicio);
        long resp = pp.eliminarServicioPorNombre( nombreServicio);		
        log.info ("Eliminando Servicio por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarServicioPorId (long idServicio)
	{
		log.info ("Eliminando Servicio por id: " + idServicio);
        long resp = pp.eliminarServicioPorId ( idServicio);
        log.info ("Eliminando Servicio por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public long actualizarDemandaPorId (long idServicio)
	{
		log.info ("Actualizando  Servicio por id: " + idServicio);
        long resp = pp.actualizarDemandaPorId ( idServicio);
        log.info ("Actualizando Servicio por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Servicio> darServicios ()
	{
		log.info ("Consultando Servicio");
        List<Servicio> servicios = pp.darServicios ();	
        log.info ("Consultando servicios : " + servicios.size() + " existentes");
        return servicios;
	}

	
	public List<VOServicio> darVOServicios ()
	{
		log.info ("Generando los VO de Servicio");        
        List<VOServicio> voServicio = new LinkedList<VOServicio> ();
        for (Servicio b : pp.darServicios ())
        {
        	voServicio.add(b);
        }
        log.info ("Generando los VO de Servicio: " + voServicio.size() + " existentes");
        return voServicio;
	}

	
	public Servicio darServiciosPorNombre (String nombreServicio) 
	{
		log.info ("Buscando Servicio por nombre: " + nombreServicio);
		List<Servicio> b = pp.darServiciosPorNombre(nombreServicio) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar SPA
	 *****************************************************************/
	
	public Spa adicionarSpa (long idServicio, int capacidadSpa, String horario, String nombre) 
	{
        log.info ("Adicionando spa: " + nombre);
        Spa spa = pp.adicionarSpa (idServicio, capacidadSpa, horario, nombre);		
        log.info ("Adicionando spa: " + spa);
        return spa;
	}
	
	
	public long eliminarSpaPorNombre(String nombreSpa)
	{
		log.info ("Eliminando spa por nombre: " + nombreSpa);
        long resp = pp.eliminarSpaPorNombre( nombreSpa);		
        log.info ("Eliminando spa por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarSpaPorId (long idSpa)
	{
		log.info ("Eliminando spa por id: " + idSpa);
        long resp = pp.eliminarSpaPorId ( idSpa);
        log.info ("Eliminando spa por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Spa> darSpas ()
	{
		log.info ("Consultando spa");
        List<Spa> spa = pp.darSpas ();	
        log.info ("Consultando spa : " + spa.size() + " existentes");
        return spa;
	}

	
	public List<VOSpa> darVOSpa ()
	{
		log.info ("Generando los VO de spa");        
        List<VOSpa> voSpa = new LinkedList<VOSpa> ();
        for (Spa b : pp.darSpas ())
        {
        	voSpa.add(b);
        }
        log.info ("Generando los VO de spa: " + voSpa.size() + " existentes");
        return voSpa;
	}

	
	public Spa darSpasPorNombre (String nombreSpa) 
	{
		log.info ("Buscando spa por nombre: " + nombreSpa);
		List<Spa> b = pp.darSpasPorNombre(nombreSpa) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar SUPERMERCADO
	 *****************************************************************/
	
	public Supermercado adicionarSupermercado (long idServicio, String horario, String nombre) 
	{
        log.info ("Adicionando supermercado: " + nombre);
        Supermercado supermercado = pp.adicionarSupermercado (idServicio, horario, nombre);		
        log.info ("Adicionando supermercado: " + supermercado);
        return supermercado;
	}
	
	
	public long eliminarSupermercadoPorNombre(String nombreSupermercado)
	{
		log.info ("Eliminando supermercado por nombre: " + nombreSupermercado);
        long resp = pp.eliminarSupermercadoPorNombre( nombreSupermercado);		
        log.info ("Eliminando supermercado por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarSupermercadoPorId (long idSupermercado)
	{
		log.info ("Eliminando supermercado por id: " + idSupermercado);
        long resp = pp.eliminarSupermercadoPorId ( idSupermercado);
        log.info ("Eliminando supermercado por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Supermercado> darSupermercado ()
	{
		log.info ("Consultando supermercado");
        List<Supermercado> supermercado = pp.darSupermercado ();	
        log.info ("Consultando supermercado : " + supermercado.size() + " existentes");
        return supermercado;
	}

	
	public List<VOSupermercado> darVOSupermercado ()
	{
		log.info ("Generando los VO de supermercado");        
        List<VOSupermercado> voSupermercado = new LinkedList<VOSupermercado> ();
        for (Supermercado b : pp.darSupermercado ())
        {
        	voSupermercado.add(b);
        }
        log.info ("Generando los VO de supermercado: " + voSupermercado.size() + " existentes");
        return voSupermercado;
	}

	
	public Supermercado darSupermercadosPorNombre (String nombreSupermercado) 
	{
		log.info ("Buscando supermercado por nombre: " + nombreSupermercado);
		List<Supermercado> b = pp.darSupermercadosPorNombre(nombreSupermercado) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar TIENDA
	 *****************************************************************/
	
	public Tienda adicionarTienda (long idServicio,String tipoTienda, String horario, String nombre)
	{
        log.info ("Adicionando tienda: " + nombre);
        Tienda tienda = pp.adicionarTienda (idServicio, tipoTienda, horario, nombre);		
        log.info ("Adicionando supermercado: " + tienda);
        return tienda;
	}
	
	
	public long eliminarTiendaPorNombre(String nombreTienda)
	{
		log.info ("Eliminando tienda por nombre: " + nombreTienda);
        long resp = pp.eliminarTiendaPorNombre( nombreTienda);		
        log.info ("Eliminando tienda por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarTiendaPorId (long idTienda)
	{
		log.info ("Eliminando tienda por id: " + idTienda);
        long resp = pp.eliminarSupermercadoPorId (idTienda);
        log.info ("Eliminando tienda por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Tienda> darTiendas ()
	{
		log.info ("Consultando tienda");
        List<Tienda> tienda = pp.darTiendas ();	
        log.info ("Consultando tienda : " + tienda.size() + " existentes");
        return tienda;
	}

	
	public List<VOTienda> darVOTienda ()
	{
		log.info ("Generando los VO de tienda");        
        List<VOTienda> voTienda = new LinkedList<VOTienda> ();
        for (Tienda b : pp.darTiendas ())
        {
        	voTienda.add(b);
        }
        log.info ("Generando los VO de tienda: " + voTienda.size() + " existentes");
        return voTienda;
	}

	
	public Tienda darTiendaPorNombre (String nombreTienda) 
	{
		log.info ("Buscando tienda por nombre: " + nombreTienda);
		List<Tienda> b = pp.darTiendaPorNombre(nombreTienda) ;
		return !b.isEmpty () ? b.get (0) : null;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar TIPOUSUARIO
	 *****************************************************************/

	public TipoUsuario adicionarTipoUsuario (String tipoEmpleo) 
	{
		log.info ("Adicionando TipoUsuario " );
		TipoUsuario tipoUsuario = pp.adicionarTipoUsuario ( tipoEmpleo) ;
        log.info ("Adicionando TipoUsuario: " + tipoUsuario);
        return tipoUsuario;
	}
	
	
	public long eliminarTipoPorNombre (String tipoEmpleo)
	{
        log.info ("Eliminando TipoUsuario por tipoEmpleo" + tipoEmpleo);
        long resp = pp.eliminarPiscinaPorNombre ( tipoEmpleo);
        log.info ("Eliminando TipoUsuario por tipoEmpleo: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarTipoPorId (long idEmpleo)
	{
        log.info ("Eliminando TipoUsuario por id: " + idEmpleo);
        long resp = pp.eliminarTipoPorId( idEmpleo);
        log.info ("Eliminando TipoUsuario por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<TipoUsuario> darTiposUsuario()
	{
        log.info ("Consultando TipoUsuario");
        List<TipoUsuario> tipoUsuario = pp.darTiposUsuario ();	
        log.info ("Consultando TipoUsuario: " + tipoUsuario.size() + " TipoUsuario existentes");
        return tipoUsuario;
	}


	public List<VOTipoUsuario> darVOTipoUsuario ()
	{
		log.info ("Generando los VO de laos tipoUsuario");       
        List<VOTipoUsuario> voTipoUsuario = new LinkedList<VOTipoUsuario> ();
        for (VOTipoUsuario g : pp.darTiposUsuario ())
        {
        	voTipoUsuario.add (g);
        }
        log.info ("Generando los VO de las tipoUsuario: " + voTipoUsuario.size() + " existentes");
        return voTipoUsuario;
	}


	public TipoUsuario darTipoPorNombre (String tipoEmpleo) 
	{
		log.info ("Buscando TipoUsuario por tipoEmpleo: " + tipoEmpleo);
		TipoUsuario c = pp.darTipoPorNombre ( tipoEmpleo)  ;
		return c;
	}
	
	
	/* **********************
	 * 			Métodos para manejar los TIPOHABITACION
	 ***********************/

	public TipoHabitacion adicionarTipoHabitacion (String tipoHabitacion)
	{
        log.info ("Adicionando tipoHabitacion: " + tipoHabitacion);
        TipoHabitacion elTipoHabitacion = pp.adicionarTipoHabitacion (tipoHabitacion);		
        log.info ("Adicionando tipoHabitacion: " + tipoHabitacion);
        return elTipoHabitacion;
	}
	
	
	public long eliminarTipoHabitacionPorTipo(String tipoHabitacion)
	{
		log.info ("Eliminando tipoHabitacion por tipo: " + tipoHabitacion);
        long resp = pp.eliminarTipoHabitacionPorTipo( tipoHabitacion);		
        log.info ("Eliminando tipoHabitacion por tipo: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public long eliminarTipoHabitacionPorId (long idTipoHabitacion)
	{
		log.info ("Eliminando TipoHabitacion por id: " + idTipoHabitacion);
        long resp = pp.eliminarTipoHabitacionPorId ( idTipoHabitacion);
        log.info ("Eliminando TipoHabitacion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<TipoHabitacion> darTipoHabitaciones ()
	{
		log.info ("Consultando TipoHabitacion");
        List<TipoHabitacion> tipoHabitaciones = pp.darTipoHabitaciones ();	
        log.info ("Consultando Bares : " + tipoHabitaciones.size() + " existentes");
        return tipoHabitaciones;
	}

	
	public List<VOTipoHabitacion> darVOTipohabitacion ()
	{
		log.info ("Generando los VO de TipoHabitacion");        
        List<VOTipoHabitacion> voTipoHabitacion = new LinkedList<VOTipoHabitacion> ();
        for (TipoHabitacion b : pp.darTipoHabitaciones ())
        {
        	voTipoHabitacion.add (b);
        }
        log.info ("Generando los VO de tipoHabitacion: " + voTipoHabitacion.size() + " existentes");
        return voTipoHabitacion;
	}

	
	public TipoHabitacion darTipoHabitacionPorTipo (String tipoHabitacion) 
	{
		log.info ("Buscando tipoHabitacion por tipo: " + tipoHabitacion);
		List<TipoHabitacion> b = pp.darTipoHabitacionPorTipo ( tipoHabitacion) ;
		return !b.isEmpty () ? b.get (0) : null;
	}

	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/
	
	public long [] limpiarHotelAndes ()
	{
        log.info ("Limpiando la BD de HotelAndes");
        long [] borrrados = pp.limpiarHotelAndes();	
        log.info ("Limpiando la BD de HotelAndes: Listo!");
        return borrrados;
	}
}
