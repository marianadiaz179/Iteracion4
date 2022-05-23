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
		this.caracteristicas = "";
		this.tipoPlan = "";
	}

	/**
	 * Constructor con valores
	 */
	public PlanDePago(String tipoPlan, String caracteristicas) 
	{
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
	
	// toString - PlanDePago

	@Override
	public String toString() {
		return "PlanDePago [tipoPlan=" + tipoPlan + ", caracteristicas=" + caracteristicas + "]";
	}
	
	
}