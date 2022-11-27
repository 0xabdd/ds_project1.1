
package abd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class Main {

 
    public static void main(String[] args) {
        
        
        
        
        String [][] seats = new String[10][4]; // creates table 2
             
        // holds passangers in an ArrayList.
        ArrayList<String> yolcular = new ArrayList<String>(Arrays.asList("Ali", "Veli", "Fatma", "Ayşe", "Yusuf", 
            "Ahmet", "Burak", "Furkan","Mutlu", "Ceyda"
        ,"Rabia", "Duru", "Atakan", "Efe", "Efecan",
        "Yunus", "Hamza", "Berat", "Duran", "Polat", 
        "Elif", "Seyfo", "Cakir", "Memati", "Nevzat",
        "Pala", "Kirve", "Ziya", "Mehmet", "Kilic",
        "Safiye", "Erhan", "Abdulhey", "Eysan", "Ezel", 
        "Ramiz", "Duygu","Doga", "Zeynep", "Ebru"));
        
        

        double [][] distanceMatrix = createDistanceMatrix(); //creates distance matrix
        
        
        placePassangers(distanceMatrix, seats, yolcular); //places passanger according to instractions.
        
        printPassangers(seats); // displays passangers 
        totalDistance(distanceMatrix); // total distance between all passangers.
       
        
        
        
  
        
    }
    //creates distance matrix 40x40
    public static double[][] createDistanceMatrix(){
        double [][] distanceMatrix = new double[40][40];
        
        for (int i = 0; i< 40; i++){ 
            for(int j = 0; j < 40; j++){
                double randomValue = createDoubleRandomValue(0, 10); // generate random value between 0 - 10
                // D.M[i][j] = D.M[j][i]
                distanceMatrix[i][j] = randomValue; 
                distanceMatrix[j][i] = randomValue;
            }
            distanceMatrix[i][i] = 0; // no distance with herself
    
        }
        return distanceMatrix;
    }
    
    
    // creates random double valeus for distances.
    public static double createDoubleRandomValue(int rangeMin, int rangeMax){
        Random rs = new Random();
        return rangeMin + (rangeMax - rangeMin) * rs.nextDouble();
        
    } 

    
    //finds min distance for first 4 passanger
    public static int findMinDistance(int passanger, double distanceMatrix[][]){
        double minDistance = 10; //range max
        int index = 0;
        for(int i = 0; i<40; i++){
            double distance = distanceMatrix[passanger][i];
            if(distance == 0){
                continue;
            }
            else if(distance < minDistance){
                minDistance = distance;
                index = i;
            }
        }
        return index;
    }
    // finds min distance according to 2 passangers.
    public static int findSumMinDistanceB2(int pass1, int pass2, int seat, double distanceMatrix[][]){
        double minSum = 10;
        int index = 0;
        for(int i = seat; i < distanceMatrix.length; i++){
            double dis1 = distanceMatrix[seat][pass1];
            double dis2 = distanceMatrix[seat][pass1];
            double sum = dis1 + dis2;
            if(sum < minSum){
                minSum = sum;
                index = i;
            }
        }
        return index;
    }
    // finds min distance according to 3 passangers.
    public static int findSumMinDistanceB3(int pass1, int pass2, int pass3, int seat, double distanceMatrix[][]){
        double minSum = 10;
        int index = 0;
        for(int i = seat; i < distanceMatrix.length; i++){
            double dis1 = distanceMatrix[seat][pass1];
            double dis2 = distanceMatrix[seat][pass1];
            double dis3 = distanceMatrix[seat][pass3];
     
            double sum = dis1 + dis2 + dis3;
            if(sum < minSum){
                minSum = sum;
                index = i;
            }
        }
        return index;
    }
    // finds min distance according to 4 passangers.
    public static int findSumMinDistanceB4(int pass1, int pass2, int pass3,int pass4, int seat, double distanceMatrix[][]){
        double minSum = 10;
        int index = 0;
        for(int i = seat; i < distanceMatrix.length; i++){
            double dis1 = distanceMatrix[seat][pass1];
            double dis2 = distanceMatrix[seat][pass1];
            double dis3 = distanceMatrix[seat][pass3];
            double dis4 = distanceMatrix[seat][pass4];
            double sum = dis1 + dis2 + dis3+dis4;
            if(sum < minSum){
                minSum = sum;
                index = i;
            }
        }
        return index;
    }
    //places passangers
    public static void placePassangers(double distanceMatrix[][],String [][] seats, ArrayList<String> yolcular ){
        // first 4 passangers are placed with different rule.
        int firstPassangerIndex = ThreadLocalRandom.current().nextInt(0, 39 + 1);
        seats[0][0] = yolcular.get(firstPassangerIndex); 
        yolcular.remove(firstPassangerIndex);

        
        int secondPassangerIndex = findMinDistance(firstPassangerIndex, distanceMatrix);
        seats[0][1] = yolcular.get(secondPassangerIndex);  
        yolcular.remove(secondPassangerIndex);

        
        int thirdPassangerIndex = findMinDistance(secondPassangerIndex, distanceMatrix);
        seats[0][2] = yolcular.get(thirdPassangerIndex);
        yolcular.remove(thirdPassangerIndex);

       
        int fourthPassangerIndex = findMinDistance(thirdPassangerIndex, distanceMatrix);
        seats[0][3] = yolcular.get(fourthPassangerIndex);
        yolcular.remove(fourthPassangerIndex);
        
        
        
        // rest passangers are placed by an algorithm.
        int y = 0;
        for(int i = 1; i<10; i++){
                for(int j=0;  j< 4; j++){

                    if(j == 0){
                        seats[i][j] = yolcular.get((findSumMinDistanceB2(y, y+1, y+4, distanceMatrix)));
                        yolcular.remove(findSumMinDistanceB2(y, y+1, y+4, distanceMatrix));
                        
                        }
                    if(j == 1 || j== 2){
                        seats[i][j] = yolcular.get(findSumMinDistanceB4(y, y+1, y+2, y+4, y+5, distanceMatrix));
                        yolcular.remove(findSumMinDistanceB4(y, y+1, y+2, y+4, y+5, distanceMatrix));
                        
                        }
                    if(j == 3){
                        seats[i][j] = yolcular.get(findSumMinDistanceB3(y+2, y+3, y+6, y+7, distanceMatrix));
                        yolcular.remove(findSumMinDistanceB3(y+2, y+3, y+6, y+7, distanceMatrix));
                        
                        }
                  
                }
                y++;
               
            }
    }
    // displays passangers.
    public static void printPassangers(String [][] seats){
        for(int i = 0; i< 10; i++){
            for(int j = 0; j<4; j++){
                System.out.print(seats[i][j] + " " );
            }
            System.out.println();
        }
    }
    // calcultes total distance between all passangers.
    public static void totalDistance(double [][] distanceMatrix){
        double totalDistance = 0;
        for(int i= 0; i<40; i++){
            for(int j= 0; j<40; j++){
                totalDistance += distanceMatrix[i][j];
            }
        }
        System.out.printf(String.format("""
                                        Total distance between all passangers: %.2f
                                         """,totalDistance/2) );
        
    }
    
   
    
    


   
        
    }
    