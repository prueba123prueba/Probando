package Util.excepciones;

public class ElementoNoEncontrado extends Exception {

    private static final long serialVersionUID = 6800680535794895451L;

    public ElementoNoEncontrado() {
        super();
    }

    public ElementoNoEncontrado(final String message) {
        super(message);
    }

}
