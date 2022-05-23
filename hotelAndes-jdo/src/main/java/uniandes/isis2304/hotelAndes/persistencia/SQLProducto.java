/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Tomas Angel - Mariana Diaz
 * Abril 2022
 * 
 * Revisado por: Claudia Jiménez
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Producto;

/**
 * Clase que encapsula los métodos que ppcen acceso a la base de datos para el concepto BEBIDA de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Tomas Angel
 */

class SQLProducto {
	

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
	public SQLProducto (PersistenciaHotelAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PRODUCTO a la base de datos de HotelAndes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del producto
	 * @param nombreProducto - El nombre del producto
	 * @param costoProducto - El costo asociado el producto. En caso de ser gratis, el costo asociado es de 0.
	 * @param idServicio - El identificador del servicio (FK)
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarProducto (PersistenceManager pm, long idProducto, String nombreProducto, float costoProducto, long idServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProducto () + "(id, nombreProducto, costoProducto, idServicio) values (?, ?, ?, ?)");
        q.setParameters(idProducto, nombreProducto, costoProducto, idServicio);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTOS de la base de datos de HotelAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreProducto - El nombre del producto
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProductoPorNombre (PersistenceManager pm, String nombreProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE nombreProducto = ?");
        q.setParameters(nombreProducto);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar PRODUCTOS de la base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador del producto
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarProductoPorId (PersistenceManager pm, long idProducto)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto () + " WHERE id = ?");
        q.setParameters(idProducto);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PRODUCTO de la 
	 * base de datos de HotelAndes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idProducto - El identificador del producto
	 * @return El objeto BEBIDA que tiene el identificador dado
	 */
	public Producto darProductoPorId (PersistenceManager pm, long idProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE id = ?");
		q.setResultClass(Producto.class);
		q.setParameters(idProducto);
		return (Producto) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de PRODUCTO de la 
	 * base de datos de HotelAndes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreProducto - El nombre de la bebida
	 * @return Una lista de objetos PRODUCTO que tienen el nombre dado
	 */
	public List<Producto> darProductosPorNombre (PersistenceManager pm, String nombreProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto () + " WHERE nombreProducto = ?");
		q.setResultClass(Producto.class);
		q.setParameters(nombreProducto);
		return (List<Producto>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PRODUCTOS de la 
	 * base de datos de HotelAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PRODUCTO
	 */
	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProducto ());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	
}
