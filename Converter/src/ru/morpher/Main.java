package ru.morpher;

import org.waxeye.ast.print.ArrowPrinter;
import org.waxeye.ast.print.SexprPrinter;
import org.waxeye.parser.ParseResult;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("user.dir"));
        BufferedWriter jsonFile = new BufferedWriter(new FileWriter("out/zal.json"));
        BufferedWriter parseErrorsFile = new BufferedWriter(new FileWriter("out/parse-errors.txt"));
        ZalParser parser = new ZalParser();
        BufferedReader br = new BufferedReader(new FileReader("../../zalizniak-2010/dictionary/Нарицательные/А.txt"));
        {
            String line;
            while ((line = br.readLine()) != null) {
                ParseResult<ZalType> result = parser.parse(line);
                String o;
                if (result.getError() == null) {
                    jsonFile.write(new SexprPrinter(result.getAST()).toString());
                    jsonFile.newLine();
                }
                else {
                    parseErrorsFile.write(line);
                    parseErrorsFile.write(": ");
                    parseErrorsFile.write(result.getError().toString());
                    parseErrorsFile.newLine();
                }
            }
        }
        br.close();
        jsonFile.close();
    }
}
