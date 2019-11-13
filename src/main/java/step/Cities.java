package step;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Cities implements Iterable<String>{
  private final List<String> cities = Arrays.asList("New York",
      "Los Angeles",
      "Chicago",
      "Houston[3]",
      "Phoenix",
      "Philadelphia[e]",
      "San Antonio",
      "San Diego",
      "Dallas",
      "San Jose",
      "Austin",
      "Jacksonville[f]",
      "Fort Worth",
      "Columbus",
      "San Francisco[g]",
      "Charlotte",
      "Indianapolis[h]",
      "Seattle",
      "Denver[i]",
      "Washington[j]",
      "Bosto");

  @Override
  public Iterator<String> iterator() {
    return cities.iterator();
  }

  public void create() {
    try (
        BufferedWriter bw =
            new BufferedWriter(new FileWriter(new File("src/main/java/lesson12/step/data/cities.txt")));
        ) {
      cities.forEach(c -> {
        try {
          bw.write(c);
          bw.newLine();
        } catch (IOException e) {
          System.out.println("smth went wrong during cities file filling");
        }
      });
    } catch (IOException e) {
      System.out.println("smth went wrong during cities file creation");
    }


  }
}
