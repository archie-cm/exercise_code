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

// Java Code Challenge: Loops Involving ArrayLists

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

// exercise

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

// 2D array
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

// Selection Sort Ascending

import java.util.Arrays;

class SelectionSort {
  public static void selectionSort (int arr[]) {
    int size = arr.length;
    for (int i = 0; i < size - 1; i++) {
      int currentMinimumIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[currentMinimumIndex]) {
          currentMinimumIndex = j;
        }
      }
      swap(arr, i, currentMinimumIndex);
  }
}
  public static void swap(int[] arr, int indexOne, int indexTwo) {
    int temp = arr[indexTwo];
    arr[indexTwo] = arr[indexOne];
    arr[indexOne] = temp;
 }
  public static void main(String args[]) {
     int[] data = { 2, 7, 1, 16, 4, 0 };
     SelectionSort.selectionSort(data);
     System.out.println(Arrays.toString(data));
  }
}

// insertion sort

import java.util.Arrays;
class InsertionSort {
  public static void sort(int[] array) {
   for (int i = 1; i < array.length; i++) {
     int current = array[i];
     int j = i -1;
     while (j >= 0 && array[j] > current) {
       array[j+1] = array[j];
       j--;
     }
    array[j+1] = current;
   }
  }
  public static void main(String[] args) {
    int[] numbers = {7, 2, 14, -7, 72};
    System.out.println("Array in ascending order");
    sort(numbers);
    System.out.println(Arrays.toString(numbers));
  }
}

// Language Families

// Language.java
class Language {
  protected String name;
  protected int numSpeakers;
  protected String regionsSpoken;
  protected String wordOrder;

  Language(String langName, int speakers, String regions, String wdOrders) {
    this.name = langName;
    this.numSpeakers = speakers;
    this.regionsSpoken = regions;
    this.wordOrder = wdOrders;
  }

  public void getInfo() {
    System.out.println(this.name + " is spoken by " + this.numSpeakers + " people mainly in " + this.regionsSpoken + ".");
    System.out.println("The language follows the word order: " + this.wordOrder);
  }

  public static void main(String[] args) {
    Language one = new Language("Ana", 2, "Java", "10");
    one.getInfo();
    Mayan two = new Mayan("Chuj", 61630);
    two.getInfo();
    SinoTibetan three = new SinoTibetan("Chinese", 1000000);
    three.getInfo();
    SinoTibetan four = new SinoTibetan("Burmese", 500000);
    four.getInfo();
  }
}

// Mayan.java
class Mayan extends Language {
  Mayan(String languageName, int speakers) {
    super(languageName, speakers, "Central America", "verb-object-subject");
  }

  @Override
  public void getInfo() {
    System.out.println(this.name + " is spoken by " + this.numSpeakers + " people mainly in " + this.regionsSpoken + ".");
    System.out.println("The language follows the word order: " + this.wordOrder);
    System.out.println("Fun fact: " + this.name + "is an ergative language.");
  }
}

// SinoTibetan.java
class SinoTibetan extends Language {
  SinoTibetan(String languageName, int speakers) {
    super(languageName, speakers, "Asia", "subject-object-verb");
    if(languageName.contains("Chinese")) {
      this.wordOrder = "subject-verb-object";
    }
  }
}

// webserver localhost:4001/restaurants
package com.codecademy.springcap.controller;

import com.codecademy.springcap.model.Restaurant;
import com.codecademy.springcap.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.regex.Pattern;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;
    private final Pattern zipCodePattern = Pattern.compile("\\d{5}");

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Insert code here:
    @GetMapping
    public Iterable<Restaurant> getAllRestaurants() {
      return restaurantRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        validateNewRestaurant(restaurant);

        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private void validateNewRestaurant(Restaurant restaurant) {
        if (ObjectUtils.isEmpty(restaurant.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        validateZipCode(restaurant.getZipCode());

        Optional<Restaurant> existingRestaurant = restaurantRepository.findRestaurantsByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
        if (existingRestaurant.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateZipCode(String zipcode) {
        if (!zipCodePattern.matcher(zipcode).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
