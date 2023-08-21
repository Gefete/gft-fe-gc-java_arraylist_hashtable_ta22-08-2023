import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Gerard_Fernandez_fe_gc_c4_ta22_4 {

	public static void main(String[] args) {		
		
		//Declaracion de algunos productos
		ArrayList<Product> products= new ArrayList<Product>();
		products.add(new Product("Lentejas", 1.80, 5));
		products.add(new Product("Garbanzos", 1.80,20));
		products.add(new Product("Zumo Naranja", 1.20,10));
		products.add(new Product("Cocacola", 0.98,20));
		products.add(new Product("Champiñones", 2.30, 5));
		products.add(new Product("Helado de fresa", 3.50,4));
		products.add(new Product("Monster", 2.50, 10));
		
		int selectedOption=0;
		
		while(selectedOption!=4) {
			//Metodo para escoger opcion (devuelve el numero de la eleccion y asi pone en marcha el switch)
			selectedOption=showIntefaceUser();
			
			//Switch para escoger los modos
			switch (selectedOption) {
				//Añadir
				case 0: {
					products=newProduct(products);
					break;
				}
				//Mostrar todos los items
				case 1: {
					showItems(products);
					break;
				}
				//Mostrar un producto especifico
				case 2: {
					showItemespecify(products);
					break;
				}
				//Habilita el carro simulando una compra
				case 3: {
					products=carShoppingProcess(products);
					break;
				}
				//Cuando se escoge la opcion cancelar
				case 4: {
					JOptionPane.showMessageDialog(null, "El usuario ha selecionado cerrar sesion", "Cierre Sesion", selectedOption);
					break;
				}
				//Default es cuando se cierra desde la cruz
				default:
					selectedOption=4;
					JOptionPane.showMessageDialog(null, "El usuario ha selecionado cerrar sesion", "Cierre Sesion", selectedOption);
			}
			
		}
	}
	
	//Metodo para seleccionar una opcion
	public static int showIntefaceUser() {
		//Mediante un JOptionPane mostramos las opciones del array y segun cual se seleccione devolvera el numero de la posicion
	    return JOptionPane.showOptionDialog(null, "Selecciona que tarea quiere realizar", "Productos",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, new Object[] {"Añadir articulo", "informacion articulos", "informacion de 1 articulo","Compra" , "Cancelar"}, null);
	}
	
	//Metodo para añadir
	public static ArrayList<Product> newProduct(ArrayList<Product> products){
		//Añade el producto al arraylist
		products.add(new Product(JOptionPane.showInputDialog("Nombre del producto"), Double.valueOf(JOptionPane.showInputDialog("Precio del producto")), Integer.valueOf(JOptionPane.showInputDialog("Cantidad de productos"))));
		
		return products;
	}
	
	//Metodo para mostrar todos los productos
	public static void showItems(ArrayList<Product> products) {
		String concateResult="";
		//Se repetira siempre que el enumerador contenga elemntos 
		//(no haya llegado al final de la lista)
		for (Product product : products) {
			//Se guarda y concatena en un String para luego mostrarlo en un JOptionPane
			concateResult+="Producto: "+product.getName() +" // Precio: "+product.getPrice()+" // Cantidad: "+product.getQuantity()+"\n";
		}
		
		//Muestra los resultados por consola y JOptionPane
		System.out.println(concateResult);
		JOptionPane.showMessageDialog(null, concateResult, "Listado", 1);
	}
	
	//Metodo para mostrar un producto especificado por la entrasda del usuario
	public static void showItemespecify(ArrayList<Product> products) {
		String concateResult="";
		
		//Recoge la clave de busqueda de el usuario
		String search = JOptionPane.showInputDialog("Nombre del producto a buscar");
		
		//Con el for busca entre todos los objetos dentro del array
		for (Product product : products) {
			//Si encuentra el valor lo guarda en una variable String
			if (product.getName().equalsIgnoreCase(search)) {
				concateResult+="Producto: "+product.getName() +" // Precio: "+product.getPrice()+" // Cantidad: "+product.getQuantity()+"\n";
			}
		}
		
		//Muestra los resultado tanto por consola como JOptionPane
		System.out.println(concateResult);
		JOptionPane.showMessageDialog(null, concateResult, "Listado", 1);
	}
	
	
	//-------------------METODOS CARRO COMPRA---------------------------------
	
	//Metodo general del carrito de compra  
	public static ArrayList<Product> carShoppingProcess(ArrayList<Product> products){
		double totalPriceRaw = 0;
        boolean stopProcess =false;
        
        //Guarda el producto con la cantidad
        Hashtable<String, Integer> listQuantity= new Hashtable<String, Integer>();
        
        while(!stopProcess) {
        	String search = JOptionPane.showInputDialog("Nombre del producto ha añadir en el carrito");
        	
        	//Llama al metodo para comprobar que el producto existe y coger el precio en caso afirmativo
        	double priceFind = gestorItemsCarShop(products, search);
        	
        	//Si el precio es 0 o menor significa que  no encontro el producto
        	if(priceFind<=0) {
        		JOptionPane.showMessageDialog(null, "Precio de producto no encontrado", "Not Found", 0);
        	}else {
        		//Recoge la cantidad de productos de este tipo
        		int quantitymultiply=Integer.valueOf(JOptionPane.showInputDialog("Cantidad de este producto"));
        		
        		//si la cantidad es menor a 1 lo contaremos como 1 unidad
        		if(quantitymultiply<1) {
        			quantitymultiply=1;
        		}
        		
        		//Lo mete en un Hashtable para luego reflejarlo en el arraylist de productos
        		listQuantity.put(search, quantitymultiply);
        		totalPriceRaw+=priceFind*quantitymultiply;
        	}
        	
        	//Este es un comprobador para saber si el usuario quiere añadir mas productos al carrito
        	int option=JOptionPane.showConfirmDialog(null, "Añadir mas productos al carrito");
        	//Si es equivalente a una de estas 3 opciones significa que el usuario no quiere continuar
        	if(option==1 || option==2 || option==-1) {
        		//Boleano para parar el while
        		stopProcess=true;
        	}
        	
        }
        
        //Se pide que introduzca el tipo de IVA que habra
        double iva = Double.valueOf(JOptionPane.showInputDialog("Introduzca el IVA aplicado (21% o 4%)"));

        //Realiza la operacion del IVA al precio bruto
        double totalPriceIVA = totalPriceRaw + (totalPriceRaw * (iva / 100));
        
        //Variable para concatenar informacion y mostrarla en un JOptionPane
        String dataBuy="";
        
        dataBuy+="\nInformacion compra:"
        		+"\nIVA aplicado -> " + iva + "%"
        		+"\nTotal bruto --> " + totalPriceRaw + " euros"
        		+"\nTotal + IVA --> " + totalPriceIVA + " euros";
        
        
        
        //Proceso para saber la cantidad de productos total---------------------------------------
        int quantityProducts=0;
        //Se llama a un Enumerador para ir cogiendo el contenido 
        //del hastable que es la cantidad de cada producto
        Enumeration<Integer> elements = listQuantity.elements();
        
        //Coge el total de articulos en la cesta incluida la cantidad de un mismo articulo
        while(elements.hasMoreElements()) {
        	quantityProducts+=elements.nextElement();
        }
        //----------------------------------------------------------------------------------------
        dataBuy+="\nNumero de articulos comprados -> " + quantityProducts;
        
        //Muestra por consola los datos
        System.out.println(dataBuy);
        
        double cantidadPagada = Double.valueOf(JOptionPane.showInputDialog(dataBuy+"\n\nIntroduzca la cantidad pagada por el cliente"));
        
        //Condicional que comprueba si se paga correctamente el precio total
        if(cantidadPagada<totalPriceIVA) {
        	System.out.println("El cliente no ha pagado el precio correcto"
        			+ "\nCancelando compra");
        } else {
        	//Si el precio total es correcto mostrara el dinero que hay que devolver
        	//al cliente en el caso de que sobre pase el pago
        	double cambio = cantidadPagada - totalPriceIVA;
        	System.out.println("Cambio a devolver al cliente: " + cambio + " euros");
        	products=controlStock(products, listQuantity);
        	
        }
        
        return products;
	}
	
	//Metodo para buscar si el producto pasado como argumento existe
	public static double gestorItemsCarShop(ArrayList<Product> products, String search) {
			
		double price=0;
		
		//Si el producto coincide guarda el precio del mismo y lo retorna
		for (Product product : products) {
			 if (product.getName().equalsIgnoreCase(search)) {
				 price=product.getPrice();
			 }
		}

		return price;
	}
	
	//Este metodo controla que el estoc que sale por la compra de un cliente se vea reflejado en la base de datos
	public static ArrayList<Product> controlStock(ArrayList<Product> products, Hashtable<String, Integer> listQuantity){
		Enumeration<String> nameKey = listQuantity.keys();
		Enumeration<Integer> quantity = listQuantity.elements();
		
		
		while(nameKey.hasMoreElements()) {
			String nameProduct = nameKey.nextElement();
			//Con este for revisa con cada elemento de la Hastable contra todo el Arraylist de productos
			for (Product product : products) {
				if(product.getName().equalsIgnoreCase(nameProduct)) {
					product.setQuantity(quantity.nextElement());
				}	
			}
		}
		 
		//retorna el arraylist con los datos de cantidadd modificados (los que se han vendido)
		return products;
		
	}
	
}
