package lyc.compiler.asm;
import lyc.compiler.symboltable.SymbolTableManager;
import lyc.compiler.terceto.IntermediateCodeManager;
import lyc.compiler.terceto.Terceto;
import java.util.List;

public class AsmCodeManager {

    public static String generaAssembler(SymbolTableManager symbolTableManager, IntermediateCodeManager intCodeManager)
    {
        String result = "";
        String header = setAsmHeader();
        result = header;
        String tercetosAsm = "";
        List<Terceto> tercetosList = intCodeManager.getTercetosList();
        for (Terceto terceto : tercetosList)
        {
            tercetosAsm += Terceto.tercetoToAsm(terceto, intCodeManager);
        }
        result += tercetosAsm;
        return result;
    }

    private static String setAsmHeader()
    {
        return "include macros2.asm\ninclude number.asm\n\n.MODEL SMALL\n.386\n.STACK 200h\n\n";
    }
}
