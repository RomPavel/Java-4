import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map <String, RecordBook> array = new HashMap<>();
        try {

            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            String str;

            while((str=in.readLine())!=null)
            {
                StringTokenizer strcopy = new StringTokenizer(str);
                String info = strcopy.nextToken()+' '+strcopy.nextToken();

                if(array.containsKey(info)) {
                strcopy.nextToken();
                array.get(info).addSession(strcopy);
                }

                else {
                array.put(info, new RecordBook(info, Integer.parseInt((strcopy.nextToken()))));
                array.get(info).addSession(strcopy);
                }
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        try (Writer out = new FileWriter("output.txt")) {

            out.write("All students");
            out.write(System.getProperty("line.separator"));

            for (String studName : array.keySet()) {
                 array.get(studName).OutPut(out);
            }
            out.write(System.getProperty("line.separator"));
            out.write(System.getProperty("line.separator"));
            out.write("Students, with all marks >=4");
            out.write(System.getProperty("line.separator"));
            for (String studName : array.keySet()) {
                if (array.get(studName).isGood()) array.get(studName).OutPut(out);
            }
            System.out.println("Ok. Check output file");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
