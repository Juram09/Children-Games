package games.tetris.logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import games.tetris.ui.Form;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import logic.GamesMenuController;

public class Board{
	public static int move = 25;
	public static int size = 25;
	public static int xMax = size * 12;
	public static int yMax = size * 24;
	public static int [ ] [ ] mesh = new int [ xMax / size ] [ yMax / size ];
	private static Pane group = new Pane ( );
	private static Form object;
	private static Scene scene = new Scene ( group, xMax + 150, yMax );
	public static int score = 0;
	private static int top = 0;
	private static boolean game = true;
	private static Form next = Controller.makeRect ( );
	private static int lines = 0;

	
	public void start () throws Exception {
                Stage stage=new Stage(); 
		for ( int [ ] a : mesh ) {
			Arrays.fill ( a, 0 );
		}
		Line line = new Line ( xMax, 0, xMax, yMax );
		Text scoretext = new Text ( "SCORE: " );
		scoretext.setStyle ( "-fx-font: 20 arial;" );
		scoretext.setY ( 50 );
		scoretext.setX ( xMax + 5 );
		Text level = new Text ( "LINES: " );
		level.setStyle ( "-fx-font: 20 arial;" );
		level.setY ( 100 );
		level.setX ( xMax + 5 );
		level.setFill ( Color.GREEN );
		group.getChildren ( ).addAll ( scoretext, line, level );

		Form a = next;
		group.getChildren ( ).addAll ( a.a, a.b, a.c, a.d );
		moveOnKeyPress ( a );
		object = a;
		next = Controller.makeRect ( );
		stage.setScene (scene);
		stage.setTitle ( "Children Games" );
                stage.setResizable(false);
		stage.show ( );
                stage.setOnCloseRequest((WindowEvent we) -> {
                try {
                    openGamesMenu();
                } catch (IOException ex) {
                    Logger.getLogger(GamesMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }); 

		Timer fall = new Timer ( );
		TimerTask task = new TimerTask ( ) {
			public void run ( ) {
				Platform.runLater ( new Runnable ( ) {
					public void run ( ) {
						if ( object.a.getY ( ) == 0 || object.b.getY ( ) == 0 || object.c.getY ( ) == 0
								|| object.d.getY ( ) == 0 ) {
							top++;
						}
						else {
							top = 0;
						}
						if ( top == 2 ) {
                                                    game = false;
                                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                                    alert.setHeaderText(null);
                                                    alert.setTitle("Has perdido");
                                                    alert.setContentText("Obtuviste un puntaje de: "+Integer.toString ( score ));
                                                    alert.showAndWait();
                                                    scene.getWindow().hide();
                                                    try {
                                                        openGamesMenu();
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                }
						if ( game ) {
							MoveDown ( object );
							scoretext.setText ("SCORE: " + Integer.toString ( score ) );
							level.setText ( "LINES: " + Integer.toString ( lines ) );
						}
					}
                                        
				} );
                                
			}
		};
		fall.schedule ( task, 0, 300 ); 
	}

	private void moveOnKeyPress ( Form form ) {
		scene.setOnKeyPressed ( new EventHandler < KeyEvent > ( ) {
			@Override
			public void handle ( KeyEvent event ) {
				switch ( event.getCode ( ) ) {
				case RIGHT:
					Controller.MoveRight ( form );
					break;
				case DOWN:
					MoveDown ( form );
					score++;
					break;
				case LEFT:
					Controller.MoveLeft ( form );
					break;
				case UP:
					MoveTurn ( form );
					break;
				}
			}
		} );
	}

	private void MoveTurn ( Form form ) {
		int f = form.form;
		Rectangle a = form.a;
		Rectangle b = form.b;
		Rectangle c = form.c;
		Rectangle d = form.d;
		switch ( form.getName ( ) ) {
		case "j":
			if ( f == 1 && cB ( a, 1, -1 ) && cB ( c, -1, -1 ) && cB ( d, -2, -2 ) ) {
				MoveRight ( form.a );
				MoveDown ( form.a );
				MoveDown ( form.c );
				MoveLeft ( form.c );
				MoveDown ( form.d );
				MoveDown ( form.d );
				MoveLeft ( form.d );
				MoveLeft ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 2 && cB ( a, -1, -1 ) && cB ( c, -1, 1 ) && cB ( d, -2, 2 ) ) {
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				MoveLeft ( form.d );
				MoveLeft ( form.d );
				MoveUp ( form.d );
				MoveUp ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 3 && cB ( a, -1, 1 ) && cB ( c, 1, 1 ) && cB ( d, 2, 2 ) ) {
				MoveLeft ( form.a );
				MoveUp ( form.a );
				MoveUp ( form.c );
				MoveRight ( form.c );
				MoveUp ( form.d );
				MoveUp ( form.d );
				MoveRight ( form.d );
				MoveRight ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 4 && cB ( a, 1, 1 ) && cB ( c, 1, -1 ) && cB ( d, 2, -2 ) ) {
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveRight ( form.c );
				MoveDown ( form.c );
				MoveRight ( form.d );
				MoveRight ( form.d );
				MoveDown ( form.d );
				MoveDown ( form.d );
				form.changeForm ( );
				break;
			}
			break;
		case "l":
			if ( f == 1 && cB ( a, 1, -1 ) && cB ( c, 1, 1 ) && cB ( b, 2, 2 ) ) {
				MoveRight ( form.a );
				MoveDown ( form.a );
				MoveUp ( form.c );
				MoveRight ( form.c );
				MoveUp ( form.b );
				MoveUp ( form.b );
				MoveRight ( form.b );
				MoveRight ( form.b );
				form.changeForm ( );
				break;
			}
			if ( f == 2 && cB ( a, -1, -1 ) && cB ( b, 2, -2 ) && cB ( c, 1, -1 ) ) {
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveRight ( form.b );
				MoveRight ( form.b );
				MoveDown ( form.b );
				MoveDown ( form.b );
				MoveRight ( form.c );
				MoveDown ( form.c );
				form.changeForm ( );
				break;
			}
			if ( f == 3 && cB ( a, -1, 1 ) && cB ( c, -1, -1 ) && cB ( b, -2, -2 ) ) {
				MoveLeft ( form.a );
				MoveUp ( form.a );
				MoveDown ( form.c );
				MoveLeft ( form.c );
				MoveDown ( form.b );
				MoveDown ( form.b );
				MoveLeft ( form.b );
				MoveLeft ( form.b );
				form.changeForm ( );
				break;
			}
			if ( f == 4 && cB ( a, 1, 1 ) && cB ( b, -2, 2 ) && cB ( c, -1, 1 ) ) {
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveLeft ( form.b );
				MoveLeft ( form.b );
				MoveUp ( form.b );
				MoveUp ( form.b );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				form.changeForm ( );
				break;
			}
			break;
		case "o":
			break;
		case "s":
			if ( f == 1 && cB ( a, -1, -1 ) && cB ( c, -1, 1 ) && cB ( d, 0, 2 ) ) {
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				MoveUp ( form.d );
				MoveUp ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 2 && cB ( a, 1, 1 ) && cB ( c, 1, -1 ) && cB ( d, 0, -2 ) ) {
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveRight ( form.c );
				MoveDown ( form.c );
				MoveDown ( form.d );
				MoveDown ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 3 && cB ( a, -1, -1 ) && cB ( c, -1, 1 ) && cB ( d, 0, 2 ) ) {
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				MoveUp ( form.d );
				MoveUp ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 4 && cB ( a, 1, 1 ) && cB ( c, 1, -1 ) && cB ( d, 0, -2 ) ) {
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveRight ( form.c );
				MoveDown ( form.c );
				MoveDown ( form.d );
				MoveDown ( form.d );
				form.changeForm ( );
				break;
			}
			break;
		case "t":
			if ( f == 1 && cB ( a, 1, 1 ) && cB ( d, -1, -1 ) && cB ( c, -1, 1 ) ) {
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveDown ( form.d );
				MoveLeft ( form.d );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				form.changeForm ( );
				break;
			}
			if ( f == 2 && cB ( a, 1, -1 ) && cB ( d, -1, 1 ) && cB ( c, 1, 1 ) ) {
				MoveRight ( form.a );
				MoveDown ( form.a );
				MoveLeft ( form.d );
				MoveUp ( form.d );
				MoveUp ( form.c );
				MoveRight ( form.c );
				form.changeForm ( );
				break;
			}
			if ( f == 3 && cB ( a, -1, -1 ) && cB ( d, 1, 1 ) && cB ( c, 1, -1 ) ) {
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveUp ( form.d );
				MoveRight ( form.d );
				MoveRight ( form.c );
				MoveDown ( form.c );
				form.changeForm ( );
				break;
			}
			if ( f == 4 && cB ( a, -1, 1 ) && cB ( d, 1, -1 ) && cB ( c, -1, -1 ) ) {
				MoveLeft ( form.a );
				MoveUp ( form.a );
				MoveRight ( form.d );
				MoveDown ( form.d );
				MoveDown ( form.c );
				MoveLeft ( form.c );
				form.changeForm ( );
				break;
			}
			break;
		case "z":
			if ( f == 1 && cB ( b, 1, 1 ) && cB ( c, -1, 1 ) && cB ( d, -2, 0 ) ) {
				MoveUp ( form.b );
				MoveRight ( form.b );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				MoveLeft ( form.d );
				MoveLeft ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 2 && cB ( b, -1, -1 ) && cB ( c, 1, -1 ) && cB ( d, 2, 0 ) ) {
				MoveDown ( form.b );
				MoveLeft ( form.b );
				MoveRight ( form.c );
				MoveDown ( form.c );
				MoveRight ( form.d );
				MoveRight ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 3 && cB ( b, 1, 1 ) && cB ( c, -1, 1 ) && cB ( d, -2, 0 ) ) {
				MoveUp ( form.b );
				MoveRight ( form.b );
				MoveLeft ( form.c );
				MoveUp ( form.c );
				MoveLeft ( form.d );
				MoveLeft ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 4 && cB ( b, -1, -1 ) && cB ( c, 1, -1 ) && cB ( d, 2, 0 ) ) {
				MoveDown ( form.b );
				MoveLeft ( form.b );
				MoveRight ( form.c );
				MoveDown ( form.c );
				MoveRight ( form.d );
				MoveRight ( form.d );
				form.changeForm ( );
				break;
			}
			break;
		case "i":
			if ( f == 1 && cB ( a, 2, 2 ) && cB ( b, 1, 1 ) && cB ( d, -1, -1 ) ) {
				MoveUp ( form.a );
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveRight ( form.a );
				MoveUp ( form.b );
				MoveRight ( form.b );
				MoveDown ( form.d );
				MoveLeft ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 2 && cB ( a, -2, -2 ) && cB ( b, -1, -1 ) && cB ( d, 1, 1 ) ) {
				MoveDown ( form.a );
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveLeft ( form.a );
				MoveDown ( form.b );
				MoveLeft ( form.b );
				MoveUp ( form.d );
				MoveRight ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 3 && cB ( a, 2, 2 ) && cB ( b, 1, 1 ) && cB ( d, -1, -1 ) ) {
				MoveUp ( form.a );
				MoveUp ( form.a );
				MoveRight ( form.a );
				MoveRight ( form.a );
				MoveUp ( form.b );
				MoveRight ( form.b );
				MoveDown ( form.d );
				MoveLeft ( form.d );
				form.changeForm ( );
				break;
			}
			if ( f == 4 && cB ( a, -2, -2 ) && cB ( b, -1, -1 ) && cB ( d, 1, 1 ) ) {
				MoveDown ( form.a );
				MoveDown ( form.a );
				MoveLeft ( form.a );
				MoveLeft ( form.a );
				MoveDown ( form.b );
				MoveLeft ( form.b );
				MoveUp ( form.d );
				MoveRight ( form.d );
				form.changeForm ( );
				break;
			}
			break;
		}
	}

	private void RemoveRows ( Pane pane ) {
		ArrayList < Node > rects = new ArrayList < Node > ( );
		ArrayList < Integer > layout = new ArrayList < Integer > ( );
		ArrayList < Node > newrects = new ArrayList < Node > ( );
		int full = 0;
		for ( int i = 0; i < mesh [ 0 ].length; i++ ) {
			for ( int j = 0; j < mesh.length; j++ ) {
				if ( mesh [ j ] [ i ] == 1 )
					full++;
			}
			if ( full == mesh.length ) {
				layout.add(i);
			}
			full = 0;
		}
		if ( layout.size ( ) > 0 )
			do {
				for ( Node node : pane.getChildren ( ) ) {
					if ( node instanceof Rectangle ) {
						rects.add ( node );
					}
				}
				score += 50;
				lines++;

				for ( Node node : rects ) {
					Rectangle a = ( Rectangle ) node;
					if ( a.getY ( ) == layout.get ( 0 ) * size ) {
						mesh [ ( int ) a.getX ( ) / size ] [ ( int ) a.getY ( ) / size ] = 0;
						pane.getChildren ( ).remove ( node );
					} 
					else {
						newrects.add ( node );
					}
				}

				for ( Node node : newrects ) {
					Rectangle a = ( Rectangle ) node;
					if ( a.getY ( ) < layout.get ( 0 ) * size ) {
						mesh [ ( int ) a.getX ( ) / size ] [ ( int ) a.getY ( ) / size ] = 0;
						a.setY ( a.getY ( ) + size );
					}
				}
				layout.remove ( 0 );
				rects.clear ( );
				newrects.clear ( );
				for ( Node node : pane.getChildren ( ) ) {
					if ( node instanceof Rectangle ) {
						rects.add ( node );
					}
				}
				for ( Node node : rects ) {
					Rectangle a = ( Rectangle ) node;
					try {
						mesh [ ( int ) a.getX ( ) / size ] [ ( int ) a.getY ( ) / size ] = 1;
					} 
					catch ( ArrayIndexOutOfBoundsException e ) {
					}
				}
				rects.clear ( );
			} 
			while ( layout.size ( ) > 0 );
	}

	private void MoveDown ( Rectangle rect ) {
		if ( rect.getY ( ) + move < yMax ) {
			rect.setY ( rect.getY ( ) + move );
		}
	}

	private void MoveRight ( Rectangle rect ) {
		if ( rect.getX ( ) + move <= xMax - size ) {
			rect.setX ( rect.getX ( ) + move );
		}
	}

	private void MoveLeft ( Rectangle rect ) {
		if ( rect.getX ( ) - move >= 0 ) {
			rect.setX ( rect.getX ( ) - move );
		}
	}

	private void MoveUp ( Rectangle rect ) {
		if ( rect.getY ( ) - move > 0 ) {
			rect.setY ( rect.getY ( ) - move );
		}
	}

	private void MoveDown ( Form form ) {
		if ( form.a.getY ( ) == yMax - size || form.b.getY ( ) == yMax - size || form.c.getY ( ) == yMax - size
				|| form.d.getY ( ) == yMax - size || moveA ( form ) || moveB ( form ) || moveC ( form ) || moveD ( form ) ) {
			mesh [ ( int ) form.a.getX ( ) / size ] [ ( int ) form.a.getY ( ) / size ] = 1;
			mesh [ ( int ) form.b.getX ( ) / size ] [ ( int ) form.b.getY ( ) / size ] = 1;
			mesh [ ( int ) form.c.getX ( ) / size ] [ ( int ) form.c.getY ( ) / size ] = 1;
			mesh [ ( int ) form.d.getX ( ) / size ] [ ( int ) form.d.getY ( ) / size ] = 1;
			RemoveRows ( group );

			Form a = next;
			next = Controller.makeRect ( );
			object = a;
			group.getChildren ( ).addAll (a.a, a.b, a.c, a.d );
			moveOnKeyPress(a);
		}

		if ( form.a.getY ( ) + move < yMax && form.b.getY ( ) + move < yMax && form.c.getY ( ) + move < yMax
				&& form.d.getY() + move < yMax ) {
			int movea = mesh [  ( int ) form.a.getX ( ) / size ] [ ( ( int ) form.a.getY ( ) / size ) + 1 ];
			int moveb = mesh [ ( int ) form.b.getX ( ) / size ] [ ( ( int ) form.b.getY ( ) / size) + 1 ];
			int movec = mesh [ ( int ) form.c.getX ( ) / size ] [ ( ( int ) form.c.getY ( ) / size ) + 1 ];
			int moved = mesh [ ( int ) form.d.getX ( ) / size ] [ ( ( int ) form.d.getY ( ) / size ) + 1 ];
			if ( movea == 0 && movea == moveb && moveb == movec && movec == moved) {
				form.a.setY ( form.a.getY ( ) + move );
				form.b.setY ( form.b.getY ( ) + move );
				form.c.setY ( form.c.getY ( ) + move );
				form.d.setY ( form.d.getY ( ) + move );
			}
		}
	}

	private boolean moveA ( Form form ) {
		return ( mesh [ ( int ) form.a.getX ( ) / size ] [ ( ( int ) form.a.getY ( ) / size ) + 1 ] == 1 );
	}

	private boolean moveB ( Form form ) {
		return ( mesh [ ( int ) form.b.getX ( ) / size ] [ ( (  int ) form.b.getY ( ) / size ) + 1] == 1 );
	}

	private boolean moveC ( Form form ) {
		return ( mesh [ ( int ) form.c.getX ( ) / size ] [ ( ( int ) form.c.getY ( ) / size ) + 1] == 1 );
	}

	private boolean moveD ( Form form ) {
		return ( mesh [ ( int ) form.d.getX ( ) / size ] [ ( ( int ) form.d.getY ( ) / size ) + 1 ] == 1 );
	}

	private boolean cB ( Rectangle rect, int x, int y ) {
		boolean xb = false;
		boolean yb = false;
		if ( x >= 0 ) {
			xb = rect.getX ( ) + x * move <= xMax - size;
		}
		if ( x < 0 ) {
			xb = rect.getX ( ) + x * move >= 0;
		}
		if ( y >= 0 ) {
			yb = rect.getY ( ) - y * move > 0;
		}
		if ( y < 0 ) {
			yb = rect.getY ( ) + y * move < yMax;
		}
		return xb && yb && mesh [ ( ( int ) rect.getX ( ) / size ) + x ] [ ( ( int ) rect.getY ( ) / size ) - y ] == 0;
	}
        private void openGamesMenu() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/gamesMenu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene=new Scene(root,790,590);
        stage.setScene(scene);  
        stage.setResizable(false);
        stage.show(); 
        stage.setOnCloseRequest((WindowEvent we) -> {
                   System.exit(0);
        }); 
    }       
}