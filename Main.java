import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
  public static class Gun {
    int blanks;
    int lives;
    int rounds;
    // false is blank, true is live
    ArrayList<Boolean> roundList = new ArrayList<Boolean>();

    public void set() {
      roundList.clear();

      rounds = (int) Math.floor(Math.random() * 7) + 2;
      blanks = (int) Math.ceil(rounds / 2);
      lives = (int) Math.floor(rounds / 2);

      for (int i = 0; i < blanks; i++) {
        roundList.add(false);
      }
      for (int i = 0; i < lives; i++) {
        roundList.add(true);
      }
      Collections.shuffle(roundList);
    }
  }

  public static void main(String[] args) {
    int healthP = 3;
    int healthD = 3;
    Scanner input = new Scanner(System.in);
    boolean playerTurn;
    // true is player, false is dealer
    boolean shootPlayer = false;

    // define the gun
    Gun gun = new Gun();

    // cycle through game rounds until one player is out of health
    while (healthD > 0 && healthP > 0) {
      playerTurn = true;

      // stock the gun
      gun.set();
      System.out.println("There are " + gun.blanks + " blank rounds and " + gun.lives + " live rounds.");

      // cycle through turns
      while (gun.roundList.size() > 0) {
        if (playerTurn) {
          // choose whom to shoot
          for (int i = 0; i >= 0; i++) {
            System.out.println("Choose whom to shoot first by inputting 'player' or 'dealer'.");
            String choice = input.nextLine();
            if (choice == "player") {
              shootPlayer = true;
              break;
            } else if (choice == "dealer") {
              shootPlayer = false;
              break;
            } else {
              System.out.println("Invalid entry.");
            }
          }

          // update health & gun
          if (gun.roundList.get(0) && shootPlayer) {
            System.out.println("BANG!\nPlayer was shot and loses 1 life.");
            healthP--;
          } else if (gun.roundList.get(0) && !shootPlayer) {
            System.out.println("BANG!\nDealer was shot and loses 1 life.");
            healthD--;
          } else if (!gun.roundList.get(0)) {
            System.out.println("*click*\nBlank fired.");
          }

          // check if anyone died
          if (healthD <= 0 || healthP <= 0)
            break;

          // pass turn
          if (gun.roundList.get(0) || !shootPlayer)
            playerTurn = false;
          gun.roundList.remove(0);

        } else {
          // choose whom to shoot
          if (gun.lives / gun.blanks >= 1) {
            shootPlayer = true;
          } else {
            shootPlayer = false;
          }

          // update health & gun
          if (gun.roundList.get(0) && shootPlayer) {
            System.out.println("BANG!\nPlayer was shot and loses 1 life.");
            healthP--;
          } else if (gun.roundList.get(0) && !shootPlayer) {
            System.out.println("BANG!\nDealer was shot and loses 1 life.");
            healthD--;
          } else if (!gun.roundList.get(0)) {
            System.out.println("*click*\nBlank fired.");
          }

          // check if anyone died
          if (healthD <= 0 || healthP <= 0)
            break;

          // pass turn
          if (gun.roundList.get(0) || !shootPlayer)
            playerTurn = true;
          gun.roundList.remove(0);
        }
      }
    }

    // logic if player died
    if (healthP == 0) {
      System.out.println("WELCOME TO HELL ASSHOLE. NOW START SCREAMING");
    }
    // logic if dealer died
    if (healthD == 0) {
      System.out.println("PLAYER WINS!");
    }
    input.close();
  }
}