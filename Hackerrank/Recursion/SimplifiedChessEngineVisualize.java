package Hackerrank.Recursion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SimplifiedChessEngineVisualize {

    public static List<Piece> toChessCoords(char[][] coords) {
	List<Piece> pieces = new ArrayList<>();
	Coord board = new Coord(4, 4);
	for (int ind = 0; ind < coords.length; ind++) {
	    int x = (int) coords[ind][1] - 65;
	    int y = 52 - (int) coords[ind][2];
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

    static void visualBoard(List<Piece> whitePieces, List<Piece> blackPieces) {
	char[][] board = new char[][] { { '-', '-', '-', '-' }, { '-', '-', '-', '-' }, { '-', '-', '-', '-' },
		{ '-', '-', '-', '-' } };

	for (Piece piece : whitePieces) {
	    board[piece.cur.y][piece.cur.x] = piece instanceof Queen ? 'Q'
		    : piece instanceof Bishop ? 'B' : piece instanceof Rook ? 'R' : 'N';
	}

	for (Piece piece : blackPieces) {
	    board[piece.cur.y][piece.cur.x] = piece instanceof Queen ? 'q'
		    : piece instanceof Bishop ? 'b' : piece instanceof Rook ? 'r' : 'n';
	}

	for (int ind = 0; ind < board.length; ind++) {
	    System.out.println("----------");
	    for (int j = 0; j < board[0].length; j++) {
		System.out.print(board[ind][j] + "|");
	    }
	    System.out.println();
	}
	System.out.println("----------");
    }

    static String result = "";
    static Map<Integer, String> checkResult = new LinkedHashMap<>();
    static List<String> analysisRes= new ArrayList<>();

    public static void backtracking(List<Piece> whitePieces, List<Piece> blackPieces, int moves, int turn, String s) {
	if (moves < 1) {
	    analysisRes.add(s + "=>Draw");
	    return;
	}
	List<Coord> ignoreBlackMoves = blackPieces.stream().map(p -> p.cur).collect(Collectors.toList());
	List<Coord> ignoreWhiteMoves = whitePieces.stream().map(p -> p.cur).collect(Collectors.toList());
	// System.out.println("\nMove at " + moves);
	// visualBoard(whitePieces, blackPieces);

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
			    analysisRes.add(s + "=>White-Win");
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
		    backtracking(whitePiecesClone, blackPiecesClone, moves - 1, 1, s + "B");
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
			    analysisRes.add(s + "=>Black-Win");
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
		    backtracking(whitePiecesClone, blackPiecesClone, moves - 1, 0, s + "W");
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
	// System.out.println("whitePieces:" + whitePieces);
	// System.out.println("blackPieces:" + blackPieces);

	checkResult = new LinkedHashMap<>(); /*
					      * new TreeMap<>(new Comparator<Integer>() {
					      * 
					      * @Override public int compare(Integer o1, Integer o2) { return o2 - o1; }
					      * });
					      */
	result = "";
	analysisRes = new ArrayList<>();
	backtracking(whitePieces, blackPieces, moves, 0, "W");
	
	System.out.println("analysisRes" + analysisRes);
	
//	System.out.println("checkResult " + checkResult);
	
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
