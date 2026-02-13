public class Personaje {

    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int ataques = 10;
    protected int defensas = 5;
    protected int curaciones = 3;

    public Personaje(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
    }

    public void atacar(Personaje objetivo) {
        if (ataques > 0) {
            objetivo.vida -= ataque;
            System.out.println(nombre + " ataca a " + objetivo.nombre + " por " + ataque + " de daño.");
            ataques --;
        } else {
            System.out.println("No quedan ataques");
        }

    }
    public boolean defensas() {
        if (defensas > 0) {
            defensas--;
            System.out.println("Este turno y el siguiente no recibiras daño");
            return true;
        }
        System.out.println("No quedan defensas");
        return false;
    }


    public void curaciones(Personaje jugador) {
        if (curaciones > 0) {
            jugador.vida += 50;
            System.out.println("Curando 40 puntos de vida");
            curaciones--;
        } else {
            System.out.println("No quedan curaciones");
        }

    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void mostrarEstado() {
        System.out.println(nombre + " - Vida: " + vida);
    }
}
