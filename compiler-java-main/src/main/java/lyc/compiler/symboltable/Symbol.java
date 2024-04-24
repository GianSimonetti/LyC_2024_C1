package lyc.compiler.symboltable;

public class Symbol {
    private String name;
    private DataType type;
    private Integer intValue;
    private Double floatValue;
    private String stringValue;
    private Integer length;

    public Symbol(String name, DataType type)
    {
        this.type = type;
        this.name = name;

        this.stringValue = null;
        this.length = null;
        this.intValue = null;
        this.floatValue = null;
    }

    public Symbol(String name, String value)
    {
        this.type = DataType.STRING;
        this.name = name;
        this.stringValue = value;
        this.length = value.length();

        this.intValue = null;
        this.floatValue = null;
    }

    public Symbol(String name, Integer value)
    {
        this.type = DataType.INTEGER;
        this.name = name;
        this.intValue = value;

        this.length = null;
        this.stringValue = null;
        this.floatValue = null;
    }

    public Symbol(String name, Double value)
    {
        this.type = DataType.FLOAT;
        this.name = name;
        this.floatValue = value;

        this.length = null;
        this.stringValue = null;
        this.intValue = null;
    }

    private String getValueAsString()
    {
        if(this.type == DataType.STRING)
        {
            return this.stringValue;
        } else
        {
            if(this.type == DataType.FLOAT)
            {
                return this.floatValue.toString();
            } else
            {
                return this.intValue.toString();
            }
        }
    }

    private String getDataTypeAsString()
    {
        if(this.type == DataType.STRING)
        {
            return "String";
        } else
        {
            if(this.type == DataType.FLOAT)
            {
                return "Float";
            } else
            {
                return "Int";
            }
        }
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        String s = "";
        String sName = this.name == null ? "" : this.name;
        String sValue = "";
        if(!(this.intValue == null && this.floatValue == null && this.stringValue == null)) //Si alguno tiene valor
        {
            sValue = getValueAsString();
        }
        String sLength = this.length == null ? "" : this.length.toString();
        s = String.format("%-20s|%-20s|%-20s|%-20s", sName, getDataTypeAsString(), sValue, sLength);
        //s += sName + "\t" + getDataTypeAsString() + "\t" + sValue + "\t" + sLength;
        return s;
    }
}
