import java.util.Scanner;

public class Juego {
    private boolean defendido;
    private Jugador jugador;
    private Enemigo enemigo;
    private final Scanner sc = new Scanner(System.in);

    private String name;
    private int nivel = 1;

    public void establecerNombre() {
        System.out.println("¿Como te quieres llamar?");
        name = sc.next();
        System.out.println("Un placer " + name);

        jugador = new Jugador(name);
        crearEnemigo();
    }

    private void crearEnemigo() {
        if (nivel == 1) {
            enemigo = new EnemigoDebil();
        } else if (nivel == 2) {
            enemigo = new EnemigoFacil();
        } else if (nivel == 3) {
            enemigo = new EnemigoMedio();
        } else if (nivel == 4) {
            enemigo = new EnemigoFuerte();
        } else if (nivel == 5) {
            enemigo = new EnemigoFinal();
        }
    }

    public void combate() {
        System.out.println("⚔️ COMIENZA EL COMBATE ⚔️");
        System.out.println("Te enfrentas a: " + enemigo.nombre);

        while (jugador.estaVivo() && enemigo.estaVivo()) {

            turnoJugador();

            if (enemigo.estaVivo()) {
                turnoEnemigo();
            }

            mostrarEstados();
        }

        if (jugador.estaVivo()) {
            System.out.println("🎉 Has ganado!");

            if (nivel == 5) {
                System.out.println("Genial acabaste el juego");
            } else {
                nivel++;
                System.out.println("¿Quieres luchar contra el siguiente enemigo?");
                System.out.println("""
                1.- Si
                2.- No
                """);

                int opt = sc.nextInt();

                if (opt == 1) {
                    crearEnemigo();
                    jugador = new Jugador(name);  // reiniciamos stats
                    combate();
                }
            }


        } else {
            System.out.println("💀 Has perdido...");
        }
    }

    private void turnoJugador() {

        System.out.println("\nTu turno:");
        System.out.println("1. Atacar " + jugador.ataques + "/10");
        System.out.println("2. Defender " + jugador.defensas + "/5");
        System.out.println("3. Curarse " + jugador.curaciones + "/3");

        int opcion = sc.nextInt();

        if (opcion == 1) {
            jugador.atacar(enemigo);
        } else if (opcion == 2) {
            defendido = jugador.defensas();
        } else if (opcion == 3) {
            jugador.curaciones(jugador);
        } else {
            System.out.println("Opción no válida");
        }
    }

    private void turnoEnemigo() {
        System.out.println("\nTurno del enemigo...");

        if (defendido) {
            System.out.println("El enemigo falló el ataque");
            defendido = false;
        } else {
            enemigo.atacar(jugador);
        }
    }

    private void mostrarEstados() {
        System.out.println("\nEstado actual:");
        jugador.mostrarEstado();
        enemigo.mostrarEstado();
    }
}
