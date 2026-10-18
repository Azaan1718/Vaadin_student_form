
class process{

        public static void process_id(int id){
            if(id<300){
                System.out.println("Item of id " +id+" is Small item");
            }

            else if(id>=300 && id<=700){
                System.out.println("Item of id "+id+" is Medium item");
            }

            else if(id>700){
                System.out.println("Item of id "+id+" is Large item");
            }
        }
    }

class Smarthouse{
     
        void find_id(){
            for(int i=100;i<=1000;i+=100){
                if(i>900){
                    System.out.println("Ware house capacity reached for product id "+i);
                    break;
                }

                if(i==500){
                  System.out.println("Product under quality inspection for product id "+i);
                    continue;
                }
                process.process_id(i);
            }
        }
    }


public class smartwarehouseoop{
    
    public static void main(String args[]){
        Smarthouse house=new Smarthouse();
        house.find_id();
    }
}