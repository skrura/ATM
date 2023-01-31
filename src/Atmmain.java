import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Atmmain {
    //入口类
    public static void main(String[] args) {
        //add yy
        Scanner input = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        while(true) {
            welcome();
            int choose_1 = input.nextInt();
            switch (choose_1) {
                case 1:
                    login(accounts,input);
                    break;
                case 2:
                    enroll(accounts,input);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("操作不存在");
            }
        }

    }
    private static void welcome(){
        System.out.println("========================ATM=============================");
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("3.退出");
        System.out.println("请您选择操作：");
    }
    private static void login(ArrayList<Account> accounts,Scanner input){
        System.out.println("========================ATM_login=============================");
        if(accounts.isEmpty()){
            System.out.println("系统内不存在账户，请您先进行注册");
            return;
        }
        while (true){
            System.out.println("输入您要登录的账户卡号");
            System.out.println("0.退出");
            String login_cardnum = input.next();
            Account login_accout = findaccoutbycardnum(login_cardnum,accounts);
            if (login_cardnum.equals("0")) {
                System.out.println("已为您退出");
                return;
            } 
            if(login_accout == null){
                System.out.println("您所输入的账户不存在");
            }
            else {
                int intput_password_frequency = 3;
                while (intput_password_frequency > 0){
                    System.out.println("请您输入密码");
                    String login_password = input.next();
                    String ture_passwprd = login_accout.getPassword();
                    if(ture_passwprd.equals(login_password)){
                         login_operate(accounts,login_accout,input);
                         return;
                    }
                    else {
                        intput_password_frequency--;
                        System.out.println("密码错误\t剩余输入次数" + intput_password_frequency);
                    }

                }
                return;
            }

        }

    }

    private static void login_operate(ArrayList<Account> accounts,Account login_accout,Scanner input){
        while (true) {
            System.out.println("========================ATM_operate=============================");
            System.out.println("欢迎"+ login_accout.getName()+"先生你已经成功进入系统" + "您的卡号为:" + login_accout.getCardnum());
            System.out.println("选择您的操作：\n1.账户信息查询\n2.存款\n3.取款\n4.转账\n5.修改密码\n6.退出\n7.注销账户");
            int choose_2 = input.nextInt();
            switch (choose_2){
                case 1:
                    account_Inquire(login_accout);
                    break;
                case 2:
                    saving_money(login_accout,input);
                    System.out.println("账户余额：" + login_accout.getMoney());
                    break;
                case 3:
                    taking_money(login_accout,input);
                    System.out.println("账户余额：" + login_accout.getMoney());
                    break;
                case 4:
                    transfer(accounts,login_accout,input);
                    System.out.println("账户余额：" + login_accout.getMoney());
                    break;
                case 5:
                    modify_password(login_accout,input);
                    break;
                case 6:
                    return;
                case 7:
                    logout_accout(accounts,login_accout,input);
                    return;
                default:
                    System.out.println("没有此项命令");
            }
        }
    }

    private static void logout_accout(ArrayList<Account> accounts,Account login_accout,Scanner input){
        System.out.println("======================logout_accout=================================");
        if(login_accout.getMoney()>0){
            System.out.println("账户余额不满足销户条件");
            return;
        }
        int count=3;
        while (count>0){
            System.out.println("输入当前密码进行验证");
            String true_password = login_accout.getPassword();
            String verify_password = input.next();
            if(true_password.equals(verify_password)){
                accounts.remove(login_accout);
                System.out.println("账户销毁成功,已为您自动退出");
                return;
            }
            else {
                count--;
                System.out.println("密码验证错误\t剩余次数"+ count);
            }

        }
        return;
    }
    private static void modify_password(Account login_accout,Scanner input){
        int count=3;
        while (count>0){
            System.out.println("输入当前密码进行验证");
            String true_password = login_accout.getPassword();
            String verify_password = input.next();
            if(true_password.equals(verify_password)){
                System.out.println("输入新的密码");
                String After_modify_passsword = input.next();
                login_accout.setPassword(After_modify_passsword);
                System.out.println("密码修改成功");
                return;
            }
            else {
                count--;
                System.out.println("密码验证错误\t剩余次数"+ count);
            }
        }
    return;
    }
    private static void taking_money(Account login_accout,Scanner input)
    {
        while (true) {
            System.out.println("输入取钱金额");
            System.out.println("0.退出");
            int taking = input.nextInt();
            if(taking > login_accout.getMoney()){
                System.out.println("余额不足");
            } else if (taking> login_accout.getWithdrawal_Credit()) {
                System.out.println("提现额度不足");
            } else if (taking == 0) {
                System.out.println("已为您退出");
                return;
            } else {
                login_accout.take_money(taking);
                System.out.println("账户余额：" + login_accout.getMoney());
                break;
            }
        }
    }
    private static void saving_money(Account login_accout,Scanner input){
        System.out.println("输入存钱金额");
        System.out.println("0.退出");
        int Saving = input.nextInt();
        if (Saving == 0) {
            System.out.println("已为您退出");
            return;
        }
        login_accout.exist_money(Saving);

    }
    private static void transfer(ArrayList<Account> accounts,Account login_accout,Scanner input) {
        System.out.println("============================transfer==========================================");
        if(accounts.size()<2){
            System.out.println("系统内账户数量不允许转账操作");
            return;
        }
        if (login_accout.getMoney()==0){
            System.out.println("账户内余额不允许转账操作");
            return;
        }

        while (true) {
            System.out.println("输入您要转账的对方账户");
            System.out.println("0.退出");
            String target_cardnum = input.next();
            Account target_accout = findaccoutbycardnum(target_cardnum, accounts);
            if(target_cardnum.equals("0")) {
                System.out.println("已为您退出");
                return;
            }

            if (target_accout==login_accout){
                System.out.println("不能转账自身");
                System.out.println("1.退出\n2.继续");
                int i = input.nextInt();
                if(i==1){
                    return;
                }
                else {
                    continue;
                }
            }
            if (target_accout == null) {
                System.out.println("您要转账的账户不存在");
                System.out.println("1.退出\n2.继续");
                int i = input.nextInt();
                if(i==1){
                    return;
                }
                else {
                    continue;
                }
            }
            else {
                String target_name = target_accout.getName();
                String display_name = "*"+target_name.substring(1);

                System.out.println("输入" + display_name + "的姓氏");
                String input_name = input.next();
                if (target_name.startsWith(input_name)) {
                    while (true) {
                        System.out.println("输入您要转账的金额");
                        System.out.println("0.退出");
                        int transfer_money = input.nextInt();
                        if (transfer_money == 0){
                            System.out.println("已为您退出");
                            return;
                        }
                        if (transfer_money > login_accout.getMoney()) {
                            System.out.println("余额不足，余额还剩余"+ login_accout.getMoney());
                        } else if (transfer_money > login_accout.getTransfer_amount()) {
                            System.out.println("转账额度不足,最高额度为"+ login_accout.getTransfer_amount());
                        } else {
                            login_accout.take_money(transfer_money);
                            target_accout.exist_money(transfer_money);
                           return;
                        }
                    }
                }
                else {
                    System.out.println("输入信息有误");
                }

            }

        }

    }



    private static void account_Inquire(Account login_accout){
        System.out.println("账户卡号" + login_accout.getCardnum());
        System.out.println("账户名称"+ login_accout.getName() + "\n账户余额：" + login_accout.getMoney());
        System.out.println( "账户取现额度" + login_accout.getWithdrawal_Credit());
        System.out.println("账户转账额度" + login_accout.getTransfer_amount());
    }
    private static void enroll(ArrayList<Account> accounts,Scanner input){
        System.out.println("========================ATM_enroll=============================");
        System.out.println("请输入账户名称：");
        String name = input.next();
        String password = null;
        while (true){
            System.out.println("请输入账户密码：");
            password = input.next();
            System.out.println("请确认账户密码：");
            String password_2 = input.next();
            if(password.equals(password_2)){
                System.out.println("密码确认成功");
                break;
            }
            else {
                System.out.println("确认密码错误");
            }
        }
            System.out.println("输入提现额度");
            double Withdrawal_Credit = input.nextDouble();
            System.out.println("输入转账额度");
            double transfer_amount = input.nextDouble();

            String cardnum = getranddomcardnum(accounts);

            accounts.add(new Account(cardnum,name,password,0,Withdrawal_Credit,transfer_amount));
            System.out.println(name + "先生"+"注册成功" + "\t您的卡号为 : " + cardnum);
    }
    private static String getranddomcardnum(ArrayList<Account> accounts){
        Random r = new Random();
        while(true){
            String cardnum="";
            for (int i=0;i<8;i++){
                cardnum += r.nextInt(10);
            }
            if(findaccoutbycardnum(cardnum,accounts)==null){
                return cardnum;
            }
        }
    }
    private static Account findaccoutbycardnum(String cardnum,ArrayList<Account> accounts){
        for (int i=0;i<accounts.size();i++){
            Account x = accounts.get(i);
            if(cardnum.equals(x.getCardnum())){
                return x;
            }
        }
        return null;
    }
}
