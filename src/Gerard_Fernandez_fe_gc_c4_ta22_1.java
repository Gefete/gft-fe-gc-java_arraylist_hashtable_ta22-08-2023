import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Gerard_Fernandez_fe_gc_c4_ta22_1 {

	public static void main(String[] args) {
		//Declaramos la variable hash para almacenar claves -> valor
        Hashtable<String, Double> notesAvg = new Hashtable<>();
        
        //Recogemos la cantidad de alumnos del usuario
        int numberAlumns = Integer.valueOf(JOptionPane.showInputDialog("Introduce el número de alumnos"));
        
        //Se repite a partir de la cantidad de alumnos propuesta por el usuario
        for (int i = 0; i < numberAlumns; i++) {
            
        	//recoge nombre mediante JOptionPane
            String nameAlumn = JOptionPane.showInputDialog("Introduce nombre alumno");
            
            //Tambien se recoge las notas mediante JOptionPane
            String[] notesString =  JOptionPane.showInputDialog("Introduce las notas del alumno separadas por espacios").split(" ");
            
            double sumNotes = 0;
            
            //se recogen las notas del alumno y se suman para optener el total
            for (int j = 0; j < notesString.length; j++) {
				String noteStr = notesString[j];
				double nota = Double.parseDouble(noteStr);
                sumNotes += nota;
			}
            
            //Se realiza una operacionpara obtener la media 
            //mediante el valor de resultado total de las notas y el tamaño del array
            double notaMedia = sumNotes / notesString.length;
            //La nota media se añade como valor de la clave alumno
            notesAvg.put(nameAlumn, notaMedia);
        }
        
        //Declaramos enumerations para poder interactuar con los valores almacenados 
        //en el hash
        Enumeration<String> keys = notesAvg.keys();
		Enumeration<Double> elements = notesAvg.elements();
    	
		//Se repetira siempre que el enumerador contenga elemntos 
		//(no haya llegado al final de la lista)
		while(keys.hasMoreElements()) {
			//mediante nextElement pide el siguiente dato
			System.out.println("Alumno: "+keys.nextElement());
			System.out.println("Nota media: "+elements.nextElement()+"\n");
		}

	}

}
