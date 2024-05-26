package lyc.compiler.terceto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TercetoManager {
    private ArrayList<Terceto> lista = new ArrayList<>();

    private Stack<Integer> pila = new Stack<Integer>();
    private Stack<String> pilaM = new Stack<String>(); // condiciones multiples
    private Stack<String> expresionesPrimos = new Stack<String>();
    private Stack<String> frase = new Stack<String>();
    private Stack<String> subFrase = new Stack<String>();

    public void insertar(String elemento) {
        switch (elemento) {
            case "UNSTACK":
                int index = pila.pop() - 1;
                Terceto t = lista.get(index);
                lista.set(index, new Terceto(t.getOperacion(), t.getOperando1(), "#" + (lista.size() + 1)));
                if (!pilaM.isEmpty() && (pilaM.peek().equals("AND") || pilaM.peek().equals("OR"))) {
                    pilaM.pop();
                    int index2 = pila.pop() - 1;
                    Terceto t2 = lista.get(index2);
                    lista.set(index2, new Terceto(t2.getOperacion(), t2.getOperando1(), "#" + (lista.size() + 1)));
                }
                break;
            case "BLE":
            case "BNE":
            case "BEQ":
            case "BGT":
            case "BLT":
            case "BGE":
                lista.add(new Terceto("CMP", null, null));
                lista.add(new Terceto(elemento, null, "#"));
                pila.add(lista.size());
                break;
            case "ET":
                lista.add(new Terceto("ET", null, null));
                pila.add(lista.size());
                break;
            case "BI":
                int index3 = pila.pop() - 1;
                Terceto t3 = lista.get(index3);
                lista.set(index3, new Terceto(t3.getOperacion(), t3.getOperando1(), "#" + (lista.size() + 3)));
                lista.add(new Terceto(elemento, "#", pila.pop().toString()));
                break;
            case "AND":
            case "OR":
            case "SINGLE":
                pilaM.add(elemento);
                break;
            case "CONTARPRIMOS":
                while (!expresionesPrimos.isEmpty()) {
                    String valor = expresionesPrimos.pop();

                    // Lógica para verificar si la expresión es primo
                    lista.add(new Terceto("2", null, null)); // Inicializar el divisor a 2
                    lista.add(new Terceto(valor, null, null)); // Etiqueta para el bucle
                    lista.add(new Terceto("DIV", "ant2", "ant2")); // Calcular el módulo de la expresión y 2
                    lista.add(new Terceto("BNE", null, "#")); // Saltar si el módulo no es igual a 0
                    lista.add(new Terceto("ADD", "1", null)); // Incrementar el contador de divisores
                    lista.add(new Terceto("ADD", "1", null)); // Incrementar el divisor
                    lista.add(new Terceto("LT", null, expresion)); // Comparar si el divisor es menor que la expresión
                    lista.add(new Terceto("BNE", null, "#LABEL")); // Saltar si el divisor es menor que la expresión
                    lista.add(new Terceto("EQ", "0", null)); // Verificar si el contador de divisores es igual a 0 (es primo)
                    lista.add(new Terceto("BNE", null, "#")); // Saltar si no es primo
                    contadorPrimos++; // Incrementar el contador de primos
                    pila.add(lista.size()); // Agregar la posición actual de la lista a la pila
                }
                break;
            case "BUSCOYREEMPLAZO":
                Stack<String> subFraseAux = subFrase;
                while (!frase.isEmpty()) {
                    while (!subFraseAux.isEmpty()) {
                        String caracter = frase.pop();
                        String aux = subFraseAux.pop();

                        lista.add(new Terceto(aux, null, null));
                        lista.add(new Terceto(caracter, null, null));
                    }
                    subFraseAux = subFrase;
                }
                break;
            default:
                lista.add(new Terceto(elemento, null, null));
                break;
        }
    }

    public void mostrar() {
        System.out.println("----------------MOSTRANDO TERCETOS---------------------");
        int i = 1;
        for (Terceto terceto : lista) {
            System.out.println(i + ") " + terceto);
            i++;
        }
        System.out.println("--------------------------------------------------------");
    }

    public ArrayList<Terceto> getLista() {
        return this.lista;
    }
}
