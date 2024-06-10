package lyc.compiler.asm;
import lyc.compiler.terceto.Terceto;
import java.util.List;

public class AsmCodeManager {

    public static String generaAssembler(List<Terceto> tercetosList)
    {
        String result = "";
        String header = setAsmHeader();
        result = header;
        return result;
    }

    private static String setAsmHeader()
    {
        return "include macros2.asm\ninclude number.asm\n\n.MODEL SMALL\n.386\n.STACK 200h";
    }
}
