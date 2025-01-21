// Maryam Badalyengejeh, Felix Oliveira-Machado, Divaey Kumar
//1/21/25
//Assigment to update blackjack

import java.util.Random;
import java.util.Scanner;

public class BlackJack {

    //constants - cannot change their values 
    //static - I can use these in every function without having to pass them in 
    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace" };
    private static final int[] DECK = new int[52];
    private static int currentCardIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        initializeDeck();
        shuffleDeck();

        // Integer for the players/ dealers total cards 
        int playerTotal = dealInitialPlayerCards();
        int dealerTotal = dealInitialDealerCards();

        playerTotal = playerTurn(scanner, playerTotal);
        if (playerTotal > 21) { // if the player total is greater than 21 do the following command
            System.out.println("You busted! Dealer wins.");  //Displays message when player has lost 
            return;
        }

        dealerTotal = dealerTurn(dealerTotal);
        determineWinner(playerTotal, dealerTotal); 

        scanner.close(); // closes the scanner 
    }

    // loops through deck of cards array 
    private static void initializeDeck() {
        for (int i = 0; i < DECK.length; i++) {
            DECK[i] = i;
        }
    }

    // Shuffles deck and picks random card 
    private static void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < DECK.length; i++) {
            int index = random.nextInt(DECK.length);
            // swapping two integers in an array
            int temp = DECK[i];
            DECK[i] = DECK[index];
            DECK[index] = temp;

        } 
        System.out.println("printed deck");
        for (int i = 0; i < DECK.length; i++) {  // for loop 
            System.out.println(DECK[i] + " ");
        }
    }

    private static int dealInitialPlayerCards() {
        int card1 = dealCard();
        int card2 = dealCard();
        System.out.println("Your cards: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4] + " and "
                + RANKS[card2] + " of " + SUITS[card2 / 13]);
        return cardValue(card1) + cardValue(card2); // adds the value of cards 1 and 2 
    }
    // The method dealInitialDealerCards() is in place because it deals one card to the dealer, prints its details, and returns its value
    private static int dealInitialDealerCards() {
        int card1 = dealCard();
        System.out.println("Dealer's card: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4]);
        return cardValue(card1);
    }
    //This approach shows the player's decision-making during their turn, making sure that their input works and goes through while managing the outcomes of their actions
    private static int playerTurn(Scanner scanner, int playerTotal) {
        while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
            String action = scanner.nextLine().toLowerCase();  // .toLowerCase (makes whatever the player types lowercase)
            if (action.equals("hit")) {
                int newCard = dealCard();
                playerTotal += cardValue(newCard);
                System.out.println("new card index is " + newCard);
                System.out.println("You drew a " + RANKS[newCard] + " of " + SUITS[DECK[currentCardIndex] % 4]);
                if (playerTotal > 21) {
                    break;
                }
            } else if (action.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'.");
            }
        }
        return playerTotal;
    }
        //This makes the dealer continue to deal themselves a card until dealerTotal < 17 is false 
    private static int dealerTurn(int dealerTotal) {
        while (dealerTotal < 17) {
            int newCard = dealCard();
            dealerTotal += cardValue(newCard);
        }
        System.out.println("Dealer's total is " + dealerTotal);
        return dealerTotal;
    }
    
    // Determines if the player wins vs Dealer through comparing the dealerTotal with 21 and playerTotal
    private static void determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (dealerTotal == playerTotal) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
    // Deals Cards to the players the currentCardIndex++ keeps track of the position of the next card to be dealt
    private static int dealCard() {
        return DECK[currentCardIndex++] % 13;
    }

    private static int cardValue(int card) {
        return card < 9 ? card + 2 : 10;
    }
    //linearSearch checks each element of the array for a match to the key and returns the index of the first match or -1 if no match is found
    int linearSearch(int[] numbers, int key) {
        int i = 0;
        for (i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1; // not found 
    }
}
