package ru.morpher;

import org.waxeye.ast.print.SexprPrinter;
import org.waxeye.parser.ParseResult;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> files = new ArrayList<>();
        Files.walk(Paths.get("../zalizniak-2010/dictionary/"))
                .filter(Files::isRegularFile)
                .forEach(file -> files.add(file.toString()));

        String errorFileName = "parse-errors.txt";
        //Files.copy(Paths.get(errorFileName), Paths.get("out/parse-errors-prev.txt"), StandardCopyOption.REPLACE_EXISTING);

        Writer jsonFile = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("zal.json"), "UTF-8"));
        Writer parseErrorsFile = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(errorFileName), "UTF-8"));
        ZalParser parser = new ZalParser();
        int ok = 0;
        int errors = 0;
        for (String fileName : files)
        {
            for (String line : Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8))
            {
                ParseResult<ZalType> result = parser.parse(line);
                if (result.getError() == null) {
                    jsonFile.write(new SexprPrinter(result.getAST()).toString());
                    jsonFile.write("\n");
                    ++ok;
                }
                else {
                    parseErrorsFile.write(line);
                    parseErrorsFile.write(": ");
                    parseErrorsFile.write(result.getError().toString());
                    parseErrorsFile.write("\n");
                    ++errors;
                }
            }
        }
        jsonFile.close();
        parseErrorsFile.close();

        final int total = ok + errors;
        System.out.printf("%d of %d (%.2f%%) parsed successfully.\n",
                ok, total, ok * 100.0 / total);
        System.out.printf("%d of %d (%.2f%%) errors.\n",
                errors, total, errors * 100.0 / total);
    }
}
