# SquareOfDeath

If I am being completely honest, the game "Square of Death" is a drinking game I was playing with a couple friends on holiday. 
Two of my friends ended up debating who had the best strategy which didn't really go anywhere after a few drinks.

Both of my friends were stubbornly sure they had the best strategy, so the next morning I decided to spend a couple hours writing a simulation.

The code could be very easily adapted if the user wanted to play the game, but it was built with the purpose of testing two specific strategies.

I can't find the actual game online, so I assume the game has been adapted over time.

Basic rules:

9 (3x3) cards are placed face up making the board. A player chooses a card and says higher or lower. A card is then dealt from the deck of cards 
and placed face-up. If the new card was guessed correctly (higher or lower), they continue until they have correctly predicted 3 dealt cards in a row. If they make a false prediction, they have to drink as many times as there are cards on that pile. They then continue until they get 3 correct in a row.

If there is a king on the board, then that is a good card as statistically you can say 'lower' and the chances are very high that you will be correct.
The decision is more difficult when the board is made up of cards around the middle point 7.

------------------
Ollie's Strategy:

This strategy involved saving your statistically best card to last. This meant that you had great odds provided you could get passed the first two 
decisions.

------------------
Patrick's Strategy:

This was to make the statistically best decision for each choice. So you would treat every choice as an isolated go and ignore the future.

------------------
Result:

The end result after running the simulation 100k times was that Patrick on average will have 2 fingers less of alcohol per game than Ollie, 
making Patrick the winner with the best strategy.


Extra:

Patrick was beginning to learn Java at the time so it was a good opportunity for me to show him OO programming and why it is beneficial.

Overall, if you're not interested at all in the code, at least you've learnt a new drinking game!

Andreas :)
