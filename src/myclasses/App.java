/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;


import entity.Brand;
import entity.Sneaker;
import entity.Buyer;
import entity.History;
import entity.User;
import facade.BrandFacade;
import facade.BuyerFacade;
import facade.HistoryFacade;
import facade.SneakerFacade;
import facade.UserFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author krasa
 */
public class App {
      
   Scanner scan = new Scanner(System.in);
   private UserFacade userFacade;
   private BuyerFacade buyerFacade;
   private SneakerFacade sneakerFacade;
   private BrandFacade brandFacade;
   private HistoryFacade historyFacade;
//    
   public App(){
      userFacade= new UserFacade();
      buyerFacade = new BuyerFacade(Buyer.class);
      sneakerFacade = new SneakerFacade(Sneaker.class);
      brandFacade = new BrandFacade(Brand.class);
      historyFacade = new HistoryFacade(History.class);
    }   

    public void run(){
        int exit=0;
        do{
            System.out.println("---------МЕНЮ---------");
            System.out.println("1. Выход из программы");
            System.out.println("2. Добавить кроссовок");
            System.out.println("3. Вывести список кроссовок");
            System.out.println("4. Добавить покупателя");
            System.out.println("5. Список покупателей");
            System.out.println("6. Покупка обуви");
            System.out.println("7. Доход магазина");
            System.out.println("8. Добавить деньги пользователю");
            System.out.println("9. Поступление кроссовок");
            System.out.println("10. Вывести базу фирм кроссовок");
            System.out.println("11. Редактировать кроссовки");
            System.out.println("12. Редактировать покупателя");
            System.out.print("Выберите задачу: ");
            int task= getNumber();
            switch(task){
                case 1:
                    exit++;
                    break;
                case 2:
                    addSneaker();
                    break;
                case 3:
                    sneakerList();
                    break;
                case 4:
                    addBuyer();
                    break;
                case 5:
                    buyerList();
                    break;
                case 6:
                    purchase();
                    break;
                case 7:
                    IncomePerMonth();
                    break;
                case 8:
                    addMoney();
                    break;
                case 9:
                    addQuantitySneaker();
                    break;
                case 10:
                    listBrands();
                    break;
                case 11:
                    changeSneaker();
                    break;
                case 12:
                    changeBuyer();
                    break;
                default:
                    System.out.println("Выберите номер из списка!");
            }

        }while(exit==0);
        System.out.println("*ВЫХОД*");
    }
//------------------------------------------------------------------------------
private void sneakerList(){
    System.out.println("*СПИСОК КРОССОВОК*");
    List<Sneaker> sneakers = sneakerFacade.findAll();
    int n=0;
    for (int i = 0; i < sneakers.size(); i++) {
        if(sneakers.get(i)!=null){
            System.out.printf("%d. %s %s, размер: %.0f, цена: %.2f евро, в наличии: %d%n",
            sneakers.get(i).getId(),
            sneakers.get(i).getSneakerFirm().getBrand(),
            sneakers.get(i).getSneakerModel(),
            sneakers.get(i).getSneakerSize(),
            sneakers.get(i).getSneakerPrice(),
            sneakers.get(i).getSneakerQuantity()
            );
            n++;
        }
    }  
    if(n<1){
        System.out.println("Кроссовок нет!");
    }
}
//------------------------------------------------------------------------------
private void buyerList(){
    System.out.println("*СПИСОК ПОКУПАТЕЛЕЙ*");
    List<Buyer> buyers = buyerFacade.findAll();
    int n=0;
    for (int i = 0; i < buyers.size(); i++) {
        if(buyers.get(i)!=null){
            System.out.printf("%d. %s %s, номер телефона: %s, доступные деньги: %.2f евро%n",
            buyers.get(i).getId(),
            buyers.get(i).getBuyerFirstName(),
            buyers.get(i).getBuyerLastName(),
            buyers.get(i).getBuyerPhone(),
            buyers.get(i).getBuyerMoney()
            );
            n++;
        }
    } 
    if(n<1){
        System.out.println("Покупателей нет!");
    }
}
//------------------------------------------------------------------------------
private void addSneaker(){
    System.out.println("*ДОБАВЛЕНИЕ КРОССОВКА*");
    Sneaker sneaker= new Sneaker();
    Brand brand = new Brand();
    System.out.print("Введите количество кроссовок для добавления: ");
    sneaker.setSneakerQuantity(getNumber()); 
    listBrands();
    int i = 0;
    while(i==0){
    System.out.print("Если в списке есть желаемая фирма, нажмите 0, если нет и вы хотите добавить фирму, нажмите 1: ");
    int choice=getNumber();
    switch (choice) {
        case 1:
            System.out.print("Введите фирму кроссовка: ");
            brand.setBrand(scan.nextLine());
            brandFacade.create(brand);
            sneaker.setSneakerFirm(brand);
            i++;
            break;
        case 0:
            System.out.print("Введите номер нужной фирмы: ");
            int choice2=getNumber();
            sneaker.setSneakerFirm(brandFacade.find((long)choice2));
            i++;
            break;
        default:
            System.out.println("Ошибка, наберите 0 или 1!");
            break;
    }
    }
    System.out.print("Введите модель кроссовка: ");
    sneaker.setSneakerModel(scan.nextLine());
    System.out.print("Введите размер кроссовка: ");
    sneaker.setSneakerSize(getNumber());
    System.out.print("Введите цену кроссовка: ");
    sneaker.setSneakerPrice(scan.nextDouble()); scan.nextLine();
    System.out.println("Вы добавили"+sneaker.toString());
    sneakerFacade.create(sneaker);
}  
//------------------------------------------------------------------------------
private void addBuyer(){
    System.out.println("*ДОБАВЛЕНИЕ ПОКУПАТЕЛЯ*");
    Buyer buyer= new Buyer();
    System.out.print("Введите имя покупателя: ");
    buyer.setBuyerFirstName(scan.nextLine());
    System.out.print("Введите фамилию покупателя: ");
    buyer.setBuyerLastName(scan.nextLine());
    System.out.print("Введите номер телефона покупателя: ");
    buyer.setBuyerPhone(scan.nextLine());
    System.out.print("Введите количество денег у покупателя: ");
    buyer.setBuyerMoney(scan.nextDouble()); scan.nextLine();
    System.out.println("Вы добавили "+buyer.toString());
    buyerFacade.create(buyer);
}
//------------------------------------------------------------------------------
private void purchase(){
    System.out.println("*ПОКУПКА ОБУВИ*"); 
    System.out.println("-----------------------------");
    buyerList();
    System.out.print("Выберите нужного покупателя: ");
    int buyerNum= getNumber();
    System.out.println("-----------------------------");
    int n=0;
    while(n==0){
        sneakerList();
        System.out.print("Выберите нужную модель обуви: ");
        int sneakerNum= getNumber();
        History history= new History();
        history.setSneaker(sneakerFacade.find((long) sneakerNum));
        history.setBuyer(buyerFacade.find((long)buyerNum));
        Calendar c = new GregorianCalendar();
        history.setGivenSneaker(c.getTime());
        if(history.getBuyer().getBuyerMoney()>=history.getSneaker().getSneakerPrice() && history.getSneaker().getSneakerQuantity()!=0){
            System.out.println("-----------------------------");
            System.out.printf("Кроссовки %s %s купил %s %s за %.2f евро %s%n",
            history.getSneaker().getSneakerFirm().getBrand(),
            history.getSneaker().getSneakerModel(),
            history.getBuyer().getBuyerFirstName(),
            history.getBuyer().getBuyerLastName(),
            history.getSneaker().getSneakerPrice(),
            history.getGivenSneaker()
            );
            history.getBuyer().setBuyerMoney(history.getBuyer().getBuyerMoney()-history.getSneaker().getSneakerPrice());
            history.getSneaker().setSneakerQuantity(history.getSneaker().getSneakerQuantity()-1);
            historyFacade.create(history);
            
            n++;
        }else if(history.getBuyer().getBuyerMoney()<history.getSneaker().getSneakerPrice()){
            System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег на этот товар, выберите другой товар!");   
        }else if(history.getSneaker().getSneakerQuantity()==0){
            System.out.println("Этого товара нет в наличии, выберите другой!");
        }
    }   
}   
//------------------------------------------------------------------------------
private void income(){
    double income = 0;
    List<History> histories = historyFacade.findAll();
    System.out.println("*ОБЩИЙ ДОХОД*");
    for (int i = 0; i < histories.size(); i++) {
        Date date = histories.get(i).getGivenSneaker();
        if (histories.get(i)!=null) {
           income+=histories.get(i).getSneaker().getSneakerPrice();
        }
        
    }
    System.out.printf("Доход за все время: %s евро%n", income);
}
//------------------------------------------------------------------------------
private void addMoney(){
    System.out.println("*ДОБАВИТЬ ДЕНЬГИ ПОКУПАТЕЛЮ*");
    buyerList();
    System.out.print("Выберите нужного покупателя: ");
    int choice= getNumber();
    Buyer buyer = buyerFacade.find((long) choice);
    System.out.print("Введите сколько денег вы хотите добавить этому покупателю: ");
    double add= scan.nextDouble(); scan.nextLine();
    buyer.setBuyerMoney(buyer.getBuyerMoney()+add);
    buyerFacade.edit(buyer);
    //buyers.get(choice-1).setBuyerMoney(buyers.get(choice-1).getBuyerMoney()+add);
    }
//------------------------------------------------------------------------------
private int getNumber(){
    int number;
    int number2;
    do{
        String strNumber= scan.nextLine();
        try{
            number= Integer.parseInt(strNumber);
            number2=0;
            return number;
        }catch(NumberFormatException e){
            System.out.print("Введено \""+strNumber+"\". Выбирайте номер: ");
        }catch(IllegalArgumentException e){
            System.out.println("0");
        }
    }while(true);
}
//------------------------------------------------------------------------------
private void addQuantitySneaker(){
    sneakerList();
    System.out.print("Введите номер кроссовка для добавления количества: ");
    int num=getNumber();
    Sneaker sneaker = sneakerFacade.find((long)num);
    System.out.print("Введите сколько пар кроссовок поступило: ");
    int add=getNumber();
    sneaker.setSneakerQuantity(sneaker.getSneakerQuantity()+add);
    sneakerFacade.edit(sneaker);
//    sneakers.get(num-1).setSneakerQuantity(sneakers.get(num-1).getSneakerQuantity()+add);
//    saver.saveSneaker(sneakers);
}
//------------------------------------------------------------------------------
private void listBrands(){
    int n =0;
    List<Brand> brands= brandFacade.findAll();
    for (int i = 0; i < brands.size(); i++) {
        if(brands.get(i)!=null){
            n++;
            System.out.printf("%d. %s%n",
            brands.get(i).getId(),
            brands.get(i).getBrand()
            );
        n++;
        }
}
    if(n==0){
        System.out.println("Фирм нет!");
    }
}
//------------------------------------------------------------------------------
private void changeSneaker(){
    System.out.println("*РЕДАКТИРОВАТЬ КРОССОВОК*");
    sneakerList();
    System.out.print("Выберите кроссовок для редактирования: ");
    int numsneaker= getNumber();
    Sneaker sneakers= sneakerFacade.find((long) numsneaker);
    Brand brand= new Brand();
    int n = 0;
    while(n==0){
    System.out.println("-------------------------");
    System.out.println("1. Редактировать фирму кроссовка");
    System.out.println("2. Редактировать модель кроссовка");
    System.out.println("3. Редактировать размер кроссовка");
    System.out.println("4. Редактировать цену кроссовка");
    System.out.println("5. Редактировать количество кроссовк");
    System.out.println("6. Выход");
    System.out.print("Выберите номер того, что хотите конкретно изменить или выйдите: ");
    int numchange= getNumber();
    switch(numchange){
        case 1:
            listBrands();
            int i = 0;
            while(i==0){
            System.out.print("Если в списке есть желаемая фирма, нажмите 0, если нет и вы хотите добавить фирму, нажмите 1: ");
            int choice=getNumber();
            switch (choice) {
                case 1:
                    System.out.print("Введите фирму кроссовка: ");
                    brand.setBrand(scan.nextLine());
                    brandFacade.create(brand);
                    sneakers.setSneakerFirm(brand);
                    sneakerFacade.edit(sneakers);
                    i++;
                    break;
                case 0:
                    System.out.print("Введите номер нужной фирмы: ");
                    int choice2=getNumber();
                    sneakers.setSneakerFirm(brandFacade.find((long)choice2));
                    sneakerFacade.edit(sneakers);
                    i++;
                    break;
                default:
                    System.out.println("Ошибка, наберите 0 или 1!");
                    break;
            }
            }  
            break;
        case 2:
            System.out.print("Введите новую модель кроссовок: ");
            sneakers.setSneakerModel(scan.nextLine());
            sneakerFacade.edit(sneakers);
            break;
        case 3:
            System.out.print("Введите новый размер кроссовок: ");
            sneakers.setSneakerSize(scan.nextDouble());scan.nextLine();
            sneakerFacade.edit(sneakers);
            break;
        case 4:
            System.out.print("Введите новую цену кроссовок: ");
            sneakers.setSneakerPrice(scan.nextDouble());scan.nextLine();
            sneakerFacade.edit(sneakers);
            break;
        case 5:
           System.out.print("Введите новое количество кроссовок: ");
            sneakers.setSneakerQuantity(getNumber());
            sneakerFacade.edit(sneakers);
            break;
        case 6:
            System.out.println("*ВЫХОД*");
            n++;
            break;
        default:
            System.out.println("Ошибка, выберите номер из списка!");
            break;
    }
    }
}
//------------------------------------------------------------------------------
private void changeBuyer(){
    System.out.println("*РЕДАКТИРОВАТЬ ПОКУПАТЕЛЯ*");
    buyerList();
    System.out.print("Выберите покупателя для редактирования: ");
    Buyer buyers = buyerFacade.find((long)getNumber());
    int n = 0;
    while(n==0){
    System.out.println("-------------------------");
    System.out.println("1. Изменить имя покупателя");
    System.out.println("2. Изменить фамилию покупателя");
    System.out.println("3. Изменить номер телефона покупателя");
    System.out.println("4. Изменить количество денег покупателя");
    System.out.println("5. Выход");
    System.out.print("Выберите номер того, что хотите конкретно изменить или выйдите: ");
    int numchange= getNumber();
    switch(numchange){
        case 1:
            System.out.print("Введите новое имя покупателя: ");
            buyers.setBuyerFirstName(scan.nextLine());
            buyerFacade.edit(buyers);
            break;
        case 2:
            System.out.print("Введите новую фамилию покупателя: ");
            buyers.setBuyerLastName(scan.nextLine());
            buyerFacade.edit(buyers);
            break;
        case 3:
            System.out.print("Введите новый телефон покупателя: ");
            buyers.setBuyerPhone(scan.nextLine());
            buyerFacade.edit(buyers);
            break;
        case 4:
            System.out.print("Введите новое количество денег у покупателя: ");
            buyers.setBuyerMoney(scan.nextDouble());scan.nextLine();
            buyerFacade.edit(buyers);
            break;
        case 5:
            System.out.println("*ВЫХОД*");
            n++;
            break;    
        default:
            System.out.println("Ошибка, выберите номер из списка!");
            break;
    }
    
}
}
//------------------------------------------------------------------------------
private void IncomePerMonth(){
    double income = 0;
    List<History> histories= historyFacade.findAll();
    System.out.println("*ДОХОД МАГАЗИНА*");
    System.out.println("1 - Январь");
    System.out.println("2 - Февраль");
    System.out.println("3 - Март");
    System.out.println("4 - Апрель");
    System.out.println("5 - Май");
    System.out.println("6 - Июнь");
    System.out.println("7 - Июль");
    System.out.println("8 - Август");
    System.out.println("9 - Сентябрь");
    System.out.println("10 - Октябрь");
    System.out.println("11 - Ноябрь");
    System.out.println("12 - Декабрь");
    System.out.println("13 - Вывести доход за все время");
    System.out.print("Введите нужный месяц: ");
    int choicemonth=getNumber();
    switch(choicemonth){
        case 13:
           income();
           break;
        default:      
    System.out.print("Введите год: ");
    int years=getNumber();
    for (int i = 0; i < histories.size(); i++) {
        Date date = histories.get(i).getGivenSneaker();
        boolean toSum = summator(date, choicemonth-1, years);
        if (histories.get(i)!=null & toSum) {
           income+=histories.get(i).getSneaker().getSneakerPrice();
        }
        
    }
    System.out.printf("Доход за выбранный месяц: %s евро%n", income);
            break;
    }
}
    private boolean summator(Date date, int choicemonth, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if(month==choicemonth && year==years){
            return true;
    }
        return false;
    }    
}



