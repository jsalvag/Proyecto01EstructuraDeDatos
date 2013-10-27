package newairlineseller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
    Cliente c = new Cliente();
    Manejador m = new Manejador();
    Vuelos v = new Vuelos();
    Boleto b = new Boleto();
    Reportes r = new Reportes();
    
    
    void menuPrincipal() throws IOException{
        m.crear("vuelo");
        m.crear("cliente");
        System.out.println("\033[35m-------------------------------------------------------------------------------");
        System.out.println("\033[35m----------------------------- BienVenido --------------------------------------");
        System.out.println("\033[35m----------------Sistema de venta de Boletos para JSalvaG Airlines--------------");
        System.out.println("\033[35m-------------------------------------------------------------------------------");
        System.out.println("\033[35m-----------------------------Selecciones su accion-----------------------------");
        System.out.println("\033[35m-------------------------------------------------------------------------------");
        boolean salir = false;
        
        do{
            System.out.println("\n1.-Gestion de Vuelos.");
            System.out.println("2.-Gestion de clientes.");
            System.out.println("3.-Gestion de boletos.");
            System.out.println("4.-Reportes.");
            System.out.print("5.-Salir.\nSeleccion: ");
            
            switch (br.readLine()) {
                case "1": //GEstion de vuelos...
                    subMenu2();
                    break;
                case "2": 
                    subMenu1();
                    break;
                case "3": 
                    subMenu3();
                    break;
                case "4": 
                    subMenu4();
                    break;
                case "5": 
                    salir = true; 
                    System.out.println("Adios!!!");
                    break;
                default: 
                    System.out.println("Opcion invalida."); 
                    break;
            }
        }while(!salir);
    }
    
    void subMenu1() throws IOException{
        boolean salir = false;
        do{
            System.out.println("--------------------------------------");
            System.out.println("-   Registro y gestion de clientes   -");
            System.out.println("--------------------------------------");
            System.out.println("1.-Registrar Cliente Nuevo");
            System.out.println("2.-Buscar Cliente Registrado");
            System.out.println("3.-Ver lista de Clientes");
            System.out.println("4.-Regresar al menu anterior");
            System.out.println("--------------------------------------");
            System.out.print("Opcion: ");
            String op = br.readLine();

            switch(op){
                case "1":
                    c.crearCliente();
                    break;
                case "2":
                    c.buscarCliente();
                    break;
                case "3":
                    m.leerArchivoCliente();
                    break;
                case "4":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    salir = false;
            }
        }while(!salir);
    }
    
    void subMenu2() throws IOException{//Submenu de gestion de vuelos
        boolean salir = false;
        do{
            System.out.println("--------------------------------------");
            System.out.println("-    Registro y gestion de vuelos    -");
            System.out.println("--------------------------------------");
            System.out.println("1.-Crear Vuelo");
            System.out.println("2.-Buscar Vuelo");
            System.out.println("3.-Ver lista de Vuelos");
            System.out.println("4.-Ver Disponibilidad en Vuelo");
            System.out.println("5.-Regresar al menu anterior");
            System.out.println("--------------------------------------");
            System.out.print("Opcion: ");
            String op = br.readLine();

            switch(op){
                case "1"://crear vuelo.
                    v.crearVuelo();
                    break;
                case "2"://ver vuelos creados.
                    v.verVuelos();
                    break;
                case "3":
                    m.leerArchivoVuelo();
                    break;
                case "4":
                    System.out.print("Numero de Vuelo aconsultar: ");
                    String nV = br.readLine();
                    if(v.verificarVuelo(nV)){
                        v.disponibilidad(nV);
                    }else{
                        System.out.println("\033[31m|---------------------------------|");
                        System.out.println("\033[31m|El vuelo no existe en el registro|");
                        System.out.println("\033[31m|---------------------------------|");
                    }
                    break;
                case "5":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    salir = false;
            }
        }while(!salir);
    }
    
    void subMenu3() throws IOException{
        boolean salir = false;
        do{
            System.out.println("--------------------------------------");
            System.out.println("-   Gestion y venta de boletos       -");
            System.out.println("--------------------------------------");
            System.out.println("1.-Venta de Boletos");
            System.out.println("2.-Ver lista de Boletos");
            System.out.println("3.-Regresar al menu anterior");
            System.out.println("--------------------------------------");
            System.out.print("Opcion: ");
            String op = br.readLine();

            switch(op){
                case "1":
                    b.crearBoleto();
                    break;
                case "2":
                    b.imprimirBoleto();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    salir = false;
            }
        }while(!salir);
    }
    
    void subMenu4() throws IOException{
        boolean salir = false;
        do{
            System.out.println("--------------------------------------");
            System.out.println("-   Generacion de reportes           -");
            System.out.println("--------------------------------------");
            System.out.println("1.-Ventas totales.");
            System.out.println("2.-Vuelos vendidos.");
            System.out.println("3.-Regresar al menu anterior");
            System.out.println("--------------------------------------");
            System.out.print("Opcion: ");
            String op = br.readLine();

            switch(op){
                case "1":
                    r.imprimirTotal();
                    break;
                case "2":
                    r.vueloVendidos();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    salir = false;
            }
        }while(!salir);
    }
}
