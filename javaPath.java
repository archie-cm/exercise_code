public static boolean hasSpecialItem(double[] groceryPrices) {
  for (double itemCost : groceryPrices) {
    String itemCostStr = String.valueOf(itemCost);
    if (itemCostStr.length() > 1 && itemCostStr.substring(itemCostStr.length() - 2, itemCostStr.length()).equals("99")) {
      return true;
    }
  }

  return false;
}

public static boolean hasDuplicates(String[] groceryList) {
  for (int i = 0; i < groceryList.length - 1; i++) {
    for (int j = i + 1; j < groceryList.length; j++) {
      if (groceryList[i] == groceryList[j]) {
       return true; 
      }
    }
  }
  
  return false;
}

public static void reverseGroceries(String[] groceryList) {
  int j = groceryList.length - 1;
  for (int i = 0; i < groceryList.length/2; i++) {
    String temp = groceryList[i];
    groceryList[i] = groceryList[j];
    groceryList[j] = temp;
    j--;
  }
}

public static void rotateGroceries(String[] groceryList) {
  if (groceryList.length > 1) {
    String previous = groceryList[groceryList.length - 1];
    for (int i = 0; i < groceryList.length; i++) {
      String temp = groceryList[i];
      groceryList[i] = previous;
      previous = temp;
    }
  }
}

# Java Code Challenge: Loops Involving ArrayLists

public class Bee {
  private String name;
  private boolean isHome;

  public Bee(String name) {
    this.name = name;
    this.isHome = true;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public boolean isHome() {
    return this.isHome;
  }

  public void leaveHome() {
    this.isHome = false;
  }

  public void returnHome() {
    this.isHome = true;
  }
} 

# exercise

public void printBees() {
  for (Bee b : this.beeList) {
    System.out.println(b.getName());
  }
}


public void leaveHome(String beeName) {
  for (Bee b : this.beeList) {
    if (b.getName().equals(beeName)) {
      b.leaveHome();
    }
  }
}


public void addBees(ArrayList<String> beeNames) {
  for (String name : beeNames) {
    this.beeList.add(new Bee(name));
  }
}


public void moveOutBees() {
  for (int i = 0; i < this.beeList.size(); i++) {
    this.beeList.remove(i);
  }
}


public void renameBees(ArrayList<String> newNames) {
  int numNamesToAssign = this.beeList.size();
  if (newNames.size() < this.beeList.size()) {
    numNamesToAssign = newNames.size();
  }

  for (int i = 0; i < numNamesToAssign; i++) {
    this.beeList.get(i).setName(newNames.get(i));
  }
}


