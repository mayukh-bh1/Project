package com.mayukh.project;
import java.util.InputMismatchException;

import java.util.Scanner;
public class GroceryShopping{
    public static void main(String[] args){
        String[] itemList = {"Apple","Mango","Papaya","Egg","Milk","Notebook","Banana","Mosquito Repellant","Pen","Pencil","Rice","Daal"};
        float[] itemUnitPrice = {10.0f,20.0f,25.0f,5.0f,40.0f,50.0f,30.0f,100.0f,10.0f,5.0f,35.0f,40.0f};
        int[] stock = {10,20,10,30,40,40,30,40,20,40,10,20};
        float totalBill = 0.0f;
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3= new Scanner(System.in);
        
        
        while(true){
        	
            while(true){
            	System.out.println("Welcome to our store.");
            	System.out.println("If you have certain budget and want items according to your budget. Press 1");
            	System.out.println("If you want to simply search for an item press 2");
            	System.out.println("If you want to proceed. Press anything");
            	
            	
            	try {
            		int x = scanner3.nextInt();
            		if(x==1) {
            			System.out.println("Enter your budget:");
            			float y = scanner3.nextFloat();
            			filterItemsBelowPrice(itemList,itemUnitPrice,y);
            		}
            		else if(x==2) {
            			String itemName2 = scanner.nextLine();
            			itemSearch(itemList,itemName2);
            			
            		}
            		else continue;
            	}
            	catch(InputMismatchException e ) {
            		System.out.println("Write a number for price");
            		scanner3.next();
            		
            	}
            	
                
                System.out.println("Average Price of our store items:"+averagePrice(itemUnitPrice));
                System.out.println("Write the name of the item you want to buy in the format 'Apple' here:");
                String itemName = scanner.nextLine();
                int t = 0;
         
                for (int i = 0; i<itemList.length;i++){
                    if (itemName.equals(itemList[i]) ){
                    	
                        System.out.println("Enter the quantity you want to buy:");
                        try{
                        	int quantity = scanner2.nextInt();
                        
	                        if(stock[i]< quantity){
	                            System.out.println("Insufficient stock");
	                        }
	                        else{
	                            totalBill += quantity*itemUnitPrice[i];
	                            stock[i] -= quantity;
	                            System.out.println("Your item has been sent to cart");
	                        }
	                        t = 1;
	                        break;
                        }
                        catch(InputMismatchException e) {
                        	System.out.println("Write a number for quantity");
                        	scanner2.next();//reads and discards
                        	//if it is not used resource(previous wrong input) remains in scanner 
                        	
                        	
                        	break;
                        }

                    }
                    else continue;
                    
                }
                
                System.out.println((t==0)?"Item not found":"");
                

                
                System.out.println("If you want to continue. Write anything. If you have completed your shopping and want your bill. Write 'Complete' here:");
            
                String complete = scanner.nextLine();
                
                if (complete.equals("Complete")){
                    System.out.println("Your total Bill is " + totalBill);
                    if (totalBill> 100.0f){
                        System.out.println("congratulations. You have shopped above 100 bucks. You have been offered a discount of 10%. Your discounted bill is "+(totalBill*0.9));
                    }
                    break;
                }
                else continue;
                
                
               

            }
            
            System.out.println("Please shop with us again.");
            System.out.println("If you want to shop again. Write anything.To Exit the Program write 'Exit' here:");
            String exit = scanner.nextLine();
            
            if (exit.equals("Exit")){
                break;
            }
            else continue;
        }
        scanner.close();
        scanner2.close();
        scanner3.close();
    }
    public static void itemSearch(String[] s, String t){
    	int u = 0;
        for(int i = 0;i<s.length;i++){
            if(s[i].equals(t)){
                System.out.println("Index:"+i);
                u=1;
                break;

            }
            
        }
        if(u==0){
            System.out.println("item not found");
        }

    }
    public static float averagePrice(float[] s){
        float sum = 0;

        for (int i = 0;i<s.length;i++){
            sum+=s[i];
        }
        float average = sum / s.length ;
        return average;
    }
    public static void filterItemsBelowPrice(String[] s, float[] p, float pr) throws NumberFormatException{
        for (int i = 0; i<p.length;i++){
            if (p[i]<pr){
                System.out.println(s[i]);
            }
        }
    }
}
