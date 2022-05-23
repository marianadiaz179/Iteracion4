package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto EMPLEADOHOTEL del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class Usuario implements VOUsuario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La cédula ÚNICO del empleado 
	 */
	private long cedula;
	
	/**
	 * El nombre del empleado
	 */
	private String nombre;

	/**
	 * El hotel en el que trabaja el empleado 
	 */
	private long idHotel;
	
	/**
	 * El trabajo que desempeña el empleado (GERENTE,RECEPCIONISTA,ADMINISTRADORDATOS,GERENTE,ORGANIZADOREVENTOS,CLIENTE)
	 */
	private long tipoUsuario;
	
	/**
	 * El correo del empleado 
	 */
	private String correo;
	
	/**
	 *  Los gastos en el hotel
	 */
	private double gastosHotel;
	
	/**
	 * Tiempo que se ha quedado en el hotel
	 */
	
	private int estadia;
	
	/**
	 * Habitacion en la que se hospeda
	 */
	
	private long habitacion;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
  /**
   * Constructor por defecto
   */
	public Usuario() 
  {
  	this.cedula = 0;
  	this.nombre = "";
  	this.idHotel = 0;
  	this.tipoUsuario = 0;
  	this.correo = "";
  	this.gastosHotel = 0;
  	this.estadia = 0;

	}

	/**
	 * Constructor con valores
	 */
  public Usuario(long cedulaEmpleado, String nombreEmpleado, long idHotel, long tipoUsuario, String correo, double gastos, int estadia) 
  {
	  	this.cedula = cedulaEmpleado;
	  	this.nombre = nombreEmpleado;
	  	this.idHotel = idHotel;
	  	this.tipoUsuario = tipoUsuario;
	  	this.correo = correo;
	  	this.gastosHotel = gastos;
	  	this.estadia = estadia;
	  
	}
  
  	//cedulaEmpleado - Getter y Setter

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}
	
	// nombreEmpleado - Getter y Setter

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// idHotel - Getter y Setter
	
	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	
	// tipoUsuario - Getter y Setter

	public long getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(long tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	// correo - Getter y Setter

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	// gastoshotel - Getter y Setter

	public double getGastosHotel() {
		return gastosHotel;
	}

	public void setGastosHotel(double gastosHotel) {
		this.gastosHotel = gastosHotel;
	}
	
	//estadia - Getter y Setter

	public int getEstadia() {
		return estadia;
	}

	public void setEstadia(int estadia) {
		this.estadia = estadia;
	}

	
	@Override
	public String toString() {
		return "Usuario [cedula=" + cedula + ", nombre=" + nombre + ", idHotel=" + idHotel + ", tipoUsuario="
				+ tipoUsuario + ", correo=" + correo + ", gastosHotel=" + gastosHotel + ", estadia=" + estadia
				+  "]";
	}
	
	
  
}
