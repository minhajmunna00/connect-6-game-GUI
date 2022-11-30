
package connectsix;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Connect_six extends JFrame 
{ 
    
    private int boardsize;                             
    private int playernumber=0;                    
    private int no_player;                  
    private static int cell_no=0;        
   
   
    private final JFrame frame_size;                   
    private final JPanel panel_size;                  
    private final JButton[][] btn;            
    private Cell_no game_Board[][];                   
    private final GridLayout grid_view;               
  
  
    ImageIcon empty_cel   = new ImageIcon(getClass().getResource("emptycell.png"));
    ImageIcon user1 = new ImageIcon(getClass().getResource("player1.png"));
    ImageIcon user2 = new ImageIcon(getClass().getResource("player2.jpg"));
    
    
    public static void main(String[] args)
    {
        Connect_six game_play = new Connect_six();
    }
    
    public Connect_six () 
    {       
        frame_size = new JFrame("Connect 6 Game");
        panel_size = new JPanel();
         
        userno_bodsize();  
        dynamic_Allocation();         

        btn = new JButton[getBoardSize()][getBoardSize()];    
        grid_view = new GridLayout(getBoardSize(),getBoardSize());      
        panel_size.setLayout(grid_view);   
        
      
        primary_Board();
        
       
        frame_size.setContentPane(panel_size);
        frame_size.pack();                 
        frame_size.setLocationRelativeTo(null); 
        frame_size.setVisible(true);           
        frame_size.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }

    public void setBoardSize(int newSize)
    {
        boardsize = newSize;
    }
 
    public int getBoardSize()
    {
        return boardsize;
    }

    public static int numberOfLivingCells()
    {
        return cell_no;
    }
    

    public void userno_bodsize()
    {
        String playerNumber = JOptionPane.showInputDialog( "Enter User Number Between one or two" );
        String boardSize = JOptionPane.showInputDialog( "Enter Board Size Number" );

        no_player  = Integer.parseInt(playerNumber); 
        int sizeOfBoard = Integer.parseInt(boardSize);

        if(sizeOfBoard < 2)
        {
           JFrame frameInputError = new JFrame();
           JOptionPane.showMessageDialog(frameInputError,
           "Sorry!! Please Enter The higher Board number or Must be greater than 4.",
           "Error",
           JOptionPane.ERROR_MESSAGE);
           System.exit(0);
        }

        setBoardSize(sizeOfBoard);  
    }

    public void dynamic_Allocation()
    {
        game_Board = new Cell_no[getBoardSize()][getBoardSize()];
        for (int i = 0; i <getBoardSize(); i++)
        {
            for (int j = 0; j <getBoardSize(); j++)
            {
                game_Board [i][j]=new Cell_no();
            }
        }
    } 
  
    public void btn_add_gameboard()
    {
        for(int i=0; i<getBoardSize(); ++i)
        {
            for(int j=0; j<getBoardSize(); ++j)
            {
                btn[i][j] = new JButton(empty_cel); 
                
                if(no_player==1)  
                {
                    btn[i][j].addActionListener(new listenButtonOnePlayer());
                }
                
                if(no_player==2)  
                {
                    btn[i][j].addActionListener(new userbtntow());
                }
                
                panel_size.add(btn[i][j]);  
            }
        }            
    }       
 
    public void primary_Board()
    {
        for (int i=getBoardSize()-2; i>= 0; --i) 
        {
            for (int j = getBoardSize()-1; j>=0; --j)
            {
                game_Board[i][j].Cell_State(-99);
            }
        }    
       btn_add_gameboard(); 
    }
    

    public void win_user(int champion)
    {
        for(int i=0; i<getBoardSize(); ++i)
        {         
            for(int k=0; k<getBoardSize(); ++k)
            {     
                if(game_Board[i][k].getCellState() == champion)
                {    
                    if(i+3<getBoardSize())
                    {
                        if(game_Board[i+1][k].getCellState() == champion && game_Board[i+2][k].getCellState() == 
                                champion && game_Board[i+3][k].getCellState() == champion)  
                        {
                            if(champion==1)
                                outcome_view(1);
                            else
                                outcome_view(2);
                        }
                    }
                    if(k + 3 <getBoardSize())
                    { 
                        if(game_Board[i][k+1].getCellState() == champion && game_Board[i][k+2].getCellState() == 
                                champion && game_Board[i][k+3].getCellState() == champion)
                        { 
                            if(champion==1)
                                outcome_view(1);
                            else
                                outcome_view(2);
                        }
                    }

                    if(i  < getBoardSize()- 3 && k<getBoardSize()-3)
                    {
                        if(game_Board[i+1][k+1].getCellState() == champion && game_Board[i+2][k+2].getCellState() == 
                                champion && game_Board[i+3][k+3].getCellState() == champion)
                        {  
                            if(champion==1)
                                outcome_view(1);
                            else
                                outcome_view(2);
                        }   
                    }

                    if(i  < getBoardSize()- 3 && k - 3 >= 0 )
                    {
                        if(game_Board[i+1][k-1].getCellState() == champion && game_Board[i+2][k-2].getCellState() == 
                                champion && game_Board[i+3][k-3].getCellState() == champion)
                        {
                            if(champion==1)
                                outcome_view(1);
                            else
                                outcome_view(2);
                        } 
                    }                           
                }         
            }             
        } 
    } 

   public void outcome_view(int chap_user)
   {
       JFrame frameShowResult = new JFrame();       
       if(chap_user==1)
       {
            JOptionPane.showMessageDialog(frameShowResult,
            "\nChampion: User 1\n\nNew Game begin\n\n",
            "Finshed",
            JOptionPane.INFORMATION_MESSAGE);
            repeat_game(); 
       }
       else
       {
            JOptionPane.showMessageDialog(frameShowResult,
            "\nChampion : User 2\n\nNew Game Begin.\n\n",
            "Finshed",
            JOptionPane.INFORMATION_MESSAGE); 
            repeat_game();    
       }
   }
   

   public void repeat_game()
   {
       
        for(int i=0; i<getBoardSize(); ++i)
        {         
            for(int l=0; l<getBoardSize(); ++l)
            {
                game_Board[i][l].Cell_State(-99);  
                btn[i][l].setIcon(empty_cel);     
            }
        }
        
        frame_size.setVisible(false);                       
        Connect_six newGame = new Connect_six();         
   }
     
  
    private class listenButtonOnePlayer implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try { 
                for(int i=getBoardSize()-1; i>=0; --i)  
                {
                    for(int j=0; j<=getBoardSize()-1; ++j)
                    {
                        if(btn[i][j]== e.getSource())
                        {  
                            
                           if(0 == playernumber%2)   
                           { 
                                for(int k=0; k<=getBoardSize(); ++i)
                                {
                                   
                                    if(game_Board[i-k][j].getCellState() == 0)
                                    {
                                       btn[i-k][j].setIcon(user1);           
                                       game_Board[i-k][j].setAllPosition('X', i);  
                                       game_Board[i-k][j].Cell_State(1);         
                                       ++cell_no;                       
                                       win_user(1);                            
                                       break; 
                                    } 
                                }

                                setUpperCellToEmpty(i,j); 
                                System.out.println("... Player 1 played ... ");
                                ++playernumber;  
                                break;    
                            }

                           
                            if(1 == playernumber%2) 
                            { 
                                moveComputer(i);
                                System.out.println("... Computer played ... ");
                                ++playernumber; 
                                break;
                            }
                            else 
                            {
                                warningMessage();
                            }
                        } 
                    }        
                } 
            
            
            } 
            catch(Exception ex)
            { 
                warningMessage();
            }
        } 
              
    } 
    
    
    public void warningMessage()
    {
        JFrame frameWarning = new JFrame();           
        JOptionPane.showMessageDialog(frameWarning,
        "Invalid Movement !!\nThe cell is not empty.", "Warning",
        JOptionPane.WARNING_MESSAGE);
    }
                        
    
    public void setUpperCellToEmpty(int rowPos, int columnPos)
    {
        try 
        {
            game_Board[rowPos-1][columnPos].Cell_State(0);    
        }   
        catch (Exception ex) 
        { }      
    }
     
   
    public void moveComputer(int rowPosition)
    {
        int l,m;
        boolean flag=false;

        for(l=getBoardSize()-1; (l>=0)&& !flag; --l)
        { 
            for(m=0; (m<getBoardSize()) && !flag; ++m)
            {
                if(game_Board[l][m].getCellState() == 0)
                {
                    btn[l][m].setIcon(user2);        
                    game_Board[l][m].setAllPosition('O', rowPosition); 
                    game_Board[l][m].Cell_State(2);      
                    ++cell_no;
                    win_user(2);
                    flag = true;  
                    setUpperCellToEmpty(l,m);
                }
            }
        } 
    }

    private class userbtntow implements ActionListener
    {           
        @Override
        public void actionPerformed(ActionEvent e)
        {            
            try {
                int type_Flag = 0;
                int Player_Order=0;

                for(int i=getBoardSize()-1; i>=0; --i)
                {
                    for(int j=0; j<=getBoardSize()-1; ++j)
                    {
                        if(type_Flag==0 && btn[i][j]== e.getSource()) 
                        {  
                           if(Player_Order==0 && playernumber%2==0) 
                           { 
                               
                                for(int k=0; k<=getBoardSize(); ++i)    
                                {
                                    if(game_Board[i-k][j].getCellState()==0 && playernumber%2==0)
                                    {
                                       btn[i-k][j].setIcon(user1);          
                                       game_Board[i-k][j].setAllPosition('X', i); 
                                       ++cell_no;  
                                       win_user(1);     
                                       Player_Order=1;   
                                       type_Flag=1;
                                       break; 
                                    } 
                                }

                                setUpperCellToEmpty(i,j);   
                                System.out.println("... Player 1 played ... ");
                                ++playernumber; 
                                break;
                            }

                            if(Player_Order==0 && playernumber%2==1) 
                            { 
                                for(int k=0; k<=getBoardSize(); ++i)
                                {
                                    if(game_Board[i-k][j].getCellState()==0 && playernumber%2==1)
                                    {
                                       btn[i-k][j].setIcon(user2);            
                                       game_Board[i-k][j].setAllPosition('O', i);    
                                       game_Board[i-k][j].Cell_State(2);         
                                       ++cell_no;
                                       win_user(2);
                                       Player_Order=1;
                                       type_Flag=1;
                                       break;
                                    } 
                                }
                                setUpperCellToEmpty(i,j);
                                System.out.println("User 2 PLayed Succesfully ");
                                ++playernumber;
                                break;
                            }
                        } 
                    }          
                }    
        }catch(Exception ex) 
        { 
            warningMessage(); 
        }     
       
        }   
    } 
} 
