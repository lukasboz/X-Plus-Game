
public class Configurations {

	private char[][] board;
	private int boardSize;
	private int lengthToWin;
	private int max_levels;

	public Configurations(int board_size, int lengthToWin, int max_levels) {

		this.board = new char[board_size][board_size];
		this.boardSize = board_size;
		this.lengthToWin = lengthToWin;
		this.max_levels = max_levels;

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				board[i][j] = ' ';
			}
		}

	}

	public HashDictionary createDictionary() {
		return new HashDictionary(9973);
	}

	public int repeatedConfiguration(HashDictionary hashTable) {
		String boardCharString = "";

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				boardCharString += board[i][j];
			}
		}

		int score = hashTable.get(boardCharString);

		if (score != -1) {
			return score;
		} else {
			return -1;
		}

	}

	public void addConfiguration(HashDictionary hashDictionary, int score) {
		String boardCharString = "";

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				boardCharString += board[i][j];
			}
		}

		// System.out.println(boardCharString);

		try {
			hashDictionary.put(new Data(boardCharString, score));
		} catch (DictionaryException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void savePlay(int row, int col, char symbol) {
		board[row][col] = symbol;
	}

	public boolean squareIsEmpty(int row, int col) {
		if (board[row][col] == ' ')
			return true;
		else
			return false;
	}

	public boolean wins(char symbol) {

		char[][] centrepointX = new char[boardSize - 1][boardSize - 1];

		String boardCharString = "";

		int countPlus = 0;

		if (isCentrePiecePlus(symbol)) {
			System.out.println("AYO WE GOT SOMETHING HERE");

		} else if (isCentrePieceX(symbol)) {
			System.out.println("YOOOOOOOOOOOOOOOOOOOOOOO");
		}

		//System.out.println(countPlus);

		return false;

	}

	// System.out.println(boardCharString);

	public boolean isDraw() {

		int empty = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == ' ') {
					empty++;
				}
			}
		}

		int nonEmpty = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] != ' ') {
					nonEmpty++;
				}
			}
		}

		System.out.println("non-empty spaces: " + nonEmpty);
		System.out.println("empty spaces: " + empty);

		if (nonEmpty == boardSize * boardSize && empty == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int evalBoard() {
		if (wins('O')) {
			return 3;
		} else if (wins('X')) {
			return 0;
		} else if (isDraw()) {
			return 2;
		} else {
			return 1;
		}
	}

	private boolean isCentrePiecePlus(char symbol) {

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == symbol) {
					try {
						if (board[i - 1][j] == symbol && board[i + 1][j] == symbol && board[i][j - 1] == symbol
								&& board[i][j + 1] == symbol) {
							return true;
						}
					} catch (IndexOutOfBoundsException e) {
						return false;
					}

				}
			}
		}

		return false;
	}
	
	private boolean isCentrePieceX(char symbol) {

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (board[i][j] == symbol) {
					try {
						if (board[i - 1][j - 1] == symbol && board[i + 1][j + 1] == symbol && board[i + 1][j - 1] == symbol
								&& board[i - 1][j + 1] == symbol) {
							return true;
						}
					} catch (IndexOutOfBoundsException e) {
						return false;
					}

				}
			}
		}

		return false;
	}

}
