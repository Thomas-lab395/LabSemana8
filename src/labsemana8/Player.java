/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana8;

/**
 *
 * @author Mayra Bardales
 */
import java.util.Map;

public class Player {
    private String username;
    private String password;
    private int puntos;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
    }

    public String getUsername() {
        return username;
    }

    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }

    public int getPuntos() {
        return puntos;
    }

    public void incrementarPuntos() {
        this.puntos++;
    }

    public static boolean UsernameValido(String username, Map<String, Player> usuarios) {
        return !usuarios.containsKey(username);
    }

    public static boolean PasswordValido(String password) {
        return password != null && password.length() == 5;
    }
}
