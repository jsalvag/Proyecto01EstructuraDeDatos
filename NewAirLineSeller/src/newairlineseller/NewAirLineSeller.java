/*Venta de boletos aéreos para JSalvaG Airlines con una flota de 10 aviones Boeing 373, con vuelos a las principales ciudades venezolanas:
 * Caracas
 * Porlamar
 * Valencia
 * Maracaibo
 * Barcelona
 * Barquisimeto
 * Cada aeronave cuenta con 3 clases: VIP, Ejecutiva y turística.
 */
package newairlineseller;

import java.io.IOException;

/**
 * @author JSalvaG
 */
public class NewAirLineSeller {
    
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu();
        
        menu.menuPrincipal();
    }
}
