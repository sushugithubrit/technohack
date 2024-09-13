import java.util.Scanner;

public class TicTacToe {

    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static char[][] board = new char[3][3];
    private static char currentPlayer = PLAYER_X;

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    // Initialize the Tic-Tac-Toe board
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    // Print the Tic-Tac-Toe board
    private static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
            System.out.println("---------");
        }
    }

    // Play the game
    private static void playGame() {
        boolean gameWon = false;
        boolean gameDraw = false;

        while (!gameWon && !gameDraw) {
            printBoard();
            playerMove(currentPlayer);

            gameWon = checkWin(currentPlayer);
            gameDraw = checkDraw();

            if (gameWon) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
            } else if (gameDraw) {
                printBoard();
                System.out.println("The game is a draw.");
            } else {
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X; // Switch player
            }
        }
    }

    // Handle player's move
    private static void playerMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int move;
        int row, col;

        while (true) {
            System.out.print("Player " + player + ", enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            row = move / 3;
            col = move % 3;

            if (move < 0 || move > 8) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            } else if (board[row][col] != EMPTY) {
                System.out.println("This spot is already taken. Choose another.");
            } else {
                board[row][col] = player;
                break;
            }
        }
    }

    // Check if the current player has won
    private static boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // Check if the game is a draw (no empty spaces left)
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}