package torodelcampo;

import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import com.alnaiyr.display.GraphicEntity;
import com.alnaiyr.display.impl.basic.DrawEntity;
import com.alnaiyr.generator.layers.LayerFactory;
import com.alnaiyr.ressources.image.ToroImage;

public class Scene {

	List<Personnage>	personnages;
	List<Obstacle>		obstacles;
	GraphicEntity		back;

	Scene() {

		back = new DrawEntity(new Vector2f(-.38f, 0, true), false,
				ToroImage.BACKGROUND.image.getScaledCopy(4));
		LayerFactory.getInstance().addToLayer(48, back);

	}
}