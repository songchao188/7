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
        NowGeneration=new JLabel("��ǰ������0");
        randomInit=new JButton("�������ϸ��");
        BeginAndOver=new JButton("��ʼ��Ϸ");
        Exit=new JButton("�˳�");
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
        this.setLocationRelativeTo(null); // �ô�������Ļ����
        this.setVisible(true);// ����������Ϊ�ɼ���
 
        //ע�������
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
        if (e.getSource() == randomInit&&BeginAndOver.getText()=="��ʼ��Ϸ") {//������ɵ�һ��
            world.randonInitCell();
            showWorld();
            isRunning = false;
            thread = null;
            randomInit.setText("��������");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "��ʼ��Ϸ"&&randomInit.getText()=="��������") {//��ʼ��Ϸ
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
            BeginAndOver.setText("������Ϸ");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "������Ϸ") {//������Ϸ
            isRunning = false;
            thread = null;
            world.deleteAllCell();
            showWorld();
            BeginAndOver.setText("��ʼ��Ϸ");
            randomInit.setText("�������ϸ��");
            NowGeneration.setText("��ǰ������0");
        }else if(e.getSource()==Exit){//�˳���Ϸ
            isRunning = false;
            thread = null;
            this.dispose();
            System.exit(0);
        }
    }
 
    public void Change(){
        world.updateOfCell();
        showWorld();
        NowGeneration.setText("��ǰ������"+world.getNowGeneration());
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