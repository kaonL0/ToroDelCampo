package torodelcampo.scene;

import com.alnaiyr.math.geom.shape.segments.LinearSegment;
import com.alnaiyr.ressources.image.ToroImage;

public enum EnumScene {

	SCENE1(new Scene(ToroImage.BACKGROUND0.image, new LinearSegment(780, 0,
			780, 1080), new LinearSegment(1138, 0, 1138, 1080))),

	SCENE2(new Scene(ToroImage.BACKGROUND1.image, new LinearSegment(780, 1080,
			780, 908), new LinearSegment(780, 908, 816, 822),
			new LinearSegment(816, 822, 976, 606), new LinearSegment(976, 606,
					994, 558), new LinearSegment(994, 558, 970, 492),
			new LinearSegment(970, 492, 796, 300), new LinearSegment(796, 300,
					780, 0)

			, new LinearSegment(1138, 1080, 1138, 900), new LinearSegment(1138,
					900, 1173, 811), new LinearSegment(1173, 811, 1282, 641),
			new LinearSegment(1282, 641, 1303, 541), new LinearSegment(1303,
					541, 1278, 442), new LinearSegment(1278, 442, 1222, 360),
			new LinearSegment(1222, 360, 1140, 252), new LinearSegment(1140,
					252, 1138, 0))),

	SCENE3(new Scene(ToroImage.BACKGROUND2.image, new LinearSegment(780, 1080,
			774, 902), new LinearSegment(774, 902, 882, 546),
			new LinearSegment(882, 546, 782, 184), new LinearSegment(782, 184,
					780, 0)

			, new LinearSegment(1138, 1080, 1144, 908), new LinearSegment(1144,
					908, 1248, 544), new LinearSegment(1248, 544, 1142, 180),
			new LinearSegment(1142, 180, 1138, 0))),

	SCENE4(new Scene(ToroImage.BACKGROUND3.image, new LinearSegment(780, 1080,
			770, 966), new LinearSegment(770, 966, 440, 582),
			new LinearSegment(440, 582, 436, 528), new LinearSegment(436, 528,
					776, 172), new LinearSegment(776, 172, 780, 0)

			, new LinearSegment(1138, 1080, 1132, 968), new LinearSegment(1132,
					968, 1092, 890), new LinearSegment(1092, 890, 812, 590),
			new LinearSegment(812, 590, 810, 512), new LinearSegment(810, 512,
					1094, 246), new LinearSegment(1094, 246, 1142, 162),
			new LinearSegment(1142, 162, 1138, 0))),

	SCENE5(new Scene(ToroImage.BACKGROUND4.image, new LinearSegment(780, 1080,
			782, 960), new LinearSegment(782, 960, 1104, 590),
			new LinearSegment(1104, 590, 1100, 506), new LinearSegment(1100,
					506, 812, 236), new LinearSegment(812, 236, 772, 170),
			new LinearSegment(772, 170, 780, 0)

			, new LinearSegment(1148, 990, 1166, 932), new LinearSegment(1166,
					932, 1480, 582), new LinearSegment(1480, 582, 1473, 500),
			new LinearSegment(1473, 500, 1152, 188), new LinearSegment(1152,
					188, 1138, 0))),

	SCENE6(new Scene(ToroImage.BACKGROUND5.image

	, new LinearSegment(780, 1080, 780, 920), new LinearSegment(780, 920, 810,
			852), new LinearSegment(810, 852, 984, 654), new LinearSegment(984,
			654, 992, 606), new LinearSegment(992, 606, 974, 530),
			new LinearSegment(974, 530, 806, 284), new LinearSegment(806, 284,
					772, 198), new LinearSegment(772, 198, 780, 0)

			, new LinearSegment(1148, 990, 1146, 952), new LinearSegment(1146,
					952, 1184, 894), new LinearSegment(1184, 894, 1314, 782),
			new LinearSegment(1314, 782, 1362, 720), new LinearSegment(1362,
					720, 1364, 652), new LinearSegment(1364, 652, 1284, 470),
			new LinearSegment(1284, 470, 1164, 260), new LinearSegment(1164,
					260, 1142, 192), new LinearSegment(1142, 192, 1138, 0)

	));

	//
	// SCENE1(new Scene(ToroImage.BACKGROUND0.image,
	// new LinearSegment(324, 0, 248, 248),
	// new LinearSegment(248, 248, 272, 1149),
	// new LinearSegment(702, 0, 603, 268),
	// new LinearSegment(603, 268, 627, 1149)));

	public Scene scene;

	private EnumScene(final Scene scene) {
		this.scene = scene;
	}

}