import java.io.*;

public class preprocess {
    public static void main(String[] args) {
        try{
            File data = new File(args[0]);
            File dataset = new File(args[1]);
            File answer = new File(args[2]);
  
            FileInputStream file = new FileInputStream(data);
            FileWriter fileOut1 = new FileWriter(dataset);
            FileWriter fileOut2 = new FileWriter(answer);
            
            String inputString;
            int countClass=0;
            int tempInt;
            double tempDouble;
          
            BufferedReader buffer;
            int startPosition = 0;
            int comaPosition = 0;
            int countComa = 0;
            String tempInput;
            
            buffer = new BufferedReader(new InputStreamReader(file));
            while((inputString = buffer.readLine())!=null){
                if(countClass!=0){
                       while(comaPosition < inputString.length()){
                           if(countComa==16){
                               tempInput = inputString.substring(startPosition);
                                if(tempInput.equals("yes")){
                                    fileOut2.write("1");
                                    
                                }else{
                                    fileOut2.write("0");
                                }
                            fileOut2.write("\r\n");
                           comaPosition = inputString.length();
                           }else{
                          comaPosition = inputString.indexOf(",",comaPosition);
                          countComa++;
                          if(countComa == 1 || countComa == 2 || countComa == 3 || countComa == 4 || countComa==5 || countComa==6||countComa==7 || countComa==8|| countComa==9 || countComa == 12 || countComa == 13 || countComa==14 || countComa==15|| countComa==16){
                              tempInput = inputString.substring(startPosition,comaPosition);
                             if(countComa==1){ 
                                  tempDouble = Double.parseDouble(tempInput);
                                  tempDouble = (tempDouble/100);
                                  tempInput = Double.toString(tempDouble);
                                  fileOut1.write(tempInput);
                                   /*if(tempInt<21){fileOut1.write("0");}
                                   else if(tempInt>20 && tempInt<27){fileOut1.write("0.1");}
                                   else if(tempInt>26 && tempInt<33){fileOut1.write("0.2");}
                                   else if(tempInt>32 && tempInt<39){fileOut1.write("0.3");}
                                   else if(tempInt>38 && tempInt<45){fileOut1.write("0.4");}
                                   else if(tempInt>44 && tempInt<51){fileOut1.write("0.5");}
                                   else if(tempInt>50 && tempInt<57){fileOut1.write("0.6");}
                                   else if(tempInt>56 && tempInt<63){fileOut1.write("0.7");}
                                   else if(tempInt>62 && tempInt<69){fileOut1.write("0.8");}
                                   else if(tempInt>68 && tempInt<75){fileOut1.write("0.9");}
                                   else if(tempInt>74){fileOut1.write("1");}
                                   else{fileOut1.write("0");}
                                   */
                             }else if(countComa==2){
                                  if(tempInput.equals("admin.")){fileOut1.write("0");}
                                  else if(tempInput.equals("unknown")){fileOut1.write("0.0909");}
                                  else if(tempInput.equals("unemployed")){fileOut1.write("0.1818");}
                                  else if(tempInput.equals("management")){fileOut1.write("0.2727");}
                                  else if(tempInput.equals("housemaid")){fileOut1.write("0.3636");}
                                  else if(tempInput.equals("entrepreneur")){fileOut1.write("0.4545");}
                                  else if(tempInput.equals("student")){fileOut1.write("0.5454");}
                                  else if(tempInput.equals("blue-collar")){fileOut1.write("0.6363");}
                                  else if(tempInput.equals("self-employed")){fileOut1.write("0.7272");}
                                  else if(tempInput.equals("retired")){fileOut1.write("0.8181");}
                                  else if(tempInput.equals("technician")){fileOut1.write("0.909");}
                                  else if(tempInput.equals("services")){fileOut1.write("1");}
                                  else{fileOut1.write("0.0909");}
                              }else if(countComa==3){
                                  if(tempInput.equals("married")){fileOut1.write("0");}
                                  else if(tempInput.equals("divorced")){fileOut1.write("0.5");}
                                  else if(tempInput.equals("single")){fileOut1.write("1");}
                                  else{fileOut1.write("0");}
                              }else if(countComa==4){
                                  if(tempInput.equals("unknown")){fileOut1.write("0");}
                                  else if(tempInput.equals("secondary")){fileOut1.write("0.333");}
                                  else if(tempInput.equals("primary")){fileOut1.write("0.666");}
                                  else if(tempInput.equals("tertiary")){fileOut1.write("1");}
                                  else{fileOut1.write("0");}
                              }else if(countComa==6){
                                  tempDouble = Double.parseDouble(tempInput);
                                  tempDouble = (tempDouble/10000);
                                  tempInput = Double.toString(tempDouble);
                                  fileOut1.write(tempInput);
                              /*    
                              tempInt = Integer.parseInt(tempInput);
                                  if(tempInt<1){fileOut1.write("0");}
                                   else if(tempInt>0 && tempInt<1001){fileOut1.write("0.1");}
                                   else if(tempInt>1000 && tempInt<2001){fileOut1.write("0.2");}
                                   else if(tempInt>2000 && tempInt<3001){fileOut1.write("0.3");}
                                   else if(tempInt>3000 && tempInt<4001){fileOut1.write("0.4");}
                                   else if(tempInt>4000 && tempInt<5001){fileOut1.write("0.5");}
                                   else if(tempInt>5000 && tempInt<6001){fileOut1.write("0.6");}
                                   else if(tempInt>6000 && tempInt<7001){fileOut1.write("0.7");}
                                   else if(tempInt>7000 && tempInt<8001){fileOut1.write("0.8");}
                                   else if(tempInt>8000 && tempInt<9001){fileOut1.write("0.9");}
                                   else if(tempInt>9000){fileOut1.write("1");}
                                   else{fileOut1.write("0");}
                                   */
                              }else if(countComa==5||countComa==7||countComa==8){
                                  if(tempInput.equals("yes")){
                                      fileOut1.write("1");
                                  }else{
                                      fileOut1.write("0");
                                  }
                              }else if(countComa==9){
                                  if(tempInput.equals("unknown")){fileOut1.write("0");}
                                  else if(tempInput.equals("telephone")){fileOut1.write("0.5");}
                                  else if(tempInput.equals("cellular")){fileOut1.write("1");}
                                  else{fileOut1.write("0");}
                              }else if(countComa==12){
                                  tempDouble = Double.parseDouble(tempInput);
                                  tempDouble = (tempDouble/2000);
                                  tempInput = Double.toString(tempDouble);
                                  fileOut1.write(tempInput);
                                  /*
                                  tempInt = Integer.parseInt(tempInput);
                                  if(tempInt>-1 && tempInt<201){fileOut1.write("0");}
                                   else if(tempInt>200 && tempInt<401){fileOut1.write("0.1");}
                                   else if(tempInt>400 && tempInt<601){fileOut1.write("0.2");}
                                   else if(tempInt>600 && tempInt<801){fileOut1.write("0.3");}
                                   else if(tempInt>800 && tempInt<1001){fileOut1.write("0.4");}
                                   else if(tempInt>1000 && tempInt<1201){fileOut1.write("0.5");}
                                   else if(tempInt>1200 && tempInt<1401){fileOut1.write("0.6");}
                                   else if(tempInt>1400 && tempInt<1601){fileOut1.write("0.7");}
                                   else if(tempInt>1600 && tempInt<1801){fileOut1.write("0.8");}
                                   else if(tempInt>1800 && tempInt<2001){fileOut1.write("0.9");}
                                   else if(tempInt>2000){fileOut1.write("1");}
                                   else{fileOut1.write("0");}
                                   */
                              }else if(countComa==13){
                                  tempDouble = Double.parseDouble(tempInput);
                                  tempDouble = (tempDouble/18);
                                  tempInput = Double.toString(tempDouble);
                                  fileOut1.write(tempInput);
                                  /*
                                   tempInt = Integer.parseInt(tempInput);
                                   if(tempInt>-1 && tempInt<2){fileOut1.write("0");}
                                   else if(tempInt>1 && tempInt<3){fileOut1.write("0.1");}
                                   else if(tempInt>2 && tempInt<5){fileOut1.write("0.2");}
                                   else if(tempInt>4 && tempInt<7){fileOut1.write("0.3");}
                                   else if(tempInt>6 && tempInt<9){fileOut1.write("0.4");}
                                   else if(tempInt>8 && tempInt<11){fileOut1.write("0.5");}
                                   else if(tempInt>10 && tempInt<13){fileOut1.write("0.6");}
                                   else if(tempInt>12 && tempInt<15){fileOut1.write("0.7");}
                                   else if(tempInt>14 && tempInt<17){fileOut1.write("0.8");}
                                   else if(tempInt>16 && tempInt<19){fileOut1.write("0.9");}
                                   else if(tempInt>18){fileOut1.write("1");}
                                   else{fileOut1.write("0");}
                                   */
                              }else if(countComa==14){
                                  tempDouble = Double.parseDouble(tempInput);
                                  if(tempDouble==-1){
                                  tempDouble=0;
                                  }else{
                                  tempDouble = (tempDouble/200);
                                  }
                                  tempInput = Double.toString(tempDouble);
                                  fileOut1.write(tempInput);
                              }else if(countComa==15){
                                  tempDouble = Double.parseDouble(tempInput);
                                  tempDouble = (tempDouble/10);
                                  tempInput = Double.toString(tempDouble);
                                  fileOut1.write(tempInput);
                              }else if(countComa==16){
                                  if(tempInput.equals("unknown")){fileOut1.write("0");}
                                  else if(tempInput.equals("other")){fileOut1.write("0.333");}
                                  else if(tempInput.equals("failure")){fileOut1.write("0.666");}
                                  else if(tempInput.equals("success")){fileOut1.write("1");}
                                  else{fileOut1.write("0");}
                              }else{
                              fileOut1.write(tempInput);
                              }
                              fileOut1.write(" ");
                          }
                          comaPosition++;
                          startPosition = comaPosition;
                           }
                       }
                       startPosition = 0;
                       comaPosition = 0;
                       countComa = 0;
                fileOut1.write("\r\n");
                }
                countClass++;
            }
            fileOut1.close();
            fileOut2.close();
            }catch(Exception a){
                System.out.println("Error - " + a.toString());
            }
        }   
}
