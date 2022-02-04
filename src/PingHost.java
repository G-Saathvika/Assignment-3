import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PingHost {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        ArrayList<Integer> al = new ArrayList<>();

        System.out.println("Enter an IP address:");
        String ip = keyboard.nextLine();

        String pingResult = "";
        String result="";
        int median=0;

        Pattern pattern = Pattern.compile("time=(\\d+)ms");
        Matcher m = null;

        String pingCmd = "ping " + ip;

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);

                m = pattern.matcher(inputLine);
                if (m.find()) {
                    String tmp = m.group(1);
                    int time = Integer.parseInt(tmp);
                    al.add(time);
                }

                pingResult += inputLine;

            }

            Collections.sort(al);
            for(int i=0;i<al.size();i++){
                median = (al.get(al.size()/2-1) + al.get(al.size()/2)) / 2;
            }

            System.out.println("Median time taken to ping: "+median+"ms");

            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}