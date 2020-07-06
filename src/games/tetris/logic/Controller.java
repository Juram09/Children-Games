package games.tetris.logic;
import games.tetris.logic.Board;
import javafx.scene.shape.Rectangle;
import games.tetris.ui.Form;

public class Controller {
	public static final int move = Board.move;
	public static final int size = Board.size;
	public static int xMax = Board.xMax;
	public static int yMax = Board.yMax;
	public static int [ ] [ ] mesh = Board.mesh;

	public static void MoveRight ( Form form ) {
		if ( form.a.getX ( ) + move <= xMax - size && form.b.getX ( ) + move <= xMax - size
				&& form.c.getX ( ) + move <= xMax - size && form.d.getX ( ) + move <= xMax - size ) {
			int movea = mesh [ ( ( int ) form.a.getX ( ) / size ) + 1 ] [ ( ( int ) form.a.getY ( ) / size ) ];
			int moveb = mesh [ ( ( int ) form.b.getX ( ) / size ) + 1 ] [ ( ( int ) form.b.getY ( ) / size ) ];
			int movec = mesh [ ( ( int ) form.c.getX ( )  / size ) + 1 ] [ ( ( int ) form.c.getY ( ) / size ) ];
			int moved = mesh [ ( ( int ) form.d.getX ( ) / size ) + 1 ] [ ( ( int ) form.d.getY ( ) / size ) ];
			if ( movea == 0 && movea == moveb && moveb == movec && movec == moved ) {
				form.a.setX ( form.a.getX ( ) + move );
				form.b.setX ( form.b.getX ( ) + move );
				form.c.setX ( form.c.getX ( ) + move );
				form.d.setX ( form.d.getX ( ) + move);
			}
		}
	}

	public static void MoveLeft ( Form form ) {
		if ( form.a.getX ( ) - move >= 0 && form.b.getX ( ) - move >= 0 && form.c.getX ( ) - move >= 0
				&& form.d.getX() - move >= 0 ) {
			int movea = mesh [ ( ( int ) form.a.getX ( ) / size ) - 1 ] [ ( ( int ) form.a.getY ( ) / size ) ];
			int moveb = mesh [ ( ( int ) form.b.getX ( ) / size ) - 1 ] [ ( ( int ) form.b.getY ( )  / size ) ];
			int movec = mesh [ ( ( int ) form.c.getX ( ) / size ) - 1 ] [ ( ( int ) form.c.getY ( ) / size ) ];
			int moved = mesh [ ( ( int ) form.d.getX ( ) / size ) - 1 ] [ ( ( int ) form.d.getY ( ) / size ) ];
			if ( movea == 0 && movea == moveb && moveb == movec && movec == moved ) {
				form.a.setX ( form.a.getX ( ) - move );
				form.b.setX ( form.b.getX ( ) - move );
				form.c.setX ( form.c.getX ( ) - move );
				form.d.setX ( form.d.getX ( ) - move );
			}
		}
	}

	public static Form makeRect ( ) {
		int block = ( int ) ( Math.random ( ) * 100 );
		String name;
		Rectangle a = new Rectangle ( size - 1, size - 1 ), b = new Rectangle ( size - 1, size - 1 ), 
				c = new Rectangle ( size - 1, size - 1 ), d = new Rectangle ( size - 1, size - 1 );
		if ( block < 15 ) { 
			a.setX ( xMax / 2 - size );
			b.setX ( xMax / 2 - size );
			b.setY ( size );
			c.setX ( xMax / 2 );
			c.setY ( size );
			d.setX( xMax / 2 + size );
			d.setY ( size );
			name = "j";
		} 
		else if ( block < 30 ) { 
			a.setX ( xMax / 2 + size );
			b.setX ( xMax / 2 - size );
			b.setY ( size );
			c.setX ( xMax / 2 );
			c.setY ( size );
			d.setX ( xMax / 2 + size );
			d.setY ( size );
			name = "l";
		} 
		else if ( block < 45 ) { 
			a.setX ( xMax / 2 - size );
			b.setX ( xMax / 2 );
			c.setX ( xMax / 2 - size );
			c.setY ( size );
			d.setX ( xMax / 2 );
			d.setY ( size );
			name = "o";
		} 
		else if ( block < 60 ) { 
   			a.setX ( xMax / 2 + size );
			b.setX ( xMax / 2 );
			c.setX ( xMax / 2 );
			c.setY ( size );
			d.setX ( xMax / 2 - size );
			d.setY ( size );
			name = "s";
		} 
		else if ( block < 75 ) { 
			a.setX ( xMax / 2 - size );
			b.setX ( xMax / 2);
			c.setX ( xMax / 2 );
			c.setY ( size );
			d.setX ( xMax / 2 + size );
			name = "t";
		} 
		else if ( block < 90 ) { 
			a.setX ( xMax / 2 + size );
			b.setX ( xMax / 2 );
			c.setX ( xMax / 2 + size );
			c.setY ( size );
			d.setX ( xMax / 2 + size + size );
			d.setY ( size );
			name = "z";
		} 
		else { 
			a.setX ( xMax / 2 - size - size );
			b.setX ( xMax / 2 - size );
			c.setX ( xMax / 2 );
			d.setX ( xMax / 2 + size );
			name = "i";
		}
		return new Form ( a, b, c, d, name );
	}
}