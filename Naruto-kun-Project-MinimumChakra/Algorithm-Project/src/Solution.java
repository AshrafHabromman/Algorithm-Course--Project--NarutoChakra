    import java.io.*;
    import java.util.*;

    public class Solution {

        final static int numberOfTechniques = 3;
        static int [][] pathForNarutoToTrain;
        static int minIndexTech;
        public static void main(String[] args) {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            int mat[][];
            mat = enterMatrix();
            pathForNarutoToTrain = new int[mat.length][mat[0].length];
            int minChakra = 0;
            if(mat[0].length != 0){
                //minChakra = minimumChakraDP(mat);              //using dynamic programming
                minChakra = minimumChakraDAQ(mat,-1,-1);      //using divide and qonquer 
            }
            System.out.println(""+minChakra);
            
             
            /*
            //This commented code to print the path which Naruto have to follow to be Hokage.
            //note that you have to call minimumChakraDP function to print the path otherwise you can not print it 
            
            System.out.println("This Path which Naruto have to follow to be Hokage:\n");
            System.out.println("0 number for fire technique, 1 for water technique and 2 for wind technique.\n");
            int previousMinimumIndexTech = minIndexTech;
         
            System.out.println("day "+ pathForNarutoToTrain[0].length +" -> technique "+previousMinimumIndexTech+"\n");

            for(int day = pathForNarutoToTrain[0].length-2; day>-1 ; day--){

                System.out.println("day "+ (day+1) +" -> technique "+pathForNarutoToTrain[previousMinimumIndexTech][day]+"\n");
                previousMinimumIndexTech = pathForNarutoToTrain[previousMinimumIndexTech][day];

            }*/

        }

        public static int minimumChakraDAQ(int [][]matrix, int preventedTechniqueIndex, int dayNumber){
            if(dayNumber == matrix[0].length)
                return 0;

            int minimumChakraInThisDay = Integer.MAX_VALUE; //high value 
            int result;
            for(int i=0 ; i < numberOfTechniques; i++){
                if(i == preventedTechniqueIndex){
                    continue;
                }
                result = minimumChakraDAQ(matrix, i, dayNumber + 1);
                if(result < minimumChakraInThisDay) {
                    minimumChakraInThisDay = result;
                }
            }

            if(preventedTechniqueIndex == -1) {
                 return (minimumChakraInThisDay);
            }

            return (minimumChakraInThisDay + matrix[preventedTechniqueIndex][dayNumber]);

        }

        public static int minimumChakraDP(int [][]matrix){

            int numberOfdays = matrix[0].length;
            int [][] dPTable = new int[matrix.length][matrix[0].length];

            for(int technique = 0 ; technique < numberOfTechniques; technique++){
                dPTable[technique][0] = matrix[technique][0];           //initialization for the first column 
                //System.out.println("hhi"+dPTable[technique][0]);
            }

            for(int day = 1; day < numberOfdays; day++){
                for(int technique = 0; technique < numberOfTechniques; technique++){
                    dPTable[technique][day] = getMinBySkip(dPTable, day-1, technique) + matrix[technique][day];
                    //System.out.println("hello"+dPTable[technique][day]);
                }
            }
            int minimumChakra = getMinBySkip(dPTable, dPTable[0].length-1, -1);
            return minimumChakra;   
        }

        public static int getMinBySkip(int [][]matrix, int day, int preventedIndex){

            int min = Integer.MAX_VALUE;
            for(int technique = 0; technique < numberOfTechniques; technique++){
                if(technique == preventedIndex){
                    continue;
                }
                if(matrix[technique][day] < min){
                    min = matrix[technique][day];
                    if(preventedIndex != -1){
                        pathForNarutoToTrain[preventedIndex][day] = technique;
                    }
                    if(day == matrix[0].length-1){
                        minIndexTech = technique;
                    }
                }
            }
            return min;

        }


         public static int [][] enterMatrix() {

            Scanner input = new Scanner(System.in);
            int [][] matrix;
            int numberOfDays = input.nextInt();

            matrix = new int[3][numberOfDays];
            for(int i=0; i<3; i++)
                for(int j=0; j<numberOfDays;j++){
                    /*int value = input.nextInt();
                    if(value < 0){
                        matrix[i][j] = 0;
                    }
                    else*/ matrix[i][j] = input.nextInt();;
                }


            input.close();
            return matrix;
        }

        /*
    5
    3 1 5 5 4 
    4 5 5 1 5 
    5 6 8 1 3
    ---------
    3
    2 5 8 
    2 2 4
    1 1 5
    -----------
    3
    2 1 7 
    3 5 1 
    4 6 8 

        }*/



    }