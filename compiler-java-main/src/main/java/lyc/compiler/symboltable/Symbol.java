package lyc.compiler.symboltable;

import java.util.Objects;

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
        int final_length = 0;
        String final_name = "_EMPTY_STRING";
        String final_value = "";

        if(!value.equals("\"\""))
        {
            //final_value = value.substring(1, value.length() - 1);
            final_value = value;
            final_name = "_" + final_value;
            final_length = final_value.length();
        }

        this.name = final_name;
        this.stringValue = final_value;
        this.length = final_length;

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

    public Integer getLength()
    {
        return Objects.requireNonNullElse(this.length, 0);
    }

    public String getStringValue()
    {
        return Objects.requireNonNullElse(this.stringValue, "");
    }

    public void setValue(String value)
    {
        this.stringValue = value;
        this.length = value.length();
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
