package lyc.compiler.terceto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class IntermediateCodeManager {
    private ArrayList<Terceto> tercetos = new ArrayList<Terceto>();
    private HashMap<String, Integer> punterosTercetos = new HashMap<String, Integer>();

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
