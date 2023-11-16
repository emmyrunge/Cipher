package org.example;

public class Main {

    public static void main(String[] args) {

        int[][] key = {
                {0, 1, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0}
        };

        String plaintext = "HELLOWORLD";

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }

    public static String encrypt(String plainText, int[][] key) {
        int gridSize = 9;
        char[][] grid = new char[gridSize][gridSize];
        int index = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (key[i][j] == 1 && index < plainText.length()) {
                    grid[i][j] = plainText.charAt(index++);
                } else {
                    grid[i][j] = '-';
                }
            }
        }

        StringBuilder encryptedText = new StringBuilder();
        for (int rotations = 0; rotations < 4; rotations++) {
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (key[i][j] == 1) {
                        encryptedText.append(grid[i][j]);
                    }
                }
            }
            key = rotateKeyClockwise(key);
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int[][] key) {
        int gridSize = 9;
        char[][] grid = new char[gridSize][gridSize];
        int index = 0;

        for (int rotations = 0; rotations < 4; rotations++) {
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (key[i][j] == 1 && index < encryptedText.length()) {
                        grid[i][j] = encryptedText.charAt(index++);
                    } else {
                        grid[i][j] = '-';
                    }
                }
            }
            key = rotateKeyClockwise(key);
        }

        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (key[i][j] == 1) {
                    plainText.append(grid[i][j]);
                }
            }
        }

        return plainText.toString();
    }

    public static int[][] rotateKeyClockwise(int[][] key) {
        int size = key.length;
        int[][] rotatedKey = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotatedKey[i][j] = key[size - j - 1][i];
            }
        }

        return rotatedKey;
    }
}
