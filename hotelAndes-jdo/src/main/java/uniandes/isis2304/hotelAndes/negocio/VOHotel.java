package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de HOTEL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOHotel {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	
		// idHotel - Getter 

		public long getIdHotel() ;
		// nombreHotel - Getter 

		public String getNombreHotel() ;
		
		// numeroEstrellas - Getter 

		public int getNumeroEstrellas() ;
		
		// paisHotel - Getter 

		public String getPaisHotel() ;
		
		// ciudadHotel - Getter

		public String getCiudadHotel();
		
		// cadenaHotelera - Getter 

		public String getCadenaHotelera() ;
		// toString - Hotel

		@Override
		public String toString() ;
}