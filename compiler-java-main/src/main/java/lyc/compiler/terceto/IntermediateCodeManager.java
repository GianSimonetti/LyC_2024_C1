package lyc.compiler.terceto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class IntermediateCodeManager {
    private ArrayList<Terceto> tercetos = new ArrayList<Terceto>();
    private HashMap<String, Integer> punterosTercetos = new HashMap<String, Integer>();
    private Stack<Integer> pilaPunteros = new Stack<Integer>();
    private Stack<String> pilaLexemas = new Stack<String>();
    private Stack<String> pilaComparadores = new Stack<String>();

    public Integer crearTerceto(String operador, String operando1, String operando2)
    {
        Integer numTerceto = tercetos.size();
        tercetos.add(new Terceto(operador, operando1, operando2));
        return numTerceto;
    }

    public Integer crearTerceto(String valor)
    {
        Integer numTerceto = tercetos.size();
        tercetos.add(new Terceto(valor));
        return numTerceto;
    }

    public Integer crearTerceto(String operador, String operando1)
    {
        Integer numTerceto = tercetos.size();
        tercetos.add(new Terceto(operador, operando1));
        return numTerceto;
    }

    public Terceto getTerceto(Integer numTerceto)
    {
        return tercetos.get(numTerceto);
    }

    public void setPunteroTerceto(String puntero, Integer numeroTerceto)
    {
        punterosTercetos.put(puntero, numeroTerceto);
    }

    public Integer getNumeroTercetoFromPuntero(String puntero)
    {
        return punterosTercetos.get(puntero);
    }

    public String getStrNumeroTercetoFromPuntero(String puntero)
    {
        return "#" + punterosTercetos.get(puntero).toString();
    }

    public void apilarPuntero(Integer puntero)
    {
        this.pilaPunteros.add(puntero);
    }

    public Integer desapilarPuntero()
    {
        return this.pilaPunteros.pop();
    }

    public void apilarLexema(String lexema)
    {
        this.pilaLexemas.add(lexema);
    }

    public String desapilarLexema()
    {
        return this.pilaLexemas.pop();
    }

    public void apilarComparador(String comparador)
    {
        this.pilaComparadores.add(comparador);
    }

    public String desapilarComparador()
    {
        return this.pilaComparadores.pop();
    }

    public Integer getUltimoNumeroTerceto()
    {
        return this.tercetos.size() - 1;
    }

    public void actualizarTerceto(Integer numeroTerceto, CampoTerceto campo, String valor)
    {
        if(campo == CampoTerceto.PRIMER_ELEM)
        {
            this.tercetos.get(numeroTerceto).setOperacion(valor);
            return;
        }
        if(campo == CampoTerceto.SEGUNDO_ELEM)
        {
            this.tercetos.get(numeroTerceto).setOperando1(valor);
            return;
        }
        if(campo == CampoTerceto.TERCER_ELEM)
        {
            this.tercetos.get(numeroTerceto).setOperando2(valor);
            return;
        }
    }

    public void mostrarTercetos()
    {
        System.out.println("----------------MOSTRANDO TERCETOS---------------------");
        for(int i = 0; i < tercetos.size(); i++)
        {
            System.out.println(i + ") " + tercetos.get(i));
        }
        System.out.println("--------------------------------------------------------");
    }

    public List<Terceto> getTercetosList()
    {
        return this.tercetos;
    }
}
