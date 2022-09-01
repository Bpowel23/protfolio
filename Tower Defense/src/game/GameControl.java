/**one of three main classes responsible for keeping control of game flow
 * creates gamestate and gameview objects
 * keeps track of the timer
 * using the time in action events it updates game, spawns enemys , and keeps track of over all time.
 * 
 */
package game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameControl implements Runnable, ActionListener
{
	//fields
	GameView view;
	GameState state;
	//keeps track of the time before timer goes off
	long previousTime;
	//keeps track of total time game has been running
	double totalTime;
	//keeps track of how many enemy's gets spawned                 (set value as of right now)
	double enemySpawner;
	int waveScaling;
	
	double tVirus , tSludge;
	long   virusCount, sludgeCount;
	
	double adjustScore;
	
	//has no real need of constructor as application calls the run function first
    public GameControl ()
    {
    	
    	// I moved all the code into a function named 'run' below.
    	
    }
    
    /**this is where the game first takes shape building a GameView and GameState objects
     * adds initial items to be drawn such as backdrop and menu
     * starts the timer (nanoseconds)
     */
    public void run ()
    {    	
    	// Build the game state.
    	state = new GameState();
    	// Build a view.  Note that the view builds it's own frame, etc.  All the work is there.
    	view = new GameView (state);
    	
    	// add backdrop and gameMenu
    	state.addGameObject(new backdrop());
    	state.addGameObject(new gameMenu(state));
    	state.addGameObject(new gameStart(state, 225,225));
    	
    	//test batch of enemy's for gameover
//    	for (int i = 0; i < 10 ;i++)
//    	{
//    		double displacement = i * 0.01;
//    		state.addGameObject(new EnemyVirus(0.0 + displacement, state));
//    	}
    	
    	// Start the animation loop. A.K.A timer real loop starts in action performed
    	
    	Timer t = new Timer(16, this);
    	t.start();
    	//every time timer runs it holds the value here
    	previousTime = System.nanoTime();
    	//initialize enemy generation parameters
    	totalTime = 0.0;
    	virusCount = 0;
    	sludgeCount = 0;
    	tVirus = 1.0;
    	tSludge = 6.0;
    	adjustScore = 0;
    	waveScaling = 10;
    	
    	
    }
    /**timer action
     * 
     * 
     */
    public void actionPerformed(ActionEvent e)
    {
    	long currentTime = System.nanoTime();
    	
    	double elapsedTime = (currentTime - previousTime)/1_000_000_000.0;
    	
    	previousTime = currentTime;
    	totalTime   += elapsedTime;
    	
    	
//    	System.out.println(elapsedTime);
    	//creates a enemy every few seconds if game has started (gameStart has been removed)
    	if(state.hasStart)
    	{
    		
    		wave();
    		enemyMovement();
    		adjustScore += elapsedTime;
    		//update score
    		if(adjustScore > .5 )
    		{
    			state.score ++;
    			adjustScore = 0;
    		}
    		
    	}
//    	System.out.println(totalTime);
    	
    	// update the game objects
    	
    	state.updateAll(elapsedTime);
    	
    	//if a object didnt use mouse then this will use it on nothing
    	state.consumeMouseClick();
    	
    	
    	// draw the game objects
    	view.repaint();
    
    }
    /**creates a random enemy every few seconds 
     * 
     */
    public void wave()
    {
    	
    	enemySpawner++;
    	if(enemySpawner == 600 )
    	{
    			double x = .001;
    			for(int i = 0; i < waveScaling ;i++)
    			{
    				state.addGameObject(new EnemyVirus(0.0 - x, state));
    				x += .01;
    				if( (waveScaling % 5) == 0)
    					state.addGameObject(new EnemySludge(0.0, state));
    			}
    		waveScaling += 5;
    		enemySpawner = 0 ;
    	}	
    	

    }
    public void enemyMovement()
    {
    	
    	if (totalTime > tVirus)
    	{
    		state.addGameObject(new EnemyVirus(0.0, state));
//    		state.addGameObject(new EnemyVirus(0.01, state));
    		virusCount ++;
    		tVirus += 0.25;
    		
    		if(virusCount % 4 == 0)
    		{
    			tVirus += 4.0;
    		}
    	}
    	
    	if (totalTime > tSludge)
    	{
    		state.addGameObject(new EnemySludge(0.0, state));
    		sludgeCount++;
    		tSludge += 6.0;
    		
    		if(sludgeCount % 3 == 0)
    		{
    			tSludge -= 3.0;
    		}
    	}	
    	
    }
   
}
