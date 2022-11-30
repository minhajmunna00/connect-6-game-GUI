
package connectsix;


public class Cell_no {
    
    private char cell_Pos;
    private int  row_Pos;
    private int  cell_State;
     
   
    public Cell_no()
    {
      cell_Pos = ' ';
      row_Pos  =  0; 
      cell_State = 0;
    } 
    
  
    public Cell_no( char newCellPosition,  int newRowPosition)
    {
      cell_Pos = newCellPosition;
      row_Pos  =  newRowPosition;  
    }
    
  
    public void  Cell_Position(char newCellPosition)
    {
        cell_Pos = newCellPosition;     
    }

   
    public void setRowPosition( int newRowPosition)
    {
          row_Pos = newRowPosition;
    }
      
   
    public void setAllPosition( char newCellPosition, int newRowPosition)
    {
      cell_Pos =  newCellPosition;
      row_Pos  =  newRowPosition;  
    }


    public void  Cell_State(int newCellState)
    {
        cell_State = newCellState;     
    }
    
  
    public int getCellState()
    {
        return cell_State;
    }
    

    public char getCellPosition() 
    {
      return cell_Pos;      
    }

 
    public int getRowPosition() 
    {
      return row_Pos;
    }
   
    
}
