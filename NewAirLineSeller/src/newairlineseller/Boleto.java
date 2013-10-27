/*Gestion y venta de boletos*/
package newairlineseller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class Boleto {
    String nom, ape, ci, dir, telf, nVuel, fVuelo, hVuelo, nBoleto, 
             clase, ciudadOrigen, ciudadDestino, aux, asiento;
    boolean si;
    double tarifa, base;
    int fil, col;
    Manejador man = new Manejador();
    Vuelos v = new Vuelos();
    Cliente c = new Cliente();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    double tarifaBase(String nV){
//busca en el archivo vuelo la tarifa base correspondiente a un vuelo especifico
        double b;
        String[] vl = man.buscar("vuelo", nV);
        b = Double.parseDouble(vl[5]);
        return b;
    }
    
    void crearBoleto() throws IOException{
/*inicia la recoleccion de datos para la cracion del boleto y su
 * correspondiente registro en archivo*/
        String nomArch = "boletos";
/*se inicializa la variable para evitar que se cree un archivo con
 * nombre incorrecto*/
        si = false;
        
        do{
            System.out.print("Nuemro de vuelo a vender: ");
            aux = br.readLine();
            if(v.verificarVuelo(aux)){
                nVuel = aux;
                nomArch = "boletos"+nVuel;
/*se crea un archivo con el nombre boleto y la referencia del numero de vuelo*/
                man.crear(nomArch);
                nBoleto = Integer.toString(man.buscar(nomArch)+1);
                System.out.println("Venta de boleto para el vuelo nº \033[31m"+nVuel+"\033[30m.");
                System.out.println("\033[30mBoleto #\033[31m"+nBoleto+"\033[30m.");
                System.out.print("Cedula: ");
                ci = br.readLine();
                String[] ver = man.buscar("cliente", ci);
                
                if(ver!=null){
                    if(ver[0].equals(ci)){
                            System.out.println("\033[30mNombre:\t\t\033[31m"+ver[1]);
                            nom = ver[1];
                            System.out.println("\033[30mApellido:\t\033[31m"+ver[2]);
                            ape = ver[2];
                            System.out.println("\033[30mTelefono:\t\033[31m"+ver[3]);
                            telf = ver[3];
                            System.out.println("\033[30mDireccion:\t\033[31m"+ver[4]);
                            dir = ver[4];
                            System.out.println("\033[31mCliente encontrado!!!\033[30m");
                            
                            si = true;
                    }else{
                            String[] datos = c.pedirDatos2();
                            nom = datos[0]; ape = datos[1]; telf = datos[2]; dir = datos[3];
                            String data = ci+"-"+nom+"-"+ape+"-"+telf+"-"+dir;
                            man.guardar("cliente", data);
                            si = true;
                    }
                    
                }else{
                    String[] datos = c.pedirDatos2();
                    nom = datos[0]; ape = datos[1]; telf = datos[2]; dir = datos[3];
                    String data = ci+"-"+nom+"-"+ape+"-"+telf+"-"+dir;
                    man.guardar("cliente", data);
                    si = true;
                }
            }else{
                System.out.print("No se ha creado el vuelo.\n"
                        + "Desea crear un nuevo vuelo? (s/n): ");
                String aux1 = br.readLine();
                if(aux1.equals("s")){
                    v.crearVuelo();
                    si = false;
                }else{
                    si = true;
                }
            }
        }while(!si);
        
        v.disponibilidad(nVuel);

        System.out.println("Asiento");
        do{
            System.out.print("Linea: ");
            aux = br.readLine();
            fil = Integer.parseInt(aux);
            tarifa = tarifaBase(nVuel);
            if((fil>0)&&(fil<17)){
                if(fil<3){
                    tarifa += tarifa*0.25; clase = "A";
                    si = true;
                }
                if((fil>=3)&&(fil<8)){
                    tarifa += tarifa*0.15; clase = "B";
                    si = true;
                }
                if(fil>=8){
                    tarifa = tarifaBase(nVuel); clase = "C";
                    si = true;
                }
            }else{
                System.out.println("Linea invalida");
                si = false;
            }
        }while(!si);
        
        do{            
            System.out.print("Letra: ");
            aux = br.readLine();
            asiento = fil+aux.toUpperCase();
            switch(aux.toLowerCase()){
                case "a" : col = 0; si=true; break;
                case "b" : col = 1; si=true; break;
                case "c" : col = 2; si=true; break;
                case "d" : col = 3; si=true; break;
                case "e" : col = 4; si=true; break;
                case "f" : col = 5; si=true; break;
                default:
                    System.out.println("Letra invalida.");
                    si = false; break;
            }
            
        }while(!si);
        
        String data = nBoleto+"-"+nVuel+"-"+ci+"-"+(fil-1)+"-"+col+"-"+clase+"-"+tarifa;
        man.guardar(nomArch, data);
        
        imprimirBoleto(nBoleto, nVuel, ci);
    }
    
    void imprimirBoleto(String Bol, String Vuel, String CI){
        String[] vuelo= man.buscar("vuelo", Vuel);
        String[] cliente= man.buscar("cliente", CI);
        System.out.println("\033[31m-----------------------------------------------------------");
        System.out.println("\033[31mBoleto\t#\033[30m"+Bol+".\t\t\t\033[31mVuelo\t#\033[30m"+nVuel);
        System.out.println("\033[31mNombre:\t\033[30m"+cliente[2]+", "+cliente[1]+"\t\t\033[31mFecha de Vuelo:\t\033[30m"+vuelo[1]);
        System.out.println("\033[31mCedula:\t\033[30m"+cliente[0]+"\t\t\033[31mHora de Vuelo:\t\033[30m"+vuelo[2]+".");
        System.out.println("\033[31mClase:\t\033[30m"+clase+"\t\033[31mAsiento:\033[30m"+asiento+"\t\033[31mPrecio:\t\033[30m"+tarifa);
        System.out.println("\033[31mCiudad origen:\033[30m"+vuelo[3]+"\t\033[31mCiudad destino:\t\033[30m"+vuelo[4]);
        System.out.println("\033[31m-----------------------------------------------------------");
    }
    
    void imprimirBoleto() throws IOException{
        int i = 1;
        System.out.print("Introduzca numero de vuelo a consultar: ");
        String n = br.readLine();
        String[] vuelo= man.buscar("vuelo", n);
        
        while(true){
            if(vuelo==null){
                System.out.println("\033[31m-----------------------------------------------------------");
                System.out.println("El vuelo #"+n+" no existe.");
                break;
            }
            String[] boleto = man.buscar("boletos"+n, Integer.toString(i));
            i++;
            if(boleto!=null){
                String[] cliente= man.buscar("cliente", boleto[2]);
                String aux1 = "";

                switch(boleto[4]){
                    case "0" : aux1 = "A"; si=true; break;
                    case "1" : aux1 = "B"; si=true; break;
                    case "2" : aux1 = "C"; si=true; break;
                    case "3" : aux1 = "D"; si=true; break;
                    case "4" : aux1 = "E"; si=true; break;
                    case "5" : aux1 = "F"; si=true; break;
                }

                String lugar = (Integer.parseInt(boleto[3])+1)+aux1+"";
                System.out.println("\033[31m-----------------------------------------------------------");
                System.out.println("\033[31mBoleto\t#\033[30m"+boleto[1]+".\t\t\t\033[31mVuelo\t#\033[30m"+n);
                System.out.println("\033[31mNombre:\t\033[30m"+cliente[2]+", "+cliente[1]+"\t\t\033[31mFecha de Vuelo:\t\033[30m"+vuelo[1]);
                System.out.println("\033[31mCedula:\t\033[30m"+cliente[0]+"\t\t\033[31mHora de Vuelo:\t\033[30m"+vuelo[2]+".");
                System.out.println("\033[31mClase:\t\033[30m"+boleto[5]+"\t\033[31mAsiento:\033[30m"+lugar+"\t\033[31mPrecio:\t\033[30m"+boleto[6]);
                System.out.println("\033[31mCiudad origen:\033[30m"+vuelo[3]+"\t\033[31mCiudad destino:\t\033[30m"+vuelo[4]);
            }else{
                break;
            }
            
        }
        System.out.println("\033[31m-----------------------------------------------------------");
    }
}
