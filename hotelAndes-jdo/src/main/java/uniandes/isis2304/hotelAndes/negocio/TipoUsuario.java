package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto TIPOUSUARIO del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class TipoUsuario implements VOTipoUsuario
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador del empleo
	 */
	private long idEmpleo;
	
	/**
	 * El nombre del empleo
	 */
	private String tipoEmpleo;



	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public TipoUsuario() 
	{
		this.idEmpleo = 0;
		this.tipoEmpleo = "";
	}

	/**
	 * Constructor con valores
	 */
	public TipoUsuario(long idEmpleo, String tipoEmpleo)
	{
		this.idEmpleo = idEmpleo;
		this.tipoEmpleo = tipoEmpleo;
	}
	
	//idEmpleo - Getter y Setter

	public long getIdEmpleo() {
		return idEmpleo;
	}

	public void setIdEmpleo(long idEmpleo) {
		this.idEmpleo = idEmpleo;
	}
	
	// tipoEmpleo - Getter y Setter

	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}
	
	//toString - TipoUsuario

	@Override
	public String toString() {
		return "TipoUsuario [idEmpleo=" + idEmpleo + ", tipoEmpleo=" + tipoEmpleo + "]";
	}
	
	
}
	
