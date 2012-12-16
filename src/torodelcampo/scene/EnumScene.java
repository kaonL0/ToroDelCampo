package torodelcampo.scene;

import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.ressources.image.ToroImage;

public enum EnumScene {

	SCENE1(new Scene(ToroImage.BACKGROUND.image, new LinearSegment(0, 200, 800,
			200)));

	public Scene	scene;

	private EnumScene(final Scene scene) {
		this.scene = scene;
	}

}
