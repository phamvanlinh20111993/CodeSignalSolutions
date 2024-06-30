package Hackerrank.Recursion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/simplified-chess-engine/problem?isFullScreen=true
 * 
 */

class Coord {

    public int x;
    public int y;

    public Coord(int x, int y) {
	this.x = x;
	this.y = y;
    }

    @Override
    public int hashCode() {
	return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Coord other = (Coord) obj;
	return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
	return "Coord [x=" + x + ", y=" + y + "]";
    }
}

abstract class Piece {

    public Coord cur;

    public final Coord board;

    public Piece(Coord coord, Coord board) {
	this.cur = coord;
	this.board = board;
    }

    protected boolean isInTheBoard(Coord coord, Coord board) {
	return coord.x >= 0 && coord.x < board.x && coord.y >= 0 && coord.y < board.y;
    }

    public void setCoord(Coord coord) {
	this.cur = coord;
    }

    abstract public List<Coord> getNextMoves(List<Coord> ally, List<Coord> enermy);

    abstract public Piece clone();

    @Override
    public String toString() {
	return "Piece [cur=" + cur + ", board=" + board + "]";
    }
}

class Knight extends Piece {

    public Knight(Coord coord, Coord board) {
	super(coord, board);
    }

    @Override
    public List<Coord> getNextMoves(List<Coord> ally, List<Coord> enermy) {

	List<Coord> moveCoords = new ArrayList<>();

	Coord[] knightDirections = new Coord[] { new Coord(-1, -2), new Coord(1, -2), new Coord(2, -1), new Coord(2, 1),
		new Coord(1, 2), new Coord(-1, 2), new Coord(-2, 1), new Coord(-2, -1) };
	for (Coord d : knightDirections) {
	    Coord newCoord = new Coord(cur.x + d.x, cur.y + d.y);
	    if (isInTheBoard(newCoord, board)) {
		if (ally.contains(newCoord)) {
		    continue;
		}
		moveCoords.add(newCoord);
	    }
	}

	return moveCoords;
    }

    public Piece clone() {
	return new Knight(new Coord(this.cur.x, this.cur.y), board);
    }
}

class Queen extends Piece {

    public Queen(Coord coord, Coord board) {
	super(coord, board);
    }

    @Override
    public List<Coord> getNextMoves(List<Coord> ally, List<Coord> enermy) {
	List<Coord> moveCoords = new ArrayList<>();
	// horizontal move
	for (int i = cur.x + 1; i < board.x; i++) {
	    Coord c = new Coord(i, cur.y);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	for (int i = cur.x - 1; i > -1; i--) {
	    Coord c = new Coord(i, cur.y);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	// vertical move
	for (int i = cur.y + 1; i < board.y; i++) {
	    Coord c = new Coord(cur.x, i);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	for (int i = cur.y - 1; i > -1; i--) {
	    Coord c = new Coord(cur.x, i);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	// first diagonal move
	int i = cur.x + 1, j = cur.y + 1;
	while (i < board.x && j < board.y) {
	    Coord c = new Coord(i++, j++);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	i = cur.x - 1;
	j = cur.y - 1;
	while (i > -1 && j > -1) {
	    Coord c = new Coord(i--, j--);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	// second diagonal move
	i = cur.x - 1;
	j = cur.y + 1;
	while (i > -1 && j < board.y) {
	    Coord c = new Coord(i--, j++);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	i = cur.x + 1;
	j = cur.y - 1;
	while (i < board.x && j > -1) {
	    Coord c = new Coord(i++, j--);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}

	return moveCoords;
    }

    public Piece clone() {
	return new Queen(new Coord(this.cur.x, this.cur.y), board);
    }

}

class Bishop extends Piece {

    public Bishop(Coord coord, Coord board) {
	super(coord, board);
    }

    @Override
    public List<Coord> getNextMoves(List<Coord> ally, List<Coord> enermy) {
	List<Coord> moveCoords = new ArrayList<>();
	// first diagonal move
	int i = cur.x + 1, j = cur.y + 1;
	while (i < board.x && j < board.y) {
	    Coord newCoord = new Coord(i++, j++);
	    if (enermy.contains(newCoord) || ally.contains(newCoord)) {
		if (enermy.contains(newCoord))
		    moveCoords.add(newCoord);
		break;
	    }
	    moveCoords.add(newCoord);
	}
	i = cur.x - 1;
	j = cur.y - 1;
	while (i > -1 && j > -1) {
	    Coord newCoord = new Coord(i--, j--);
	    if (enermy.contains(newCoord) || ally.contains(newCoord)) {
		if (enermy.contains(newCoord))
		    moveCoords.add(newCoord);
		break;
	    }
	    moveCoords.add(newCoord);
	}

	// second diagonal move
	i = cur.x - 1;
	j = cur.y + 1;
	while (i > -1 && j < board.y) {
	    Coord newCoord = new Coord(i--, j++);
	    if (enermy.contains(newCoord) || ally.contains(newCoord)) {
		if (enermy.contains(newCoord))
		    moveCoords.add(newCoord);
		break;
	    }
	    moveCoords.add(newCoord);
	}
	i = cur.x + 1;
	j = cur.y - 1;
	while (i < board.x && j > -1) {
	    Coord newCoord = new Coord(i++, j--);
	    if (enermy.contains(newCoord) || ally.contains(newCoord)) {
		if (enermy.contains(newCoord))
		    moveCoords.add(newCoord);
		break;
	    }
	    moveCoords.add(newCoord);
	}

	return moveCoords;
    }

    public Piece clone() {
	return new Bishop(new Coord(this.cur.x, this.cur.y), board);
    }
}

class Rook extends Piece {

    public Rook(Coord coord, Coord board) {
	super(coord, board);
    }

    @Override
    public List<Coord> getNextMoves(List<Coord> ally, List<Coord> enermy) {
	List<Coord> moveCoords = new ArrayList<>();
	// horizontal move
	for (int i = cur.x + 1; i < board.x; i++) {
	    Coord c = new Coord(i, cur.y);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	for (int i = cur.x - 1; i > -1; i--) {
	    Coord c = new Coord(i, cur.y);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	// vertical move
	for (int i = cur.y + 1; i < board.y; i++) {
	    Coord c = new Coord(cur.x, i);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	for (int i = cur.y - 1; i > -1; i--) {
	    Coord c = new Coord(cur.x, i);
	    if (enermy.contains(c) || ally.contains(c)) {
		if (enermy.contains(c))
		    moveCoords.add(c);
		break;
	    }
	    moveCoords.add(c);
	}
	return moveCoords;
    }

    public Piece clone() {
	return new Rook(new Coord(this.cur.x, this.cur.y), board);
    }

}

public class SimplifiedChessEngine {

    public static List<Piece> toChessCoords(char[][] coords) {
	List<Piece> pieces = new ArrayList<>();
	Coord board = new Coord(4, 4);
	for (int ind = 0; ind < coords.length; ind++) {
	    int y = 52 - (int) coords[ind][2];
	    int x = (int) coords[ind][1] - 65;
	    if (coords[ind][0] == 'N') {
		pieces.add(new Knight(new Coord(x, y), board));
	    }
	    if (coords[ind][0] == 'Q') {
		pieces.add(new Queen(new Coord(x, y), board));
	    }
	    if (coords[ind][0] == 'R') {
		pieces.add(new Rook(new Coord(x, y), board));
	    }
	    if (coords[ind][0] == 'B') {
		pieces.add(new Bishop(new Coord(x, y), board));
	    }
	}

	return pieces;
    }

    static String result = "";

    static Map<Integer, String> checkResult = new TreeMap<>();

    public static void backtracking(List<Piece> whitePieces, List<Piece> blackPieces, int moves, int turn) {
	if (moves < 1 || result == "NO") {
	    return;
	}

	List<Coord> ignoreBlackMoves = blackPieces.stream().map(p -> p.cur).collect(Collectors.toList());
	List<Coord> ignoreWhiteMoves = whitePieces.stream().map(p -> p.cur).collect(Collectors.toList());
	// white move
	if (turn == 0) {
	    for (int index = 0; index < whitePieces.size(); index++) {
		List<Coord> pieceMoves = whitePieces.get(index).getNextMoves(ignoreWhiteMoves, ignoreBlackMoves);
		for (Coord move : pieceMoves) {
		    Iterator<Piece> bIT = blackPieces.iterator();
		    while (bIT.hasNext()) {
			Piece bPiece = bIT.next();
			if (bPiece.cur.equals(move) && bPiece instanceof Queen) {
			    // System.out.println("White win at move " + moves);
			    result = "YES";
			    String dt = checkResult.getOrDefault(moves, "");
			    checkResult.put(moves, dt + "Y");
			    return;
			}
		    }
		}
	    }
	    for (int index = 0; index < whitePieces.size(); index++) {
		List<Piece> blackPiecesClone = blackPieces.stream().map(p -> p.clone()).collect(Collectors.toList());
		List<Piece> whitePiecesClone = whitePieces.stream().map(p -> p.clone()).collect(Collectors.toList());
		List<Coord> pieceMoves = whitePieces.get(index).getNextMoves(ignoreWhiteMoves, ignoreBlackMoves);
		for (Coord move : pieceMoves) {
		    Iterator<Piece> bIT = blackPiecesClone.iterator();
		    while (bIT.hasNext()) {
			Piece bPiece = bIT.next();
			if (bPiece.cur.equals(move)) {
			    bIT.remove();
			}
		    }
		    Piece piece = whitePiecesClone.get(index);
		    piece.setCoord(move);
		    whitePiecesClone.set(index, piece);
		    backtracking(whitePiecesClone, blackPiecesClone, moves - 1, 1);
		}
	    }
	    // black move
	} else {
	    for (int index = 0; index < blackPieces.size(); index++) {
		List<Coord> pieceMoves = blackPieces.get(index).getNextMoves(ignoreBlackMoves, ignoreWhiteMoves);
		for (Coord move : pieceMoves) {
		    Iterator<Piece> wIT = whitePieces.iterator();
		    while (wIT.hasNext()) {
			Piece wPiece = wIT.next();
			if (wPiece.cur.equals(move) && wPiece instanceof Queen) {
			    // System.out.println("Black win at move " + moves);
			    result = "NO";
			    String dt = checkResult.getOrDefault(moves, "");
			    checkResult.put(moves, dt + "N");
			    return;
			}
		    }
		}
	    }
	    for (int index = 0; index < blackPieces.size(); index++) {
		List<Piece> blackPiecesClone = blackPieces.stream().map(p -> p.clone()).collect(Collectors.toList());
		List<Piece> whitePiecesClone = whitePieces.stream().map(p -> p.clone()).collect(Collectors.toList());
		List<Coord> pieceMoves = blackPieces.get(index).getNextMoves(ignoreBlackMoves, ignoreWhiteMoves);
		for (Coord move : pieceMoves) {
		    Iterator<Piece> wIT = whitePiecesClone.iterator();
		    while (wIT.hasNext()) {
			Piece wPiece = wIT.next();
			if (wPiece.cur.equals(move)) {
			    wIT.remove();
			}
		    }
		    Piece piece = blackPiecesClone.get(index);
		    piece.setCoord(move);
		    blackPiecesClone.set(index, piece);
		    backtracking(whitePiecesClone, blackPiecesClone, moves - 1, 0);
		}
	    }
	}
    }

    /*
     * Complete the simplifiedChessEngine function below.
     */
    static String simplifiedChessEngine(char[][] whites, char[][] blacks, int moves) {
	List<Piece> whitePieces = toChessCoords(whites);
	List<Piece> blackPieces = toChessCoords(blacks);

	checkResult = new TreeMap<>(new Comparator<Integer>() {
	    @Override
	    public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	    }
	});
	result = "";
	backtracking(whitePieces, blackPieces, moves, 0);

	System.out.println("checkResult " + checkResult);
	for (String v : checkResult.values()) {
	    if (v.contains("N"))
		return "NO";
	    return "YES";
	}

	return result == "" ? "NO" : result;
    }

    public static void main(String[] args) {
	char[][] whites = { { 'R', 'B', '1' }, { 'Q', 'A', '4' } };
	char[][] blacks = { { 'R', 'C', '4' }, { 'N', 'D', '3' }, { 'Q', 'B', '2' } };
	int moves = 6;
	System.out.println("#################################### test 1, YES #########################");
	System.out.println("Result " + simplifiedChessEngine(whites, blacks, moves));

	char[][] whites1 = { { 'N', 'B', '1' }, { 'R', 'A', '1' }, { 'Q', 'A', '4' } };
	char[][] blacks1 = { { 'B', 'B', '3' }, { 'R', 'A', '3' }, { 'R', 'D', '1' }, { 'Q', 'C', '1' } };
	int moves1 = 6;
	System.out.println("#################################### test 2, NO #########################");
	System.out.println("Result " + simplifiedChessEngine(whites1, blacks1, moves1));

	char[][] whites2 = { { 'R', 'C', '2' }, { 'B', 'D', '4' }, { 'B', 'A', '4' }, { 'R', 'A', '3' },
		{ 'Q', 'D', '3' } };
	char[][] blacks2 = { { 'R', 'C', '3' }, { 'Q', 'C', '1' } };
	int moves2 = 6;
	System.out.println("#################################### test 3, YES #########################");
	System.out.println("Result " + simplifiedChessEngine(whites2, blacks2, moves2));

	char[][] whites3 = { { 'R', 'C', '3' }, { 'N', 'D', '3' }, { 'Q', 'C', '1' } };
	char[][] blacks3 = { { 'Q', 'D', '4' } };
	int moves3 = 5;
	System.out.println("#################################### test 4, YES #########################");
	System.out.println("Result " + simplifiedChessEngine(whites3, blacks3, moves3));

	char[][] whites4 = { { 'N', 'B', '1' }, { 'N', 'C', '4' }, { 'Q', 'D', '4' } };
	char[][] blacks4 = { { 'N', 'A', '4' }, { 'B', 'B', '3' }, { 'R', 'A', '3' }, { 'Q', 'C', '1' } };
	int moves4 = 5;
	System.out.println("#################################### test 5, NO #########################");
	System.out.println("Result " + simplifiedChessEngine(whites4, blacks4, moves4));

	char[][] whites5 = { { 'R', 'D', '2' }, { 'B', 'B', '4' }, { 'Q', 'B', '3' } };
	char[][] blacks5 = { { 'N', 'C', '3' }, { 'N', 'A', '2' }, { 'Q', 'C', '1' } };
	int moves5 = 6;
	System.out.println("#################################### test 6, NO #########################");
	System.out.println("Result " + simplifiedChessEngine(whites5, blacks5, moves5));
    }

}
