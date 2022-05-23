package uniandes.isis2304.hotelAndes.negocio;

/**
 * Interfaz para los métodos get de TIPOUSUARIO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Tomás Angel y Mariana Díaz
 */

public interface VOTipoUsuario {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

		//idEmpleo - Getter 

		public long getIdEmpleo() ;
		
		// tipoEmpleo - Getter

		public String getTipoEmpleo() ;
		//toString - TipoUsuario

		@Override
		public String toString() ;
	
}