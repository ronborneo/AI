# AI

Short answer portion:
 1. 4! * 4! since there are 4 starting spots, 3 secondary choices, and so on. There are 4 * 3 * 2 * 1 possibilities for X and the same for O. 256 combinations in total.
 
 2.
  a. Max depth is 9 since there are 9 spaces on the board and filling each space will end the game.
  b. The minimum depth level is 5, since X goes first, he can end the game with 3 in a row before the sixth move begins if O never blocks the third spot.
  c. A terminal loss has a depth level of 5.
  d. A terminal win has a depth level of 5.
  
  3. Searches all possible next moves for C after P's move, then Searches P's counter-moves using a heuristic to determine the best move. 
  So the first part of the heuristic is based on the concept of picking the most winnable moves. 
  This is determined by how many different ways the Computer can win from P, the opponent's next move. So it "draws a line" across the board in eight ways, since there are eight different ways of getting 3 in a row, and checks for the enemy player's pieces to eliminate impossible ways of winning for the computer. Let the # of possible wins per move = w1. 
  
  At the same time, it searches P's number of wins based on every move of C. If one of the opponent's next moves gives them a winning state, the weight of that P's move is given -100.  Let # of wins for P = w2. w1 - w2 = W, the main weight.
  H(B,P) chooses the path for C's next move with highest minimum weight out of it's children.
 
 So since a player's next move migh miss blocking an opponent's winning move normally with this, the search for the opponent's next move will check for their winning move, and give it the highest negative weight possible every time, essentially eliminating that branch of moves from the possible choices.
 This strategy should open up more chances of winning, for the computer, while blocking the opponent as much as possible.
