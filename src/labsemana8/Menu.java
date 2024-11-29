/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana8;

/**
 *
 * @author Mayra Bardales
 */
import javax.swing.*;
import java.util.*;

public class Menu {
    private Map<String, Player> usuarios;
    private Player jugadorActual;

    public Menu() {
        this.usuarios = new HashMap<>();
    }

    public void mostrarMenuInicio() {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Menú Inicio\n\n1. Iniciar Sesión\n2. Registro\n3. Salir");
            if (opcion == null) break;

            switch (opcion) {
                case "1":
                    iniciarSesion();
                    break;
                case "2":
                    registrarUsuario();
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Gracias por jugar. ¡Hasta pronto!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private void iniciarSesion() {
        String username = JOptionPane.showInputDialog("Ingrese su username:");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña:");

        if (usuarios.containsKey(username) && usuarios.get(username).validarPassword(password)) {
            jugadorActual = usuarios.get(username);
            JOptionPane.showMessageDialog(null, "¡Bienvenido, " + jugadorActual.getUsername() + "!");
            mostrarMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
        }
    }

    private void registrarUsuario() {
        String username = JOptionPane.showInputDialog("Ingrese su username:");
        if (!Player.UsernameValido(username, usuarios)) {
            JOptionPane.showMessageDialog(null, "El username ya existe. Intenta con otro.");
            return;
        }
        String password = JOptionPane.showInputDialog("Ingrese su contraseña (5 caracteres):");
        if (!Player.PasswordValido(password)) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener 5 caracteres.");
            return;
        }
        usuarios.put(username, new Player(username, password));
        JOptionPane.showMessageDialog(null, "Registro exitoso. Ahora puede iniciar sesión.");
    }

    private void mostrarMenuPrincipal() {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Menú Principal\n\n1. Jugar X-0\n2. Mostrar Ranking\n3. Cerrar Sesión\n4. Salir");
            if (opcion == null) break;

            switch (opcion) {
                case "1":
                    jugarX0();
                    break;
                case "2":
                    mostrarRanking();
                    break;
                case "3":
                    cerrarSesion();
                    return;
                case "4":
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private void jugarX0() {
        String usernameO = JOptionPane.showInputDialog("Ingrese el username del segundo jugador:");
        if (!usuarios.containsKey(usernameO) || usernameO.equals(jugadorActual.getUsername())) {
            JOptionPane.showMessageDialog(null, "Jugador no válido.");
            return;
        }

        Player jugadorO = usuarios.get(usernameO);
        Juego juego = new Juego(jugadorActual, jugadorO);

        while (true) {
            juego.mostrarTablero();
            String input = JOptionPane.showInputDialog("Turno de " + juego.getJugadorActual().getUsername() + ". Ingrese fila y columna (0-2):");
            if (input == null) break;

            try {
                String[] partes = input.split(" ");
                int fila = Integer.parseInt(partes[0]);
                int columna = Integer.parseInt(partes[1]);

                if (juego.hacerMovimiento(fila, columna)) {
                    if (juego.hayGanador()) {
                        JOptionPane.showMessageDialog(null, "¡" + juego.getJugadorActual().getUsername() + " ha ganado!");
                        juego.getJugadorActual().incrementarPuntos();
                        break;
                    }
                    if (juego.esEmpate()) {
                        JOptionPane.showMessageDialog(null, "¡Empate!");
                        break;
                    }
                    juego.cambiarTurno();
                } else {
                    JOptionPane.showMessageDialog(null, "Movimiento inválido. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Inténtalo de nuevo.");
            }
        }
    }

    private void mostrarRanking() {
        StringBuilder ranking = new StringBuilder("Ranking de Jugadores:\n\n");
        usuarios.values().stream()
                .sorted(Comparator.comparingInt(Player::getPuntos).reversed())
                .forEach(jugador -> ranking.append(jugador.getUsername())
                        .append(": ")
                        .append(jugador.getPuntos())
                        .append(" puntos\n"));

        JOptionPane.showMessageDialog(null, ranking.toString());
    }

    private void cerrarSesion() {
        JOptionPane.showMessageDialog(null, "Sesión cerrada.");
        jugadorActual = null;
    }
}
