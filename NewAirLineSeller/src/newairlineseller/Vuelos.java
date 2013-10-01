/*Creacion y gestion vuelos*/
package newairlineseller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vuelos {
    String fechaVuelo, horaVuelo, ciudadOrigen, ciudadDestino, aux, color;
    double tarifaBase;
    boolean si, dispon;
    int cantNaves = 10, disp = 96;
    Manejador man = new Manejador();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    void crearVuelo() throws IOException{//Crear vuelo
        man.crear("vuelo");//Crear archivo vuelo.txt
        int num = man.buscar("vuelo")+1;//busca en el numero del ultimo vuelo creado y le suma 1 para crear el siguiente vuelo.
        System.out.println("\033[30mVuelo #\033[31m"+num+"\033[30m.");//Imprime en pantalla el numero de vuelo
        do{//bucle para verificacion de datos ingresados para la cracion del vuelo
            System.out.print("Fecha del Vuelo: ");
            aux = br.readLine();
            if(man.validarFecha(aux)){//evalua mediante una funcion si el dato fue ingrado en el formato correcto.
                fechaVuelo = aux;
                si = true;
                //Si el dato es correcto se carga el valor de la variable auxiliar en la variable definitiva.
                //la variable "si" se evalua a verdadero.
            }else{
                System.out.println("Fecha invalida.");
                si = false;
                //si el dato no tiene un formato valido, le indica al usuario que el dato no paso la verificacion
                //evalua la variable "si" a falso para que el bucle se repita y permita ingresarl el dato nuevamente.
            }
        }while(!si);//El bucle se reperita mientras la variable "si" se evalue falsa.
        do{//bucle de virificacion del formato de la hora.
            System.out.print("Hora de salida: ");
            aux = br.readLine();
            if(man.validarHora(aux)){
                horaVuelo = aux;
                si = true;
            }else{
                System.out.println("Hora invalida.");
                si = false;
            }
        }while(!si);

        System.out.println("Ciudad de origen: ");
        ciudadOrigen = man.selecCiudad();//mediante la fucion selecCiudad carga un codigo de ciudad en la variable correspondiente
        
        System.out.println("Ciudad destino: ");
        ciudadDestino = man.selecCiudad();
        
        do{
            System.out.print("Tarifa base: ");
            aux = br.readLine();
            if(man.validarTarifa(aux)){
                tarifaBase = Double.parseDouble(aux);
                si = true;
            }else{
                System.out.println("Fecha invalida.");
                si = false;
            }
        }while(!si);
        String data = num+"-"+fechaVuelo+"-"+horaVuelo+"-"+ciudadOrigen
                +"-"+ciudadDestino+"-"+tarifaBase;
        man.guardar("vuelo", data);
    }
    
    void verVuelos() throws IOException{//muestra los vuelos creados
        System.out.print("Introduzca el numero del vuelos: ");
        aux = br.readLine();
        String[] datos = man.buscar("vuelo", aux);
        //si la funcion buscar() devuelve un null significa que el archivo esta vacio
        if(datos!=null){
            System.out.println("Vuelo #"+datos[0]);
            System.out.println("Con fecha: "+datos[1]+" - a las "+datos[2]);
            System.out.println("De "+datos[3]+" a "+datos[4]);
            System.out.println("Con una tatira base de "+datos[5]);
        }else{
            System.out.println("Vuelo no encontrado...!");
        }
        
    }

    boolean verificarVuelo(String n){//verifica la existencia de un vuelo en le archivo vuelo.txt
        String[] data = man.buscar("vuelo", n);
        if(data!=null){
            if(data[0].equals(n)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    String[]infoVuelo(String n){//envia un arreglo con la informacion de un buelo buscado
        String[] data;
        data = man.buscar("vuelo",n);
        return data;
    }
    
    void disponibilidad(String numV){
/*imprime en pantalla una matriz con la disposicion de los diferentes asientos
 * en un vuelo. Se incican en color negro los asientos disponibles, y en
 * color rojo los asientos ocupados*/
        int i,j,x;
        
        for(x=1; x<=16; x++){
            if(x<=9){
                System.out.print("\033[32m |"+x+"| ");
            }else{
                System.out.print("\033[32m|"+x+"| ");
            }
        }

        System.out.println();
        System.out.println("\033[32m-------------------------------------------------------------------------------");
        for (i=0;i<=5;i++){
            if(i==3){
                System.out.println();
            }
            for (j=0;j<=15;j++){
                //funcion de ver disponibilidad
                if(man.verDispon(j, i, numV)){
                    dispon = false;
                }else{
                    dispon = true;
                }
                if(dispon){
                    disp--;
                    color = "\033[31m";
                }else{
                    color = "\033[30m";
                }
                switch(i){
                    case 0 : System.out.print(color+" |A| "); break;
                    case 1 : System.out.print(color+" |B| "); break;
                    case 2 : System.out.print(color+" |C| "); break;
                    case 3 : System.out.print(color+" |D| "); break;
                    case 4 : System.out.print(color+" |E| "); break;
                    case 5 : System.out.print(color+" |F| "); break;
		}
            }
            System.out.println();
        }
        System.out.println("\033[32m-------------------------------------------------------------------------------");
        System.out.println("Lugares disponibles: \033[31m"+disp);
    }
    
}