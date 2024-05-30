package lyc.compiler.files;

import lyc.compiler.terceto.Terceto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class IntermediateCodeGenerator implements FileGenerator {

    private List<Terceto> tercetosList;

    public IntermediateCodeGenerator()
    {
        this.tercetosList = Collections.emptyList();
    }

    public IntermediateCodeGenerator(List<Terceto> tercetos)
    {
        this.tercetosList = tercetos;
    }

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        try
        {
            if(this.tercetosList.isEmpty())
            {
                fileWriter.write("SIN CODIGO INTERMEDIO");
                return;
            }

            //fileWriter.write(String.format("%-20s|%-20s|%-20s|%-20s", "NOMBRE", "TIPODATO", "VALOR", "LONGITUD") + "\n");
            fileWriter.write("LISTA DE TERCETOS:" + "\n");
            tercetosList.forEach(terceto -> {
                try
                {
                    fileWriter.write(terceto.toString() + "\n");
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
