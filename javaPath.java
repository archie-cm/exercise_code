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


import java.util.ArrayList;

public class BeeHive {
  private ArrayList<Bee> beeList;

  public BeeHive(int numBees) {
    this.beeList = new ArrayList<Bee>();
    for (int i = 0; i < numBees; i++) {
      this.beeList.add(new Bee("bee" + i));
    }
  }

  public static void main(String[] args) {
    BeeHive myHive = new BeeHive(365);
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

# 2D array
public static int largestColumn(int[][] arr2D) {
    int largestColumn = 0;
    int largestNumber = 0;
    for (int col = 0; col < arr2D[0].length; col++){
    int columnVal = 0;
        for (int row = 0; row < arr2D.length; row++) {
            columnVal += arr2D[row][col];
        }
        if (columnVal > largestNumber) {
            largestNumber = columnVal;
            largestColumn = col;
        }
    }
    return largestColumn;
} 


public static String reverseString(String text){
  // base case
  if (text.length() == 0) {
    return text;
  } else {
    // recursive call
    return reverseString(text.substring(1)) + text.charAt(0);
  }
}

public static void main(String[] args) {
  String str = new String("howdy");
  // calling recursive function
  String reverse = reverseString(str);
  System.out.println(reverse); // Prints: ydwoh
}

// binary search


// recursive
class Main
{
  public static int binarySearch(int[] arr, int left, int right, int target)
  {
   
    int mid = left + (right - left) / 2;

    // Base condition (key value is found)
    if (target == arr[mid]) {
      return mid;
    }

    // discard all elements in the right search space
    // including the mid element
    if (target < arr[mid]) {
      return binarySearch(arr, left, mid - 1, target);
    }

    // discard all elements in the left search space
    // including the mid element
     if (target > arr[mid]) {
      return binarySearch(arr, mid + 1, right, target);
    }
  }

  public static void main(String[] args)
  {
    int[] arr = { 2, 5, 6, 8, 9, 10 };
    int target = 8;
 
    int left = 0;
    int right = arr.length - 1;
 
    int index = binarySearch(arr, left, right, target);
   if (index != -1) {
      System.out.println("Element found at index " + index);
    } else {
      System.out.println("Element not found in the array");
    }
  }
}


// iterative
class BinarySearch
{
  public static int binarySearch(int[] arr, int target)
  {
     int left = 0; 
     int right = array.length - 1;

    while (left <= right)
    {
      
      int mid = (left + right) / 2;

      if (target == array[mid]) {
        return mid;
      }

     if (target < arr[mid]) {
        right = mid - 1;
      }

      if (target > arr[mid]) {
        left = mid + 1;
      }
    }

    return -1;
  }
 
   public static void main(String[] args)
  {
    int[] arr = { 2, 5, 6, 8, 9, 10 };
    int target = 6;

    int index = binarySearch(arr, target);
    if (index != -1) {
    System.out.println("Element found at index " + index);
     } else {
    System.out.println("Element not found in the array");
     }
  }
}
