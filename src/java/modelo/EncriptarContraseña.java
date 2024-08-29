package modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase que proporciona métodos para encriptar contraseñas utilizando el algoritmo SHA-256.
 */
public class EncriptarContraseña {

    /**
     * Encripta una contraseña utilizando el algoritmo SHA-256.
     * 
     * @param password La contraseña en texto plano que se desea encriptar.
     * @return La contraseña encriptada en formato hexadecimal, o {@code null} si ocurre un error.
     */
    public static String encriptar(String password) {
        try {
            // Obtiene una instancia del algoritmo de encriptación SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convierte la contraseña a un arreglo de bytes y calcula el hash
            byte[] hash = digest.digest(password.getBytes());

            // Convierte el hash a formato hexadecimal y lo retorna
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            // Maneja el caso en el que el algoritmo SHA-256 no esté disponible
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convierte un arreglo de bytes a una cadena hexadecimal.
     * 
     * @param hash El arreglo de bytes que se desea convertir a formato hexadecimal.
     * @return La representación en cadena hexadecimal del arreglo de bytes.
     */
    private static String bytesToHex(byte[] hash) {
        // Inicializa un StringBuilder con el doble de longitud del arreglo de bytes para almacenar la cadena hexadecimal
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        // Itera sobre cada byte del arreglo de bytes
        for (byte b : hash) {
            // Convierte cada byte a su representación hexadecimal y asegura que no tenga signo
            String hex = Integer.toHexString(0xff & b);

            // Si la representación hexadecimal tiene una sola cifra, añade un '0' al inicio
            if (hex.length() == 1) {
                hexString.append('0');
            }
            // Añade la representación hexadecimal al StringBuilder
            hexString.append(hex);
        }

        // Devuelve la cadena hexadecimal completa
        return hexString.toString();
    }
}
