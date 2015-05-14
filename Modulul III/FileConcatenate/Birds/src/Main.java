public class Main
{
    public static void main(String[] args)
    {
        /*Format train = new Format();
        //Se seteaza path-urile
        train.setPaths();

        //Se incarca map-ul
        train.loadMap();
        //printMap();

        //Se creaza fisierul
        train.format();*/


        FormatTest test = new FormatTest();

        //Se seteaza path-urile
        test.setPaths();

        //Se creaza fisierele
        test.format();
    }
}