import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILE_NAME = "Numbered_";

    // open and read the file
    private static List<String> readFile(String filename)
    {
        System.out.println("Reading the file...");
        List<String> records = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            System.out.println("Done!");
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    // add line numbers
    private static void numberLines(List<String> fileContent) {
        System.out.println("Adding line numbers...");
        for (int i = 0; i < fileContent.size(); i++) {
            var line = fileContent.get(i);
            fileContent.set(i, (1 + i) + ". \t"  + line);
        }
        System.out.println("Done!");
    }
    // save the file
    public static void writeFile(String canonicalFilename, List<String> text) throws IOException
    {
        System.out.println("Saving the file...");
        File file = new File (FILE_NAME + canonicalFilename);
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        for (var line : text) {
            out.write(line + "\n");
        }
        out.close();
        System.out.println("Done!");
    }

    public static void main(String[] args) {
        String fileName;
        if (args.length != 0) {
            fileName = args[0];
        } else {
            System.out.println("Add Line Numbers Usage: ");
            System.out.println("AddLineNumbers <file_name> -options");
            System.out.println("Type --help or -h for help.");
            return;
            //fileName = "file.txt";
        }

        if (fileName.contains("--help") || fileName.contains("-h")) {
            System.out.println("Add Line Numbers Usage: ");
            System.out.println("AddLineNumbers <file_name> -options");
            System.out.println("Available options: ");
            System.out.println("-C - remove comments (// /* */) [not implemented]");
            System.out.println("-E - remove empty lines [not implemented]");
            System.out.println("-N - remove line numbers [not implemented]");
        }

        try {
            var fileContent = readFile(fileName);
            if (fileContent == null || fileContent.isEmpty()) {
                System.out.println("File is empty. Nothing to do.");
                return;
            }
            numberLines(fileContent);
            writeFile(fileName, fileContent);
            System.out.println("All done!");
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e);
        }
    }
}
