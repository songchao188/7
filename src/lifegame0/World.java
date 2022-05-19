package lifegame0;

public class World {
    private int lx,ly;
    private int nowGeneration;
    private Cell[][] cell=new Cell[80][80];
 
    public World(int lx,int ly){
        this.lx=lx;
        this.ly=ly;
        nowGeneration=0;
        for(int x=0;x<lx;x++)
            for(int y=0;y<ly;y++){
                cell[x][y]=new Cell(x,y);
                cell[x][y].setIsLive(false);
            }
    }
 
    public int getLx() {
        return lx;
    }
 
    public int getLy() {
        return ly;
    }
 
    public boolean getCellXY(int x, int y){
        return cell[x][y].getIsLive();
    }
 
    public int getNowGeneration() {
        return nowGeneration;
    }
 
    //�����ʼ��ϸ��
    public void randonInitCell(){
        for(int x=0;x<lx;x++)
            for(int y=0;y<ly;y++)
                cell[x][y].setIsLive(Math.random()>0.5?true:false);
    }
 
    //ϸ������
    public void deleteAllCell(){
        for(int x=0;x<lx;x++)
            for(int y=0;y<ly;y++)
                cell[x][y].setIsLive(false);
        nowGeneration=0;
    }
 
    //���ܻ���
    public void updateOfCell(){
        Cell[][] ce=new Cell[lx][ly];
        //Cell[][] ce=cell;
        for(int x=0;x<lx;x++) {
            for (int y = 0; y < ly; y++) {
                ce[x][y]=new Cell(x,y);
            }
        }
        for(int x=0;x<lx;x++) {
            for (int y = 0; y < ly; y++){
                int c=0;
                for(int i=x-1;i<=x+1;i++){
                    for(int j=y-1;j<=y+1;j++){
                        if(i>=0&&i<lx&&j>=0&&j<ly&&cell[i][j].getIsLive())c++;
                    }
                }
                if(cell[x][y].getIsLive())c--;
                if(c==3)ce[x][y].setIsLive(true);
                else if(c==2)ce[x][y].setIsLive(cell[x][y].getIsLive());
                else ce[x][y].setIsLive(false);
            }
        }
        for(int x=0;x<lx;x++) {
            for (int y = 0; y < ly; y++) {
                cell[x][y]=ce[x][y];
            }
        }
        nowGeneration++;
    }
}