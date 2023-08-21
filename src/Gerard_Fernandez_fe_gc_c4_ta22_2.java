import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Gerard_Fernandez_fe_gc_c4_ta22_2 {

	public static void main(String[] args) {
		//Declaramos el arraylist donde se almacena los articulos
        ArrayList<Double> carShop = new ArrayList<>();

        //Se pide la cantidad de productos que hay en la cesta
        int numArticles = Integer.valueOf(JOptionPane.showInputDialog("Introduzca el número de artículos"));

        //Se va pidiendo el precio de cada articulo
        for (int i = 0; i < numArticles; i++) {
            double priceArticle = Double.valueOf(JOptionPane.showInputDialog("Introduzca el precio del artículo " + (i + 1)));
            carShop.add(priceArticle);
        }

        //Se pide que introduzca el tipo de IVA que habra
        double iva = Double.valueOf(JOptionPane.showInputDialog("Introduzca el IVA aplicado (21% o 4%)"));

        //Variable que se utilizara como acumulador de precios de articulos
        double totalPriceRaw = 0;
        
        //Va recogiendo el precio de los articulos y los suma en total
        for (int i = 0; i < carShop.size(); i++) {
        	totalPriceRaw += carShop.get(i);
		}

        
        double totalPriceIVA = totalPriceRaw + (totalPriceRaw * (iva / 100));

        System.out.println("\nInformacion compra:");
        System.out.println("IVA aplicado -> " + iva + "%");
        System.out.println("Total bruto --> " + totalPriceRaw + " euros");
        System.out.println("Total + IVA --> " + totalPriceIVA + " euros");
        System.out.println("Numero de articulos comprados -> " + numArticles);

        double cantidadPagada = Double.valueOf(JOptionPane.showInputDialog("Introduzca la cantidad pagada por el cliente"));
        
        //Condicional que comprueba si se paga correctamente el precio total
        if(cantidadPagada<totalPriceIVA) {
        	System.out.println("El cliente no ha pagado el precio correcto"
        			+ "\nCancelando compra");
        } else {
        	//Si el precio total es correcto mostrara el dinero que hay que devolver
        	//al cliente en el caso de que sobre pase el pago
        	double cambio = cantidadPagada - totalPriceIVA;
        	System.out.println("Cambio a devolver al cliente: " + cambio + " euros");
        }

	}

}
