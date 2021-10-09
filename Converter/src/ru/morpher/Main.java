package ru.morpher;

import org.waxeye.ast.print.ArrowPrinter;
import org.waxeye.ast.print.SexprPrinter;
import org.waxeye.parser.ParseResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> files = new ArrayList<>();
        Files.walk(Paths.get("../../zalizniak-2010/dictionary/"))
                .filter(Files::isRegularFile)
                .forEach(file -> files.add(file.toString()));

        BufferedWriter jsonFile = new BufferedWriter(new FileWriter("out/zal.json"));
        BufferedWriter parseErrorsFile = new BufferedWriter(new FileWriter("out/parse-errors.txt"));
        ZalParser parser = new ZalParser();
        int ok = 0;
        int errors = 0;
        for (String fileName : files)
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            {
                String line;
                while ((line = br.readLine()) != null) {
                    ParseResult<ZalType> result = parser.parse(line);
                    String o;
                    if (result.getError() == null) {
                        jsonFile.write(new SexprPrinter(result.getAST()).toString());
                        jsonFile.newLine();
                        ++ok;
                    }
                    else {
                        parseErrorsFile.write(line);
                        parseErrorsFile.write(": ");
                        parseErrorsFile.write(result.getError().toString());
                        parseErrorsFile.newLine();
                        ++errors;
                    }
                }
            }
            br.close();
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
