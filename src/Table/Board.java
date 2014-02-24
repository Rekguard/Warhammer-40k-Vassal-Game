package Table;

public abstract class Board {
	
		/* Measurements */
	protected float foot = 12.0f;
	protected float halfFoot = 6.0f;
	protected float inchInMM = 25.4f;
	protected float footInMM = inchInMM*12.0f;
	
	
		/* Board Dimensions */
	protected float dimensionX;
	protected float dimensionY;
	protected float dimensionZ;
	

	public abstract void drawBoard();
	
}
