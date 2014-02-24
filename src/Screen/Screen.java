package Screen;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PERSPECTIVE_CORRECTION_HINT;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3d;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.awt.event.KeyEvent;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Screen {

	
	
	/* Measurements */
	
	protected float foot = 12.0f;
	protected float halfFoot = 6.0f;
	protected float inchInMM = 25.4f;
	protected float footInMM = inchInMM*12.0f;
	
	int screenSizeW;
	int screenSizeH;

	public Screen(int screenSizeWidth, int screenSizeHeight) {
		screenSizeW = screenSizeWidth;
		screenSizeH = screenSizeHeight;
		screenSetup(screenSizeWidth, screenSizeHeight);
	}

	private void screenSetup(int screenWidth, int screenHeight) {
		{
			// Calculate Aspect Ratio
			float aspect = (float) screenWidth / screenHeight;
			// Load Frame
			try {
				Display.setDisplayMode(new DisplayMode(screenWidth,
						screenHeight));
				Display.setTitle("..Warhammer 40,000 Vassel..");
				Display.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}

			// Initialise Screen code
			//glEnable(GL_TEXTURE_2D);
			glClearDepth(1.0);
			glEnable(GL_DEPTH_TEST);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			gluPerspective(50.0f, aspect, 0.001f, 1000.0f);
			
			glTranslatef(0.0f,0.0f,-50.0f);

			glMatrixMode(GL_MODELVIEW);
			glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
			glTranslatef(0.0f, 0.0f, -20.0f);

			// Initialise Components code
			init();

			while (!Display.isCloseRequested()) {
				// Render
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				
				
				input();
				drawEntities();
				Display.update();
				Display.sync(60);
			}
			// Close Window
			Display.destroy();

		}

	}

	private void init() {

	}

	private void input() {
		
		float rotationSpeed = 5.0f;
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			glRotatef(rotationSpeed, 0.0f, 0.0f, 1.0f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			glRotatef(rotationSpeed, 0.0f, 0.0f, -1.0f);
		}

	}

	private void drawEntities() {
		glPushMatrix();
			tableSection();
		glPopMatrix();
	}

	private void tableSection(){
		
		glColor3f(0.0f, 1.0f, 0.0f);
		
		
		for (float x = 0.0f; x < 12.0f; x++){
			for (float y = 0.0f; y < 12.0f; y++){
				glTranslatef(0.0f,1.0f,0.0f);
				InchSquare();
			}
			glTranslatef(1.0f,-12.0f,0.0f);
		}
		
		
	}
	private void InchSquare(){
		glPushMatrix();
			glBegin(GL_LINES);
				glVertex3d(0.0,0.0,0.0);
					glVertex3d(1.0, 0.0, 0.0);
				glVertex3d(1.0, 0.0, 0.0);
					glVertex3d(1.0, 1.0, 0.0);
				glVertex3d(1.0, 1.0, 0.0);
					glVertex3d(0.0, 1.0, 0.0);
				glVertex3d(0.0, 1.0, 0.0);
					glVertex3d(0.0, 0.0, 0.0);
			glEnd();
		glPopMatrix();
	}
}
