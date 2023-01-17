package beans;



public class ConversionCF {
	
	private float celsius, fahrenheit, temperatura;
	private String tipo;

	
	public ConversionCF(String tipo, float temperatura) {
		this.tipo = tipo;
		this.temperatura = temperatura;
	}
	
	
	public float convertir(String tipoConversion, float cantidad) {
		
		float resultado = 0;
		
		if(tipoConversion.equals("c")) {
			resultado = cantidad * 9 / 5 + 32;
		}
		
		if(tipoConversion.equals("f")) {
			resultado = (cantidad - 32) * 5 / 9;
		}
//		resultado = Math.round(resultado * 10) / 10; no funciona así
		resultado *= 10;
		resultado = Math.round(resultado);
		resultado /= 10;
		return resultado;
	}


	public float getTemperatura() {
		return temperatura;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
	
}
