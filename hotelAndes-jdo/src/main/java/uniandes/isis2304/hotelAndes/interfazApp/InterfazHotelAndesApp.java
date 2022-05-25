/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: HotelAndes Uniandes
 * @version 1.0
 * @author Mariana Diaz - Tomás Angel
 * Abril 2022
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.hotelAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import oracle.sql.DATE;
import uniandes.isis2304.hotelAndes.negocio.Usuario;
import uniandes.isis2304.hotelAndes.negocio.VOConsumo;
import uniandes.isis2304.hotelAndes.negocio.VOConvencion;
import uniandes.isis2304.hotelAndes.negocio.VOFactura;
import uniandes.isis2304.hotelAndes.negocio.Consumo;
import uniandes.isis2304.hotelAndes.negocio.Convencion;
import uniandes.isis2304.hotelAndes.negocio.Habitacion;
import uniandes.isis2304.hotelAndes.negocio.HotelAndes;
import uniandes.isis2304.hotelAndes.negocio.PlanDePago;
import uniandes.isis2304.hotelAndes.negocio.Producto;
import uniandes.isis2304.hotelAndes.negocio.ReservaHabitacion;
import uniandes.isis2304.hotelAndes.negocio.Servicio;
import uniandes.isis2304.hotelAndes.negocio.TipoHabitacion;
import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;
import uniandes.isis2304.hotelAndes.negocio.VOUsuario;
import uniandes.isis2304.hotelAndes.negocio.VOHabitacion;
import uniandes.isis2304.hotelAndes.negocio.VOHotel;
import uniandes.isis2304.hotelAndes.negocio.VOPlanDePago;
import uniandes.isis2304.hotelAndes.negocio.VOProducto;
import uniandes.isis2304.hotelAndes.negocio.VOReservaHabitacion;
import uniandes.isis2304.hotelAndes.negocio.VOReservaServicio;
import uniandes.isis2304.hotelAndes.negocio.VOServicio;
import uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion;
import uniandes.isis2304.hotelAndes.negocio.VOTipoUsuario;


/**
 * Clase principal de la interfaz
 * @author Mariana Díaz - Tomás Angel
 */
@SuppressWarnings("serial")

public class InterfazHotelAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazHotelAndesApp.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private HotelAndes hotelAndes;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;
    
    private String Usuario;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazHotelAndesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        hotelAndes = new HotelAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "HotelAndes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "HotelAndes APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
    /*
     * Función de iniciar sesión para verificación de usuario
     */
    public void iniciarSesion()
    {
    	try 
    	{
    		String nombreEmpleo = JOptionPane.showInputDialog (this, "Nombre del tipo de usuario? (AdministradorDatos,Gerente,Recepcionista,Empleado, Cliente)", "Iniciar sesión", JOptionPane.QUESTION_MESSAGE);
    		String nombre = JOptionPane.showInputDialog (this, "Ingrese su nombre", "Iniciar sesión", JOptionPane.QUESTION_MESSAGE);

    		if (nombreEmpleo != null )
    		{
    			
    			
        			Usuario empleado = hotelAndes.darUsuariosPorNombre(nombre);
            		long id = empleado.getTipoUsuario();
            		TipoUsuario tipo = hotelAndes.darTipoPorNombre(nombreEmpleo);
            		long idTipo = tipo.getIdEmpleo();
            		
            		if (id == idTipo)
        			{
        				String contraseña = JOptionPane.showInputDialog (this, "Ingrese su contraseña", "Iniciar sesión", JOptionPane.QUESTION_MESSAGE);
        				Usuario = nombreEmpleo;
        			}
        			else
        			{
        				throw new Exception ("No existe un usuario con su nombre");
        			}
            		String resultado = "Se ha iniciado sesión correctamente";
        			panelDatos.actualizarInterfaz(resultado);
        		
    			
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	}
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    /*
     * Funciones del administrador de datos
     */
    
    public void adicionarTipoUsuario() 
    {
    	try 
    	{
    		if (Usuario.equals("AdministradorDatos"))
    		{
    		String nombreEmpleo = JOptionPane.showInputDialog (this, "Nombre del tipo de usuario? (OrganizadorEventos, AdministradorDatos,Gerente,Recepcionista,Empleado)", "Adicionar tipo de usuario", JOptionPane.QUESTION_MESSAGE);
    		
    		if (nombreEmpleo != null)
    		{
        		VOTipoUsuario eh = hotelAndes.adicionarTipoUsuario(nombreEmpleo);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear un tipo de usuario con nombre: " + nombreEmpleo);
        		}
        		String resultado = "En adicionarTipoUsuario\n\n";
        		resultado += "Usuario adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    		}
    		else
    		{
        		JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como AdministradorDatos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
    	}
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    
    
    public void adicionarTipoHabitacion( )
    {
    	try 
    	{
    		if (Usuario.equals("AdministradorDatos"))
    		{
    		String tipoHabitacion = JOptionPane.showInputDialog (this, "Nombre del tipo de habitacion? (Sencilla,DobleConJacuzzi,DobleSinJacuzzi,Familiar,Suite,SuitePresidencial)", "Adicionar tipo de habitacion", JOptionPane.QUESTION_MESSAGE);
    		
    		if (tipoHabitacion != null )
    		{
    			VOTipoHabitacion eh = hotelAndes.adicionarTipoHabitacion(tipoHabitacion);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear un tipo de habitacion con nombre: " + tipoHabitacion);
        		}
        		String resultado = "En adicionarTipoHabitacion\n\n";
        		resultado += "Usuario adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	}
    		else
    		{
        		JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como AdministradorDatos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
    	}
    	
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarServicio( )
    {
    	try 
    	{
    		if (Usuario.equals("AdministradorDatos"))
    		{
    		String nombreServicio = JOptionPane.showInputDialog (this, "Nombre del tipo de servicio? (Bar,Restaurante,Gimnasio,Internet,LavadoPlanchado,Piscina,Producto,SalonConferencias,SalonReuniones,Spa,Supermercado,Tienda)", "Adicionar tipo de habitacion", JOptionPane.QUESTION_MESSAGE);
    		
    		if (nombreServicio != null )
    		{
    			VOServicio eh = hotelAndes.adicionarServicio(nombreServicio,0);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear un servicio con nombre: " + nombreServicio);
        		}
        		String resultado = "En adicionarServicio\n\n";
        		resultado += "Usuario adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	}
    		else
    		{
        		JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como AdministradorDatos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
    	}
    	
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarEmpleadoHotel( )
    {
    
    	try 
    	{
    		if (Usuario.equals("AdministradorDatos"))
    		{
    
    		String nombreEmpleo = JOptionPane.showInputDialog (this, "Nombre del tipo de usuario? (AdministradorDatos,Gerente,Cliente,Empleado,OrganizadorEventos)", "Adicionar usuario", JOptionPane.QUESTION_MESSAGE);
    		TipoUsuario empleo = hotelAndes.darTipoPorNombre(nombreEmpleo);
    		long idEmpleo = empleo.getIdEmpleo();
    		String nombreEmpleado = JOptionPane.showInputDialog (this, "Nombre del usuario?", "Adicionar usuario", JOptionPane.QUESTION_MESSAGE);
    		String cedula = JOptionPane.showInputDialog (this, "cedula de usuario?", "AdicionarUsuario", JOptionPane.QUESTION_MESSAGE);
    		long cedulaEmpleado = Long.parseLong(cedula);
    		String correo = JOptionPane.showInputDialog (this, "Correo del usuario? ", "Adicionar usuario", JOptionPane.QUESTION_MESSAGE);
    		String Hotel = JOptionPane.showInputDialog (this, "Nombre del hotel del usuario? )", "Adicionar usuario", JOptionPane.QUESTION_MESSAGE);
    		uniandes.isis2304.hotelAndes.negocio.Hotel hCompleto = hotelAndes.darHotelPorNombre(Hotel);
    		long idHotel = hCompleto.getIdHotel();
    		if (idEmpleo != 0 && nombreEmpleo != null && correo != null && idHotel != 0 )
    		{
        		VOUsuario eh = hotelAndes.adicionarUsuario(idEmpleo, nombreEmpleado, cedulaEmpleado,correo, idHotel,0,0);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear un tipo de usuario con nombre: " + nombreEmpleado);
        		}
        		String resultado = "En adicionarEmpleadoHotel\n\n";
        		resultado += "Usuario adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	}
    	else
		{
    		JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como AdministradorDatos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarHabitacion( )
    {
    
    	try 
    	{
    		if (Usuario.equals("AdministradorDatos"))
    		{
    		String capac = JOptionPane.showInputDialog (this, "Capacidad de la habitacion?", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
    		int capacidad = Integer.parseInt(capac);
    		String costoNoche = JOptionPane.showInputDialog(this, "Costo por noche", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
    		double costo = Double.parseDouble(costoNoche);
    		String tipoHabitacion = JOptionPane.showInputDialog(this, "Tipo De Habitacion", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
    		TipoHabitacion habitacion = hotelAndes.darTipoHabitacionPorTipo(tipoHabitacion);
    		long tipo = habitacion.getIdTipoHabitacion();
    		String Hotel = JOptionPane.showInputDialog (this, "Nombre del hotel del usuario? )", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
    		uniandes.isis2304.hotelAndes.negocio.Hotel hCompleto = hotelAndes.darHotelPorNombre(Hotel);
    		long idHotel = hCompleto.getIdHotel();
    		if (capacidad != 0 && costo != 0 && tipo != 0 && idHotel != 0 && Usuario.equals("AdministradorDatos"))
    		{
        		VOHabitacion eh = hotelAndes.adicionarHabitacion(capacidad, costo, tipo, idHotel, "Vacia", 0);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear una habitacion de tipo: " + tipoHabitacion);
        		}
        		String resultado = "En adicionarHabitacion\n\n";
        		resultado += "Habitacion adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    		}
        	else
    		{
        		JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como AdministradorDatos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarPlanDePago( )
    {
    
    	try 
    	{
    		if (Usuario.equals("AdministradorDatos"))
    		{
    		String tipoPlan = JOptionPane.showInputDialog (this, "Tipo de plan?", "Adicionar plan", JOptionPane.QUESTION_MESSAGE);
    		String caracteristicas = JOptionPane.showInputDialog (this, "Caracteristicas?", "Adicionar plan", JOptionPane.QUESTION_MESSAGE);

    		
    		if (tipoPlan != null && caracteristicas != null )
    		{
        		VOPlanDePago eh = hotelAndes.adicionarPlanDePago(tipoPlan,caracteristicas);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear una plan de tipo: " + tipoPlan);
        		}
        		String resultado = "En adicionarPlanDePago\n\n";
        		resultado += "Plan De Pago adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	
		else
		{
			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como AdministradorDatos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
    	}
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    /*
     * Funcion del cliente
     */
    
    public void adicionarReservaHabitacion( )
    {
    
    	try 
    	{
    		if (Usuario.equals("Cliente"))
    		{
    		
    		Date fechaI = Date.valueOf(JOptionPane.showInputDialog (this, "Fecha inicio de la reserva(YYYY-MM-DD)?", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE));
    		Date fechaF = Date.valueOf(JOptionPane.showInputDialog (this, "Fecha final de la reserva (YYYY-MM-DD)?", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE));
    		int duracion = Integer.valueOf(JOptionPane.showInputDialog(this, "Duración de la estadía?", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE));
    		int cantPersonas = Integer.valueOf(JOptionPane.showInputDialog(this, "Cantidad de personas?", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE));
    		String tipoPlan = JOptionPane.showInputDialog (this, "Plan de pago solicitado?", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE);
    		PlanDePago plan = hotelAndes.darPlanPorTipo(tipoPlan);
    		long idPlan = plan.getId();
    		long cliente = Long.valueOf(JOptionPane.showInputDialog(this, "Cedula del cliente", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE));
    		String tipoHabitacion = JOptionPane.showInputDialog (this, "TipoHabitacion? ", "Adicionar ReservaHabitacion", JOptionPane.QUESTION_MESSAGE);
    		TipoHabitacion Th = hotelAndes.darTipoHabitacionPorTipo(tipoHabitacion);
    		Habitacion h = hotelAndes.darHabitacionPorTipo(Th.getIdTipoHabitacion());
    		long habitacion = h.getNumHabitacion();
    		
    		if (habitacion != 0 )
    		{
        		VOReservaHabitacion eh = hotelAndes.adicionarReservaHabitacion(fechaI, fechaF, duracion, cantPersonas, 
        				idPlan, cliente, habitacion, 0);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear una reserva para el cliente: " + cliente);
        		}
        		String resultado = "En adicionarReservaHabitacion\n\n";
        		resultado += "Reserva de Habitacion adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	} 
    	
		else
		{
			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Cliente", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    
    public void adicionarReservaServicio( )
    {
    
    	try 
    	{
    		if (Usuario.equals("Cliente"))
    		{
    		
    		String dur = JOptionPane.showInputDialog (this, "duracion de la reserva?", "Adicionar ReservaServicio", JOptionPane.QUESTION_MESSAGE);
    		int duracion = Integer.parseInt(dur);
    		String tipo = JOptionPane.showInputDialog (this, "Tipo de servicio?", "Adicionar ReservaServicio", JOptionPane.QUESTION_MESSAGE);
    		Date dia = Date.valueOf(JOptionPane.showInputDialog (this, "Fecha de la reserva (YYYY-MM-DD)?", "Adicionar ReservaServicio", JOptionPane.QUESTION_MESSAGE));
    		String hora = JOptionPane.showInputDialog (this, "hora de la reserva? (00:00)", "Adicionar ReservaServicio", JOptionPane.QUESTION_MESSAGE);
    		String cliente = JOptionPane.showInputDialog(this, "Nombre del cliente", "Adicionar ReservaServicio", JOptionPane.QUESTION_MESSAGE);
    		Usuario cli = hotelAndes.darUsuariosPorNombre(cliente);
    		long cedula = cli.getCedula();
    		String servi = JOptionPane.showInputDialog (this, "Nombre del servicio? )", "Adicionar ReservaServicio", JOptionPane.QUESTION_MESSAGE);
    		Servicio ser = hotelAndes.darServiciosPorNombre(servi);
    		long servicio = ser.getId();
    		
    		if (servicio != 0 )
    		{
        		VOReservaServicio eh = hotelAndes.adicionarReservaServicio(tipo, dia, hora, duracion, cedula, servicio);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear una reserva para el cliente: " + cliente);
        		}
        		String resultado = "En adicionarReservaServicio\n\n";
        		resultado += "Reserva de Servicio adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	} 
    	
		else
		{
			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Cliente", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    /*
     * Funciones del empleado
     */
 
    public void adicionarConsumo( )
    {
    
    	try 
    	{
    		if (Usuario.equals("Empleado"))
    		{
    		String nombreProducto = JOptionPane.showInputDialog (this, "Producto consumido?", "Adicionar Consumo", JOptionPane.QUESTION_MESSAGE);
    		Producto producto = hotelAndes.darProductoPorNombre(nombreProducto);
    		long idProducto = producto.getId();
    		long idServicio = producto.getIdServicio();
    		String ha = JOptionPane.showInputDialog(this, "Habitacion en la que se hospeda?", "Adicionar Consumo", JOptionPane.QUESTION_MESSAGE);
    		Habitacion hab = hotelAndes.darHabitacionPorId(Long.valueOf(ha));
    		long habitacion = hab.getNumHabitacion();
    		long cliente= hab.getCliente();
    		LocalDate date = LocalDate.now();
    		Date fecha = Date.valueOf(date);
    		
    		
    		if (idProducto != 0 )
    		{
        		VOConsumo eh = hotelAndes.adicionarConsumo( idServicio,  habitacion,  idProducto, "Pendiente" , cliente, fecha);
        		long aumento = hotelAndes.actualizarDemandaPorId(idServicio);
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear un consumo para: " + idProducto);
        		}
        		
        		if (aumento == 0)
        		{
        			throw new Exception ("No se puedo actualizar la demanda del servicio");
        		}
        		String resultado = "En adicionarConsumo\n\n";
        		resultado += "Consumo adicionado exitosamente: " + eh;
        		resultado += "\nSe ha actualizado la demanda del servicio";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    		} 
        	
    		else
    		{
    			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Empleado", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void registrarMantenimiento()
    {
    	try 
    	{
    		if (Usuario.equals("Empleado"))
    		{
    			String habitaciones = JOptionPane.showInputDialog(this, "Ingrese el numero de las habitaciones que desea poner en mantenimiento", "Mantenimiento habitaciones", JOptionPane.QUESTION_MESSAGE);
		
				if (habitaciones != null )
				{
				
					String resultado = "Habitaciones en mantenimiento: \n";
					String[] habiE = habitaciones.split(",");
					
					for (String h: habiE)
					{
						long num = Long.valueOf(h);
						long eli = hotelAndes.actualizarEstadoHabitacion("Mantenimiento", num);
						
						if (eli == 0)
						{
							throw new Exception ("No se poner en mantenimiento la habitacion: " + String.valueOf(num));
						}
						resultado += "Se ha iniciado el mantenimiento de la habitacion "+ String.valueOf(h) + "\n";
					}
					
					
					panelDatos.actualizarInterfaz(resultado);
				
					
				}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    		} 
        	
    		else
    		{
    			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Empleado", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void finalizarMantenimiento()
    {
    	try 
    	{
    		if (Usuario.equals("Empleado"))
    		{
    			String habitaciones = JOptionPane.showInputDialog(this, "Ingrese el numero de las habitaciones de las que desea finalizar el mantenimient ", "Mantenimiento habitaciones", JOptionPane.QUESTION_MESSAGE);
		
				if (habitaciones != null )
				{
				
					String resultado = "Fin de mantenimiento: \n";
					String[] habiE = habitaciones.split(",");
					
					for (String h: habiE)
					{
						long num = Long.valueOf(h);
						long eli = hotelAndes.actualizarEstadoHabitacion("Vacia", num);
						
						if (eli == 0)
						{
							throw new Exception ("No se pudo finalizar el mantenimiento la habitacion: " + String.valueOf(num));
						}
						resultado += "Se ha finalizado el mantenimiento de la habitacion "+ String.valueOf(h) + "\n";
					}
					
					
					panelDatos.actualizarInterfaz(resultado);
				
					
				}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    		} 
        	
    		else
    		{
    			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Empleado", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    /*
     * Funciones del Recepcionista
     */
    
    public void registrarLlegadaCliente( )
    {
    
    	try 
    	{
    		if (Usuario.equals("Recepcionista"))
    		{
    		
    		long cliente = Long.valueOf(JOptionPane.showInputDialog(this, "Ingrese la cedula del cliente", "Registrar llegada al hotel", JOptionPane.QUESTION_MESSAGE));
    		ReservaHabitacion rH = hotelAndes.darReservaHabitacionPorCliente(cliente);
    		
    		if (rH != null )
    		{
        		long actualizacion = hotelAndes.actualizarClienteHabitacion(cliente, rH.getNumHabitacion());
        		long estado = hotelAndes.actualizarEstadoHabitacion("Ocupada", rH.getNumHabitacion());
        		
        		if (actualizacion == 0)
        		{
        			throw new Exception ("No se pudo actualizar el cliente de la habitacion: " + rH.getNumHabitacion());
        		}
        		
        		String resultado = "En registrar Llegada de un cliente \n\n";
        		resultado += "Se ha ingresado al cliente " + cliente+ " a la habitacion " + rH.getNumHabitacion() + 
        				" de acuerdo a la reserva: " + rH;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else if (rH == null)
    		{
    			panelDatos.actualizarInterfaz("No se encontró una reserva a nombre de " + cliente);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	} 
    	
		else
		{
			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Recepcionista", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void registrarSalidaCliente( )
    {
    
    	try 
    	{
    		if (Usuario.equals("Recepcionista"))
    		{
    		
    		long cliente = Long.valueOf(JOptionPane.showInputDialog(this, "Ingrese la cedula del cliente", "Registrar salida del hotel", JOptionPane.QUESTION_MESSAGE));
    		ReservaHabitacion rH = hotelAndes.darReservaHabitacionPorCliente(cliente);
    		List<VOHabitacion> habitaciones = hotelAndes.darVOHabitacionPorCliente(cliente);
    		float total = 0;
    		
    		if (habitaciones.size() != 0) 
    		{
    			String resultado = "En registrar salida de un cliente \n\n";
    			for (VOHabitacion v: habitaciones)
   			 	{
    				long num = v.getNumHabitacion();
    				float factura = 0;
    				List<VOConsumo> consumosCliente =  hotelAndes.darVOConsumoPorHabitacion(num);
    				for (VOConsumo c: consumosCliente)
    				{
    					long pro= c.getProducto();
    					VOProducto producto = hotelAndes.darProductoPorId(pro);
    					total += producto.getCostoProducto();
    					factura = producto.getCostoProducto();
    					long estado = hotelAndes.actualizarEstadoConsumo("Pago",c.getId());
    				}
   				
    				VOFactura facturaC = hotelAndes.adicionarFactura(num, factura);
    				resultado += "Se ha creado la factura para la habitacion " + String.valueOf(num) + " y sus consumos han sido actualizados \n";
					
    				if (facturaC == null)
    				{
    					throw new Exception("No se pudo crear la factura");
    				}
    				long actuaEsta = hotelAndes.actualizarEstadoHabitacion("Vacia", num);
    				long actuaEstadia = hotelAndes.actualizarEstadiaCliente(rH.getDuracion(),cliente);
    				long eliReserva = hotelAndes.eliminarReservaHabitacionPorId(rH.getIdReserva());
    				
    				resultado += "La habitacion " + String.valueOf(num) + " ahora se encuentra vacía \n";
					
   			 	}
				 
    			resultado += "Al finalizar su estadía el total a pagar por el cliente es: " + String.valueOf(total);
				panelDatos.actualizarInterfaz(resultado);
				
        
        
    		}
    		else if (habitaciones.size() == 0)
    		{
    			panelDatos.actualizarInterfaz("No se encontró una habitacion a nombre de " + cliente);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    	} 
    	
		else
		{
			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como Recepcionista", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
	
    /*
     * Organizador de eventos
     */
    /**
     * Organizador de eventos
     * @throws Exception 
     */
    
    public void agregarConvencion()
    {
        
    	try 
    	{
    		if (Usuario.equals("OrganizadorEventos"))
    		{
    		String nombreConvencion = JOptionPane.showInputDialog (this, "Nombre de la convención?", "Adicionar Convención", JOptionPane.QUESTION_MESSAGE);
    		String nombreO = JOptionPane.showInputDialog (this, "Nombre del organizador?", "Adicionar Convención", JOptionPane.QUESTION_MESSAGE);
    		Usuario empleado = hotelAndes.darUsuariosPorNombre(nombreO);
    		long id = empleado.getCedula();
    		Date fechaI = Date.valueOf(JOptionPane.showInputDialog (this, "Inicio de la convención (YYYY-MM-DD)?", "Adicionar Convención", JOptionPane.QUESTION_MESSAGE));
    		Date fechaF = Date.valueOf(JOptionPane.showInputDialog (this, "Fin de la convención (YYYY-MM-DD)?", "Adicionar Convención", JOptionPane.QUESTION_MESSAGE));
    		int duracion = Integer.valueOf(JOptionPane.showInputDialog (this, "Duración de la convención?", "Adicionar Convención", JOptionPane.QUESTION_MESSAGE));
    		String plan = JOptionPane.showInputDialog (this, "Plan de pago asociado?", "Adicionar Convención", JOptionPane.QUESTION_MESSAGE);
    		PlanDePago planH = hotelAndes.darPlanPorTipo(plan);
    		long planId = planH.getId();
    		
    		
    		if (nombreConvencion != null )
    		{
        		VOConvencion eh = hotelAndes.adicionarConvencion( nombreConvencion,  id,  fechaI, fechaF,duracion, planId);
        		
        		if (eh == null)
        		{
        			throw new Exception ("No se pudo crear una convencion para: " + nombreConvencion);
        		}
        		
  
        		String resultado = "En adicionar Convencion\n\n";
        		resultado += "Convencion adicionado exitosamente: " + eh;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
    		} 
        	
    		else
    		{
    			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como OrganizadorEventos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

    		}
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void reservarHabitacionYServicios () {
    
    try 
	{
    	HashMap<String, Integer> habitaciones = new HashMap<>();
		if (Usuario.equals("OrganizadorEventos"))
		{
		
			String idConvencion = JOptionPane.showInputDialog(this, "Ingrese el nombre de la convención a la que pertenece", "Adicionar reservas", JOptionPane.QUESTION_MESSAGE);
			Convencion convencion = hotelAndes.darConvencionesPorNombre(idConvencion);
			
			if (convencion != null)
			{
			
				String hSuiteP = JOptionPane.showInputDialog (this, "Cantidad de suite presidencial que desea reservar?", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
				habitaciones.put("Suite Presidencial", Integer.parseInt(hSuiteP));
				String hSuite = JOptionPane.showInputDialog (this, "Cantidad de suite  que desea reservar??", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
				habitaciones.put("Suite", Integer.parseInt(hSuite));
				String hFamiliar = JOptionPane.showInputDialog (this, "Cantidad de familiar que desea reservar??", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
				habitaciones.put("Familiar", Integer.parseInt(hFamiliar));
				String hDobleJ = JOptionPane.showInputDialog (this, "Cantidad de dobles con jacuzzi que desea reservar?", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
				habitaciones.put("Doble con Jacuzzi", Integer.parseInt(hDobleJ));
				String hDoble = JOptionPane.showInputDialog (this, "Cantidad de dobles sin jacuzzi que desea reservar?", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
				habitaciones.put("Doble sin Jacuzzi", Integer.parseInt(hDoble));
				String hSencilla = JOptionPane.showInputDialog (this, "Cantidad de sencillas que desea reservar?", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
				habitaciones.put("Sencilla", Integer.parseInt(hSencilla));
				String servicios = JOptionPane.showInputDialog (this, "Ingrese los servicios que desea reservar separados por comas?"
						+ "\nSi no desea realizar reservas de servicios ponga 'N'", "Adicionar reservas convención", JOptionPane.QUESTION_MESSAGE);
		
		
				if (hSuiteP != null )
				{
					ArrayList<Long> habitacionesR = reservarHabitacionesConvencion(habitaciones,convencion);
					ArrayList<String> serviciosR = reservarServiciosConvencion(servicios, convencion);
					String resultado = "";
					
					resultado += "Habitaciones Reservadas: \n";
					
					for (Long h: habitacionesR)
					{
						resultado += String.valueOf(h) + "\n";
					}
					
					resultado += "Servicios Reservados: \n";
					
					for (String r: serviciosR)
					{
						resultado += r + "\n";
					}
					
					panelDatos.actualizarInterfaz(resultado);
				
					
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			} 
			
		}
    	
		else
		{
			JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como OrganizadorEventos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);

		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
	

    }
    
    public void cancelarReservasConvencion ()
    {
    	 try 
    		{
    			if (Usuario.equals("OrganizadorEventos"))
    			{
    			
    				String idConvencion = JOptionPane.showInputDialog(this, "Ingrese el nombre de la convención a la que pertenece", "Cancelar reservas", JOptionPane.QUESTION_MESSAGE);
    				VOConvencion convencion = hotelAndes.darConvencionesPorNombre(idConvencion);
    		
    				System.out.println((convencion.toString()));
    				
    				if (convencion != null)
    				{
    				
    					 String habitaciones = JOptionPane.showInputDialog(this, "Ingrese el numero de las habitaciones que desea desreservar", "Cancelar reservas", JOptionPane.QUESTION_MESSAGE);
    					 String servicios = JOptionPane.showInputDialog(this, "Ingrese el nombre de los servicios que desea desreservar", "Cancelar reservas", JOptionPane.QUESTION_MESSAGE);

    			
    					if (habitaciones != null )
    					{
    					
    						String resultado = "Reservas de Habitaciones Eliminadas: \n";
    						String[] habiE = habitaciones.split(",");
    						String [] serviE = servicios.split(",");
    						
    						for (String h: habiE)
    						{
    							long num = Long.valueOf(h);
    							long eli = hotelAndes.eliminarReservaHabitacionPorHabitacion(num, convencion.getOrganizador());
    							
    							if (eli == 0)
    							{
    								throw new Exception ("No se pudo eliminar la reserva para la habitacion: " + String.valueOf(num));
    							}
    							resultado += "Se ha eliminado la reserva para la habitacion "+ String.valueOf(h) + "\n";
    						}
    						
    						resultado += "Reservas de Servicios eliminados: \n";
    						
    						for (String s: serviE)
    						{
    							
    							long elimi = hotelAndes.eliminarReservaServicioPorTipoServicio(convencion.getOrganizador(), s);
    							if (elimi == 0)
    							{
    								throw new Exception ("No se pudo eliminar la reserva para el servicio: " + s);
    							}
    							
    							resultado += "Se ha eliminado la reserva para el servicio "+ s + "\n";
    						}
    						
    						panelDatos.actualizarInterfaz(resultado);
    					
    						
    					}
    					else
    					{
    						panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    					}
    				} 
    				
    			}
    			else
    			{
    				JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como OrganizadorEventos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);
    			}
    		} 
    		catch (Exception e) 
    		{
    			e.printStackTrace();
    			String resultado = generarMensajeError(e);
    			panelDatos.actualizarInterfaz(resultado);
    		}
    }
    
    public void registrarFinConvencion ()
    {
    	 try 
 		{
 			if (Usuario.equals("OrganizadorEventos"))
 			{
 			
 				String idConvencion = JOptionPane.showInputDialog(this, "Ingrese el nombre de la convención a la que pertenece", "Finalizar convencion", JOptionPane.QUESTION_MESSAGE);
 				VOConvencion convencion = hotelAndes.darConvencionesPorNombre(idConvencion);
 				String resultado = "Finalizando Convención \n";
 				
 				if (convencion != null)
 				{
 					 List<VOHabitacion> habitacionesConvencion = hotelAndes.darVOHabitacionPorCliente(convencion.getOrganizador());
 					 double total = 0;
 					 
 					 for (VOHabitacion v: habitacionesConvencion)
 					 {
 					    long num = v.getNumHabitacion();
 					    double factura = 0;
 						List<Consumo> consumosConvencion = hotelAndes.darConsumoPorHabitacion(num);
 						for (Consumo c: consumosConvencion)
 						{
 							long pro= c.getProducto();
 							Producto p = hotelAndes.darProductoPorId(pro);
 							total += p.getCostoProducto();
 							long estado = hotelAndes.actualizarEstadoConsumo("Pago",c.getId());
 						}
 						VOFactura facturaC = hotelAndes.adicionarFactura(num, factura);
 						resultado += "Se ha creado la factura para la habitacion " + String.valueOf(num) + " y sus consumos han sido actualizados \n";
 						
 						if (facturaC == null)
 						{
 							throw new Exception("No se pudo crear la factura");
 						}
 						long actuaEsta = hotelAndes.actualizarEstadoHabitacion("Vacia", num);
 						resultado += "La habitacion " + String.valueOf(num) + " ahora se encuentra vacía \n";
 						
 					 }
 					 
					resultado += "Al finalizar la convención el total a pagar por todos los miembros es: " + String.valueOf(total);
 					panelDatos.actualizarInterfaz(resultado);
 					
 				}	
 				
 					else
 					{
 						panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
 				
 					}
 				}
 			else
 			{
 				JOptionPane.showMessageDialog (this, "No se puede acceder a esta función. Inicie sesión como OrganizadorEventos", "Error de Usuario", JOptionPane.WARNING_MESSAGE);
 			}
 				} 
 				
 			
 		
 		catch (Exception e) 
 		{
 			e.printStackTrace();
 			String resultado = generarMensajeError(e);
 			panelDatos.actualizarInterfaz(resultado);
 		}
    }
    
    /* ****************************************************************
	 * 			Métodos adicionales
	 *****************************************************************/
    
    
    public ArrayList<String> reservarServiciosConvencion (String servicios, Convencion convencion) throws Exception
    {
    	Date fechaI = hotelAndes.darFechaInicioPorId(convencion.getId());
    	String resultado = "";
    	String[] serviciosA = servicios.split(",");
		ArrayList<String> reservas = new ArrayList<>();
		System.out.println(servicios);
    	
    	if (!servicios.equals("N")) 
    	{
    		resultado += "En AdicionarReserva \n";
    	
    		for (String e: serviciosA)
    		{
    			Producto pro = hotelAndes.darProductoPorNombre(e);
    			
    			if (pro ==null)
    			{
    				throw new Exception ("El hotel no ofrece este servicio");
    			}
    			
    			VOReservaServicio reserva = hotelAndes.adicionarReservaServicio(e, fechaI, "12:30", 30, convencion.getOrganizador(), pro.getIdServicio());
    		
    			if (reserva ==null)
    			{
    				throw new Exception ("No se pudo crear una reserva para el servicio: " + String.valueOf(pro.getIdServicio()));
    			}
    		
    			resultado += "Reserva Servicio adicionado exitosamente: " + reserva;
			
    			reservas.add(e);
    		}
    		resultado += "\n Operación terminada \n\n";
    		panelDatos.actualizarInterfaz(resultado);
    	}
    		
    	return reservas;
    	
    }
    
    public ArrayList<Long> reservarHabitacionesConvencion (HashMap<String, Integer> habitaciones, Convencion convencion) throws Exception
    {
    	Date fechaI = hotelAndes.darFechaInicioPorId(convencion.getId());
    	Date fechaF = hotelAndes.darFechaFinPorId(convencion.getId());
    	ArrayList<Long> habitacionReservadas = new ArrayList<Long>();
    	String resultado = "En adicionar ReservaHabitacion\n\n";
    	
    	for (String key: habitaciones.keySet())
    	{
    	
    		if (habitaciones.get(key) != 0)
    		{

    			TipoHabitacion tipo = hotelAndes.darTipoHabitacionPorTipo(key);
    			long idTipo = tipo.getIdTipoHabitacion();
    			List<VOHabitacion> habitacionesH = hotelAndes.darVOHabitacionPorTipo(idTipo);
				int j = 0;
   
    			int i = habitaciones.get(key);
    			while (i != 0)
    			{
    				
    				if (habitacionesH.size() >= habitaciones.get(key))
    				{
    					
    					Habitacion habitacion = (Habitacion) habitacionesH.get(j);
    					VOReservaHabitacion reserva = hotelAndes.adicionarReservaHabitacion(fechaI, fechaF, convencion.getDuracion(), habitacion.getCapacidad(), convencion.getPlanPago(),
    							convencion.getOrganizador(), habitacion.getNumHabitacion(), 0);
    					habitacionReservadas.add(habitacion.getNumHabitacion());
    					j +=1;
    					
    					if (reserva == null)
        				{
        					throw new Exception ("No se pudo crear una reserva para la habitacion: " + String.valueOf(habitacion.getNumHabitacion()));
        				}
        				
        				resultado += "Reserva Habitacion adicionado exitosamente: " + reserva;
    					resultado += "\n Operación terminada \n\n";
    					
    				}
    				
    				i -=1;
    			}
    		}
    		
    		panelDatos.actualizarInterfaz(resultado);
    	}
    	
    	return habitacionReservadas;
    }
    
    /* ****************************************************************
   	 * 			Consultas
   	 *****************************************************************/
    
    
    
    public void encontrarBuenosClientes()
    {
    try 
	{
		
		
    		List<VOUsuario> clientes = hotelAndes.darVOBuenosClientes();
    		
    		if (clientes.size()==0)
    		{
    			throw new Exception ("No hay buenos clientes");
    		}
    		String resultado = "Los buenos clientes son: \n\n";
    		
    		for (VOUsuario c: clientes)
    		{
    			resultado += c.toString() + "\n";
    		}
    		
    		
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		
	
	
	
	}
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
    }
    
    public void consultarConsumoHotelAndes()
    {
    try 
	{
		
			String nomServicio = JOptionPane.showInputDialog(this, "Ingrese el servicio a consultar", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE);
			Servicio ser = hotelAndes.darServiciosPorNombre(nomServicio);
			long id = ser.getId();
			Date fechaI = Date.valueOf(JOptionPane.showInputDialog(this, "Ingrese la fecha inicial del rango de fechas (YYYY-MM-DD)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE));
			Date fechaF = Date.valueOf(JOptionPane.showInputDialog(this, "Ingrese la fecha final del rango de fechas (YYYY-MM-DD)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE));
			
			String consulta1 = JOptionPane.showInputDialog(this, "Desea conocer la información de los clientes ? (SI/NO)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE);
			String consulta2 = JOptionPane.showInputDialog(this, "Desea conocer la cantidad de veces consumidas de los clientes ? (SI/NO)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE);
			String resultado = "";

			if (consulta1.equals("SI"))
			{
				List<VOUsuario> clientes = hotelAndes.darVOConsumidoresHotelAndes(fechaI, fechaF, id);
    		
				if (clientes.size()==0)
				{
					throw new Exception ("No hay clientes que hayan consumido este servicio en el rango de fechas dado");
				}
				resultado += "Los clientes son: \n\n";
    		
				for (VOUsuario c: clientes)
				{
					resultado += c.toString() + "\n";
				}
			}
			
			if (consulta2.equals("SI"))
			{
				List<Usuario> info = hotelAndes.cantidadConsumosConsumidores(fechaI, fechaF, id);
	    		
				if (info.size()==0)
				{
					throw new Exception ("No hay clientes que hayan consumido este servicio en el rango de fechas dado");
				}
				resultado += "La información es: \n\n";
    		
				for (Usuario c: info)
				{
					resultado += c.toString() + "\n";
				}
			}
    		
    		
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		
	
	
	
	}
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
    }
    
    public void consultarConsumoHotelAndes2()
    {
    try 
	{
		
			String nomServicio = JOptionPane.showInputDialog(this, "Ingrese el servicio a consultar", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE);
			Servicio ser = hotelAndes.darServiciosPorNombre(nomServicio);
			long id = ser.getId();
			Date fechaI = Date.valueOf(JOptionPane.showInputDialog(this, "Ingrese la fecha inicial del rango de fechas (YYYY-MM-DD)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE));
			Date fechaF = Date.valueOf(JOptionPane.showInputDialog(this, "Ingrese la fecha final del rango de fechas (YYYY-MM-DD)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE));
			
			String consulta1 = JOptionPane.showInputDialog(this, "Desea conocer la información de los clientes ? (SI/NO)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE);
			String consulta2 = JOptionPane.showInputDialog(this, "Desea conocer la cantidad de veces consumidas de los clientes ? (SI/NO)", "Consultar consumo HotelAndes", JOptionPane.QUESTION_MESSAGE);
			String resultado = "";

			if (consulta1.equals("SI"))
			{
				List<VOUsuario> clientes = hotelAndes.darVOConsumidoresHotelAndes2(fechaI, fechaF, id);
    		
				if (clientes.size()==0)
				{
					throw new Exception ("No hay clientes que hayan consumido este servicio en el rango de fechas dado");
				}
				resultado += "Los clientes son: \n\n";
    		
				for (VOUsuario c: clientes)
				{
					resultado += c.toString() + "\n";
				}
			}
			
			if (consulta2.equals("SI"))
			{
				List<Usuario> info = hotelAndes.cantidadConsumosConsumidores(fechaI, fechaF, id);
	    		
				if (info.size()==0)
				{
					throw new Exception ("No hay clientes que hayan consumido este servicio en el rango de fechas dado");
				}
				resultado += "La información es: \n\n";
    		
				for (Usuario c: info)
				{
					resultado += c.toString() + "\n";
				}
			}
    		
    		
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		
	
	
	
	}
	catch (Exception e) 
	{
//		e.printStackTrace();
		String resultado = generarMensajeError(e);
		panelDatos.actualizarInterfaz(resultado);
	}
    }

	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("hotelAndes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogHotelAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("hotelAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = hotelAndes.limpiarHotelAndes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Gustan eliminados\n";
			resultado += eliminados [1] + " Sirven eliminados\n";
			resultado += eliminados [2] + " Visitan eliminados\n";
			resultado += eliminados [3] + " Bebidas eliminadas\n";
			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			resultado += eliminados [5] + " Bebedores eliminados\n";
			resultado += eliminados [6] + " Bares eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaHotelAndes.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	
    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y hotelAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazHotelAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazHotelAndesApp interfaz = new InterfazHotelAndesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
