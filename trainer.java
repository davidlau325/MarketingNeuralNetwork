import java.io.*;
import java.lang.Math;

public class trainer
{
 public static int numEpochs = 5000; //number of training cycles
 public static int numInputs  = 15; //number of inputs (include bias)
 public static int numHidden  = 24; //number of hidden units
 public static int numPatterns = 50000; //number of training patterns
 public static double LR_IH = 0.3; //learning rate
 public static double LR_HO = 0.3; //learning rate
 public static double bias=1; //bias

 public static int patNum;
 public static double errThisPat;
 public static double outPred;
 public static double RMSerror;
 public static double gradient1;
 public static double Accuracy;

 public static double[][] trainInputs  = new double[numPatterns][numInputs];
 public static double[] trainOutput = new double[numPatterns];
 public static double[] hiddenVal  = new double[(numHidden+1)]; // add one for bias
 public static double[][] weightsIH = new double[numInputs][numHidden];
 public static double[] weightsHO = new double[(numHidden+1)]; // add one for bias

 public static void main(String[] args)
 {

    initWeights();
    if(args.length==4){
        numEpochs = Integer.parseInt(args[3]);
    };
    if(args.length==5){
        numEpochs = Integer.parseInt(args[3]);
        if(args[4].equals("1")){
            numEpochs = 7000;
        }
    }
    
    try{
    File dataset = new File(args[0]);
    File answer = new File(args[1]);
    File best = new File(args[2]);
    
    FileInputStream filein1 = new FileInputStream(dataset);
    FileInputStream filein2 = new FileInputStream(answer);
    FileWriter fileOut = new FileWriter(best);
    
    BufferedReader buffer,bufferAnswer;
    String inputString;
    String tempInput;
    buffer = new BufferedReader(new InputStreamReader(filein1));
    bufferAnswer = new BufferedReader(new InputStreamReader(filein2));
    int startPosition=0;
    int spacePosition=0;
    int countPattern=0;
    int countClass=0;
    
    while((inputString = buffer.readLine())!=null){
        while(spacePosition < inputString.length()){
            if(countClass==(numInputs-2)){
                    tempInput = inputString.substring(startPosition);
                    tempInput = tempInput.trim();
                    trainInputs[countPattern][countClass]=Double.parseDouble(tempInput);
                    break;
                }else{
                    spacePosition=inputString.indexOf(" ",spacePosition);
                    tempInput = inputString.substring(startPosition,spacePosition);
                    trainInputs[countPattern][countClass]=Double.parseDouble(tempInput);
                    countClass++;
                    spacePosition++;
                    startPosition = spacePosition;
                }
         }
        trainInputs[countPattern][(numInputs-1)]=bias;
        spacePosition = 0;
        countClass = 0;
        startPosition = 0;
        countPattern++;
    }
    countPattern=0;
    while((inputString = bufferAnswer.readLine())!=null){
        trainOutput[countPattern]=Integer.parseInt(inputString);
        countPattern++;
    }
    numPatterns = countPattern;
    
    filein1.close();
    filein2.close();

    for(int j = 0;j <= numEpochs;j++){
        for(int i = 0;i<numPatterns;i++){
            patNum = (int)((Math.random()*numPatterns)-0.001);
            caloutput();
            WeightChangesHO();
            WeightChangesIH();
        }
     //  calcOverallError();
     //  System.out.println("epoch = " + j + "  RMS Error = " + RMSerror);
    }
    displayResults();
    
    String tempOutput;
    //write into best.nn
    tempOutput = Double.toString(Accuracy);
    fileOut.write(tempOutput);fileOut.write("\r\n");
    tempOutput = Integer.toString((numInputs-1));
    fileOut.write(tempOutput);fileOut.write(" ");
    tempOutput = Integer.toString(numHidden);
    fileOut.write(tempOutput);
    fileOut.write(" 1\r\n");
    
    fileOut.write("I ");
    tempOutput = Integer.toString((numInputs-1));
    fileOut.write(tempOutput);fileOut.write(" H ");
    tempOutput = Integer.toString(numHidden);
    fileOut.write(tempOutput);fileOut.write("\r\n");
    for(int i=0;i<numHidden;i++){
        for(int j=0;j<numInputs;j++){
            tempOutput = Double.toString(weightsIH[j][i]);
            fileOut.write(tempOutput);
            fileOut.write(" ");
        }
        fileOut.write("\r\n");
    }
    fileOut.write("H ");
    tempOutput = Integer.toString(numHidden);
    fileOut.write(tempOutput);
    fileOut.write(" O 1\r\n");
    for(int i=0;i<(numHidden+1);i++){
        tempOutput = Double.toString(weightsHO[i]);
        fileOut.write(tempOutput);
        fileOut.write(" ");
    }
    
    fileOut.close();
    }catch(Exception a){
           System.out.println("Error - " + a.toString());
    }
 }

public static void caloutput(){
    for(int i = 0;i<numHidden;i++)
    {
	hiddenVal[i] = 0.0;

        for(int j = 0;j<numInputs;j++){
        hiddenVal[i] = hiddenVal[i] + (trainInputs[patNum][j] * weightsIH[j][i]);
        }
        hiddenVal[i] = tanh(hiddenVal[i]);
    }
    hiddenVal[numHidden]=bias;

   outPred = 0.0;

   for(int i = 0;i<(numHidden+1);i++){
    outPred = outPred + hiddenVal[i] * weightsHO[i];
   }
  
   outPred = tanh(outPred);
   errThisPat = outPred - trainOutput[patNum];
 }

 public static void WeightChangesHO(){
   gradient1 = outPred*(1-outPred);
   for(int k = 0;k<(numHidden+1);k++){
    double weightChange = LR_HO * errThisPat * hiddenVal[k]*gradient1;
    weightsHO[k] = weightsHO[k] - weightChange;
   }
 }

 public static void WeightChangesIH(){
  double changeI = gradient1*errThisPat;
  double changeJ=0.0;
  for(int i=0;i<numHidden;i++){
      for(int k=0;k<numInputs;k++){
            changeJ = changeJ + (weightsIH[k][i]*changeI);
      }  
  }
  
  for(int i = 0;i<numHidden;i++){
   for(int k = 0;k<numInputs;k++)
   {
    double weightChange = LR_IH*trainInputs[patNum][k]*changeJ*(hiddenVal[i]*(1-hiddenVal[i]));        
    weightsIH[k][i] = weightsIH[k][i] - weightChange;
   }
  }
 }

 public static void initWeights(){

  for(int j = 0;j<numHidden;j++)
  {
    weightsHO[j] = (Math.random() - 0.5);
    for(int i = 0;i<numInputs;i++){
    weightsIH[i][j] = (Math.random() - 0.5);
    }
  }
  weightsHO[numHidden] = (Math.random() - 0.5);
 }

 public static double tanh(double x)
 {   
     double a = Math.exp(-x);
     return (1/(1+a));   
 }


 public static void displayResults(){
     double test,Precision,Recall,Fmeasure;
     double TP=0,FN=0,FP=0,TN=0;
     for(int i = 0;i<numPatterns;i++){
        patNum = i;
        caloutput();
      // System.out.println("pat = " + (patNum+1) + " actual = " + trainOutput[patNum] + " neural model = " + outPred);
        if(outPred >= 0.5){test = 1;}else{test = 0;}
        if(test==1 && trainOutput[patNum]==1){ TP++; }
        else if(test==1 && trainOutput[patNum]==0){ FP++; }
        else if(test==0 && trainOutput[patNum]==1){ FN++; }
        else if(test==0 && trainOutput[patNum]==0){ TN++; }
     }
     Accuracy = (TP+TN)/(TP+TN+FP+FN);
     Precision = TP/(TP+FP);
     Recall = TP/(TP+FN);
     Fmeasure = (2*Recall*Precision)/(Recall+Precision);
     System.out.println("Accuracy: "+Accuracy + " TP="+TP+" FP="+FP+" TN="+TN+" FN="+FN);
     System.out.println("Precision: "+Precision);
     System.out.println("Recall: "+Recall);
     System.out.println("Fmeasure: "+Fmeasure);
 }
 public static void calcOverallError(){
     RMSerror = 0.0;
     for(int i = 0;i<numPatterns;i++)
        {
        patNum = i;
        caloutput();
        RMSerror = RMSerror + (errThisPat * errThisPat);
        }
     RMSerror = RMSerror/numPatterns;
     RMSerror = java.lang.Math.sqrt(RMSerror);
    }
}
