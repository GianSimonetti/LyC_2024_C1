package lyc.compiler.symboltable;

import lyc.compiler.model.InvalidVarTypeException;
import lyc.compiler.model.UnknownVariableException;

import java.util.*;

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

    public void setVarValue(String name, String value) throws UnknownVariableException {
        if(isSymbolInTable(name))
        {
            symbolsList.get(getSymbolPos(name)).setValue(value);
        } else
        {
            // throw error, variable inexistente
            throw new UnknownVariableException(name);
        }
    }

    public void setVarValue(String name, Integer value) throws UnknownVariableException {
        if(isSymbolInTable(name))
        {
            symbolsList.get(getSymbolPos(name)).setValue(value);
        } else
        {
            // throw error, variable inexistente
            throw new UnknownVariableException(name);
        }
    }

    public void setVarValue(String name, Double value) throws UnknownVariableException {
        if(isSymbolInTable(name))
        {
            symbolsList.get(getSymbolPos(name)).setValue(value);
        } else
        {
            // throw error, variable inexistente
            throw new UnknownVariableException(name);
        }
    }

    public Integer getVarLength(String name) throws UnknownVariableException {
        if(isSymbolInTable(name))
        {
           return symbolsList.get(getSymbolPos(name)).getLength();
        } else
        {
            // throw error, variable inexistente
            throw new UnknownVariableException(name);
            //return -1;
        }
    }

    public String getVarValue(String name) throws UnknownVariableException {
        if(isSymbolInTable(name))
        {
            return symbolsList.get(getSymbolPos(name)).getStringValue();
        } else
        {
            // throw error, variable inexistente
            throw new UnknownVariableException(name);
            //return "";
        }
    }

    private Boolean isVarNumeric(String name) throws UnknownVariableException {
        if(isSymbolInTable(name))
        {
            return symbolsList.get(getSymbolPos(name)).getType() != DataType.CTE_STRING;
        } else
        {
            // throw error, variable inexistente
            throw new UnknownVariableException(name);
            //return "";
        }
    }

    public void checkVarIsNumeric(String name) throws InvalidVarTypeException, UnknownVariableException {
        if(!isVarNumeric(name))
        {
            throw new InvalidVarTypeException(name);
        }
    }

    public void checkVarExists(String name) throws UnknownVariableException {
        if(!isSymbolInTable(name))
        {
            throw new UnknownVariableException(name);
        }
    }

    public void cargarAuxiliares(Queue<String> auxiliares, DataType type)
    {
        for(String aux : auxiliares)
        {
            this.addSymbol(aux, type);
        }
    }

    public List<Symbol> getSymbolsList()
    {
        return symbolsList;
    }
}
