package lifegame0;

public class Main {
    private static GUI Game;
    private static World world;
 
    public static void main(String arg[]){
        world=new World(50,50);
        Game=new GUI("lifegame",world);
    }
}