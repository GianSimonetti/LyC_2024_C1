package lyc.compiler.terceto;

public class Terceto {
    private String primerElemento;
    private String segundoElemento;
    private String tercerElemento;
    private TercetoType tipo;

    public Terceto(String operador, String operando1, String operando2) {
        this.primerElemento = operador;
        this.segundoElemento = operando1;
        this.tercerElemento = operando2;
        this.tipo = TercetoType.FULL;
    }

    public Terceto(String operador, String operando1) {
        this.primerElemento = operador;
        this.segundoElemento = operando1;
        this.tercerElemento = null;
        this.tipo = TercetoType.SEMI;
    }

    public Terceto(String valor)
    {
        this.primerElemento = valor;
        this.segundoElemento = null;
        this.tercerElemento = null;
        this.tipo = TercetoType.SINGLE_VALUE;
    }


    public String getOperacion()
    {
        if(this.tipo != TercetoType.SINGLE_VALUE)
        {
            return primerElemento;
        }

        return null;
    }

    public void setOperacion(String operacion)
    {
        if(this.tipo != TercetoType.SINGLE_VALUE)
        {
            this.primerElemento = operacion;
        } else
        {
            this.segundoElemento = this.primerElemento;
            this.primerElemento = operacion;
            this.tipo = TercetoType.SEMI;
        }
    }

    public String getOperando1()
    {
        if(this.tipo != TercetoType.SINGLE_VALUE)
        {
            return this.segundoElemento;
        }

        return this.primerElemento;
    }

    public void setOperando1(String operando1)
    {
        if(this.tipo != TercetoType.SINGLE_VALUE)
        {
            this.segundoElemento = operando1;
        } else
        {
            this.primerElemento = operando1;
        }
    }

    public String getOperando2()
    {
        if(this.tipo == TercetoType.FULL)
        {
            return this.tercerElemento;
        }

        return null;
    }

    public void setOperando2(String operando2)
    {
        if(this.tipo != TercetoType.SINGLE_VALUE)
        {
            this.tercerElemento = operando2;
        }
    }

    @Override
    public String toString()
    {
        if(this.tipo == TercetoType.SINGLE_VALUE)
        {
            return "(" + primerElemento + ", " + "_" + ", " + "_" + ")";
        }
        if(this.tipo == TercetoType.SEMI)
        {
            return "(" + primerElemento + ", " + segundoElemento + ", " + "_" + ")";
        }

        return "(" + primerElemento + ", " + segundoElemento + ", " + tercerElemento + ")";
    }
}