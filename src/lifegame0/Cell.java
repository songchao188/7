package lifegame0;

public class Cell {
    private int x,y;//ϸ��λ��
    private boolean isLive;//ϸ��״̬��true�����  false��������
    
    public Cell (int x,int y) {
    	this.x=x;
    	this.y=y;
    	isLive=false;
    }
    
    public int getX() {
    	return x;
    }
    
    public void setX() {
    	this.x=x;
    }
    public int getY() {
        return y;
    }
 
    public void setY(int y) {
        this.y = y;
    }
 
    public boolean getIsLive() {
        return isLive;
    }
 
    public void setIsLive(boolean live) {
        isLive = live;
    }
}
