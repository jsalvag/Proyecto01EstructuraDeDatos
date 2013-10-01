/*Clase creada para la interaccion con los archivos de texto*/
package newairlineseller;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manejador {
    PrintWriter pf;
    FileReader fr;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    void crear(String nArchivo){
/*crea el archivo vacio o verifica su existencia,
 * necesario para almacenar los datos que generan los distintos metodos
 * nArchivo es el parametro con el nombre que tendra el ficrero de texto
 */
        try{
            pf = new PrintWriter(new FileWriter(nArchivo+".txt",true));
            pf.close();
            System.out.println("Archivo "+nArchivo+".txt se genero o existe...");
        }catch (Exception e){
            System.out.println("Error en creacion de archivo.\nError: "+e);
        }
    }
    
    void guardar(String nArchivo, String nDatos){
/*crea o verifica la existencia del archivo para agregar los datos
 * generados en los diferentes metodos que lo invocan
 */
        try{
            pf = new PrintWriter(new FileWriter(nArchivo+".txt",true));
            pf.println(nDatos);
            pf.close();
            System.out.println("Archivo "+nArchivo+".txt se genero o existe...");
        }catch (Exception e){
            System.out.println("Error de guardado en archivo.\nError: "+e);
        }
    }
    
    String[] buscar(String nArchivo, String ID){
/*busca por numero de cedula una linea especifica dentro de un archivo de texto
 */
        String[] datos;
        try {
            fr = new FileReader(nArchivo+".txt");
            BufferedReader brf = new BufferedReader(fr);
            String linea;
            while((linea=brf.readLine())!=null){
                datos = linea.split("-");
                if(datos[0].equalsIgnoreCase(ID)){
                    return datos;
                }
            }
        } catch (Exception e) {
        }
        return datos = null;
    }
    
    double[] buscarMontos(int nVuelo) throws IOException{
/*busca los montos en un archivo de texto*/
        String[] datos;
        double[] suma = {0,0,0,0,0,0,0,0};
        //suma contiene{0-monto total, 1-monto clase A, 2-monto clase B, 0-monto clase C,
    //4-cantidad total de boletos, 5-cant boletos A, 6-cant boletos B, 7-cant boletos C}
        fr = new FileReader("boletos"+nVuelo+".txt");
        BufferedReader brf = new BufferedReader(fr);
        String linea;

        while((linea=brf.readLine())!=null){
            datos = linea.split("-");
            if(datos!=null){
                suma[4]++;
                double aux = Double.parseDouble(datos[6]);
                suma[0] += aux;
                if(datos[5].equals("A")){
                    suma[1] += aux;
                    suma[5]++;
                }
                if(datos[5].equals("B")){
                    suma[2] += aux;
                    suma[6]++;
                }
                if(datos[5].equals("C")){
                    suma[3] += aux;
                    suma[7]++;
                }
            }
        }
        fr.close();
        return suma;
    }
    
    int buscar(String nArchivo) throws IOException{
/*busca en un archivo el valor de la primera posicion en cada linea para
 * saber el siguiente numero para el el vuelo*/
        int mayor = 0;
        String[] datos;
        fr = new FileReader(nArchivo+".txt");
        BufferedReader brf = new BufferedReader(fr);
        String linea;
        while((linea = brf.readLine())!=null){
            datos = linea.split("-");
            
            if (datos.length==1){
                return 0;
            }else{
//                for (int i = 0; i < datos.length; i++) {
                    int indice = Integer.parseInt(datos[0]);
                    if(indice>= mayor){
                        mayor = indice;
                    }else{
                        return -1;
                    }
//                }
            }
        }
        return mayor; //retorna el valor del ultimo vuelo
    }
    
    void leerArchivoCliente() throws IOException{
/*Imprime en pantalla una lista entendible con el contenido del archivo
 * de clientes
 */
        fr = new FileReader("cliente.txt");
        BufferedReader brf = new BufferedReader(fr);
        String linea;
        System.out.println("Litado de clientes");
        while((linea=brf.readLine())!=null){
            String[] datos = linea.split("-");
            System.out.println("Cedula:\t\t"+datos[0]);
            System.out.println("Nombre:\t\t"+datos[2]+" "+datos[1]);
            System.out.println("Telefono:\t"+datos[3]+"\nDireccion:\t"+datos[4]);
            System.out.println();
        }
    }
    
    void leerArchivoVuelo() throws IOException{
/*Imprime en pantalla una lista entendible con el contenido del archivo
 * de clientes
 */
        fr = new FileReader("vuelo.txt");
        BufferedReader brf = new BufferedReader(fr);
        String linea;
        System.out.println("\033[31mLitado de vuelos");
        System.out.println("|---------------------------------------------------------------|");
        while((linea=brf.readLine())!=null){
            String[] datos = linea.split("-");
            System.out.println("|Vuelo nº | Fecha      | Hora  | Origen | Destino | Tarifa\t|");
            System.out.println("|    "+datos[0]+"\t  | "+datos[1]+" | "+datos[2]+" |   "+datos[3]+"  |   "+datos[4]+"   | "+datos[5]+"\t|");
            System.out.println("|---------------------------------------------------------------|");
//            System.out.println(  "Nº de vuelo:\t"+datos[0]);
//            System.out.println( "Fecha y hora:\t"+datos[2]+" "+datos[1]);
//            System.out.println("Ciudad Origen:\t"+datos[3]
//                           +"\nCiudad Destino:\t"+datos[4]);
//            System.out.println(  "Tarifa Base:\t"+datos[5]);
//            System.out.println("--------------------------------------");
//            System.out.println();
        }
    }
    
    boolean validarCedula(String ci){
        //valida la cedula comparandola con un patron
        Pattern pat = Pattern.compile("[0-9]{6,8}");
        Matcher mat = pat.matcher(ci);
        if(mat.matches()){return true;}
        else{return false;}
    }
    
    boolean validarNombApe(String nom){
        //valida el nombre y el apellido comparandolo con un patron
        boolean patF = Pattern.matches("[a-zA-Z]{1,100}",nom);
        return patF;
    }
    
    boolean validarTelf(String telf){
        //valida el telefono comparandolo con un patron
        boolean patF = Pattern.matches("[0-9]{11}",telf);
        return patF;
    }
    
    boolean validarDir(String dir){
        //valida la direccion comparandola con un patron
        boolean patF = Pattern.matches("[a-zA-Z ]{1,200}",dir);
        return patF;
    }
    
    boolean validarTarifa(String t){
        boolean patT = Pattern.matches("\\d+\\.*\\d*",t);
        return patT;
    }
    
    boolean validarFila(String fil){
        //verifica que el numero de fila este entre 1 y 16
        boolean patT = Pattern.matches("[1-16]",fil);
        return patT;
    }
    
    boolean validarHora(String vHora){
        //verifica que la hora ingresada tenga un formato valido.
        boolean patH = Pattern.matches("[0-2]?\\d:[0-5]?\\d",vHora);
        return patH;
    }
    
    boolean validarFecha(String vFecha){
        //verifica que la fecha tenga un formato valido.
        boolean patF = Pattern.matches("\\d+/\\d+/\\d{4}",vFecha);
        return patF;
    }
    
    String selecCiudad() throws IOException{
        /*muestra una lista de ciudades disponibles para los vuelos y devuelve
         * el codigo para una de ella*/
        String ciudad = "";
        boolean val=false;
        do {
            System.out.println("1 - Caracas.");
            System.out.println("2 - Barquisimeto.");
            System.out.println("3 - Valencia.");
            System.out.println("4 - Barcelona.");
            System.out.println("5 - Maracaibo.");
            System.out.println("6 - Porlamar");
            System.out.print("Seleccione Ciudad: ");

            String aux3 = br.readLine();
            if(Pattern.matches("[1-6]", aux3)){
                switch(aux3){
                    case "1" : ciudad = "CCS"; val=true; break;
                    case "2" : ciudad = "BRM"; val=true; break;
                    case "3" : ciudad = "VLN"; val=true; break;
                    case "4" : ciudad = "BLA"; val=true; break;
                    case "5" : ciudad = "MAR"; val=true; break;
                    case "6" : ciudad = "POR"; val=true; break;
                }
            }else{
                System.out.println("Seleccion invalida.");
                val=false;
            }
        } while (!val);
        return ciudad;
    }
    
    boolean verDispon(int fil, int col, String numVuelo){
//        verifica la disponibilidad de un asiento en un vuelo especifico
        String[] datos;
        try {
            fr = new FileReader("boletos"+numVuelo+".txt");
            BufferedReader brf = new BufferedReader(fr);
            String linea;
            while((linea=brf.readLine())!=null){
                datos = linea.split("-");
                if((datos[3].equals(fil+""))&&(datos[4].equals(col+""))){
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return true;
    }
    
}
