import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Party {

    private static int k1;

    private static int k2;

    private static ArrayList<Integer> badMem;

    public static void main(String[] args)
    {
        File file = new File("/Users/Pey/Desktop/description.txt");
        File out = new File("/Users/Pey/Desktop/party.txt");

        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int length = 0;
        while(sc.hasNextLine()) {
            length++;
            sc.nextLine();
        }
        length -= 2;

        badMem = new ArrayList<>();

        ArrayList<String> party = new ArrayList<>();

        try {
            sc = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int counter = 0;
        k1 = Integer.parseInt(sc.nextLine());
        k2 = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < length; i++) {
            String info = sc.nextLine();
            int known = (info.length()-info.replace("1","").length());
            int unknown = info.length() - known - 1;
            party.add(counter++ + " " + info + " " +  known + " " + unknown);
            if(known < k1 || unknown < k2)
                badMem.add(i);
        }
        while(badMem.size() > 0) {
            System.out.println(badMem.size());
            for(int i = 0; i < badMem.size(); i++) {
                unlink(party, badMem.get(i));
                badMem.remove(badMem.get(i));
            }
        }

        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(out);
            printWriter = new PrintWriter(fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(String p : party)
            printWriter.println(Integer.parseInt(p.substring(0, p.indexOf(" "))) + 1);


        try {
            fileWriter.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        printWriter.close();
    }

    private static void unlink(ArrayList<String> party, int index) {
        for(int i = 0; i < party.size(); i++) {
            int indexLeftBegin = 0;
            int indexLeftEnd = party.get(i).substring(0, party.get(i).lastIndexOf(" ")).lastIndexOf(" ");
            int indexKnownBegin = indexLeftEnd+1;
            int indexKnownEnd = party.get(i).lastIndexOf(" ");
            int indexUnknownBegin = party.get(i).lastIndexOf(" ") + 1;
            int indexUnknownEnd = party.get(i).length();
            if(party.get(i).substring(party.get(i).indexOf(" ") + 1, party.get(i).substring(0, party.get(i).lastIndexOf(" ")).lastIndexOf(" ")).charAt(Integer.parseInt(party.get(index).substring(0,party.get(index).indexOf(" ")))) == '0')
                party.set(i, party.get(i).substring(indexLeftBegin, indexLeftEnd) + " " + party.get(i).substring(indexKnownBegin, indexKnownEnd) + " " + String.valueOf(Integer.parseInt(party.get(i).substring(indexUnknownBegin, indexUnknownEnd))-1));
            else
                party.set(i, party.get(i).substring(indexLeftBegin, indexLeftEnd) + " " + String.valueOf(Integer.parseInt(party.get(i).substring(indexKnownBegin, indexKnownEnd)) - 1) + " " + party.get(i).substring(indexUnknownBegin, indexUnknownEnd));

            indexKnownBegin = indexLeftEnd+1;
            indexKnownEnd = party.get(i).lastIndexOf(" ");
            indexUnknownBegin = party.get(i).lastIndexOf(" ") + 1;
            indexUnknownEnd = party.get(i).length();
            int known = Integer.parseInt(party.get(i).substring(indexKnownBegin, indexKnownEnd));
            int unknown = Integer.parseInt(party.get(i).substring(indexUnknownBegin, indexUnknownEnd));
            if((known < k1 || unknown < k2) && !badMem.contains(i))
                badMem.add(i);
        }
        party.remove(index);
        for(int i = 0; i < badMem.size(); i++) {
            if(badMem.get(i) >= index) {
                badMem.set(i, badMem.get(i)-1);
            }
        }
    }
}