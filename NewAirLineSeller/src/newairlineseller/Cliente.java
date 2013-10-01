/*Creacion y gestion del registro de clientes*/
package newairlineseller;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    boolean si, si1;
    String nom, ape, ci, dir, telf, aux;
    Manejador man = new Manejador();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    void crearCliente(){
/*crea un archivo con la informacion de los clientes que compran un voleto
 * se emplean ciclos reperitivos para verificar que los datos ingresados
 * son correctos o tiene un formato compatible con el sistema*/
        try {
            man.crear("cliente");
            do{
                System.out.print("Cedula: \t");
                aux = br.readLine();
                if(man.validarCedula(aux)){
                    ci = aux;
                    String[] ver = man.buscar("cliente", ci);
                    
                    if (ver==null){
                        pedirDatos();
                        String data = ci+"-"+nom+"-"+ape+"-"+telf+"-"+dir;
                        man.guardar("cliente", data);
                        si = true;
                    }else{
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
                            pedirDatos();
                            String data = ci+"-"+nom+"-"+ape+"-"+telf+"-"+dir;
                            man.guardar("cliente", data);
                            si = true;
                        }
                    }
                }else{
                    System.out.println("Cedula invalida..");
                    si = false;
                }
            }while(!si);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void pedirDatos() throws IOException{
//        pide los datos basicos de informacion para el archivo cliente.txt
        do{
            System.out.print("Nombre:\t\t");
            aux = br.readLine();
            if(man.validarNombApe(aux)){
                nom = aux;
                si1 = true;
            }else{
                System.out.println("Nombre invalido!!!");
                si1 = false;
            }
        }while(!si1);
        do{
            System.out.print("Apelido:\t");
            aux = br.readLine();
            if(man.validarNombApe(aux)){
                ape = aux;
                si1 = true;
            }else{
                System.out.println("Apellido invalido!!!");
                si1 = false;
            }
        }while(!si1);
        do{
            System.out.print("Telefono:\t");
            aux = br.readLine();
            if(man.validarTelf(aux)){
                telf = aux;
                si1 = true;
            }else{
                System.out.println("Telefono invalido!!!");
                si1 = false;
            }
        }while(!si1);
        do{
            System.out.print("Diereccion:\t");
            aux = br.readLine();
            if(man.validarDir(aux)){
                dir = aux;
                si1 = true;
            }else{
                System.out.println("Direccion invalido!!!");
                si1 = false;
            }
        }while(!si1);
    }
    
    String[] pedirDatos2() throws IOException{
/*solicita los datos de un cliente y devuelve un arreglo para utilizar
 * los datos en otra clase*/
        String[] datos = new String[4];
        do{
            System.out.print("Nombre:\t\t");
            aux = br.readLine();
            if(man.validarNombApe(aux)){
                datos[0] = aux;
                si1 = true;
            }else{
                System.out.println("Nombre invalido!!!");
                si1 = false;
            }
        }while(!si1);
        do{
            System.out.print("Apelido:\t");
            aux = br.readLine();
            if(man.validarNombApe(aux)){
                datos[1] = aux;
                si1 = true;
            }else{
                System.out.println("Apellido invalido!!!");
                si1 = false;
            }
        }while(!si1);
        do{
            System.out.print("Telefono:\t");
            aux = br.readLine();
            if(man.validarTelf(aux)){
                datos[2] = aux;
                si1 = true;
            }else{
                System.out.println("Telefono invalido!!!");
                si1 = false;
            }
        }while(!si1);
        do{
            System.out.print("Diereccion:\t");
            aux = br.readLine();
            if(man.validarDir(aux)){
                datos[3] = aux;
                si1 = true;
            }else{
                System.out.println("Direccion invalido!!!");
                si1 = false;
            }
        }while(!si1);
        return datos;
    }
    
    void buscarCliente() throws IOException{
//        imprime por pantalla los datos de un cliente registrado en el archivo
        do{
            System.out.print("Introduzca cedula a buscar: ");
            aux = br.readLine();
            if(man.validarCedula(aux)){
                ci = aux;
                String[] ver = man.buscar("cliente", ci);

                if (ver==null){
                    System.out.println("\033[31mCliente no encontrado...\033[30m");
                    si = true;
                }else{
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
                        pedirDatos();
                        String data = ci+"-"+nom+"-"+ape+"-"+telf+"-"+dir;
                        man.guardar("cliente", data);
                        si = true;
                    }
                }
            }else{
                System.out.println("Cedula invalida..");
                si = false;
            }
        }while(!si);
    }
}
