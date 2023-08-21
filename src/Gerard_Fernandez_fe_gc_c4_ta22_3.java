import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Gerard_Fernandez_fe_gc_c4_ta22_3 {

	public static void main(String[] args) {
		Hashtable<String, Double> products= new Hashtable<String, Double>();
			products.put("Lentejas", 1.80);
			products.put("Garbanzos", 1.80);
			products.put("Zumo Naranja", 1.20);
			products.put("Cocacola", 0.98);
			products.put("Champiñones", 2.30);
			products.put("Helado de fresa", 3.50);
			products.put("Monster", 2.50);
			
		int selectedOption=0;
		
		while(selectedOption!=3) {
			
			selectedOption=showIntefaceUser();
			
			switch (selectedOption) {
			case 0: {
				products=newProduct(products);
				break;
			}
			case 1: {
				showItems(products);
				break;
			}
			case 2: {
				showItemespecify(products);
				break;
			}
			case 3: {
				JOptionPane.showMessageDialog(null, "El usuario ha selecionado cerrar sesion", "Cierre Sesion", selectedOption);
				break;
			}
			default:
				selectedOption=3;
				JOptionPane.showMessageDialog(null, "El usuario ha selecionado cerrar sesion", "Cierre Sesion", selectedOption);
			}
		}
		

	}
	
	
	
	public static int showIntefaceUser() {
		//Mediante un JOptionPane mostramos las opciones del array y segun cual se seleccione devolvera el numero de la posicion
	    return JOptionPane.showOptionDialog(null, "Selecciona que tarea quiere realizar", "Productos",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, new Object[] {"Añadir articulo", "informacion articulos", "informacion de 1 articulo", "Cancelar"}, null);
	}
	
	public static Hashtable<String, Double> newProduct(Hashtable<String, Double> products){		
		products.put(JOptionPane.showInputDialog("Nombre del producto"), Double.valueOf(JOptionPane.showInputDialog("Precio del producto")));
		
		return products;
	}
	
	public static void showItems(Hashtable<String, Double> products) {
		Enumeration<String> keys = products.keys();
		Enumeration<Double> elements = products.elements();
		
		String concateResult="";
		//Se repetira siempre que el enumerador contenga elemntos 
		//(no haya llegado al final de la lista)
		while(keys.hasMoreElements()) {
			//mediante nextElement pide el siguiente dato
			concateResult+="Producto: "+keys.nextElement() +" // Precio: "+elements.nextElement()+"\n";
		}
		
		System.out.println(concateResult);
		JOptionPane.showMessageDialog(null, concateResult, "Listado", 1);
	}
	
	public static void showItemespecify(Hashtable<String, Double> products) {
		
		
		String concateResult="";

		String search = JOptionPane.showInputDialog("Nombre del producto a buscar");
		
		//keys.equals(JOptionPane.showInputDialog("Nombre del producto a buscar"))
		for (String storedKey : products.keySet()) {
			 if (storedKey.equalsIgnoreCase(search)) {
				 concateResult+="Producto: "+storedKey +" // Precio: "+products.get(storedKey)+"\n";
			 }
		}
		
		System.out.println(concateResult);
		JOptionPane.showMessageDialog(null, concateResult, "Listado", 1);
	}

}
