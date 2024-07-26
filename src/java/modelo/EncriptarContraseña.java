package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptarContraseña {
    
    // Metodos para enncriptar contraseñas utilizando el algoritmos SHA-256
    
    public static String encriptar(String password){
        try {
            // Obtiene una instancia del algoritmo el cual se va a encriptar
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            // Convierte la contraseña a un arregllo de bytes y calcula el hash
            byte[] hash = digest.digest(password.getBytes());
            
            // Convierte el hash en hexadecimal y lo retorna
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String bytesToHex(byte[] hash){
        // Inicializa un String con el doble de longitud para almacenar la cadena hexadecimal
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        
        // Iterador de cada byte del arreglo de bytes
        for (byte b : hash) {
            
            // Convierte cada byte en su representacion hexadecimal y se utiliza para asegurarse de que no se trate de un valor con signo
            String hex = Integer.toHexString(0xff & b);
            
            // Si la contraseña stiene una sola cifra agrega un 0 por cada byte
            if (hex.length() == 1) {
                // Añade al StringBuilder
                hexString.append(0);
            }
            hexString.append(hex);
        }
        // Devuelve la cadena hexadecimal completa
        return hexString.toString();
    }
}
