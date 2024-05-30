package lyc.compiler.symboltable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTableManager {
    private List<Symbol> symbolsList;
    private Map<String, Integer> symbolsPosMap;

    private Integer getSymbolPos(String name)
    {
        return symbolsPosMap.get(name);
    }

    private Boolean isSymbolInTable(String name)
    {
        return !(getSymbolPos(name) == null);
    }

    public SymbolTableManager()
    {
        this.symbolsList = new ArrayList<Symbol>();
        this.symbolsPosMap = new HashMap<String, Integer>();
    }

    public void addSymbol(String name, DataType type)
    {
        if(!isSymbolInTable(name))
        {
            Integer symbolPos = symbolsList.size();
            Symbol symbol = new Symbol(name, type);
            symbolsList.add(symbol);
            symbolsPosMap.put(symbol.getName(), symbolPos);
        }
    }

    public void addSymbol(String name, String value)
    {
        if(!isSymbolInTable(name))
        {
            Integer symbolPos = symbolsList.size();
            Symbol symbol = new Symbol(name, value);
            symbolsList.add(symbol);
            symbolsPosMap.put(symbol.getName(), symbolPos);
        }
    }

    public void addSymbol(String name, Integer value)
    {
        if(!isSymbolInTable(name))
        {
            Integer symbolPos = symbolsList.size();
            Symbol symbol = new Symbol(name, value);
            symbolsList.add(symbol);
            symbolsPosMap.put(symbol.getName(), symbolPos);
        }
    }

    public void addSymbol(String name, Double value)
    {
        if(!isSymbolInTable(name))
        {
            Integer symbolPos = symbolsList.size();
            Symbol symbol = new Symbol(name, value);
            symbolsList.add(symbol);
            symbolsPosMap.put(symbol.getName(), symbolPos);
        }
    }

    public void addVars(ArrayList<String> varsList, DataType type)
    {
        varsList.forEach(var ->
        {
            this.addSymbol(var, type);
        });
        varsList.clear();
    }

    public void setVarValue(String name, String value)
    {
        if(isSymbolInTable(name))
        {
            symbolsList.get(getSymbolPos(name)).setValue(value);
        } else
        {
            // throw error, variable inexistente
        }
    }

    public Integer getVarLength(String name)
    {
        if(isSymbolInTable(name))
        {
           return symbolsList.get(getSymbolPos(name)).getLength();
        } else
        {
            // throw error, variable inexistente
            return -1;
        }
    }

    public String getVarValue(String name)
    {
        if(isSymbolInTable(name))
        {
            return symbolsList.get(getSymbolPos(name)).getStringValue();
        } else
        {
            // throw error, variable inexistente
            return "";
        }
    }

    public List<Symbol> getSymbolsList()
    {
        return symbolsList;
    }
}
