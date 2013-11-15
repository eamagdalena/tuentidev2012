package tuenti.p3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problema3 {

	public static boolean DEBUG = false;

	public static void main(String[] args)  throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;
		ArrayList<Long> lista = new ArrayList<Long>();
		while(null != (s = br.readLine())){
			lista.add(Long.parseLong(s));
		}

		br.close();

		try{
			solve(lista);
		}catch(Exception e){
			System.out.println("ERROR");
			if(DEBUG){
				e.printStackTrace();
			}
		}

	}

	static class Operacion{
		long tiempoCompra = 0;
		long tiempoVenta= 0;
		long beneficio = 0;

		@Override
		public String toString() {

			return tiempoCompra + " " + tiempoVenta + " " + beneficio;
		}

	}

	private static void solve(ArrayList<Long> lista) {

		Long precioCompra = null;
		Long precioVenta = null;

		long tiempoCompra = 0L;
		long tiempoVenta = 0L;

		int iteracion = -1;

		Operacion mejorOperacion = new Operacion();

		for(Long precio : lista){
			iteracion++;

			if(precioCompra == null){
				precioVenta = precioCompra = precio;
				continue;
			}

			if(precio > precioVenta){
				precioVenta = precio;
				tiempoVenta = iteracion * 100;
			}

			if(precio < precioCompra){

				/* Guardamos la operacion actual como la mejor conocida, si lo es */

				if(precioVenta - precioCompra > mejorOperacion.beneficio){
					mejorOperacion.tiempoCompra = tiempoCompra;
					mejorOperacion.tiempoVenta = tiempoVenta;
					mejorOperacion.beneficio = precioVenta - precioCompra;
				}

				precioCompra = precioVenta = precio;
				tiempoCompra = tiempoVenta = iteracion * 100;
			}
		}

		if(precioVenta - precioCompra > mejorOperacion.beneficio){
			mejorOperacion.tiempoCompra = tiempoCompra;
			mejorOperacion.tiempoVenta = tiempoVenta;
			mejorOperacion.beneficio = precioVenta - precioCompra;
		}

		System.out.println(mejorOperacion);

	}

}
