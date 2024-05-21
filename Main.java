import java.util.*;

class Main {
  public static class Gun {
    int blanks;
    int lives;
    int rounds;
    // false is blank, true is live
    ArrayList<Boolean> roundList = new ArrayList<Boolean>();

    public void set() {
      roundList.clear();

      blanks = (int) Math.floor(Math.random() * 5) + 1;
      lives = (int) Math.floor(Math.random() * Math.min(blanks, 8 - blanks)) + 1;
      rounds = blanks + lives;

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
    boolean playerTurn;

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

          // update health & gun

          // check if anyone died

          // pass turn
        } else {
          // choose whom to shoot

          // update health & gun

          // check if anyone died

          // pass turn
        }
      }
    }

    // logic if player died

    // logic if dealer died
  }
}