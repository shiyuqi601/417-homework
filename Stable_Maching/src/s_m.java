import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by yuqishi on 1/12/17.
 */


public class s_m
{
    private int N, engagedCount;
    private String[][] menPref;
    private String[][] womenPref;
    private String[] men;
    private String[] women;
    private String[] womenPartner;
    private boolean[] menEngaged;

    /** Constructor **/
    public s_m(String[] m, String[] w, String[][] mp, String[][] wp)
    {
        N = mp.length;
        engagedCount = 0;
        men = m;
        women = w;
        menPref = mp;
        womenPref = wp;
        menEngaged = new boolean[N];
        womenPartner = new String[N];
    }

    /** function to calculate all matches **/
    private void allMatches()
    {
        while (engagedCount < N)
        {
            int free;
            for (free = 0; free < N; free++)
                if (!menEngaged[free])
                    break;

            for (int i = 0; i < N && !menEngaged[free]; i++)
            {
                int index = womenIndexOf(menPref[free][i]);
                if (womenPartner[index] == null)
                {
                    womenPartner[index] = men[free];
                    menEngaged[free] = true;
                    engagedCount++;
                }
                else
                {
                    String currentPartner = womenPartner[index];
                    if (morePreference(currentPartner, men[free], index))
                    {
                        womenPartner[index] = men[free];
                        menEngaged[free] = true;
                        menEngaged[menIndexOf(currentPartner)] = false;
                    }
                }
            }
        }
        printWomen();
    }

    /** function to check if women prefers new partner over old assigned partner **/
    private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (womenPref[index][i].equals(newPartner))
                return true;
            if (womenPref[index][i].equals(curPartner))
                return false;
        }
        return false;
    }

    /** get men index **/
    private int menIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (men[i].equals(str))
                return i;
        return -1;
    }
    /** get women index **/
    private int womenIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (women[i].equals(str))
                return i;
        return -1;
    }
    /** print women **/
    public void printWomen()
    {
        for (int i = 0; i < N; i++)
        {
            System.out.print(women[Arrays.asList(womenPartner).indexOf(Integer.toString(i+1))]+" ");
        }
        System.out.println();
    }

    /** get average women happiness **/
    public void womenHappiness(){
        int sum = 0;
        int i = 0;
        for (i = 0; i < N; i++){
            for (int j=0; j < N; j++){
                if(womenPref[i][j].equals(womenPartner[i])) sum += (j+1);
            }
        }
        double average = (double)sum / (double)N;
        System.out.println("The average women happiness is: " + average);

    }

    /** get average men happiness **/
    public void menHappiness(){
        int sum = 0;
        int i = 0;
        for (i = 0; i < N; i++){
            for (int j=0; j < N; j++){
                if(menPref[i][j].equals(women[Arrays.asList(womenPartner).indexOf(Integer.toString(i+1))]) )
                sum += (j+1);
            }
        }
        double average = (double)sum / (double)N;
        System.out.println("The average men happiness is: " + average);

    }


    /** main function **/
    public static void main(String[] args)
    {
        try {
            String path = "/Users/yuqishi/Desktop/test/2^4.txt";

            File filename = new File(path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";

            int num = Integer.parseInt(br.readLine());
            String[] m = new String[num];
            String[] w = new String[num];

            for (int i = 0; i < num; i++){
                m[i] = Integer.toString(i+1);
                w[i] = Integer.toString(i+1);
            }

            String[][] mp = new String[num][num];
            String[][] wp = new String[num][num];
            String[] split;

            for(int i = 0; i < num; i++){
                line = br.readLine();
                split = line.split(" ");
                mp[i] = split;
            }
            for(int i = 0; i < num; i++) {
                line = br.readLine();
                split = line.split(" ");
                wp[i] = split;
            }
            s_m gs = new s_m(m, w, mp, wp);
            gs.allMatches();
            //gs.womenHappiness();
            //gs.menHappiness();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

