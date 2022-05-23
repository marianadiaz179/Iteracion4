package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto LAVADOPLANCHADO del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class LavadoPlanchado implements VOLavadoPlanchado
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del servicio al que pertenece
	 */
	private long idServicio;
	
	/**
	 * El horario del servicio (NOCTURNO, DIURNO, TODO EL DIA)
	 */
	private String horario;
	
	/**
	 * El tipo de servicio que se ofrece
	 */
	private String tipoServicio;


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public LavadoPlanchado() 
	{
		this.tipoServicio = "";
		this.horario = "";
		this.idServicio = 0;
	}

	/**
	 * Constructor con valores
	 */
	public LavadoPlanchado(String tipoServicio, String horario, long idServicio)
	{
		this.tipoServicio = tipoServicio;
		this.horario = horario;
		this.idServicio = idServicio;
	}
	
	// idServicio - Getter y Setter

	public long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
	// horario - Getter y Setter

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	// tipoServicio - Getter y Setter

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	// toString - LavadoPlanchado

	@Override
	public String toString() {
		return "LavadoPlanchado [idServicio=" + idServicio + ", horario=" + horario + ", tipoServicio=" + tipoServicio
				+ "]";
	}
	
	
	
}