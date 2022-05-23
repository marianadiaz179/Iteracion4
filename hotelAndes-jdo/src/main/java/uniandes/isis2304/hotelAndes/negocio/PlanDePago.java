package uniandes.isis2304.hotelAndes.negocio;

/**
* Clase para modelar el concepto PISCINA del negocio de HotelAndes
*
* @author Mariana Diaz - Tomás Angel
*/
public class PlanDePago implements VOPlanDePago
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;
	/**
	 * El tipo del plan de pago
	 */
	private String tipoPlan;

	/**
	 * Las caracteristicas del plan de pago
	 */
	private String caracteristicas;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
/**
* Constructor por defecto
*/
	public PlanDePago() 
	{
		this.id = 0;
		this.caracteristicas = "";
		this.tipoPlan = "";
	}

	/**
	 * Constructor con valores
	 */
	public PlanDePago(long id, String tipoPlan, String caracteristicas) 
	{
		this.id = id;
		this.caracteristicas = caracteristicas;
		this.tipoPlan = tipoPlan;
	}
	
	// tipoPlan - Getter y Setter

	public String getTipoPlan() {
		return tipoPlan;
	}

	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	
	// caracteristicas - Getter y Setter

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PlanDePago [id=" + id + ", tipoPlan=" + tipoPlan + ", caracteristicas=" + caracteristicas + "]";
	}
	
	// toString - PlanDePago

	
	
	
}