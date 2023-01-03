import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.KeyStroke;
import static java.awt.event.InputEvent.*;
 
public class UlarTangga extends JFrame implements ActionListener
{
    int posPlayer1 = 1; // untuk menentukan posisi player
    int posPlayer2 = 1;
   
    public static int cekPlayer1 = 1; // untuk mengecek dan menyimpan posisi player
    public static int cekPlayer2 = 1;
 
    String nP1; // nama player
    String nP2;
 
    int turn = 1; // untuk posisi awal kedua player
   
    // variabel nilai dadu
    public static int dc1;
    public static int dc2;
    public static Boolean temp = false;
   
    // untuk munculin gambar dadu
    public static ImageIcon dadu[] = { new ImageIcon("src/img/dice1.png"), new ImageIcon("img/dadu2.jpeg"),
                                        new ImageIcon("src/img/dice3.png"), new ImageIcon("img/dadu4.jpeg"),
                                         new ImageIcon("src/img/dice5.png"), new ImageIcon("img/dadu6.jpeg") };
   
    // image papan
    ImageIcon PapanKosong[][] = new ImageIcon[10][10];
    ImageIcon PapanP1[][] = new ImageIcon[10][10];
    ImageIcon PapanP1P2[][] = new ImageIcon[10][10];
    ImageIcon PapanP2[][] = new ImageIcon[10][10];
   
    // image player
    ImageIcon Player1 = new ImageIcon("src/img/p1.png");
    ImageIcon Player2 = new ImageIcon("src/img/p2.png");
   
    // image giliran main
    ImageIcon icon1 = new ImageIcon("src/img/ceklis1.gif");
    ImageIcon icon2 = new ImageIcon("src/img/ceklis2.gif");
 
    // pesen object buttonnya
    JButton buttonGo = new JButton("Kocok Dadu");
    JButton buttonStop = new JButton("Berhenti");
 
    // pesen object menu bar
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem menuNew = new JMenuItem("2 Player");
    JMenuItem menuExit = new JMenuItem("Keluar");
    JMenuItem menuHelp = new JMenuItem("Cara Bermain");
 
    // pembuatan container
    Container c = getContentPane();
 
    // pembuatan label2 yang digunakan batu masukin image ke object
    public static JLabel dice1 = new JLabel();
    public static JLabel dice2 = new JLabel();
    JLabel lArrPapan[][] = new JLabel[10][10];
 
    JLabel p1 = new JLabel(Player1);
    JLabel p2 = new JLabel(Player2);
 
    JLabel nama1;
    JLabel nama2;
 
    JLabel h1 = new JLabel();
    JLabel h2 = new JLabel();
   
    JLabel title = new JLabel("====== Ular Tangga ======");
 
    // pembuatan panel-panel
    JPanel pUtara = new JPanel();
    JPanel pJudul = new JPanel();
 
    JPanel pCenter = new JPanel();
    JPanel pPapan = new JPanel();
 
    JPanel pDadu1 = new JPanel();
    JPanel pDadu2 = new JPanel();
    JPanel pButton = new JPanel();
    JPanel pInButton = new JPanel();
 
    JPanel pCeklis1 = new JPanel();
    JPanel pCeklis2 = new JPanel();
 
    JPanel pP1name = new JPanel();
    JPanel pP2name = new JPanel();
 
    JPanel pP1pic = new JPanel();
    JPanel pP2pic = new JPanel();
   
    void callImage()
    {
        //papan kosong
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                PapanKosong[i][j] = new ImageIcon("src/papan/"+i+"-"+j+".png");
                PapanP1[i][j] = new ImageIcon("src/papanP1/"+i+"-"+j+".png");
                PapanP1P2[i][j] = new ImageIcon("src/papanP1P2/"+i+"-"+j+".png");
                PapanP2[i][j] = new ImageIcon("src/papanP2/"+i+"-"+j+".png");
            }
        }      
    }
 
// constructor
    UlarTangga(int t,String player1,String player2,int posisiP1,int posisiP2,Boolean sign,int jalan)
    {
        super("Permainan Ular Tangga"); //untuk judul virtual machine
        c.setLayout(null);
 
        // buat menu bar
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
 
        fileMenu.add(menuNew);
        fileMenu.add(menuExit);
 
        helpMenu.add(menuHelp);
       
        //buat shortcut menu
        menuNew.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK ));
        menuExit.setAccelerator(KeyStroke.getKeyStroke('Q', CTRL_DOWN_MASK ));
 
        // pesen action listener
        menuNew.addActionListener(this);
        menuExit.addActionListener(this);
        menuHelp.addActionListener(this);
 
        buttonGo.addActionListener(this);
        buttonStop.addActionListener(this);
 
        // masukin panel-panel yang udah dibuat
        // panel papan
        pCenter.setBounds(10,70,460,470);
        pCenter.add(pPapan);
        pPapan.setLayout(new GridLayout(10,10));
 
        callImage();
 
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                Dimension imgSize = new Dimension(45,45);
                lArrPapan[i][j] = new JLabel(PapanKosong[i][j]);
                lArrPapan[i][j].setPreferredSize(imgSize);
                pPapan.add(lArrPapan[i][j]);
            }
        }
       
        if(sign==true)
        {
            lArrPapan[9][0].setIcon(PapanP1P2[9][0]);
        }
 
        c.add(pCenter,"Utara");
 
        // panel dadu 1
        Dimension diceSize = new Dimension(45,45);
        pDadu1.setBounds(490,190,58,58);
        dice1.setIcon(dadu[0]);
        dice1.setPreferredSize(diceSize);
        pDadu1.add(dice1);
        c.add(pDadu1,"Utara");
       
        // panel dadu 2
        pDadu2.setBounds(570,190,58,58);
        dice2.setIcon(dadu[0]);
        dice2.setPreferredSize(diceSize);
        pDadu2.add(dice2);
        c.add(pDadu2,"Utara");
 
        // panel gambar player 1
        pP1pic.setBounds(480,80,50,50);
        pP1pic.add(p1);
        c.add(pP1pic,"Utara");
 
        // panel gambar player 2
        pP2pic.setBounds(480,120,50,50);
        pP2pic.add(p2);
        c.add(pP2pic,"Utara");
 
        // panel ceklis pic
        pCeklis1.setBounds(600,90,50,50);  
        h1.setIcon(icon1);
        pCeklis1.add(h1);
        c.add(pCeklis1,"Utara");
 
        pCeklis2.setBounds(600,140,50,50);
        h2.setIcon(icon2);
        pCeklis2.add(h2);
        // c.add(pCeklis2,"Utara");
       
        // panel nama player - > akan tampil bila sudah di input
        nama1 = new JLabel(player1);
        pP1name.setBounds(525,90,65,50);
        pP1name.add(nama1);
        c.add(pP1name,"Timur");
 
        nama2 = new JLabel(player2);
        pP2name.setBounds(525,140,65,50);
        pP2name.add(nama2);
        c.add(pP2name,"Timur");
 
        // simpan nama player ke variabel global
        nP1=player1;
        nP2=player2;
 
        // panel button
        pButton.setBounds(498,280,125,125);
        pButton.add(pInButton);
 
        // pakai grid background layout
        GridBagLayout gridbag = new GridBagLayout();// create a layout manager
        GridBagConstraints constraints = new GridBagConstraints();
        pInButton.setLayout(gridbag);
 
        constraints.ipadx = 70;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
 
        // atur constraints
        gridbag.setConstraints(buttonGo, constraints);
 
        // tambah button ke content panel
        pInButton.add(buttonGo);                           
 
        constraints.ipadx = 60;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
 
        gridbag.setConstraints(buttonStop, constraints);
        pInButton.add(buttonStop);
 
        c.add(pButton,"Utara");
 
        if(t==0)
        {
            buttonGo.setEnabled(false);
            buttonStop.setVisible(false);
        }
 
        // panel judul
        pUtara.setBounds(3, 0, 650, 200); // x , y , lebar, panjang
        pUtara.add(pJudul);
        title.setFont(new Font("Helvatica",Font.BOLD,35));
        title.setForeground(Color.blue);
        pJudul.add(title);
 
        c.add(pUtara,"Utara");
 
        setSize(650,600);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
    }
 
    //jalan
    void Jalan(int posisiP1,int posisiP2, int giliran)
    {
        int a1; int a2; int s1; int s2;
        if (giliran == 1)
        {
            //hapus
            if(((cekPlayer1/10)%2)==0)
            {
                s1=0;
                s2=(cekPlayer1%10)-1;
            }
            else
            {
                s1=9;
                s2=10-(cekPlayer1%10);
            }
            if (cekPlayer1==cekPlayer2)
            {
                if(cekPlayer1<=10)
                {
                    lArrPapan[9][cekPlayer1-1].setIcon(PapanP2[9][cekPlayer1-1]);
                }
                else
                {
                    if(cekPlayer1%10==0)
                    {
                        lArrPapan[10-(cekPlayer1/10)][s1].setIcon(PapanP2[10-(cekPlayer1/10)][s1]);
                    }
                    else
                    {
                        lArrPapan[9-(cekPlayer1/10)][s2].setIcon(PapanP2[9-(cekPlayer1/10)][s2]);
                    }
                }
            }
            else
            {
                if(cekPlayer1<=10)
                {
                    lArrPapan[9][cekPlayer1-1].setIcon(PapanKosong[9][cekPlayer1-1]);
                }
                else
                {
                    if(cekPlayer1%10==0)
                    {
                        lArrPapan[10-(cekPlayer1/10)][s1].setIcon(PapanKosong[10-(cekPlayer1/10)][s1]);
                    }
                    else
                    {
                        lArrPapan[9-(cekPlayer1/10)][s2].setIcon(PapanKosong[9-(cekPlayer1/10)][s2]);
                    }
                }
            }
 
            //run
            if(((posisiP1/10)%2)==0)
            {
                a1=0;
                a2=(posisiP1%10)-1;
            }
            else
            {
                a1=9;
                a2=10-(posisiP1%10);
            }
            if (posisiP1==posisiP2)
            {
                if(posisiP1<=10)
                {
                    lArrPapan[9][posisiP1-1].setIcon(PapanP1P2[9][posisiP1-1]);
                }
                else
                {
                    if(posisiP1%10==0)
                    {
                        lArrPapan[10-(posisiP1/10)][a1].setIcon(PapanP1P2[10-(posisiP1/10)][a1]);
                    }
                    else
                    {
                        lArrPapan[9-(posisiP1/10)][a2].setIcon(PapanP1P2[9-(posisiP1/10)][a2]);
                    }
                }
            }else
            {
                if(posisiP1<=10)
                {
                    lArrPapan[9][posisiP1-1].setIcon(PapanP1[9][posisiP1-1]);
                }
                else
                {
                    if(posisiP1%10==0)
                    {
                        lArrPapan[10-(posisiP1/10)][a1].setIcon(PapanP1[10-(posisiP1/10)][a1]);
                    }
                    else
                    {
                        lArrPapan[9-(posisiP1/10)][a2].setIcon(PapanP1[9-(posisiP1/10)][a2]);
                    }
                }
            }
        }
       
        if (giliran == 2){
            //hapus
            if(((cekPlayer2/10)%2)==0)
            {
                s1=0;
                s2=(cekPlayer2%10)-1;
            }
            else
            {
                s1=9;
                s2=10-(cekPlayer2%10);
            }
            if (cekPlayer1==cekPlayer2)
            {
                if(cekPlayer2<=10)
                {
                    lArrPapan[9][cekPlayer2-1].setIcon(PapanP1[9][cekPlayer2-1]);
                }
                else
                {
                    if(cekPlayer2%10==0)
                    {
                        lArrPapan[10-(cekPlayer2/10)][s1].setIcon(PapanP1[10-(cekPlayer2/10)][s1]);
                    }
                    else
                    {
                        lArrPapan[9-(cekPlayer2/10)][s2].setIcon(PapanP1[9-(cekPlayer2/10)][s2]);
                    }
                }
            }
            else
            {
                if(cekPlayer2<=10)
                {
                    lArrPapan[9][cekPlayer2-1].setIcon(PapanKosong[9][cekPlayer2-1]);
                }
                else
                {
                    if(cekPlayer2%10==0)
                    {
                        lArrPapan[10-(cekPlayer2/10)][s1].setIcon(PapanKosong[10-(cekPlayer2/10)][s1]);
                    }
                    else
                    {
                        lArrPapan[9-(cekPlayer2/10)][s2].setIcon(PapanKosong[9-(cekPlayer2/10)][s2]);
                    }
                }
            }
 
            //run
            if(((posisiP2/10)%2)==0)
            {
                a1=0;
                a2=(posisiP2%10)-1;
            }
            else
            {
                a1=9;
                a2=10-(posisiP2%10);
            }
            if (posisiP1==posisiP2)
            {
                if(posisiP1<=10)
                {
                    lArrPapan[9][posisiP2-1].setIcon(PapanP1P2[9][posisiP2-1]);
                }
                else
                {
                    if(posisiP2%10==0)
                    {
                        lArrPapan[10-(posisiP2/10)][a1].setIcon(PapanP1P2[10-(posisiP2/10)][a1]);
                    }
                    else
                    {
                        lArrPapan[9-(posisiP2/10)][a2].setIcon(PapanP1P2[9-(posisiP2/10)][a2]);
                    }
                }
            }
            else
            {
                if(posisiP2<=10)
                {
                    lArrPapan[9][posisiP2-1].setIcon(PapanP2[9][posisiP2-1]);
                }
                else
                {
                    if(posisiP2%10==0)
                    {
                        lArrPapan[10-(posisiP2/10)][a1].setIcon(PapanP2[10-(posisiP2/10)][a1]);
                    }
                    else
                    {
                        lArrPapan[9-(posisiP2/10)][a2].setIcon(PapanP2[9-(posisiP2/10)][a2]);
                    }
                }
            }
        }
    }
   
    public static void main(String[]args)
    {
        String N1 = "";
        String N2 = "";
        new UlarTangga(0,N1,N2,1,1,false,1);
    }
 
    // untuk animasi acak dadu menggunakan thread
    public static class BasicThread1 extends Thread
    {
        //metode ini dipanggil ketika thread ini jalan
        public void run()
        {
            while(true)
            {
                // untuk cara kerja acak dadu
                dc1 = (int)(Math.random() * 6);
                dice1.setIcon(dadu[dc1]);
                dc2 = (int)(Math.random() * 6);
                dice2.setIcon(dadu[dc2]);
 
                if(temp==true)
                {
                    return;
                }
            }
        }
    }
 
    public void actionPerformed(ActionEvent e)
    {
        // action listener File menu
        if(e.getSource()==menuNew)
        {
            temp = true;
           
            nP1 = JOptionPane.showInputDialog(null,"Masukkan nama Player 1 :");
            nP2 = JOptionPane.showInputDialog(null,"Masukkan nama Player 2 :");
 
            if(nP1.equals(" ") || nP2.equals(" "))
            {
                JOptionPane.showMessageDialog(this,"Nama Player Harus Diisi Terlebih Dahulu");
           
                String nP1 = "";
                String nP2 = "";
                new UlarTangga(0,nP1,nP2,1,1,false,1);
            }
            else if(nP1.equals("") || nP2.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Nama Player Harus Diisi Terlebih Dahulu ");
       
                String N1 = "";
                String N2 = "";
                new UlarTangga(0,N1,N2,1,1,false,1);
            }
            else if(nP1.equals(nP2))
            {
                JOptionPane.showMessageDialog(this,"Nama Player 1 dan Player 2 Tidak Boleh Sama");
       
                String N1 = "";
                String N2 = "";
                new UlarTangga(0,N1,N2,1,1,false,1);
            }
            else if(nP1.equals(nP2)==false)
            {
                buttonGo.setEnabled(true);
                buttonGo.setVisible(true);
                buttonStop.setVisible(false);
                buttonStop.setEnabled(false);
                turn=1;
                posPlayer1=1;  // posisi 1-100
                posPlayer2=1;
                new UlarTangga(1,nP1,nP2,posPlayer1,posPlayer2,true,turn);
            }  
        }
 
        // action listener Help Menu
        else if(e.getSource()==menuHelp)
        {
            JOptionPane.showMessageDialog(null,"1. Klik Menu File");
            JOptionPane.showMessageDialog(null,"2. Kemudian pilih 2 Player");
            JOptionPane.showMessageDialog(null,"3. Lalu isi Nama dan kocok dadu");
        }  
        else if(e.getSource()==menuExit)
        {
            System.exit(0); // untuk exit atau keluar
        }
 
        // action listener Button
        else if(e.getSource()==buttonGo)
        {
            buttonStop.setVisible(true);
            buttonStop.setEnabled(true);
            buttonGo.setEnabled(false);
            buttonGo.setVisible(false);
            temp=false;
            Thread thread = new BasicThread1();
            thread.start();
        }
        else if(e.getSource()==buttonStop)
        {
            temp = true;
            cekPlayer1 = posPlayer1;
            cekPlayer2 = posPlayer2;
            if(turn==1)
            {
                posPlayer1 = posPlayer1+dc1+dc2+2;
                if(posPlayer1>100) //jika langkah lebih dari 100 untuk player 1
                {
                    posPlayer1=100-(posPlayer1-100);
                }
                else if(posPlayer1==100) //jika langkah sama dengan 100 untuk player 1
                {
                    lArrPapan[0][0].setIcon(PapanP1[0][0]);
                    JOptionPane.showMessageDialog(this,"Horeeee! Player "+ turn +" menang yeayyyyy!");
                }
            }
            else if(turn==2)
            {
                posPlayer2 = posPlayer2+dc1+dc2+2;
 
                if(posPlayer2>100) //jika langkah lebih dari 100 untuk player 2
                {
                    posPlayer2=100-(posPlayer2-100);
                }
                else if(posPlayer2==100) //jika langkah sama dengan 100 untuk player 2
                {
                    lArrPapan[0][0].setIcon(PapanP2[0][0]);
                    JOptionPane.showMessageDialog(this,"Horeeee! Player "+ turn +" menang yeayyyyy!");
                }
            }
            switch(posPlayer1) //utk jalan yang ada tangga atau ular (player 1)
            {
                case 3  : posPlayer1=23;   
                break;
 
                case 33 : posPlayer1=15;   
                break;
 
                case 50 : posPlayer1=69;   
                break;
 
                case 65 : posPlayer1=95;   
                break;
 
                case 98 : posPlayer1=56;   
                break;
 
                case 59 : posPlayer1=39;   
                break;
 
                case 89 : posPlayer1=67;   
                break;
            }
            switch(posPlayer2)  //utk jalan yang ada tangga atau ular (player 2)
            {
                case 3  : posPlayer2=23;   
                break;
 
                case 33 : posPlayer2=15;   
                break;
 
                case 50 : posPlayer2=69;   
                break;
 
                case 65 : posPlayer2=95;   
                break;
 
                case 98 : posPlayer2=56;   
                break;
 
                case 59 : posPlayer2=39;   
                break;
 
                case 89 : posPlayer2=67;   
                break;
            }
 
            //new RUN(posPlayer1,posPlayer2,true);
            Jalan(posPlayer1,posPlayer2, turn);
 
            //ganti ceklis jika dipencet stop + end turn
            if(turn==1)
            {
                h1.setIcon(icon2);
                h2.setIcon(icon1);
                turn=2;
            }
            else
            {  
                h1.setIcon(icon1);
                h2.setIcon(icon2);
                turn=1;
            }
            if ((posPlayer1==100) || (posPlayer2==100)) //utk case dimana pemenang sudah ditemukan
            {
                buttonStop.setVisible(false);
                buttonStop.setEnabled(false);
                buttonGo.setVisible(true);
                buttonGo.setEnabled(false);
            }
            else
            {
                buttonGo.setEnabled(true);
                buttonGo.setVisible(true);
                buttonStop.setVisible(false);
                buttonStop.setEnabled(false);
            }
        }
    }
}