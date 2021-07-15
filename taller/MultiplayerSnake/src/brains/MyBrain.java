package brains;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;
import edu.unlam.snake.engine.Point;

public class MyBrain extends Brain {

	private static final int DISTANCIA_MAXIMA = 100;

	public MyBrain() {
		super("Martin");
		// throw new RuntimeException("Agregar ids loom");
	}

	public Direction getDirection(Point head, Direction previous) {
		List<Point> fruits = info.getFruits();
		List<Point> snake = info.getSnake();
		List<List<Point>> enemies = info.getEnemies();
		List<Point> obstacles = info.getObstacles();

		// Los enemigos tambien son obstaculos
		for (List<Point> enemy : enemies) {
			obstacles.addAll(enemy);
		}

		// Mi cola tambien es un obstaculo
		if (snake.size() > 1) { // Si la serpiente mide mas de un pixel
			obstacles.addAll(snake.subList(1, snake.size()));
		}

		Point frutaBuscada = getFrutaMasCercana(head, fruits);

		List<Direction> direccionesPosibles = new ArrayList<Direction>();
		direccionesPosibles.add(previous);
		direccionesPosibles.add(previous.turnLeft());
		direccionesPosibles.add(previous.turnRight());
		direccionesPosibles.sort(new Comparator<Direction>() {
			@Override
			public int compare(Direction d1, Direction d2) {
				Point head1 = head.clone();
				Point head2 = head.clone();
				head1.moveTo(d1);
				head2.moveTo(d2);
				return (int) (distanceBetween(head1, frutaBuscada) - distanceBetween(head2, frutaBuscada));
			}
		});

		for (Direction nuevaDireccion : direccionesPosibles) {
			Point newHead = head.clone();
			newHead.moveTo(nuevaDireccion);
			if (obstacles.contains(newHead) == false) {
				return nuevaDireccion;
			}
		}
//		for (Point obstacle : obstacles) {
//			if (headPrevious.equals(obstacle)) {
//
//				if (headRight.equals(obstacle))
//					return previous.turnLeft();
//				else
//					return previous.turnRight();
//			}
//		}

		return previous;
	}

//	private boolean chocaConObstaculo(Point nuevaCabeza, List<Point> obstacles) {
//		for (Point obstacle : obstacles) {
//			if (nuevaCabeza.equals(obstacle)) {
//				return true;
//			}
//		}
//		return false;
//	}

	private double distanceBetween(Point p1, Point p2) {
		double distanceX = Math.abs(Math.pow(p1.getX() - p2.getX(), 2));
		double distanceY = Math.abs(Math.pow(p1.getY() - p2.getY(), 2));
		return Math.sqrt(distanceX + distanceY);
	}

	private Point getFrutaMasCercana(Point head, List<Point> frutas) {
		Point frutaMasCercana = null;
		double distanciaMasCercana = DISTANCIA_MAXIMA;
		for (Point fruta : frutas) {
			double distancia = distanceBetween(head, fruta);
			if (distancia < distanciaMasCercana) {
				frutaMasCercana = fruta;
				distanciaMasCercana = distancia;
			}
		}
		return frutaMasCercana;
	}

}
