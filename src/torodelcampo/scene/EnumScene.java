package torodelcampo.scene;

import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.ressources.image.ToroImage;

public enum EnumScene {

	SCENE1(new Scene(ToroImage.BACKGROUND.image, new LinearSegment(324, 0, 248,
			248), new LinearSegment(248, 248, 272, 1149), new LinearSegment(
			702, 0, 603, 268), new LinearSegment(603, 268, 627, 1149)));

	public Scene	scene;

	private EnumScene(final Scene scene) {
		this.scene = scene;
	}

}
