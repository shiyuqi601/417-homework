/**
 * Created by yuqishi on 2/25/17.
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class k_shot {

    public static void findQTransactionSolution(int n, int k, int[] p){
        int Q[][] = new int[n+1][n+1];
        Transaction[][] qTraceMatrix = new Transaction[n+1][n+1];
        for (int i = 1; i < n; i++){
            Q[i][i+1] = p[i+1] - p[i];
            qTraceMatrix[i][i+1] = new PTransaction(i, i+1);
        }
        for (int l = 2; l < n; l++){
            for (int i = 1; i < n-l+1; i++){
                int j = i + l;
                int QMax;
                if(Q[i+1][j] > Q[i][j-1]){
                    QMax = Q[i+1][j];
                    qTraceMatrix[i][j] = new QTransaction(i+1, j);
                }else{
                    QMax = Q[i][j-1];
                    qTraceMatrix[i][j] = new QTransaction(i, j-1);
                }
                if(QMax >(p[j]-p[i])){
                    //do nothing
                }else{
                    QMax = p[j]-p[i];
                    qTraceMatrix[i][j] = new PTransaction(i, j);
                }
                Q[i][j]= QMax;

            }
        }

        int M[][] = new int[k+1][n+1];
        M[1][1]=0;
        for (int d = 2; d < n+1; d++){
            M[1][d] = Q[1][d];
            //System.out.println(M[0][d]);

        }
        for (int m = 1; m < k+1; m++){
            M[m][0] = 0;
            //System.out.println(M[m][0]);

        }
        for (int m = 1; m < k; m++){
            for (int d = 1; d < n+1; d++){
                M[m+1][d] = M[m][d];

                for (int i = 1; i < d; i++){
                    for (int j = i+1; j < d+1; j++){
                        if (M[m+1][d] < Q[i][j] + M[m][i-1]){
                            M[m+1][d] = Q[i][j] + M[m][i-1];
                        }
                    }
                }
            }
        }

        List<QTransaction> QTransactionSolution=new LinkedList<>();//stands for P
        int d = n;
        int m = k-1;
        while (m > 0){
            if (M[m+1][d] == M[m][d]){
                m = m - 1;
            }else {
                for (int i = 1; i < d; i++){
                    for (int j = i + 1; j < d+1; j++){
                        if (M[m+1][d] == Q[i][j] + M[m][i-1]){
                            //add (i,j) to P means add Q[i][j] to P
                            QTransaction QTransaction = new QTransaction(i, j);
                            QTransactionSolution.add(QTransaction);
                            m = m - 1;
                            d = i - 1;
                        }
                    }
                }
            }
        }
        //add Q[1][d] to P
        QTransactionSolution.add(new QTransaction(1, d));
        System.out.println("QTransactionSolution: "+QTransactionSolution);
        int sum = 0;

        for(Iterator<QTransaction> i = QTransactionSolution.iterator(); i.hasNext(); ){
            QTransaction QTransaction = i.next();
            sum += Q[QTransaction.getBeginDay()][QTransaction.getEndDay()];
        }
        System.out.println("Sum: "+sum);


        //this is the detailed QTransactionSolution, which is exactly consist of PTransactions
        List<PTransaction> pTransactionQTransactionSolution = new LinkedList<>();
        for(Iterator<QTransaction> i = QTransactionSolution.iterator(); i.hasNext(); ){
            QTransaction qTransaction = i.next();
            Transaction transaction = qTraceMatrix[qTransaction.getBeginDay()][qTransaction.getEndDay()];
            while(transaction instanceof QTransaction){
                transaction = qTraceMatrix[transaction.getBeginDay()][transaction.getEndDay()];
            }
            //now transaction is instance of PTransaction
            pTransactionQTransactionSolution.add((PTransaction)transaction);
        }
        System.out.println("pTransactionQTransactionSolution: " + pTransactionQTransactionSolution);
    }


    public static void main(String[] args) {

        try {

            String pathname = "/Users/yuqishi/Desktop/input3.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);

            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());

            int p[] = new int[n+1];
            String line = null;
            for(int i = 1; i < n+1; i++){
                line = br.readLine();
                p[i] = Integer.parseInt(line);
            }
            System.out.println("n:" + n + ", k: " + k);
            System.out.println("p[1]-p[" + p.length +"]:");
            for(int i = 1; i < p.length; i++)
                System.out.print(p[i] + " ");
            System.out.println("");
            //System.out.println(Arrays.toString(p));
            findQTransactionSolution(n, k, p);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
