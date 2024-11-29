/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labsemana8;

/**
 *
 * @author Mayra Bardales
 */
import javax.swing.JOptionPane;

public class Juego {
    private String[][] tablero;
    private String turno;
    private Player jugadorX;
    private Player jugadorO;

    public Juego(Player jugadorX, Player jugadorO) {
        this.tablero = new String[3][3];
        this.turno = "X";
        this.jugadorX = jugadorX;
        this.jugadorO = jugadorO;
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "";
            }
        }
    }

    public void mostrarTablero() {
        StringBuilder sb = new StringBuilder("Tablero actual:\n\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(tablero[i][j].isEmpty() ? "." : tablero[i][j]);
                if (j < 2) sb.append(" | ");
            }
            sb.append("\n");
            if (i < 2) sb.append("---------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public boolean hacerMovimiento(int fila, int columna) {
        if (fila < 0 || fila > 2 || columna < 0 || columna > 2 || !tablero[fila][columna].isEmpty()) {
            return false; // Movimiento inválido
        }
        tablero[fila][columna] = turno;
        return true;
    }

    public boolean hayGanador() {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(turno) && tablero[i][1].equals(turno) && tablero[i][2].equals(turno)) return true;
            if (tablero[0][i].equals(turno) && tablero[1][i].equals(turno) && tablero[2][i].equals(turno)) return true;
        }
        // Verificar diagonales
        if (tablero[0][0].equals(turno) && tablero[1][1].equals(turno) && tablero[2][2].equals(turno)) return true;
        if (tablero[0][2].equals(turno) && tablero[1][1].equals(turno) && tablero[2][0].equals(turno)) return true;
        return false;
    }

    public boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].isEmpty()) return false;
            }
        }
        return true;
    }

    public void cambiarTurno() {
        turno = turno.equals("X") ? "O" : "X";
    }

    public Player getJugadorActual() {
        return turno.equals("X") ? jugadorX : jugadorO;
    }
}
