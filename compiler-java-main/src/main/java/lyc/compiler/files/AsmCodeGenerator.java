package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

public class AsmCodeGenerator implements FileGenerator {
    private String code;

    public AsmCodeGenerator(String code)
    {
        this.code = code;
    }

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        try
        {
            if(this.code.isEmpty())
            {
                fileWriter.write("SIN CODIGO ASM");
                return;
            }

            //fileWriter.write("CODIGO ASM:" + "\n");
            fileWriter.write(this.code + "\n");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
