package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.LinkedList;

public class GamePanel extends JPanel {
    //不變的參數
    private final int GRID_SIZE = 14; //格子數
    private final int CELL_SIZE = 40; //每格長寬
    private final int PADDINGLR = 35; //邊左右
    private final int PADDINGUD = 35; //邊上下
    private final int PADDING = 20; //線外到棋盤最外面的邊
    private final int boardSize = GRID_SIZE * CELL_SIZE;//框線長
    private final int chessradius = 30; //棋子直徑
    Font font = new Font("Courier New", Font.BOLD + Font.ITALIC, 40);
    Font font2 = new Font("Times New Roman", Font.BOLD, 24);

    //變動靜態參數
    public static boolean isBlack = true; //是否黑子 true黑子 false白子
    public static int [][] board = new int[15][15]; //board[y][x]
    public static int [][] goback = new int[225][2]; //紀錄前面幾步
    public static int stepcount = 0; //步數

    public static boolean ghostmode =false;

    public static boolean bombmode = false;


    private boolean Win = false; //是否胜利
    ResetButton reset = new ResetButton("Reset",this);
    RegretButton gobackbutton = new RegretButton("Regret",this);
    BackToMenuButton backmenu = new BackToMenuButton("Menu");
    static JLabel label = new JLabel("PLAYER:BLACK");
    //思考時間timer
    public static Timer blackTimer;
    public static Timer whiteTimer;
    public static int blackTime = 0; // 黑子計時（秒）
    public static int whiteTime = 0; // 白子計時（秒）
    static JLabel timerLabel11 = new JLabel("Black Time: 0s");
    static JLabel timerLabel12 = new JLabel("White Time: 0s");
    //倒數計時timer
    public static Timer countdownTimer;
    public static int  countdown = 30; // 每個玩家30秒倒數
    static JLabel timerLabel2 = new JLabel("Time left: 30s");
    //圖
    private final Image backgroundImage;
    static Queue<Integer> queue = new LinkedList<>();
    public GamePanel() {
        label.setBounds(650, 50, 300, 100);
        label.setFont(font);
        timerLabel11.setBounds(720, 100, 300, 100);
        timerLabel11.setFont(font2);
        timerLabel12.setBounds(720, 150, 300, 100);
        timerLabel12.setFont(font2);
        timerLabel2.setBounds(720, 200, 300, 100);
        timerLabel2.setFont(font2);
        backgroundImage = new ImageIcon("resources/pics/noob.jpg").getImage();

        this.add(reset);
        this.add(gobackbutton);
        this.add(backmenu);
        this.add(label);
        this.add(timerLabel11);
        this.add(timerLabel12);
        this.add(timerLabel2);
        this.setBounds(0, 0, 1000, 700);
        reset();

        blackTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blackTime++;
                setBlackTime(blackTime);
            }
        });

        whiteTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whiteTime++;
                setWhiteTime(whiteTime);
            }
        });
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                setCountTime(countdown);
                if (countdown == 0) { //時間到
                    countdownTimer.stop();
                    blackTimer.stop();
                    whiteTimer.stop();
                    JOptionPane.showMessageDialog(null,isBlack ? "黑子超時" : "白子超時");
                }
            }
        });
        SoundPlayer soundPlayer = new SoundPlayer();
        soundPlayer.loadSound("/sounds/a.wav");
        soundPlayer.setVolume(5.0f);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if(x>PADDINGLR && y>PADDINGUD && x < PADDINGLR + 2*PADDING + boardSize && y < PADDINGLR + 2*PADDING + boardSize) {
                    int row = Math.round(((float)(y - PADDINGUD - PADDING) /CELL_SIZE));
                    int col = Math.round(((float)(x - PADDINGLR - PADDING) /CELL_SIZE));
                    if (addPiece(row, col, isBlack)) {
                        repaint();
                        goback[stepcount][0] = col;//x
                        goback[stepcount][1] = row;//y
                        stepcount++;
                        Win = isWin(row, col, isBlack);
                        if(!Win){
                            SoundPlayer.playSound();
                            if(GamePanel.countdown<=30){
                                GamePanel.countdown = 30;
                            }
                            isBlack = !isBlack;
                            label.setText(isBlack ? "PLAYER:BLACK" : "PLAYER:WHITE");
                            timerLabel2.setText("Time left: " + countdown + "s");
                            if (isBlack) {
                                whiteTimer.stop();
                                blackTimer.start();
                            } else {
                                blackTimer.stop();
                                whiteTimer.start();
                            }
                            if(ghostmode){
                                queue.offer(col);
                                queue.offer(row);
                            }
                            if(ghostmode&&stepcount>6){
                                int ghostx=queue.poll();
                                int ghosty=queue.poll();
                                board[ghosty][ghostx]+=2;
                            }
                            if(!ghostmode&&bombmode&&(stepcount%12==5||stepcount%12==0)){
                                queue.offer(col);
                                queue.offer(row);
                            }
                            if(bombmode&&stepcount>10&&(stepcount%12==11||stepcount%12==6)){
                                int bombx=queue.poll();
                                int bomby=queue.poll();
                                activate(bombx,bomby);
                            }
                        }else{
                            countdownTimer.stop();
                            blackTimer.stop();
                            whiteTimer.stop();
                            JOptionPane.showMessageDialog(null,isBlack ? "黑子勝利" : "白子勝利");
                            reset();
                            repaint();
                            Win = false;
                            isBlack = true;
                            if(countdown<=31){
                                countdown = 31;
                            }
                            blackTime = -1;
                            whiteTime = -1;
                            stepcount =0;
                            blackTimer.getActionListeners()[0].actionPerformed(null);
                            whiteTimer.getActionListeners()[0].actionPerformed(null);
                            countdownTimer.getActionListeners()[0].actionPerformed(null);
                            countdownTimer.start();
                            blackTimer.start();
                            label.setText("PLAYER:BLACK");
                        }
                    }
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        // Draw background
        g.setColor(Color.ORANGE);
        g.fillRect(PADDINGLR, PADDINGUD, boardSize+PADDING*2, boardSize+PADDING*2); //x,y,長,寬

        // Draw lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= GRID_SIZE; i++) {
            // Vertical lines
            g.drawLine(PADDINGLR + i * CELL_SIZE + PADDING, PADDINGUD+ PADDING, PADDINGLR + i * CELL_SIZE+ PADDING, PADDINGUD + boardSize+ PADDING); //x,y,x,y
            // Horizontal lines
            g.drawLine(PADDINGLR+ PADDING, PADDINGUD + i * CELL_SIZE+ PADDING, PADDINGLR + boardSize+ PADDING, PADDINGUD + i * CELL_SIZE+ PADDING);
        }
        // Draw dot
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                g.fillOval(PADDINGUD + PADDING + CELL_SIZE*(3+i*4)-3,PADDINGUD + PADDING + CELL_SIZE*(3+j*4)-3,6,6);
            }
        }
        drawpiece(g);
    }
    //繪製棋子、控制棋子陣列
    public void drawpiece(Graphics g){
        for (int i=0; i<15; i++){
            for (int j=0; j<15; j++){
                if(board[i][j] != 0&&board[i][j]!=3&&board[i][j]!=4){  //如果不是空格
                    if(board[i][j] == 1){ //如果是黑子
                        g.setColor(Color.BLACK);
                    }else{ //白子
                        g.setColor(Color.white);
                    }
                    g.fillOval(CELL_SIZE*j + PADDINGLR+PADDING-chessradius/2,CELL_SIZE*i + PADDINGUD+PADDING-chessradius/2,chessradius,chessradius);
                    //如果是白子,为了好看,给白子加上边框
                    if(board[i][j] == 2){
                        g.setColor(Color.BLACK);
                        g.drawOval(CELL_SIZE*j + PADDINGLR+PADDING-chessradius/2,CELL_SIZE*i + PADDINGUD+PADDING-chessradius/2,chessradius,chessradius);
                    }
                }
            }
        }
    }
    public void setBlackTime(int newTime){
        blackTime = newTime;
        timerLabel11.setText("Black Time: " + blackTime + "s");
    }
    public void setWhiteTime(int newTime){
        whiteTime = newTime;
        timerLabel12.setText("White Time: " + whiteTime + "s");
    }
    public void setCountTime(int newTime){
        countdown = newTime;
        timerLabel2.setText("Time left: " + countdown + "s");
    }
    //判斷棋子能不能下
    public boolean addPiece(int row, int col, boolean isBlack){
        if(board[row][col] == 0){ //没有棋子的地方才能落子
            board[row][col] = isBlack?1:2;
            return true;
        }
        return false;
    }
    public boolean isWin(int row, int col, boolean isBlack){
        //橫線
        int curx=col-4;
        int cury=row;
        int count=0;
        int a = isBlack?1:2;
        for(int i=0;i<5;i++) {
            count=0;
            for(int j=0;j<5;j++) {
                if(curx>=0&&curx<=10&&(board[cury][curx+j]==a||board[cury][curx+j]==a+2)){
                    count++;
                }
            }
            if(count==5) {
                return true;
            }
            curx++;
        }
        //直線
        curx=col;
        cury=row-4;
        for(int i=0;i<5;i++) {
            count=0;
            for(int j=0;j<5;j++) {
                if(cury>=0&&cury<=10&&(board[cury+j][curx]==a||board[cury+j][curx]==a+2)) {
                    count++;
                }
            }
            if(count==5) {
                return true;
            }
            cury++;
        }
        //左上到右下
        curx=col-4;
        cury=row-4;
        for(int i=0;i<5;i++) {
            count=0;
            for(int j=0;j<5;j++) {
                if(cury>=0&&cury<=10&&curx>=0&&curx<=10&&(board[cury+j][curx+j]==a||board[cury+j][curx+j]==a+2)) {
                    count++;
                }
            }
            if(count==5) {
                return true;
            }
            cury++;
            curx++;
        }

        //右上到左下
        curx=col+4;
        cury=row-4;
        for(int i=0;i<5;i++) {
            count=0;
            for(int j=0;j<5;j++) {
                if(cury>=0&&cury<=10&&curx>=4&&curx<=14&&(board[cury+j][curx-j]==a||board[cury+j][curx-j]==a+2)) {
                    count++;
                }
            }
            if(count==5) {
                return true;
            }
            cury++;
            curx--;
        }
        return false;
    }
    public static void activate(int x,int y){
        board[y][x]=0;
        int temp1=y+1,temp2=x+1,oldcolor=0,newcolor=0;//move right up
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp1++;
            temp2++;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move right
        temp1=y;
        temp2=x+1;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp2++;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move right down
        temp1=y-1;
        temp2=x+1;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp1--;
            temp2++;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move down
        temp1=y-1;
        temp2=x;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp1--;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move down left
        temp1=y-1;
        temp2=x-1;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp1--;
            temp2--;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move left
        temp1=y;
        temp2=x-1;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp2--;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move left up
        temp1=y+1;
        temp2=x-1;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp1++;
            temp2--;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;//move up
        temp1=y+1;
        temp2=x;
        while(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0&&board[temp1][temp2]!=0){
            newcolor=board[temp1][temp2];
            board[temp1][temp2]=oldcolor;
            oldcolor=newcolor;
            temp1++;
        }
        if(temp1<=14&&temp2<=14&&temp1>=0&&temp2>=0)
            board[temp1][temp2]=oldcolor;
        oldcolor=0;
    }
    public static void reset(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0;
            }
        }
        queue.clear();
    }
}
