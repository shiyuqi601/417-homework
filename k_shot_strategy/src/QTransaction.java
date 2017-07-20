
public class QTransaction implements Transaction{
    private int beginDay;
    private int endDay;
    public QTransaction(int beginDay, int endDay){
        this.beginDay = beginDay;
        this.endDay = endDay;
    }
    @Override
    public String toString(){
        return "("+beginDay+","+endDay+")";
    }
    @Override
    public int getBeginDay(){
        return beginDay;
    }
    @Override
    public int getEndDay(){
        return endDay;
    }
}
