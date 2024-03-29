/* The game always start from a white move, each colour can move only once! */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener{
  JLayeredPane layeredPane;
  JPanel chessBoard;
  JLabel chessPiece;

  int xAdjustment;
  int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;

	JPanel panels;
	JLabel pieces;
	String winnerMessage;
  Boolean win;
  Boolean whiteMove;




  public ChessProject(){
    Dimension boardSize = new Dimension(600, 600);

      //  Use a Layered Pane for this application
     layeredPane = new JLayeredPane();
     getContentPane().add(layeredPane);
     layeredPane.setPreferredSize(boardSize);
     layeredPane.addMouseListener(this);
     layeredPane.addMouseMotionListener(this);

      //Add a chess board to the Layered Pane
      chessBoard = new JPanel();
      layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
      chessBoard.setLayout( new GridLayout(8, 8) );
      chessBoard.setPreferredSize( boardSize );
      chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

       for (int i = 0; i < 64; i++) {
        JPanel square = new JPanel( new BorderLayout() );
        chessBoard.add( square );

        int row = (i / 8) % 2;
        if (row == 0)
         square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
        else
         square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
      pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	    panels.add(pieces);
		}
		  pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		  panels = (JPanel)chessBoard.getComponent(0);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		  panels = (JPanel)chessBoard.getComponent(1);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		  panels = (JPanel)chessBoard.getComponent(6);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		  panels = (JPanel)chessBoard.getComponent(2);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteBishop.png") );
		  panels = (JPanel)chessBoard.getComponent(5);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		  panels = (JPanel)chessBoard.getComponent(3);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		  panels = (JPanel)chessBoard.getComponent(4);
	     panels.add(pieces);
		  pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		  panels = (JPanel)chessBoard.getComponent(7);
	     panels.add(pieces);
		for(int i=48;i < 56; i++){
      pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	     panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishop.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
      whiteMove = true;
    }

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		 else{
			return true;
		 }
	}

	/*
		This is a method to check white opponent
	*/
	private Boolean checkWhiteOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			opponent = true;
		}
		else{
			opponent = false;
		}
		return opponent;
	}
  /*
    This is a method to check black opponent
  */
  private Boolean checkBlackOpponent(int newX, int newY){
		Boolean opponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("White")))){
			opponent = true;
		}
		else{
			opponent = false;
		}
		return opponent;
	}

	private Boolean pieceMove(int x, int y) {
        if ((startX == x) && (startY == y)) {
            return false;
        } else {
            return true;
        }
    }

	private String getPieceName(int x, int y) {
        Component c1 = chessBoard.findComponentAt(x, y);
        if (c1 instanceof JPanel) {
            return "empty";
        } else if (c1 instanceof JLabel) {
            JLabel awaitingPiece = (JLabel) c1;
            String tmp1 = awaitingPiece.getIcon().toString();
            return tmp1;
        } else {
            return "empty";
        }
    }

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
     chessPiece = null;
     String name = getPieceName(e.getX(), e.getY());
     Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
     if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
     if (chessPiece == null) return;
      chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

 	/*
		This method is used when the Mouse is released.
	*/
    public void mouseReleased(MouseEvent e) {
      if(chessPiece == null) return;

      chessPiece.setVisible(false);
		Boolean success =false;
		Boolean promotion = false;
		Boolean progression = false;
      Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;
		Boolean possible = false;

		/*
/* The game always start from a white move, each colour can move only once! */
		*/
    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((e.getX()/75)-startX);
    int yMovement = Math.abs((e.getY()/75)-startY);
    System.out.println("----------------------------------------");
    System.out.println("The piece that is been moved is : "+pieceName);
    System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
    System.out.println("The xMovement is: "+xMovement);
    System.out.println("The yMovement is: "+yMovement);
    System.out.println("The landing coordinates are: "+"( "+landingX+","+landingY+")");
    System.out.println("----------------------------------------");

    //Boolean possible = false;

        if (whiteMove) {
            if (pieceName.contains("White")) {
                possible = true;
            }
        } else if (pieceName.contains("Black")) {
            possible = true;
        }
        if (possible) {

          /* Defines how the bishop should move */

            if (pieceName.contains("Bishop")) {
                Boolean inTheWay = false;
                if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                    validMove = false;
                } else if (!pieceMove(landingX, landingY)) {
                    validMove = false;
                } else {
                    validMove = true;

                    if (xMovement == yMovement) {
                        if ((startX - landingX < 0) && (startY - landingY < 0)) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                    inTheWay = true;
                                }
                            }
                        } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                    inTheWay = true;
                                }
                            }
                        } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                    inTheWay = true;
                                }
                            }
                        } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                    inTheWay = true;
                                }
                            }
                        }
                        if (inTheWay) {
                            validMove = false;
                        } else if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOpponent(e.getX(), e.getY())) {
                                    validMove = true;
                                    if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                        winnerMessage = "White has won the game!";
                                    }
                                } else {
                                    validMove = false;
                                }
                            } else if (checkBlackOpponent(e.getX(), e.getY())) {
                                validMove = true;
                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "Black has won the game!";
                                }
                            } else {
                                validMove = false;
                            }
                        } else {
                            validMove = true;
                        }
                    } else {
                        validMove = false;
                    }
                }
            }
            /* Defines how the Rook moves */

            else if (pieceName.contains("Rook")) {
                Boolean intheway = false;
                if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                    validMove = false;
                } else if (!pieceMove(landingX, landingY)) {
                    validMove = false;
                } else if (((xMovement != 0) && (yMovement == 0)) || ((xMovement == 0) && (yMovement != 0))) {
                    if (xMovement != 0) {
                        if (startX - landingX > 0) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        }
                    } else
                    {
                        if (startY - landingY > 0) {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        }
                    }
                    if (intheway) {
                        validMove = false;
                    } else if (piecePresent(e.getX(), (e.getY()))) {
                        if (pieceName.contains("White")) {
                            if (checkWhiteOpponent(e.getX(), e.getY())) {
                                validMove = true;

                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "White has won the game!";
                                }
                            } else {
                                validMove = false;
                            }
                        } else if (checkBlackOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                winnerMessage = "Black has won the game!";
                            }
                        } else {
                            validMove = false;
                        }
                    } else {
                        validMove = true;
                    }
                } else {
                    validMove = false;
                }
            }




            /* Defines how the knight moves */

            else if (pieceName.contains("Knight")) {
              if (((xMovement == 1) && (yMovement == 2)) || ((xMovement == 2) && (yMovement == 1))) {
                    if (!piecePresent(e.getX(), e.getY())) {
                        validMove = true;
                    } else if (pieceName.contains("White")) {
                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                winnerMessage = "White has won the game!";
                            }
                        }
                    } else if (checkBlackOpponent(e.getX(), e.getY())) {
                        validMove = true;
                        if (getPieceName(e.getX(), e.getY()).contains("King")) {
                            winnerMessage = "Black has won the game!";
                        }
                    }
                }
            }




            /* Defines how the king moves */

            else if (pieceName.contains("King")) {
                if ((xMovement == 0) && (yMovement == 0)) {
                    validMove = false;
                } else if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                    validMove = false;
                } else if ((xMovement > 1) || (yMovement > 1)) {
                    validMove = false;
                } else if ((getPieceName((e.getX() + 75), e.getY()).contains("King")) || (getPieceName((e.getX() - 75), e.getY()).contains("King")) || (getPieceName((e.getX()), (e.getY() + 75)).contains("King")) || (getPieceName((e.getX()), (e.getY() - 75)).contains("King")) || (getPieceName((e.getX() + 75), (e.getY() + 75)).contains("King")) || (getPieceName((e.getX() - 75), (e.getY() + 75)).contains("King")) || (getPieceName((e.getX() + 75), (e.getY() - 75)).contains("King")) || (getPieceName((e.getX() - 75), (e.getY() - 75)).contains("King"))) {
                    validMove = false;
                } else if (piecePresent(e.getX(), e.getY())) {
                    if (pieceName.contains("White")) {
                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                            validMove = true;
                        }
                    } else if (checkBlackOpponent(e.getX(), e.getY())) {
                        validMove = true;
                    }
                } else {
                    validMove = true;
                }
            }




            /* define how the Queen should move*/

            else if (pieceName.contains("Queen")) {
                boolean inTheWay = false;
                if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                    validMove = false;
                } else if (!pieceMove(landingX, landingY)) {
                    validMove = false;
                } else if (xMovement == yMovement) {
                    if ((startX - landingX < 0) && (startY - landingY < 0)) {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }

                    if (inTheWay) {
                        validMove = false;
                    } else if (piecePresent(e.getX(), (e.getY()))) {
                        if (pieceName.contains("White")) {
                            if (checkWhiteOpponent(e.getX(), e.getY())) {
                                validMove = true;
                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "White has won the game!";
                                }
                            }
                        } else {
                            if (checkBlackOpponent(e.getX(), e.getY())) {
                                validMove = true;
                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "Black has won the game!";
                                }
                            }
                        }
                    } else {
                        validMove = true;
                    }
                }
                else if (((xMovement != 0) && (yMovement == 0)) || ((xMovement == 0) && (yMovement != 0))) {
                    if (xMovement != 0) {
                        if (startX - landingX > 0) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }
                    else {
                        if (startY - landingY > 0) {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }

                    if (inTheWay) {
                        validMove = false;
                    } else if (piecePresent(e.getX(), (e.getY()))) {
                        if (pieceName.contains("White")) {
                            if (checkWhiteOpponent(e.getX(), e.getY())) {
                                validMove = true;
                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "White has won the game!";
                                }
                            }
                        } else if (checkBlackOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                winnerMessage = "Black Player has won the game!";
                            }
                        }
                    } else {
                        validMove = true;
                    }
                }
            }




            /* Defines how the black pawn should move */

            else if (pieceName.equals("BlackPawn")) {
                if (startY == 6) {

                    if (((yMovement == 1) || (yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
                        if (yMovement == 2) {
                            if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), (e.getY() + 75)))) {
                                validMove = true;
                            }
                        } else if (!piecePresent(e.getX(), e.getY())) {
                            validMove = true;
                        }
                    } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
                        if (piecePresent(e.getX(), e.getY())) {
                            if (checkBlackOpponent(e.getX(), e.getY())) {
                                validMove = true;
                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "Black Player has won the game!";
                                }
                            }
                        }
                    }
                } else
                {
                    if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
                        if (!piecePresent(e.getX(), e.getY())) {
                            validMove = true;

                            if (landingY == 0) {
                                progression = true;
                            }
                        }
                    } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {
                        if (piecePresent(e.getX(), e.getY())) {
                            if (checkBlackOpponent(e.getX(), e.getY())) {
                                validMove = true;
                                if (landingY == 0) {
                                    progression = true;
                                }
                                if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                    winnerMessage = "Black Player has won the game!";
                                }
                            }
                        }
                    }
                }
            }

            /* Defines how the white pawn should move */

            else if (pieceName.equals("WhitePawn")) {
                if (startY == 1) {
                    if (((xMovement == 0)) && ((yMovement == 1) || ((yMovement) == 2))) {
                        if (yMovement == 2) {
                            if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() - 75)))) {
                                validMove = true;
                            }
                        } else if ((!piecePresent(e.getX(), (e.getY())))) {
                            validMove = true;
                        }
                    } else if ((piecePresent(e.getX(), e.getY())) && (xMovement == yMovement) && (xMovement == 1) && (startY < landingY)) {
                       if (checkWhiteOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (startY == 6) {
                                success = true;
                            }
                            if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                winnerMessage = "White has won the game!";
                            }
                        }
                    }
                }
                else if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
                    if ((piecePresent(e.getX(), e.getY())) && (xMovement == yMovement) && (xMovement == 1)) {
                        if (checkWhiteOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (startY == 6) {
                                success = true;
                            }
                            if (getPieceName(e.getX(), e.getY()).contains("King")) {
                                winnerMessage = "White has won the game!";
                            }
                        }
                    }
                    else if (!piecePresent(e.getX(), (e.getY()))) {
                        if (((xMovement == 0)) && ((e.getY() / 75) - startY) == 1) {
                            if (startY == 6) {
                                success = true;
                            }
                            validMove = true;
                        }
                    }
                }
            }
        }
        if (!validMove) {
                int location = 0;
                if (startY == 0) {
                    location = startX;
                } else {
                    location = (startY * 8) + startX;
                }
                String pieceLocation = pieceName + ".png";
                pieces = new JLabel(new ImageIcon(pieceLocation));
                panels = (JPanel) chessBoard.getComponent(location);
                panels.add(pieces);
            } else {
              // Defines turns so the same colour does not move twice in a row
                if (whiteMove) {
                    whiteMove = false;
                } else {
                    whiteMove = true;
                }

                if (progression) {
                    int location = 0 + (e.getX() / 75);
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    } else {
                        Container parent = (Container) c;
                        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    }
                    if (winnerMessage != null) {
                        JOptionPane.showMessageDialog(null, winnerMessage);
                        System.exit(0);
                    }
                } else if (success) {
                    int location = 56 + (e.getX() / 75);
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    } else {
                        Container parent = (Container) c;
                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                        parent = (JPanel) chessBoard.getComponent(location);
                        parent.add(pieces);
                    }
                } else {
                    if (c instanceof JLabel) {
                        Container parent = c.getParent();
                        parent.remove(0);
                        parent.add(chessPiece);
                    } else {
                        Container parent = (Container) c;
                        parent.add(chessPiece);
                    }
                    chessPiece.setVisible(true);
                    if (winnerMessage != null) {
                        JOptionPane.showMessageDialog(null, winnerMessage);
                        System.exit(0);
                    }
                }

            }
        }

        ///////////////////////////////////////////////////////////////////////////////////
        /* We are not using these methods for this project*/
        //////////////////////////////////////////////////////////////////////////////////
        public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method for running the game
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }

}
