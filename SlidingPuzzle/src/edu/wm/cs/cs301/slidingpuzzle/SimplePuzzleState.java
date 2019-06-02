package edu.wm.cs.cs301.slidingpuzzle;
import java.util.Arrays;
import java.util.LinkedList;


public class SimplePuzzleState implements PuzzleState {

  	private PuzzleState state1;
  	
  	private Operation op;

  	private int pathLength;
  
    private int[][] tableOfValues;
	
	public SimplePuzzleState() {
        //oh my god we need to initialize them all to null at first then use the methods to get the values I'm so stupid
      
        //honestly this is the biggest part of OO that I neevr get; i remember I struggled for hours with this on BST in data structures as well...
        //WHEN YOU MAKE A NEW INSTANCE, MAKE ALL THE STUFF NULL!!!!!!!!!!!!!!! DONT FORGET!!!!!
      
        //super();
      	this.state1 = null; //this is the parent
        this.op = null; //this is the operation
      	this.pathLength = 0; //a new instance has not moved yet
        this.tableOfValues = null;
        
      
    }
	
  
    public SimplePuzzleState(PuzzleState state1, Operation op, int pathLength, int[][] tableOfValues) {
      
        //this is where the objects will be stored
        this.state1 = state1;
        this.op = op;
        this.pathLength = pathLength;
        this.tableOfValues = tableOfValues;
    }
      
  
	@Override
	public void setToInitialState(int dimension, int numberOfEmptySlots) {
		// TODO Auto-generated method stub
		
        //make our table match the given dimensions
      	this.tableOfValues = new int[dimension][dimension];
      
        //since we start at 1 and can just have nothing be in the bottom right corner, start at 1:
      	int num = 1;
      
      	//going through all the rows and columns and putting the values starting from 1 down
      	for(int i = 0; i < dimension; i++)
        {
            for(int j = 0; j < dimension; j++)
            {
                this.tableOfValues[i][j] = num;
              	
              	//then increment num and go to the adjacent
              	num++;
            }
        }
      	
      	//we still have to add the empty spots, represented by 0's. Since there could be more than one empty spot, we can just make a loop
      	//and go backwards, putting in more than one empty spot if need be:
      
        for(int k = 0; k < numberOfEmptySlots; k++)
        {
      		//dimension-1 because 4 would be out of bounds
            this.tableOfValues[dimension-1][dimension-1-k] = 0;
        }
	}

	@Override
	public int getValue(int row, int column) {
		
      	
        //Need to make an algorithm to incorporate row and col somehow.
        //can just return col number+1 (1,2,3,4) for 0th row
      	// then for 1st row, can do col number+5 (5,6,7,8)
      	// for 2nd row, can do col number+9 (9,10,11,12)
      	// for 3rd row, can do col number+13 (13,14,15)
      	
  
  		//maybe also just return tableOfValues[row][column]
		return tableOfValues[row][column];
	}
		
    @Override
    public boolean equals(Object obj) {
    	//we can check to see if the obj we pass in is equal to the current one.
    	if (this == obj)
    		return true;
    	if (obj == null)
    		return false;
    	//this line is needed to check the equivalence or lack thereof between a PuzzleState obj and a normal Object
    	//in the unit tests
    	if (getClass() != obj.getClass())
    		return false;
    	SimplePuzzleState test = (SimplePuzzleState) obj;
    	//the documentation I saw used deepEquals to compare the arrays, required to pass some tests
    	if (Arrays.deepEquals(tableOfValues, test.tableOfValues) == false)
    		return false;
    	return true;
    }
    
    @Override
    public int hashCode() {
    	//use 31 because it's a good prime number to use for hash tables
    	int num = 31;
    	int ans = 1;
    	ans = num * ans + Arrays.deepHashCode(tableOfValues);
    	return ans;
    }

	
	@Override
	public PuzzleState getParent() {
		// TODO Auto-generated method stub
      
		//to get the parent of the current state, we should just do "this" one's state, i.e:
      	return this.state1;
	}

	@Override
	public Operation getOperation() {
		// TODO Auto-generated method stub
		
		//this should be the same as the getParent method:
        return this.op;
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		
		//if all SimplePuzzleState objects have a path length, could you just keep traveling back through each parent
		//of the current state to get the total path length?
      
        //actually, it should be the same as the other get operations as long as the constructors are correct:
        return this.pathLength;
	}

	@Override
	public PuzzleState move(int row, int column, Operation op) {
		// TODO Auto-generated method stub


		//start by making a new table; since we are making a new instance of SimplePuzzleState, we cannot just reuse the old one we have "currently"
        int[][] newTableOfValues = new int[(tableOfValues.length)][(tableOfValues.length)]; //make it the same dimensions as our current table
        
        //now we have to fill this new table with the values of our old table
        
        for(int i = 0; i < tableOfValues.length; i++)
        {
        	
            for(int j = 0; j < tableOfValues.length; j++)
            {
            	
                newTableOfValues[i][j] = tableOfValues[i][j];
            }
        }
      
      //first case we can check is MOVERIGHT
      //we can't move right if we are in the last column or if there is a another tile to our right so make those the conditions:
      //also for all of these, have to make sure the given operation parameter is upheld and actually does that move
	  if(column != newTableOfValues.length-1 && newTableOfValues[row][column+1] == 0 && op == Operation.MOVERIGHT)
	  {
	      op = Operation.MOVERIGHT;
	    
	      //make the data of the tile to the right equal to that of the one you are on to complete the move (change the 0 to a 1 essentially)
	      newTableOfValues[row][column+1] = newTableOfValues[row][column];
	      //now have to make the spot that we moved from a free spot
	      newTableOfValues[row][column] = 0;
	  }
	        
	  //now we have to do a similar process for the rest of the moves but altering it based on where we are going spatially
	  
	  else if(column != 0 && newTableOfValues[row][column-1] == 0 && op == Operation.MOVELEFT) //for left, we can move as long we as arent in the first col and the spot to the left is empty
	  {
		  //System.out.println("moves left");
	      op = Operation.MOVELEFT;
	      newTableOfValues[row][column-1] = newTableOfValues[row][column];
	      newTableOfValues[row][column] = 0;
	  }
		
	  else if(row != 0 && newTableOfValues[row-1][column] == 0 && op == Operation.MOVEUP) //for up, move if we are not at the top of the board and there is an open spot above us
	  {
	      op = Operation.MOVEUP;
	      newTableOfValues[row-1][column] = newTableOfValues[row][column];
	      newTableOfValues[row][column] = 0;
	  }
	  
	  else if(row != newTableOfValues.length-1 && newTableOfValues[row+1][column] == 0 && op == Operation.MOVEDOWN) //for down, move if we are not at the bottom of the board, and there is a open spot below
	  {
	      op = Operation.MOVEDOWN;
	      //System.out.println("moves down");
	      newTableOfValues[row+1][column] = newTableOfValues[row][column];
	      newTableOfValues[row][column] = 0;
	  }
	  
      //gotta return a new SimplePuzzleState with the updated moves
      //make this as the parent because this current state before we return will soon be the parent of the new SimplePuzzleState
      //Also have to update the path length because we moved 
      //Finally, send in this recent, updated table of values to finish the update
      return new SimplePuzzleState(this, op, this.pathLength+1, newTableOfValues);
      
	}

	@Override
	public PuzzleState drag(int startRow, int startColumn, int endRow, int endColumn) {
		// TODO Auto-generated method stub
		
		//need to create a new instance of SimplePuzzleState to return but for drag
		PuzzleState dragState = new SimplePuzzleState();
		
		//then we need it to have the same properties as the current state
		dragState = this;
		
		//need to get the difference between where we are and where we want to go
		int rowDiff = (endRow) - (startRow);
		int columnDiff = (endColumn) - (startColumn);
		
//		System.out.println("start row is: " + startRow);
//		System.out.println("start col is: " + startColumn);
//		System.out.println("end row is: " + endRow);
//		System.out.println("end col is: " + endColumn);
		//we need to make a while loop with the condition that it ends when the row or column diff is 0, indicating we are where we want to go
		while (columnDiff != 0 || rowDiff != 0)
		{
			//we need to make sure that the spot we drag to is empty
			if (isEmpty(endRow, endColumn) == false)
			{
				//System.out.println("this means the spot we drag to is not empty");
				return null;
			}
			//also need to make sure the spot we drag from has a tile on it
			if (isEmpty(startRow, startColumn) == true)
			{
				//System.out.println("this means the spot we drag from is empty");
				return null;
			}
			
			//Let's start by moving up. The conditions for this being that the the row above us is empty and the rowDiff has to be negative
			if (isEmpty(startRow-1, startColumn) == true && rowDiff < 0)
			{
				//as described in the specs, the drag method is just a series of move operations so that, if we wanted, we can use the getParent method and trace our way back to where we started
				//we can do that through moving and dragging recursively
				dragState = dragState.move(startRow, startColumn, Operation.MOVEUP);
				//because we move up we need to make the startRow one less
				startRow--;
				//we also need to make sure the rowDiff is incremented since we are negative and moving would decrease the physical distance between rows, getting us closer to 0
				rowDiff++;
				return dragState.drag(startRow, startColumn, endRow, endColumn);
			}
			//now we can do that for the other operations
			//move down
			if (isEmpty(startRow+1, startColumn) == true && rowDiff > 0)
			{
				dragState = dragState.move(startRow, startColumn, Operation.MOVEDOWN);
				//because we move down we need to make the startRow one more
				startRow++;
				//we also need to make sure the rowDiff is decremented since we are positive and moving would decrease the physical distance between rows, getting us closer to 0
				rowDiff--;
				return dragState.drag(startRow, startColumn, endRow, endColumn);
			}
			//move left
			if (isEmpty(startRow, startColumn-1) == true && columnDiff < 0)
			{
				dragState = dragState.move(startRow, startColumn, Operation.MOVELEFT);
				//because we move left we need to make the startColumn one less
				startColumn--;
				//we also need to make sure the columnDiff is incremented since we are negative and moving would decrease the physical distance between columns, getting us closer to 0
				columnDiff++;
				return dragState.drag(startRow, startColumn, endRow, endColumn);
			}
			//move right
			if (isEmpty(startRow, startColumn+1) == true && columnDiff > 0)
			{
				dragState = dragState.move(startRow, startColumn, Operation.MOVERIGHT);
				//because we move right we need to make the startColumn one more
				startColumn++;
				//we also need to make sure the columnDiff is decremented since we are positive and moving would decrease the physical distance between columns, getting us closer to 0
				columnDiff--;
				return dragState.drag(startRow, startColumn, endRow, endColumn);
			}
		}
		
		//we are now complete with regards to dragging so we can just return whatever we have at this point
		return this;
	}

  
	@Override
	public PuzzleState shuffleBoard(int pathLength) {
		// TODO Auto-generated method stub
		
		//Need to create a new instance of SimplePuzzleState that we can return
		PuzzleState shuffleState = new SimplePuzzleState();
		
		//Then we need to give it the current properties of this current SimplePuzzleState
		shuffleState = this;
		
		//my plan is to use the LinkedList feature in Java to keep track of free spots on the board (suggested by Dr. Kemper on Piazza)
		while (pathLength > 0)
		{
			//need this while loop because we need to continuously loop through the information below during each call back to a previous
			while (true)
			{
				LinkedList<Integer> openRows = new LinkedList<Integer>();
				LinkedList<Integer> openColumns = new LinkedList<Integer>();
				
				//need a for loop to check what spaces are free on the board and add those to the list
				for (int i = 0; i < tableOfValues.length; i++)
				{
					for (int j = 0; j < tableOfValues.length; j++)
					{
						//might as well just use the isEmpty method to see wha't empty
						if (isEmpty(i,j))
						{
							openRows.add(i);
							openColumns.add(j);
						}
					}
				}
				
				//need to choose a random spot from all the spots we just got above
				int randomSpot = (int) Math.floor(Math.random() * openColumns.size()); //need to use floor so we don't end up with a decimal
				
				//now from the spot we chose, we need the corresponding row and col
				int randomRow = openRows.get(randomSpot);
				int randomColumn = openColumns.get(randomSpot);
				
				//we need an operation object that we can set the new direction chosen equal to; will just make it Null for now
				Operation newOp = null;
				
				//we will also need to randomly choose a direction to go along eith the shuffle
				int randomNum = (int) Math.floor(Math.random() * 4); //again need to floor it so we don't end up with a decimal
				
				if (randomNum == 0)
				{
					newOp = Operation.MOVELEFT;
					//because we chose left need to increment the column as we move 
					randomColumn++;
				}
				else if (randomNum == 1)
				{
					newOp = Operation.MOVERIGHT;
					randomColumn--;
				}
				else if (randomNum == 2)
				{
					newOp = Operation.MOVEUP;
					//since we move up, we increment the row rather than column
					randomRow++;
				}
				else if (randomNum == 3)
				{
					newOp = Operation.MOVEDOWN;
					randomRow--;
				}
				
				//now we need to make sure that the spot we chose doesn't cancel out the current move; if that was the case the tiles wouldn't make any progress in the shuffle.
				//If that happens we can just use continue so that we don't allow this move to occur
				
				if (this.getOperation() == Operation.MOVELEFT && newOp == Operation.MOVERIGHT)
				{
					continue;
				}
				else if (this.getOperation() == Operation.MOVERIGHT && newOp == Operation.MOVELEFT)
				{
					continue;
				}
				else if (this.getOperation() == Operation.MOVEUP && newOp == Operation.MOVEDOWN)
				{
					continue;
				}
				else if (this.getOperation() == Operation.MOVEDOWN && newOp == Operation.MOVEUP)
				{
					continue;
				}
				
				//one last check we must do is to make sure that the row and column we chose randomly is not empty or out of range.
				//we can do that with the isEmpty method as well as checking the values of the random row and column and comparing it to our tableOfValues
				if (isEmpty(randomRow, randomColumn) || randomRow < 0 || randomRow >= tableOfValues.length || randomColumn < 0 || randomColumn >= tableOfValues.length)
				{
					continue;
				}
				
				//since I believe this accounts for every condition, we can finally make the move and put it in the shuffleState and get out of this while loop 
				PuzzleState moveState = this.move(randomRow, randomColumn, newOp);
				shuffleState = moveState;
				break;
			}
			
			//now of course we need to recursively call ourselves since we need to account for the other iterations as we move down lower and lower pathLengths till we hit 0
			return shuffleState.shuffleBoard(pathLength-1);
		}
		
		//now we can return ourselves as the recursion is done and we have our new State
		return this;
		
	}

  
	@Override
	public boolean isEmpty(int row, int column) {
		// TODO Auto-generated method stub
      
        //check to see if this specific spot is empty or not, pretty simple
		//but also, as seen in the drag tests, have to be able to see if the space exists or not
		//I got many array index out of bounds 4 errors
		if (row < tableOfValues.length && column < tableOfValues.length)
		{
			//then have to make sure it's not negative if it isnt out of bounds
			if (row >= 0 && column >= 0)
			{
				if (this.tableOfValues[row][column] == 0)
		        {
		            return true;
		        }
			}
			
		}
        return false;
	}

	@Override
	public PuzzleState getStateWithShortestPath() {
		// TODO Auto-generated method stub
		
		//to me it makes sense to return whatever state you have at the moment for this
		//in the specs, you talk about how the returned state from this method has the stats of the current state of tiles
		//so what better way to get to that than by returning this?
		return this;
	}

}

