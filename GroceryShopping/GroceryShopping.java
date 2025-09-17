package com.mayukh.project.basicjava;
import java.util.Arrays;
import java.util.InputMismatchException;

import java.util.Scanner;
public class GroceryShopping{
    public static void main(String[] args){
        String[] itemList = {"Apple","Mango","Papaya","Egg","Milk","Notebook","Banana","Mosquito Repellant","Pen","Pencil","Rice","Daal"};
        float[] itemUnitPrice = {10.0f,20.0f,25.0f,5.0f,40.0f,50.0f,30.0f,100.0f,10.0f,5.0f,35.0f,40.0f};
        int[] stock = {10,20,10,30,40,40,30,40,20,40,10,20};
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3= new Scanner(System.in);
        while(true){
        	float totalBill = 0.0f;
            while(true){
            	System.out.println("Welcome to our store.");
            	System.out.println("If you have certain budget and want items according to your budget. Press 1");
            	System.out.println("If you want to proceed to buy. Press anything");
            	try {
            		String[] b = null;
	            	try {
	            		int x = scanner3.nextInt();
	            		if (x==1) {
	            			System.out.println("Enter your budget:");
	            			float y = scanner3.nextFloat();
	            			b=filterItemsBelowPrice(itemList,itemUnitPrice,y);
	            		}	
	            	}
	            	catch(InputMismatchException e ) {
	            		System.out.println("Write a number for price");
	            		scanner3.next();	
	            	}
	            	String[] a = new String[itemList.length];
	            	if (Arrays.equals(a,b)) {
        				throw new ItemNotFoundException("Item not Found");
        			}
	            	else if(b==null) ;
        			else {
        				for(String r:b) {
        					if(r!=null) {
        						System.out.println(r);
        					}
        				}
        			}
                	System.out.println("Average Price of our store items:"+averagePrice(itemUnitPrice));
                	System.out.println("Write the name of the item you want to buy in the format 'Apple' here:");
                	String itemName = scanner.nextLine();
                
                
                	int i = itemSearch(itemList,itemName);
                	if (i==itemList.length) {
    					throw new ItemNotFoundException("Item not Found");
    				}
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
                    }
                    catch(InputMismatchException e) {
                    	System.out.println("Write a number for quantity");
                    	scanner2.next();//reads and discards
                    	//if it is not used ,resource(previous wrong input) remains in scanner 	
                    }    
                }
                catch(ItemNotFoundException infe ) {
                	System.out.println(infe.getMessage());
                }                
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
    public static int itemSearch(String[] s, String t) throws ItemNotFoundException{	
    	int m = s.length;
        for(int i = 0;i<s.length;i++){
            if(s[i].equals(t)){
                m= i;
            }
        }
        return m;
    }
    public static float averagePrice(float[] s){
        float sum = 0;

        for (int i = 0;i<s.length;i++){
            sum+=s[i];
        }
        float average = sum / s.length ;
        return average;
    }
    public static String[] filterItemsBelowPrice(String[] s, float[] p, float pr) throws NumberFormatException,ItemNotFoundException{
        String[] k = new String[s.length];
        for (int i = 0; i<p.length;i++){
            if (p[i]<=pr){
                k[i]=s[i];
            }
        }
    	return k;
    }
}
