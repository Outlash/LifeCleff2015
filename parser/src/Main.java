

public class Main {
    public static void main(String args[])
    {
        Parser parser = new Parser();
        parser.parseByTutu();

        for(int i=0;i<parser.getValues().length;i++){
            System.out.println("########### File :"+i+"###############");
            for(int j=0;j<parser.getValues()[i].length;j++){
                System.out.println(parser.getValues()[i][j]);
            }
            System.out.println("################################");
        }
    }
}
