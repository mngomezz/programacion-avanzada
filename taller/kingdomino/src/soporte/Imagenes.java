package soporte;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import dominio.Tipo;

public class Imagenes {
	static public BufferedImage gameBackground;
	static public BufferedImage mainBackground;
	public static BufferedImage interfazTurno;
	public static BufferedImage interfazMensaje;
	public static BufferedImage fondoUsuarios;
	public static BufferedImage salaBackground;

	public static void init() {
		try {
			interfazTurno = ImageIO.read(new File("imagenes/interfazTurno.png"));
			interfazMensaje = ImageIO.read(new File("imagenes/interfazMensaje.png"));
			gameBackground = ImageIO.read(new File("imagenes/gameBackground.jpeg"));
			mainBackground = ImageIO.read(new File("imagenes/mainBackground.jpg"));
			fondoUsuarios = ImageIO.read(new File("imagenes/fondo3.png"));
			salaBackground = ImageIO.read(new File("imagenes/salaBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getImagen(Tipo tipo, int coronas) throws IOException {
		return ImageIO.read(new File("imagenes/terrenos/"+ tipo + "/" + coronas + ".png"));

	}

	public static BufferedImage getImagen(String color) throws IOException {
		return ImageIO.read(new File("imagenes/terrenos/castillo/" + color.toLowerCase() + ".png"));
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
			throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}
}
