import java.util.Scanner;

public class Juego {
    private boolean defendido;
    private Jugador jugador;
    private Enemigo enemigo;
    private final Scanner sc = new Scanner(System.in);

    private String name;
    private String enemy;

    public void establecerNombre() {
        System.out.println("¿Como te quieres llamar?");
        name = sc.next();
        System.out.println("Un placer " + name);

        System.out.println("¿Como quieres que se llame tu enemigo?");
        enemy = sc.next();
        System.out.println("Me gusta el nombre " + enemy);

        jugador = new Jugador(name);

        enemigo = new Enemigo(enemy);
    }

    public void combate() {

        System.out.println("⚔️ COMIENZA EL COMBATE ⚔️");

        while (jugador.estaVivo() && enemigo.estaVivo()) {

            turnoJugador();

            if (enemigo.estaVivo()) {
                turnoEnemigo();
            }

            mostrarEstados();
        }

        if (jugador.estaVivo()) {
            System.out.println("🎉 Has ganado!");
            System.out.println("¿Quieres jugar contra un enemigo mas dificl?");
            System.out.println("""
                1.- Si
                2.- No
                """);
            int opt = sc.nextInt();
            if (opt == 1) {
                enemigo = new Enemigo(enemy);
                jugador = new Jugador(name);
                combate();

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
        } else {
            jugador.curaciones(jugador);
        }
    }

    private void turnoEnemigo() {
        System.out.println("\nTurno del enemigo...");
        if (defendido) {
            System.out.println("El enemigo fallo");
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
