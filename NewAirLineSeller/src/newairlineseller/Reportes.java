/*Generacion de reportes de ventas de voletos*/
package newairlineseller;

import java.io.File;
import java.io.IOException;

public class Reportes {
    Manejador man = new Manejador();
    
    double[] sumarMontos() throws IOException{
        double[] monto = {0,0,0,0,0,0,0,0}, datos;
        int i=1;
        
        while(new File("boletos"+i+".txt").exists()){
            datos = man.buscarMontos(i);
            monto[0] += datos[0];//total general
            monto[1] += datos[1];//total calse A
            monto[2] += datos[2];//total calse B
            monto[3] += datos[3];//total calse C
            monto[4] += datos[4];//total boletos
            monto[5] += datos[5];//total boletos A
            monto[6] += datos[6];//total boletos B
            monto[7] += datos[7];//total boletos C
            
            i++;
        }
        return monto;
    }
    
    void imprimirTotal() throws IOException{
        double[] total = sumarMontos();
        System.out.println("\033[30mEl monto total recaudado en \033[31m"+(int)total[4]
                +"\033[30m boletos vendidos es: \033[31m"+total[0]+"\033[30m");
        System.out.println("\033[30mEl monto total recaudado en la clase A con \033[31m"
                +(int)total[5]+"\033[30m boletos es: \033[31m"+total[1]+"\033[30m");
        System.out.println("\033[30mEl monto total recaudado en la clase B con \033[31m"
                +(int)total[6]+"\033[30m boletos es: \033[31m"+total[2]+"\033[30m");
        System.out.println("\033[30mEl monto total recaudado en la clase C con \033[31m"
                +(int)total[7]+"\033[30m boletos es: \033[31m"+total[3]+"\033[30m");
    }
    
    void vueloVendidos() throws IOException{
        double[] datos;
        int i = 1;
        int v = man.buscar("vuelo");
        System.out.println("--------------------------------------------------------");
        System.out.println("Existen \033[31m"+v+"\033[30m vuelos creados.");
        while (new File("boletos"+i+".txt").exists()){
            datos = man.buscarMontos(i);
            System.out.println("--------------------------------------------------------");
            System.out.println("Para el vuelo #\033[31m"+i+"\033[30m, se vendieron:");
            System.out.println("Clase A: \033[31m"+(int)datos[5]+"\033[30m, que suman : \033[31m"+datos[1]+"\033[30m Bs.");
            System.out.println("Clase B: \033[31m"+(int)datos[6]+"\033[30m, que suman : \033[31m"+datos[2]+"\033[30m Bs.");
            System.out.println("Clase C: \033[31m"+(int)datos[7]+"\033[30m, que suman : \033[31m"+datos[3]+"\033[30m Bs.");
            System.out.println("--------------------------------------------------------");
            i++;
        }
    }
}
