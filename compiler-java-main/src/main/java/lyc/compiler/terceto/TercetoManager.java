package lyc.compiler.terceto;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
public class TercetoManager {
        private ArrayList<Terceto> lista = new ArrayList<>();

        private Stack<Integer> pila = new Stack<Integer>();
        private Stack<String> pilaM = new Stack<String>(); //condiciones multiples

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
