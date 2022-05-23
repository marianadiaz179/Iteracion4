/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
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

package uniandes.isis2304.hotelAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
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
	public SQLUtil (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqHotelAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarHotelAndes (PersistenceManager pm)
	{
        Query qBar = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar());  
        Query qConsumo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConsumo());        
        Query qConvencion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaConvencion());        
        Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());
        Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFacturas());
        Query qGimnasio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGimnasio());
        Query qHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion());
        Query qHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel());
        Query qInternet = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInternet());
        Query qLavadoPlanchado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLavadoPlanchado());
        Query qPiscina = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPiscina());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());
        Query qPlanDePago = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPlanPago());
        Query qReservaHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservaHabitacion());
        Query qReservaServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservaServicio());
        Query qRestaurante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRestaurante());
        Query qSalonConferencias = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSalonConferencias());
        Query qSalonReuniones = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSalonReuniones());
        Query qTipoHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoHabitacion());
        Query qSpa = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSpa());
        Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
        Query qTienda = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTienda());
        Query qSuperMercado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercado());
        Query qTipoUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTipoUsuario());
        
        long baresEliminados = (long) qBar.executeUnique ();
        long usuariosEliminados = (long) qUsuario.executeUnique ();
        long facturasEliminadas = (long) qFactura.executeUnique ();
        long gimnasiosEliminados = (long) qGimnasio.executeUnique ();
        long habitacionesEliminadas = (long) qHabitacion.executeUnique ();
        long hotelesEliminados = (long) qHotel.executeUnique ();
        long internetEliminado = (long) qInternet.executeUnique ();
        long lavadoPlanchadoEliminados = (long) qLavadoPlanchado.executeUnique ();
        long piscinasEliminadas = (long) qPiscina.executeUnique ();
        long planesEliminados = (long) qPlanDePago.executeUnique ();
        long tiposUsuariosEliminados = (long) qTipoUsuario.executeUnique ();
        long conumosEliminados = (long) qConsumo.executeUnique();
        long convencionesEliminadas = (long) qConvencion.executeUnique();
        long productosEliminados = (long) qProducto.executeUnique();
        long reservasHEliminadas = (long) qReservaHabitacion.executeUnique();
        long reservasSEliminadas = (long) qReservaServicio.executeUnique();
        long restaurantesEliminados = (long) qRestaurante.executeUnique();
        long salonesCEliminados = (long) qSalonConferencias.executeUnique();
        long salonesREliminados = (long) qSalonReuniones.executeUnique();
        long tiposHEliminados = (long) qTipoHabitacion.executeUnique();
        long spaEliminados = (long) qSpa.executeUnique();
        long serviciosEliminados = (long) qServicio.executeUnique();
        long tiendasElimindas = (long) qTienda.executeUnique();
        long superEliminados = (long) qSuperMercado.executeUnique();
        
        return new long[] {baresEliminados, usuariosEliminados, facturasEliminadas, 
        		gimnasiosEliminados, habitacionesEliminadas, hotelesEliminados,
        		internetEliminado, lavadoPlanchadoEliminados, piscinasEliminadas,
        		planesEliminados, tiposUsuariosEliminados, conumosEliminados,convencionesEliminadas,
        		productosEliminados,reservasHEliminadas, reservasSEliminadas, restaurantesEliminados,
        		salonesCEliminados, salonesREliminados, tiposHEliminados, spaEliminados, serviciosEliminados,
        		tiendasElimindas, superEliminados};
	}

}
