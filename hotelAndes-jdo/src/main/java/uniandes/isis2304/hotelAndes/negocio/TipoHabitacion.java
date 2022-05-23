package uniandes.isis2304.hotelAndes.negocio;

public class TipoHabitacion  implements VOTipoHabitacion{
	
	private long idTipoHabitacion ;
	
	private String tipoHabitacion;
	
	public TipoHabitacion() 
	{
		this.idTipoHabitacion = 0;
		this.tipoHabitacion = "";
	}
	
	public TipoHabitacion(long idTipoHabitacion, String tipoHabitacion)
	{
		this.idTipoHabitacion = idTipoHabitacion;
		this.tipoHabitacion = tipoHabitacion;
	}

	public long getIdTipoHabitacion() {
		return idTipoHabitacion;
	}

	public void setIdTipoHabitacion(long idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	@Override
	public String toString() {
		return "TipoHabitacion [idTipoHabitacion=" + idTipoHabitacion + ", tipoHabitacion=" + tipoHabitacion + "]";
	}


	

}
