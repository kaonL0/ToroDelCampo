package torodelcampo;

import java.util.List;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.alnaiyr.coordinates.PlanVector;
import com.alnaiyr.display.GraphicEntity;

public class SceneCreator extends GraphicEntity {

	public SceneCreator(PlanVector coord, float width, float height) {
		super(coord, width, height);
		// TODO Auto-generated constructor stub
	}

	World world;
	List<Scene> scenes;
	Scene currentScene;
	Taureau taureau;
	
	void createScene() {
		
	}

	void createNextScenes() {
		
	}
	
	void deleteScene() {
		
	}

	@Override
	public void render(Graphics g, GameContainer container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void gUpdate(int delta, boolean condition) {
		// TODO Auto-generated method stub
		
	}

}
