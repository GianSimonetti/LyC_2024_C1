package lyc.compiler.terceto;

public class Terceto {
    private String operacion;
    private String operando1;
    private String operando2;

    public Terceto(String operacion, String operando1, String operando2) {
        this.operacion = operacion;
        this.operando1 = operando1;
        this.operando2 = operando2;
    }


    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getOperando1() {
        return operando1;
    }

    public void setOperando1(String operando1) {
        this.operando1 = operando1;
    }

    public String getOperando2() {
        return operando2;
    }

    public void setOperando2(String operando2) {
        this.operando2 = operando2;
    }

    @Override
    public String toString() {
        return "(" + operacion + ", " + operando1 + ", " + operando2 + ")";
    }
}