Marketing Predictive Analytics with Neural Network
==========================================

Implemented a preprocessor to filter/discretize the raw data and following a three-layer feedforward artificial neural network in Java to perform supervised learning in a huge marketing dataset from an insurance company to predict the purchasing possibility of the targeted customers. The training result in a 80% classification accuracy in a separate dataset with the use of cross-validation technique. 

##Usage

###Contents:
best.nn <- the best trained neural network
preprocess.java <- java-coded preprocessor source code
preprocessor.sh <- shell script for compling and running of preprocess.java
trainer.java <- java-coded trainer (Back-Propagation algorithm) source code
trainer.sh <- shell script for compling and running of trainer.java
readme.txt <- this file

###Instruction:
Both of my preprocess and trainer are coded in Java.
1) First, prepare a raw training dataset (eg. raw-training.data)
2) Run `preprocessor.sh` first to preprocess the raw dataset:
      `./preprocessor.sh raw-training.data dataset.dat answer.dat`
3) The output of the preprocessor, namely dataset.dat and answer.dat (In this example) is
the preprocessed data as an input for the trainer program to optimizedly fit with the neural network.
4) After that, run trainer.sh to train the neural network with the input from the preprocessed dataset:
      `./trainer.sh dataset.dat answer.dat best.nn`
5) The output of the trainer.sh - best.nn is the result of the trained neural network.
The trainer will not display anything on the screen but only output the best.nn

###There are two more optional input parameter for the trainer:
1) `./trainer.sh dataset.dat answer.dat best.nn <max_iteration>`
    as to indicate the number of the iteration (training loop) for the training
2) `./trainer.sh dataset.dat answer.dat best.n <max_iteration> <small_sample_mode>`
    when <samll_sample_mode> equals to 0, the training act normally as usual.
    when <small_sample_mode> equals to 1, te training will be optimal for training and handling tiny dataset
    as accurate as possible.  
