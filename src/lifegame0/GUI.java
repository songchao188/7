package lifegame0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class GUI extends JFrame implements ActionListener {
    int lx,ly;
    private World world;
    private  JButton[][] TWorld;
    private JLabel NowGeneration;
    private JButton randomInit,BeginAndOver,Exit;
    private boolean isRunning;
    private Thread thread;
    public GUI(String name,World world){
        super(name);
        this.lx=world.getLx();
        this.ly=world.getLy();
        this.world=world;
        initGUI();
    }
 
    public void initGUI(){
        JPanel backPanel,bottomPanel,centerPanel;
        backPanel= new JPanel(new BorderLayout());
        bottomPanel=new JPanel();
        centerPanel=new JPanel(new GridLayout(lx,ly));
        this.setContentPane(backPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backPanel.add(centerPanel,"Center");
        backPanel.add(bottomPanel,"South");
 
        TWorld=new JButton[lx][ly];
        NowGeneration=new JLabel("当前代数：0");
        randomInit=new JButton("随机生成细胞");
        BeginAndOver=new JButton("开始游戏");
        Exit=new JButton("退出");
        for(int x=0;x<lx;x++){
            for(int y=0;y<ly;y++){
                TWorld[x][y]=new JButton("");
                TWorld[x][y].setBackground(Color.white);
                centerPanel.add(TWorld[x][y]);
            }
        }
 
        bottomPanel.add(randomInit);
        bottomPanel.add(BeginAndOver);
        bottomPanel.add(NowGeneration);
        bottomPanel.add(Exit);
 
        
        
        
        this.setSize(1000, 1000);
        this.setResizable(true);
        this.setLocationRelativeTo(null); // 让窗口在屏幕居中
        this.setVisible(true);// 将窗口设置为可见的
 
        //注册监听器
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                System.exit(0);
            }
        });
        randomInit.addActionListener(this);
        BeginAndOver.addActionListener(this);
        Exit.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == randomInit&&BeginAndOver.getText()=="开始游戏") {//随机生成第一代
            world.randonInitCell();
            showWorld();
            isRunning = false;
            thread = null;
            randomInit.setText("重新生成");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "开始游戏"&&randomInit.getText()=="重新生成") {//开始游戏
            isRunning = true;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        Change();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
            BeginAndOver.setText("结束游戏");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "结束游戏") {//结束游戏
            isRunning = false;
            thread = null;
            world.deleteAllCell();
            showWorld();
            BeginAndOver.setText("开始游戏");
            randomInit.setText("随机生成细胞");
            NowGeneration.setText("当前代数：0");
        }else if(e.getSource()==Exit){//退出游戏
            isRunning = false;
            thread = null;
            this.dispose();
            System.exit(0);
        }
    }
 
    public void Change(){
        world.updateOfCell();
        showWorld();
        NowGeneration.setText("当前代数："+world.getNowGeneration());
    }
 
    public void showWorld(){
        for(int x=0;x<lx;x++){
            for(int y=0;y<ly;y++){
                if(world.getCellXY(x,y)){
                    TWorld[x][y].setBackground(Color.black);
                }
                else{
                    TWorld[x][y].setBackground(Color.white);
                }
            }
        }
    }
}