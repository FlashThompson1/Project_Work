package Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import Domain.FoodDomain;
import Interface.ChangerNumberItemListener;

public class ManagmentCart {
    private Context context;
    private  TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }


    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listfood = getListCart();
        boolean existAlready = false;
        int n=0;
        for (int i =0; i < listfood.size();i++){
            if(listfood.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
if (existAlready){
    listfood.get(n).setNumberInCart(item.getNumberInCart());
}else {
    listfood.add(item);
}
tinyDB.putListObject("CardList",listfood);
        Toast.makeText(context,"Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart() {
        return tinyDB.getListObject("CardList");
    }
    public  void minusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangerNumberItemListener changerNumberItemListener) {
        if(listfood.get(position).getNumberInCart()==1){
            listfood.remove(position);
        }else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CardList",listfood);
        changerNumberItemListener.changed();
    }
    public void plusNumberFood(ArrayList<FoodDomain> listfood,int position,ChangerNumberItemListener changerNumberItemListener){
        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1 );
        tinyDB.putListObject("CardList",listfood);
        changerNumberItemListener.changed();
    }

    public Double getTotal(){
        ArrayList<FoodDomain> listfood =getListCart();
        double fee=0;
        for (int i =0;i < listfood.size();i++){
            fee=fee+(listfood.get(i).getFee()*listfood.get(i).getNumberInCart());
        }
        return fee;
    }
}
