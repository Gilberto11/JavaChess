else if(pieceName.equals("WhitePawn")){
			if(startY == 1){
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2)){
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
					   else{
						  if((!piecePresent(e.getX(), (e.getY())))){
							validMove = true;
						  }
						   else{
							validMove = false;
						   }
					  }
				}
				else{
					validMove = false;
				}
			}
			else{
				int newY = e.getY()/75;
				int newX = e.getX()/75;
				if((startX-1 >=0)||(startX +1 <=7)){
				 if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						if(checkWhiteOpponent(e.getX(), e.getY())){
							validMove = true;
							if(startY == 6){
								success = true;
							}
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}
		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
			if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	         Container parent = c.getParent();
	         parent.remove(0);
					 pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					 parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	        pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			   	parent.add(pieces);
				}
			}
			else{
				if (c instanceof JLabel){
	       Container parent = c.getParent();
	       parent.remove(0);
	       parent.add( chessPiece );
	      }
	        else {
	        Container parent = (Container)c;
	        parent.add( chessPiece );
	        }
	    		chessPiece.setVisible(true);
			}
		}
    }
